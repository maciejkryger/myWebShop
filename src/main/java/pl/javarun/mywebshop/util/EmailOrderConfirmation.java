package pl.javarun.mywebshop.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import pl.javarun.mywebshop.exception.AddressNotExistException;
import pl.javarun.mywebshop.model.Address;
import pl.javarun.mywebshop.model.User;
import pl.javarun.mywebshop.model.WebOrder;
import pl.javarun.mywebshop.model.WebOrderItem;
import pl.javarun.mywebshop.service.AddressService;
import pl.javarun.mywebshop.service.CompanyService;
import pl.javarun.mywebshop.service.ConfigDataService;
import pl.javarun.mywebshop.service.WebOrderItemService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.List;
import java.util.Properties;

@Controller
public class EmailOrderConfirmation {

    private ConfigDataService configDataService;
    private AddressService addressService;
    private CompanyService companyService;
    private WebOrderItemService webOrderItemService;
    @Value("${mail_href}")
    private String href;

    public EmailOrderConfirmation(ConfigDataService configDataService, AddressService addressService,
                                  CompanyService companyService,  WebOrderItemService webOrderItemService) {
        this.configDataService = configDataService;
        this.addressService = addressService;
        this.companyService = companyService;
        this.webOrderItemService = webOrderItemService;
    }

    public void send(WebOrder webOrder) {


        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", configDataService.getConfigDataByName("mailSmtpHost").getValue());
        prop.put("mail.smtp.port", configDataService.getConfigDataByName("mailSmtpPort").getValue());
        prop.put("mail.smtp.ssl.trust", configDataService.getConfigDataByName("mailSmtpHostSslTrust").getValue());

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        configDataService.getConfigDataByName("mailUser").getValue(),
                        configDataService.getConfigDataByName("mailPassword").getValue()
                );
            }
        });

        Message message = new MimeMessage(session);

        int orderId = webOrder.getId();
        double sumToPay = webOrderItemService.calculateActualSumToPayInUserBasket(orderId);
        int sumQuantity = webOrderItemService.calculateActualQuantityInUserBasket(orderId);
        int deliveryCostsToPay = webOrder.getDeliveryOption().getPrice();
        List<WebOrderItem> webOrderItems = webOrderItemService.getOrderItemByOrderId(orderId);

        try {
            message.setHeader("Content-Type", "text/plain; charset=UTF-8");
            message.setFrom(new InternetAddress("potwierdzenie@qunsztowna.pl"));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(webOrder.getUser().getEmail()));
            String subject = "Qunsztowna.pl - informacja o zlozonym zamowieniu nr " + webOrder.getOrderNumber();
            message.setSubject(subject);
            Address address;

//------------------------------------------------------------------------------------------------------------
//|                                 first message to client                                                  |
// -----------------------------------------------------------------------------------------------------------
            StringBuilder builder = new StringBuilder();
            builder.append("Cześć <B>").append(webOrder.getUser().getFirstName()).append("!</B><BR><BR>").
                    append("Twoje zamówienie zostało przyjęte i zarejestrowane pod numerem: <B>").append(webOrder.getOrderNumber()).
                    append("</B><BR>").append("<BR><BR><B>PODSUMOWANIE</B><BR>").
                    append("------------------------------------------------------------<BR>").
                    append("- ilość zamówionych produktów: ").append(sumQuantity).append(" szt.<BR>").
                    append("- wartość zamówienia: ").append(sumToPay).append(" PLN<BR>").
                    append("- koszt tranportu: ").append(deliveryCostsToPay).append(" PLN<BR>").
                    append("------------------------------------------------------------<BR>").
                    append("łącznie do  zapłaty: ").append(sumToPay + deliveryCostsToPay).append(" PLN<BR><BR><BR>").
                    append("<strong>Twoje dane:</strong><BR>").
                    append("------------------------------------------------------------<BR>").
                    append(webOrder.getUser().getFirstName()).append(" ").append(webOrder.getUser().getLastName()).append("<BR>").append("nr telefonu: ").
                    append(webOrder.getUser().getPhone()).append("<BR>");
            try {
                address = addressService.getByUser(webOrder.getUser());
                builder.append(address.getStreet()).append(" ").append(address.getHouseNo());
                if (address.getFlatNo() != null && !address.getFlatNo().isEmpty()) {
                    builder.append("/").append(address.getFlatNo());
                }
                builder.append("<BR>").append(address.getPostCode()).append(" ").append(address.getCity()).append("<BR>");
            } catch (AddressNotExistException ignored) {
            }
            builder.append("-------------------------------------------------------------<BR>").
                    append("wybrana forma płatności: <B>").append(webOrder.getPaymentMethod().getNamePl()).append("</B><BR>").
                    append("wybrana forma dostawy: <B>").append(webOrder.getDeliveryOption().getNamePl()).append("</B><BR>").
                    append("uwagi do zamówienia: <B>");
            if (webOrder.getComment() == null) {
                builder.append("BRAK").append("</B><BR>");
            } else {
                builder.append(webOrder.getComment()).append("</B><BR>");
            }
            builder.append("-------------------------------------------------------------<BR><BR>").
                    append("Jeżeli wybrałeś metodę płatności w formie przedpłaty, pieniądze należy przelać:<BR>").
                    append("- w przypadku przelewu tradycyjnego na konto: <B>").append(companyService.getCompanyData().getAccountNumber()).append("</B><BR>").
                    append("- w przypadku przelewu BLIK na nr telefonu: <B>").append(companyService.getCompanyData().getPhone()).append("</B><BR><BR><BR>").
                    append("Dziękuję za dokonane zakupy i zapraszam ponownie<BR><B>").append(companyService.getCompanyData().getName()).append("</B>");

            String msg = builder.toString();

//------------------------------------------------------------------------------------------------------------
//|                                 second message to owner                                                  |
// -----------------------------------------------------------------------------------------------------------


            StringBuilder builder2 = new StringBuilder();
            builder2.append("Dobra wiadomość!<BR><BR>").
                    append("Masz nowe zamówienie: <B>").append(webOrder.getOrderNumber()).append("</B><BR>").
                    append("Dane klienta: <BR>Imię i nazwisko:<B>").
                    append(webOrder.getUser().getFirstName()).append(" ").append(webOrder.getUser().getLastName()).append("</B><BR>").
                    append("email: <B>").append(webOrder.getUser().getEmail()).append("</B><BR>").
                    append("telefon: <B>").append(webOrder.getUser().getPhone()).append("</B><BR>");
            try {
                address = addressService.getByUser(webOrder.getUser());
                builder2.append("adres:<BR>").append(address.getStreet()).append(" ").append(address.getHouseNo());
                if (address.getFlatNo() != null && !address.getFlatNo().isEmpty()) {
                    builder.append("/").append(address.getFlatNo());
                }
                builder2.append("<BR>").append(address.getPostCode()).append(" ").append(address.getCity()).append("<BR>");
            } catch (AddressNotExistException ignored) {
            }
            builder2.append("<BR><BR><B>PODSUMOWANIE</B><BR>").
                    append("------------------------------------------------------------<BR>").
                    append("- Ilość zamówionych produktów: ").append(sumQuantity).append(" szt.<BR>").
                    append("- Wartość zamówienia: ").append(sumToPay).append(" PLN<BR>").
                    append("- Koszt tranportu: ").append(deliveryCostsToPay).append(" PLN<BR>").
                    append("------------------------------------------------------------<BR>").
                    append("łącznie do  zapłaty: ").append(sumToPay + deliveryCostsToPay).append(" PLN<BR><BR><BR>").
                    append("uwagi do zamówienia: <B>");
            if (webOrder.getComment() == null) {
                builder.append("BRAK");
            } else {
                builder.append(webOrder.getComment()).append("</B><BR>");
            }
            builder.append("-------------------------------------------------------------<BR>").
                    append("wybrana forma płatności: <B>").append(webOrder.getPaymentMethod().getNamePl()).append("</B><BR>").
                    append("wybrana forma dostawy: <B>").append(webOrder.getDeliveryOption().getNamePl()).append("</B><BR>").
                    append("-------------------------------------------------------------<BR><BR>").
                    append("Nie zapomnij sprocesować tego zamówienia !<BR>");


            String msg2 = builder2.toString();


            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html; charset=UTF-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);

            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(companyService.getCompanyData().getEmail()));

            MimeBodyPart mimeBodyPart2 = new MimeBodyPart();
            mimeBodyPart2.setContent(msg2, "text/html; charset=UTF-8");

            Multipart multipart2 = new MimeMultipart();
            multipart2.addBodyPart(mimeBodyPart2);

            message.setContent(multipart2);

            Transport.send(message);


        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
