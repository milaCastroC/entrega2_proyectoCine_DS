package Servicios;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.mail.MessagingException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import DTOs.BoletaDTO;
import DTOs.CompraDTO;
import DTOs.FuncionDTO;
import DTOs.PeliculaDTO;
import DTOs.SalaDTO;
import DTOs.SillaDTO;
import DTOs.UsuarioDTO;

public class GenerarBoletaPDFServicio {

    private FuncionServicio funcionServicio = new FuncionServicio();
    private PeliculaServicio peliculaServicio = new PeliculaServicio();
    private SalaServicio salaServicio = new SalaServicio();
    private SillaServicio sillaServicio = new SillaServicio();
    private CorreoServicio correoServicio = new CorreoServicio();
    
    
    public void generarBoletaPDF(CompraDTO compra, List<BoletaDTO> boletas, UsuarioDTO usuario) {
        // Generar un nombre único para el archivo PDF en la carpeta PDFsBoletas
        String directoryPath = "./PDFsBoletas/";
        String fileName = directoryPath + "Compra_" + compra.getIdCompra() + ".pdf";

        try {
            // Verificar si la carpeta PDFsBoletas existe, si no, crearla
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            PdfWriter writer = new PdfWriter(fileName);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("Cine Soft - Boleta de Compra").setBold());
            document.add(new Paragraph("Fecha de Compra: " + compra.getFecha().toString()));
            if(usuario != null) {
                document.add(new Paragraph("Correo del Cliente: " + usuario.getCorreo()));
            }else {
                document.add(new Paragraph("Correo del Cliente: No registrado"));
            }
            
            document.add(new Paragraph());

            // Agregar la información de cada boleta
            for (BoletaDTO boleta : boletas) {
                try {
                    FuncionDTO funcion = funcionServicio.buscarPorId(boleta.getId_funcion());
                    PeliculaDTO pelicula = peliculaServicio.buscarPorId(funcion.getId_pelicula());
                    SalaDTO sala = salaServicio.buscarPorId(funcion.getId_sala());
                    SillaDTO silla = sillaServicio.buscarSillaPorId(boleta.getId_silla());
                    
                    document.add(new Paragraph("Película: " + pelicula.getNombre()));
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    document.add(new Paragraph("Fecha de Función: " + dateFormat.format(funcion.getFecha())));
                    document.add(new Paragraph("Hora de Función: " + funcion.getHora().toString()));
                    document.add(new Paragraph("Sala: " + sala.getNombreSala()));
                    document.add(new Paragraph("Silla: " + silla.getIdentificador()));
                    document.add(new Paragraph());

                    
                } catch (Exception e) {
                    e.printStackTrace(); 
                }
            }

            // Cerrar el documento
            document.close();
            System.out.println("Compra generada con éxito en el archivo: " + fileName);
            
            // Enviar correo si el usuario está registrado
            if (usuario != null && !usuario.getCorreo().isEmpty()) {
                try {
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
                    correoServicio.enviarCorreoCompra(usuario.getCorreo(), asunto, contenido, fileName);
                } catch (MessagingException e) {
                    e.printStackTrace(); // Manejo de excepción al enviar correo
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace(); 
        }
    }
}
