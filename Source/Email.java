/*LICENSING

Copyright (c) 2011, Matthew Wolfe, Andy Gospodnetich

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

// File Name Email.java

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
//import javax.activation.*;

import com.sun.mail.smtp.*;

public class Email
{
//public Email(String recipients[], String from, String subject, String body)
//public Email(String recipients, String pword, String from, String subject, String body)
public Email(String recipients, String pword, String subject, String body)
{
      
      // Assuming you are sending email from localhost
      //String host = "localhost";
      //String host = "smtp.mail.yahoo.com";
      String host = "smtp.gmail.com";
      String prot = "smtp";
      

      // Get system properties
      Properties properties = System.getProperties();

      // Setup mail server
      //properties.setProperty("mail.smtp.host", host);
      properties.put("mail.smtp.port", "25");
      properties.put("mail.smtp.host", host);
      properties.put("mail.smtp.auth", "true");
      properties.put("mail.smtp.starttls.enable","true");
      
      /*Authenticator auth = new PopupAuthenticator();

// Get the default Session object.
Session session = Session.getDefaultInstance(properties, auth);
*/
      Session session = Session.getInstance(properties, null);

      try{
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         //message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         /*InternetAddress[] addressTo = new InternetAddress[recipients.length];
for(int i = 0; i < recipients.length; i++)
{
addressTo[i] = new InternetAddress(recipients[i]);
}
message.setRecipients(Message.RecipientType.TO, addressTo);
*/
         
         message.addRecipient(Message.RecipientType.TO,
                                  new InternetAddress(recipients));
         
         //could add CC and BCC here
         
         
         message.addHeader("MyHeaderName", "Stocker");
         //message.setHeader("MyHeaderName", "Stocker");
         
         //message.setSentDate(newDate());

         // Set Subject: header field
         //message.setSubject("This is the Subject Line!");
         message.setSubject(subject);

         // Now set the actual message
         //message.setText("This is actual message");
         //message.setContent(message, "text/plain");
         message.setText(body);

         // Send message
         //Transport.send(message);
         
         SMTPTransport t = (SMTPTransport)session.getTransport(prot);
         //System.out.println(host + " recipients " + recipients + " pword " + pword);
         t.connect(host, recipients, pword);
         t.sendMessage(message, message.getAllRecipients());
         
         System.out.println("Sent message successfully....");
      }catch (MessagingException mex) {
         //mex.printStackTrace();
         System.out.println("Email Failed");
         System.out.println("Possibly Invalid Username or Password");
         System.out.println("You must use a gmail account");
      }
   }
}