package Servicios;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.Multipart;
import javax.mail.internet.MimeMultipart;

public class CorreoServicio {

	   private final String remitente = "cinesoft36@gmail.com";
	   private final String contrasena = "inxa ttnw qdjb fies";
	   
	   public void enviarCorreoRegistro(String destinatario, String asunto, String cuerpoMensaje) throws MessagingException {

	        // Configurar las propiedades del servidor SMTP
	        Properties props = new Properties();
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");

	        // Crear una sesión con autenticación
	        Session session = Session.getInstance(props, new Authenticator() {
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(remitente, contrasena);
	            }
	        });

	        try {
	            // Crear un nuevo mensaje
	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(remitente));
	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
	            message.setSubject(asunto);
	            message.setText(cuerpoMensaje);

	            // Enviar el mensaje
	            Transport.send(message);

	            System.out.println("Correo enviado a " + destinatario);
	        } catch (MessagingException e) {
	            e.printStackTrace();
	            System.out.println("Error al enviar el correo.");
	        }
	    }
	   
	   public void enviarCorreoCompra(String destinatario, String asunto, String cuerpoMensaje, String pdfPath) throws MessagingException {
		    // Configurar propiedades y sesión como antes
		    Properties props = new Properties();
		    props.put("mail.smtp.host", "smtp.gmail.com");
		    props.put("mail.smtp.port", "587");
		    props.put("mail.smtp.auth", "true");
		    props.put("mail.smtp.starttls.enable", "true");

		    Session session = Session.getInstance(props, new Authenticator() {
		        @Override
		        protected PasswordAuthentication getPasswordAuthentication() {
		            return new PasswordAuthentication(remitente, contrasena);
		        }
		    });

		    try {
		        Message message = new MimeMessage(session);
		        message.setFrom(new InternetAddress(remitente));
		        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
		        message.setSubject(asunto);
		        
		        // Crear un multipart para el cuerpo del mensaje y el adjunto
		        Multipart multipart = new MimeMultipart();
		        
		        // Parte de texto
		        MimeBodyPart bodyPart = new MimeBodyPart();
		        bodyPart.setContent(cuerpoMensaje, "text/html");
		        multipart.addBodyPart(bodyPart);

		        // Parte adjunto
		        MimeBodyPart attachmentPart = new MimeBodyPart();
		        attachmentPart.attachFile(pdfPath);
		        multipart.addBodyPart(attachmentPart);
		        
		        // Establecer el contenido del mensaje
		        message.setContent(multipart);
		        
		        Transport.send(message);
		        System.out.println("Correo enviado a " + destinatario);
		    } catch (MessagingException | IOException e) {
		        e.printStackTrace();
		        System.out.println("Error al enviar el correo.");
		    }
		}
}
