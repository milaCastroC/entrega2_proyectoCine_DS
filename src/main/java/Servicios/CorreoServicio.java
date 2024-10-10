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

import DTOs.UsuarioDTO;
import io.github.cdimascio.dotenv.Dotenv;

public class CorreoServicio {
		private static final Dotenv dotenv = Dotenv.load();
		private final String remitente = "cinesoft36@gmail.com";
		private final String CORREO_KEY = dotenv.get("CORREO_KEY");
	   
	   private void enviarCorreo(String destinatario, String asunto, String cuerpoMensaje) throws MessagingException {

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
	                return new PasswordAuthentication(remitente, CORREO_KEY);
	            }
	        });

	        try {
	            // Crear un nuevo mensaje
	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(remitente));
	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
	            message.setSubject(asunto);
	            message.setContent(cuerpoMensaje, "text/html; charset=ISO-8859-1");

	            // Enviar el mensaje
	            Transport.send(message);

	            System.out.println("Correo enviado a " + destinatario);
	        } catch (MessagingException e) {
	            e.printStackTrace();
	            System.out.println("Error al enviar el correo.");
	        }
	    }
	   
	   private void enviarCorreoConArchivo(String destinatario, String asunto, String cuerpoMensaje, String pdfPath) throws MessagingException {
		    // Configurar propiedades y sesión como antes
		    Properties props = new Properties();
		    props.put("mail.smtp.host", "smtp.gmail.com");
		    props.put("mail.smtp.port", "587");
		    props.put("mail.smtp.auth", "true");
		    props.put("mail.smtp.starttls.enable", "true");

		    Session session = Session.getInstance(props, new Authenticator() {
		        @Override
		        protected PasswordAuthentication getPasswordAuthentication() {
		            return new PasswordAuthentication(remitente, CORREO_KEY);
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
	   
	   public void enviarCorreoRegistro(UsuarioDTO usuario) throws MessagingException {

			String asunto = "¡Bienvenido a CineSoft!";
			String cuerpoMensaje = String.format("""

			    <html lang="es">
			    <meta charset="UTF-8">
			    <meta name="viewport" content="width=device-width, initial-scale=1.0">
			    <title>Bienvenida - CineSoft</title>
					<head>
				        <style>
				            @import url('https://fonts.googleapis.com/css2?family=Nunito:wght@400;700&display=swap');
				
				            body {
					            font-family: 'Nunito', -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif;
					            line-height: 1.6;
					            color: #343a40;
					            background-color: #f8f9fa;
					            margin: 0;
					            padding: 0;
					            font-size: 18px;
				            }
				
				            .container {
					            max-width: 600px;
					            margin: 20px auto;
					            padding: 20px;
					            background-color: #ffffff;
					            border-radius: 10px;
					            box-shadow: 0 0 40px rgba(0, 0, 0, 0.1);
				            }
				
				            h1 {
					            color: #212ea1;
					            border-bottom: 2px solid #212ea1;
					            padding-bottom: 10px;
					            font-size: 2.2em;
					            font-weight: 700;
				            }
				
				            
				            strong {
					            color: #212ea1;
					            font-weight: 700;
				            }
				
				            .greeting {
					            font-size: 1.3em;
					            font-weight: 700;
					            color: #212ea1;
					            margin-bottom: 20px;
				            }
				
				            .second-greeting{
				                font-weight: bold;
				                font-size: 1em;
				            }
				
				            ul {
					            padding-left: 20px;
				            }
				
				            li {
					            margin-bottom: 12px;
				            }
				
				            li::before {
					            content: "•";
					            color: #212ea1;
					            display: inline-block;
					            width: 1em;
					            margin-left: -1em;
					            font-weight: bold;
				            }
				
				            .footer {
					            margin-top: 30px;
					            padding-top: 15px;
					            border-top: 1px solid #343a40;
					            font-size: 0.9em;
					            color: #343a40;
					            text-align: center;
				            }
				        </style>
				    </head>
				    <body>
				        <div class="container">
				            <h1>¡Bienvenido a CineSoft, %s!</h1>
				            <p class="second-greeting">Hola %s,</p>
				            <p>Gracias por registrarte en <strong>CineSoft</strong>, tu nueva plataforma para disfrutar de una experiencia de cine única. Nos emociona que formes parte de nuestra comunidad, y estamos aquí para ofrecerte la mejor selección de películas y contenido exclusivo.</p>
				            <p>En CineSoft podrás:</p>
				            <ul>
				                <li>Explorar una vasta colección de títulos de diversos géneros.</li>
				                <li>Comentar sobre tus películas favoritas.</li>
				                <li>Tener acceso a tu registro de compras en todo momento</li>
				            </ul>
				            <p>Estamos seguros de que disfrutarás cada minuto en nuestra plataforma. Para comenzar, te invitamos a iniciar sesión y explorar lo que tenemos para ofrecerte.</p>
				            <p>Si tienes alguna pregunta o necesitas asistencia, no dudes en contactarnos. ¡Estamos aquí para ayudarte!</p>
				            <p>¡Nos vemos!</p>
				            <div class="footer">
				                © 2024 CineSoft. Todos los derechos reservados.
				            </div>
				        </div>
				    </body>			    
				</html>
			    """, usuario.getNombre(), usuario.getNombre());
			enviarCorreo(usuario.getCorreo(), asunto, cuerpoMensaje);
	   }
	   
	   public void enviarCorreoCompra(UsuarioDTO usuario, String fileName) throws MessagingException {
		   String asunto = "Compra de Boleta";
       	String contenido = String.format("""
       			<html lang="es">
					<head>
					    <meta charset="UTF-8">
					    <meta name="viewport" content="width=device-width, initial-scale=1.0">
					    <title>Confirmación de Compra - CineSoft</title>
					    <style>
					        body {
					            font-family: 'Arial', sans-serif;
					            line-height: 1.6;
					            color: #343a40;
					            margin: 0;
					            padding: 0;
					            background-color: #f8f9fa;
					        }
					        .container {
					            max-width: 600px;
					            margin: 20px auto;
					            padding: 20px;
					            background-color: #ffffff;
					            border-radius: 10px;
					            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
					        }
					        h1 {
					            color: #212ea1;
					            border-bottom: 2px solid #212ea1;
					            padding-bottom: 10px;
					            font-size: 24px;
					        }
					        .message {
					            font-size: 16px;
					            margin-bottom: 20px;
					        }
					        .highlight {
					            background-color: #e9ecef;
					            border-left: 4px solid #212ea1;
					            padding: 10px;
					            margin-bottom: 20px;
					        }
					        .footer {
					            margin-top: 20px;
					            padding-top: 10px;
					            border-top: 1px solid #dee2e6;
					            font-size: 14px;
					            color: #6c757d;
					            text-align: center;
					        }
					    </style>
					</head>
					<body>
					    <div class="container">
					        <h1>¡Gracias por tu compra en CineSoft!</h1>
					        <p class="message">Estimado cliente,</p>
					        <p class="message">Nos complace informarte que tu compra se ha realizado con éxito. Tus boletas están listas para que disfrutes de tu película.</p>
					        <div class="highlight">
					            <p>Adjunto encontrarás tu boleta de compra.</p>
					        </div>
					        <p class="message">Si tienes alguna pregunta o necesitas asistencia adicional, no dudes en contactarnos. Estamos aquí para ayudarte.</p>
					        <p class="message">¡Esperamos que disfrutes de tu película!</p>
					        <div class="footer">
					            <p>© 2024 CineSoft. Todos los derechos reservados.</p>
					        </div>
					    </div>
					</body>
					</html>
       			""");
           enviarCorreoConArchivo(usuario.getCorreo(), asunto, contenido, fileName);
	   }
}