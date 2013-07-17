package gmailclient;

import java.util.Properties;
import java.util.Scanner;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Kanishka Ganguly
 * Nightstalker
 * CSE, 2K11
 * 
 * Demonstrating use of the JAVAMAIL API to send an email via the GMAIL SMTP Server
 */
public class GmailClient {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        //USER INPUT USERNAME
        System.out.println("Enter Username");
        System.out.println("eg. abc@gmail.com");
        final String username = in.nextLine();

        //USER INPUT PASSWORD
        System.out.println("Enter Password");
        System.out.println("This application does NOT store your password for any reason whatsoever.");
        final String password = in.nextLine();

        //SETTING MAIL API PROPERTIES
        /*ENSURE YOUR GMAIL ACCOUNT ALLOWS YOU TO SEND SMTP EMAIL VIA THEIR SERVER*/
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true"); //SMTP - Simple Mail Transfer Protocol
        props.put("mail.smtp.starttls.enable", "true"); //TLS - Transport Layer Security
        props.put("mail.smtp.host", "smtp.gmail.com"); // Setting SMTP Host
        props.put("mail.smtp.port", "587"); //Setting SMTP Port

        //CREATING NEW EMAIL SESSION USING PROPERTIES
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            //USER INPUT FOR To Address, From Address, Subject AND Message Body
            System.out.println("Enter FROM Address");
            String from_address = in.nextLine();
            System.out.println("Enter RECIPIENT Address");
            String to_address = in.nextLine();
            System.out.println("Enter SUBJECT");
            String subject = in.nextLine();
            System.out.println("Enter MESSAGE TEXT");
            String body = in.nextLine();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from_address));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to_address));
            message.setSubject(subject);
            message.setText(body);

            //USING TRANSPORT FUNCTION TO SEND MESSAGE
            Transport.send(message);

            //SENT MAIL CONFIRMATION
            System.out.println("MAIL SENT");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
