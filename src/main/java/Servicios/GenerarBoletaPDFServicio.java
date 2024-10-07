package Servicios;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
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
import Repositorios.FuncionRepositorio;
import Repositorios.PeliculaRepositorio;

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
                    correoServicio.enviarCorreoCompra(usuario.getCorreo(), "Compra de Boleta", "Adjunto encontrará su boleta de compra.", fileName);
                } catch (MessagingException e) {
                    e.printStackTrace(); // Manejo de excepción al enviar correo
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace(); 
        }
    }
}
