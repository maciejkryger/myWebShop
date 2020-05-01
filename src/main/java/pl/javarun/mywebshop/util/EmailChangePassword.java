package pl.javarun.mywebshop.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

import static pl.javarun.mywebshop.util.SecretData.*;

public class EmailChangePassword {



    public EmailChangePassword() {
    }

    public void send(String username, String name, String email, String token){

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", mailSmtpHost);
        prop.put("mail.smtp.port", mailSmtpPort);
        prop.put("mail.smtp.ssl.trust", mailSmtpHostSslTrust);

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailUser, mailPassword);
            }
        });

        Message message = new MimeMessage(session);
        try {
            message.setHeader("Content-Type", "text/plain; charset=UTF-8");
            message.setFrom(new InternetAddress("zmiana_hasla@qunsztowna.pl"));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("QUNSZTOWNA.pl - zmiana hasła dla: "+username);


            String msg = "Dzień dobry <B>"+name+"</B>,<BR><BR> skorzystałeś/aś z opcji przypominania hasła w sklepie QUNSZTOWNA.<BR>" +
                    "By zmienić hasło: <a href=\"http://qunsztowna.javarun.pl/changePasswordByToken?regId="+token+"\">kliknij tutaj</a>"+
                    "<BR>Jeżeli to nie byłeś/aś Ty zignoruj tego maila, a Twoje hasło zostanie niezmienione.";

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
