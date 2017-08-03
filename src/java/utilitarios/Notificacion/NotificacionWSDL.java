/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios.Notificacion;

import java.util.Properties;
import javax.jws.WebService;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Jesus
 */
@WebService(serviceName = "NotificacionWSDL", portName = "NotificacionWSDLPort", endpointInterface = "org.netbeans.j2ee.wsdl.soa_sep.notificacion.notificacionwsdl.NotificacionWSDLPortType", targetNamespace = "http://j2ee.netbeans.org/wsdl/SOA-SEP/Notificacion/NotificacionWSDL", wsdlLocation = "WEB-INF/wsdl/NotificacionWSDL/NotificacionWSDL.wsdl")
public class NotificacionWSDL {

    public java.lang.String notificacionWSDL(org.netbeans.xml.schema.notificacionschema.Correo mensaje) throws MessagingException {
           //TODO implement this method
        
      String smtpServer = "smtp.gmail.com";
      int port = 587;
      final String userid = "webservicesoasep@gmail.com";//change accordingly
      final String password = "webservicesoa";//change accordingly
      String contentType = "text/html";
      String subject = mensaje.getMensaje();
      String from = "webservicesoasep@gmail.com";
      String to = mensaje.getReceptor();//some invalid address
      String bounceAddr = "webservicesoasep@gmail.com";//change accordingly
      String body = mensaje.getMensaje() ;

      Properties props = new Properties();

      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", smtpServer);
      props.put("mail.smtp.port", "587");
      props.put("mail.transport.protocol", "smtp");
      props.put("mail.smtp.from", bounceAddr);

      Session mailSession = Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(userid, password);
            }
         });

      MimeMessage message = new MimeMessage(mailSession);
      message.addFrom(InternetAddress.parse(from));
      message.setRecipients(Message.RecipientType.TO, to);
      message.setSubject(subject);
      message.setContent(body, contentType);

      Transport transport = mailSession.getTransport();
      try {
         System.out.println("Sending ....");
         transport.connect(smtpServer, port, userid, password);
         transport.sendMessage(message,
            message.getRecipients(Message.RecipientType.TO));
         System.out.println("Sending done ...");
         return "Mensaje Enviado";
      } catch (Exception e) {
         System.out.println("Error Sending: ");
         e.printStackTrace();
         
      }
      transport.close();
        return null;
   }// end function main()
    }
    

