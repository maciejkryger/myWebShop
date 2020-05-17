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
    @Value("${mail_href}")
    private String href;

    public EmailOrderConfirmation(ConfigDataService configDataService, AddressService addressService,
                                  CompanyService companyService) {
        this.configDataService = configDataService;
        this.addressService = addressService;
        this.companyService = companyService;
    }

    public void send(User user, WebOrder webOrder, List<WebOrderItem> productsInBasket, int sumToPay,
                     int sumQuantity, int deliveryCostsToPay) {


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

        try {
            message.setHeader("Content-Type", "text/plain; charset=UTF-8");
            message.setFrom(new InternetAddress("potwierdzenie@qunsztowna.pl"));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
            String subject = "Qunsztowna.pl - informacja o złożonym zamówieniu nr " + webOrder.getOrderNumber();
            message.setSubject(subject);
            Address address;
            StringBuilder builder = new StringBuilder();
            builder.append("Cześć <B>").append(user.getFirstName()).append("!</B><BR><BR>").
                    append("Twoje zamówienie zostało przyjęte i zarejestrowane pod numerem: <B>").append(webOrder.getOrderNumber()).
                    append("</B><BR>").append("<BR><BR><B>PODSUMOWANIE</B><BR>").
                    append("------------------------------------------------------------<BR>").
                    append("- Ilość zamówionych produktów: ").append(sumQuantity).append(" szt.<BR>").
                    append("- Wartość zamówienia: ").append(sumToPay).append(" PLN<BR>").
                    append("- Koszty tranportu: ").append(deliveryCostsToPay).append(" PLN<BR>").
                    append("------------------------------------------------------------<BR>").
                    append("łącznie do  zapłatow: ").append(sumToPay + deliveryCostsToPay).append(" PLN<BR><BR><BR>").
                    append("<strong>Twoje dane:</strong><BR>").
                    append("------------------------------------------------------------<BR>").
                    append(user.getFirstName()).append(" ").append(user.getLastName()).append("<BR>");
            try {
                address = addressService.getByUser(user);
                builder.append(address.getStreet()).append(" ").append(address.getHouseNo()).append("/").append(address.getFlatNo()).
                        append("<BR>").append(address.getPostCode()).append(" ").append(address.getCity()).append("<BR>");
            } catch (AddressNotExistException ignored) {
            }
            builder.append("-------------------------------------------------------------<BR>").
                    append("wybrana forma płatności: <B>").append(webOrder.getPaymentMethod().getNamePl()).append("</B><BR>").
                    append("wybrana forma dostawy: <B>").append(webOrder.getDeliveryOption().getNamePl()).append("</B><BR>").
                    append("-------------------------------------------------------------<BR><BR>").
                    append("Jeżeli wybrałeś metodę płatności w formie przedpłaty, pieniądze należy przelać:<BR>").
                    append("- w przypadku przelewu tradycyjnego na konto: <B>").append(companyService.getCompanyData().getAccountNumber()).append("</B><BR>").
                    append("- w przypadku przelewu BLIK na nr telefonu: <B>").append(companyService.getCompanyData().getPhone()).append("</B><BR><BR><BR>").
                    append("Dziękuję Ci za dokonane zakupy i zapraszam ponownie<BR><B>Sylwia</B>");

            String msg = builder.toString();

//            String msg ="<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">" +
//                    "<html xmlns='http://www.w3.org/1999/xhtml'>"+
//                    "<head>\n" +
//                    "    <meta charset=\"UTF-8\">\n" +
//                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
//                    "    <link rel='stylesheet' href='https://www.w3schools.com/w3css/4/w3.css'>\n " +
//                    "    <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Roboto\">\n" +
//                    "    <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Montserrat\">\n" +
//                    "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\n" +
//                    "    <script src='https://kit.fontawesome.com/a076d05399.js'></script>" +
//                    "   <style type=\"text/css\">\n" +
//                    "        body {margin: 0; padding: 0; min-width: 100%!important;}\n" +
//                    "        .content {width: 100%; max-width: 600px;}  \n" +
//                    "   </style>"+
//                    "</head>\n"+
//                    "<body>"+
//                    "<div class=\"w3-responsive w3-xLarge w3-margin\">\n" +
//                    "Twoje zamówienie zostało przyjęte i zarejestrowane pod numerem: "+webOrder.getOrderNumber()+"\n" +
//                    " </div>" +
//                    "<div class=\".content\">\n" +
//                    "                 <table width=\"100%\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"w3-table-all w3-hoverable\">\n" +
//                    "                     <thead>\n" +
//                    "                     <tr class=\"w3-light-grey \">\n" +
//                    "                         <th style=\"width:25%\">Zdjęcie</th>\n" +
//                    "                         <th>nazwa produktu</th>\n" +
//                    "                         <th>cena produktu</th>\n" +
//                    "                         <th>ilość</th>\n" +
//                    "                         <th>suma</th>\n" +
//                    "                     </tr>\n" +
//                    "                     </thead>\n" +
//                    "                     <tbody>\n" +
//                    "                     <c:forEach var=\"item\" items=\""+productsInBasket+"\">\n" +
//                    "                         <tr>\n" +
//                    "                             <td style=\"width:25%\">\n" +
//                    "                             <img src=\'"+href+"/images/${item.product.type.id}/${item.product.id}.jpg\' style=\"width:100%\">\n" +
//                    "                             </td>\n" +
//                    "                             <td>${item.product.namePl}</td>\n" +
//                    "                             <td>${item.product.price} PLN</td>\n" +
//                    "                             <td>${item.product.price*item.quantity} PLN</td>\n" +
//                    "                         </tr>\n" +
//                    "                     </c:forEach>\n" +
//                    "                     </tbody>\n" +
//                    "                     <tfoot>\n" +
//                    "                       <tr class=\"w3-light-grey \">\n" +
//                    "                         <th</th>\n" +
//                    "                         <th></th>\n" +
//                    "                         <th></th>\n" +
//                    "                         <th>PODSUMOWANIE:</th>\n" +
//                    "                         <th>"+sumQuantity+"szt</th>\n" +
//                    "                         <th>"+sumToPay+" PLN</th>\n" +
//                    "\n" +
//                    "                       </tr>\n" +
//                    "                       <tr class=\"w3-light-grey \">\n" +
//                    "                          <th</th>\n" +
//                    "                          <th></th>\n" +
//                    "                          <th></th>\n" +
//                    "                          <th>Koszty przesyłki:</th>\n" +
//                    "                          <th></th>\n" +
//                    "                          <th>"+deliveryCostsToPay+" PLN</th>\n" +
//                    "\n" +
//                    "                          </tr>\n" +
//                    "                       <tr class=\"w3-light-grey \">\n" +
//                    "                          <th</th>\n" +
//                    "                          <th></th>\n" +
//                    "                          <th></th>\n" +
//                    "                          <th>Razem do zapłaty:</th>\n" +
//                    "                          <th></th>\n" +
//                    "                          <th>"+(Integer.valueOf(sumToPay)+Integer.valueOf(deliveryCostsToPay))+" PLN</th>\n" +
//                    "\n" +
//                    "                          </tr>\n" +
//                    "                     </tfoot>\n" +
//                    "                 </table>\n" +
//                    "             </div>\n" +
//                    "\n" +
//                    "  <div class=\"w3-responsive w3-margin\">\n" +
//                    "  <p class=\"w3-large\"><strong>Dane do wysyłki:</strong></p>\n" +
//                    "  <p>"+user.getFirstName()+" "+user.getLastName()+"</p>\n" +
//                    "  <p>"+addressService.getByUser(user).getStreet()+" "+ addressService.getByUser(user).getHouseNo()+"<c:if test=\'"+addressService.getByUser(user).getFlatNo()+"!='' && "+addressService.getByUser(user).getFlatNo()+"!=null}\'>/"+addressService.getByUser(user).getFlatNo()+"</c:if></p>\n" +
//                    "  <p>"+addressService.getByUser(user).getPostCode()+" "+addressService.getByUser(user).getCity()+"</p>\n" +
//                    "  </div>\n" +
//                    "\n" +
//                    "   <div class=\"w3-responsive w3-margin\">\n" +
//                    "    <p class=\"w3-large\"><strong>Metoda płatności:</strong></p>\n" +
//                    "    <p>"+webOrder.getPaymentMethod().getNamePl()+"</p>\n" +
//                    "    </div>"+
//                    "</body>";

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html; charset=UTF-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
