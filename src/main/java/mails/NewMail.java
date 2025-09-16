package mails;

import java.io.IOException;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class NewMail {
    public static void mail() throws IOException {
        final String username = "ranjithganesan.testingzlaata@gmail.com";
        final String password = "zozvabpdotwqcjes";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ranjithganesan.testingzlaata@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("ranjith.g@thewholewave.com"));
            
            message.setSubject("Testing ZlaataMail");
            Multipart emailcontent = new MimeMultipart();
            MimeBodyPart attachment1 = new MimeBodyPart();
            String file1 = "C:\\Users\\Ranjith\\Downloads\\ZlaataUpdated\\ZlaataUpdated\\test-output\\ZlaataReport\\Zlaata.html";
            attachment1.attachFile(file1);
            emailcontent.addBodyPart(attachment1);
            
//            MimeBodyPart attachment2 = new MimeBodyPart();
//            String file2 = "C:\\Users\\Ranjith\\Downloads\\ZlaataUpdated\\ZlaataUpdated\\test-output\\ZlaataPDFReport\\ZlaataPDF.pdf";
//            attachment2.attachFile(file2);
//            emailcontent.addBodyPart(attachment2);
               
            MimeBodyPart text = new MimeBodyPart();
            text.setText("Hi this is Test Report");
            emailcontent.addBodyPart(text);
            
            message.setContent(emailcontent);
            Transport.send(message);

            System.out.println("Email sent successfully.");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Failed to send email: " + e.getMessage());
        }
    }
}
