
package Vistas;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import Controladores.ControladorVentanaCompraBoletas;
import DTOs.CompraDTO;
import DTOs.FuncionDTO;
import DTOs.PeliculaDTO;
import DTOs.SalaDTO;
import DTOs.SillaDTO;
import DTOs.UsuarioDTO;
import Exceptions.PeliculaNoEncontradaException;
import Exceptions.SillaOcupadaException;
import Exceptions.UsuarioNoEncontradoException;
import java.awt.Color;
import java.awt.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompraBoletas extends javax.swing.JFrame implements ActionListener{

    UsuarioDTO usuario;
    ControladorVentanaCompraBoletas controladorVentanaCompraBoletas;
    
    public CompraBoletas(UsuarioDTO usuario) {
        initComponents();
        
        setLocationRelativeTo(this);
        this.usuario = usuario;
        this.controladorVentanaCompraBoletas = new ControladorVentanaCompraBoletas();
        
        llenarCbPeliculas();
        llenaCbFunciones(null);
        txtCantidad.setText("0");
        
    }

    
    
    public void llenarCbPeliculas() {
    	try {
    		List<PeliculaDTO> peliculasDisponibles = controladorVentanaCompraBoletas.obtenerPeliculasConFunciones();
    		cbPeliculas.removeAllItems();
    		cbPeliculas.addItem("--Seleccione una pelicula--");
            for (int i = 0; i < peliculasDisponibles.size(); i++) {
            	PeliculaDTO pelicula = peliculasDisponibles.get(i);
                cbPeliculas.addItem(pelicula);
            }
    	}catch(SQLException | PeliculaNoEncontradaException e) {
    		e.printStackTrace();
    	}
    }
    
    public void llenaCbFunciones(PeliculaDTO pelicula) {
    	if(pelicula != null) {
    		try {
        		List<FuncionDTO> funcionesPorPelicula = controladorVentanaCompraBoletas.obtenerFuncionesPorPelicula(pelicula.getId_pelicula());
        		cbFunciones.removeAllItems();
        		cbFunciones.addItem("--Seleccione una función--");
                for (int i = 0; i < funcionesPorPelicula.size(); i++) {
                	FuncionDTO funcion = funcionesPorPelicula.get(i);
                	cbFunciones.addItem(funcion);
                }
        	}catch(SQLException | PeliculaNoEncontradaException e) {
        		e.printStackTrace();
        	}
    	} else {
    		cbFunciones.removeAllItems();
    		cbFunciones.addItem("--Seleccione una función--");
    	}
    }
    
    private void cbFuncionesItemStateChanged1(java.awt.event.ItemEvent evt) {
        
    }
    

    private void cbPeliculasItemStateChanged(java.awt.event.ItemEvent evt) {                                             
       if(cbPeliculas.getSelectedIndex() != 0){
           PeliculaDTO pelicula = (PeliculaDTO)cbPeliculas.getSelectedItem();
           llenaCbFunciones(pelicula);
           borrarContenidoPanel();
       } else {
    	   llenaCbFunciones(null);
       }
    }    
  private void cargarSala(SalaDTO sala, int idFuncion){
    borrarContenidoPanel();
    
    List<SillaDTO> sillasOcupadas = controladorVentanaCompraBoletas.obtenerSillasOcupadas(idFuncion);
    
    JButton[][] sillas = generarMatriz(sala.getCapacidad());
    int ancho = 50;
    int alto = 50;
    int separado = 20;
    int contador = 1;

    for(int i = 0; i < sillas.length; i++){
        for(int j = 0; j < sillas[i].length; j++){

            JButton botonSilla = new JButton();
            botonSilla.setBounds(
                (j * ancho + separado),
                (i * alto + separado),  
                ancho, alto);
            botonSilla.setText(contador + "");
            
            boolean sillaOcupada = false;
            for (SillaDTO silla : sillasOcupadas) {
                if (silla.getIdentificador() == contador) {
                    sillaOcupada = true;
                    break;
                }
            }

            
            if (sillaOcupada) {
                botonSilla.setEnabled(false);  
            } else {
                botonSilla.setBackground(Color.WHITE);
                botonSilla.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int sillasSeleccionadas = Integer.parseInt(txtCantidad.getText());

                        if (botonSilla.getBackground() == Color.GRAY) {
                            botonSilla.setBackground(Color.WHITE);
                        } else if (sillasSeleccionadas < sala.getCapacidad()) {
                            botonSilla.setBackground(Color.GRAY);
                        } else {
                            JOptionPane.showMessageDialog(null, "No puedes seleccionar más sillas.");
                        }

                        actualizarCantidad();
                    }
                });
            }

            panelMatrizSillas.add(botonSilla);
            contador++;
        }
    }

    panelMatrizSillas.revalidate();
    panelMatrizSillas.repaint();
}

  
  private void actualizarCantidad() {
    int sillasSeleccionadas = 0;

    for (Component comp : panelMatrizSillas.getComponents()) {
        if (comp instanceof JButton) {
            JButton boton = (JButton) comp;
            if (boton.getBackground() == Color.GRAY) {
                sillasSeleccionadas++;
            }
        }
    }

    txtCantidad.setText(String.valueOf(sillasSeleccionadas));
    
    txtCantidad.setEditable(false);
}

    
    private JButton[][] generarMatriz(int cantidadSillas){
    	int columnas = 8;
		int filas = cantidadSillas / columnas;
        JButton[][] matriz;

        if (cantidadSillas % columnas != 0) {
            matriz = new JButton[filas + 1][];

            for (int i = 0; i < filas; i++) {
                matriz[i] = new JButton[columnas];
            }
            int puestosFaltantes = cantidadSillas % columnas;
            matriz[matriz.length - 1] = new JButton[puestosFaltantes];
        } else {
            matriz = new JButton[filas][columnas]; 
        }
        return matriz;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelMatrizSillas = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbPeliculas = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        labelSala = new javax.swing.JLabel();
        btnComprar = new javax.swing.JButton();
        txtCantidad = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        cbFunciones = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelMatrizSillas.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "SillasDisponibles", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        javax.swing.GroupLayout panelMatrizSillasLayout = new javax.swing.GroupLayout(panelMatrizSillas);
        panelMatrizSillas.setLayout(panelMatrizSillasLayout);
        panelMatrizSillasLayout.setHorizontalGroup(
            panelMatrizSillasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 456, Short.MAX_VALUE)
        );
        panelMatrizSillasLayout.setVerticalGroup(
            panelMatrizSillasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Pelicula:");

        cbPeliculas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbPeliculas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbPeliculasItemStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Función:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Cantidad:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Correo:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Sala:");

        labelSala.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelSala.setText(" ");

        btnComprar.setText("Comprar");
        btnComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprarActionPerformed(evt);
            }
        });

        txtCantidad.setEditable(false);
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Precio unidad:");

        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioKeyTyped(evt);
            }
        });

        cbFunciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbFunciones.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbFuncionesItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnComprar)
                .addGap(184, 184, 184))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE))
                    .addComponent(jLabel3)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(cbFunciones, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6)
                                .addComponent(jLabel4))
                            .addGap(23, 23, 23)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(labelSala, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtCorreo, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtCantidad, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(cbPeliculas, javax.swing.GroupLayout.Alignment.TRAILING, 0, 299, Short.MAX_VALUE)))))))
                .addGap(23, 23, 23))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbPeliculas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(cbFunciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSala, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(btnComprar)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("COMPRA DE BOLETAS");

        btnRegresar.setText("REGRESAR");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        btnCerrarSesion.setText("CERRAR SESIÓN");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelMatrizSillas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnRegresar)
                        .addGap(294, 294, 294)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCerrarSesion)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 28, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnRegresar)
                            .addComponent(btnCerrarSesion))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelMatrizSillas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        MenuInicioAdmin menuInicioAdmin = new MenuInicioAdmin(usuario);
        menuInicioAdmin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        LoginUsuarios loginUsuarios = new LoginUsuarios();
        loginUsuarios.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprarActionPerformed
    	if(validarCampos()) {
            try {

            	String correo = txtCorreo.getText();
            	
            	LocalDate fecha = LocalDate.now();
                java.sql.Date sqlDate = java.sql.Date.valueOf(fecha);
                
                PeliculaDTO pelicula = (PeliculaDTO)cbPeliculas.getSelectedItem();
                FuncionDTO funcion = (FuncionDTO)cbFunciones.getSelectedItem();
                int idFuncion = funcion.getId_funcion();
                int cantidadBoletas = Integer.parseInt(txtCantidad.getText());
                
                double precio = Double.parseDouble(txtPrecio.getText());
                
            	Integer idUsuario = 0;
        		if(!correo.isEmpty()) {
        			UsuarioDTO cliente = controladorVentanaCompraBoletas.obtenerUsuarioPorCorreo(correo);
        			idUsuario = cliente.getId_usuario();
        		} else {
        			idUsuario = -1;
        	        int respuesta = JOptionPane.showConfirmDialog(null, "No ha proporcionado un correo. \nLe recordamos que el cliente no podrá acceder a los beneficios de los usuarios registrados o su historial\n y en caso de reclamo debe presentar la factura física. \n¿Desea continua?", "Confirmación", JOptionPane.YES_NO_OPTION);
        	        if (respuesta == JOptionPane.NO_OPTION) {
        	        	return;
        	        }
        		}
        		
                List<Integer> sillasSeleccionadas = obtenerSillasSeleccionadas(); 
                
                
                
                CompraDTO compra = new CompraDTO(0, sqlDate, idUsuario, cantidadBoletas);
                controladorVentanaCompraBoletas.agregarCompra(compra, idFuncion, sillasSeleccionadas, precio);
                limpiarCampos();
                borrarContenidoPanel();
                JOptionPane.showMessageDialog(null, "Compra registrada con éxito");
            } catch (UsuarioNoEncontradoException | SillaOcupadaException ex) {
                JOptionPane.showMessageDialog(this, "Error al comprar boletas: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                borrarContenidoPanel();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, rellena todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    	private List<Integer> obtenerSillasSeleccionadas() {
    	    List<Integer> sillasSeleccionadas = new ArrayList<>();

    	    
    	    for (Component comp : panelMatrizSillas.getComponents()) {
    	        if (comp instanceof JButton) {
    	            JButton boton = (JButton) comp;
    	            if (boton.getBackground() == Color.GRAY) {  
    	                int numeroSilla = Integer.parseInt(boton.getText());
    	                sillasSeleccionadas.add(numeroSilla);
    	            }
    	        }
    	    }
    	    return sillasSeleccionadas;
    }//GEN-LAST:event_btnComprarActionPerformed

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
         char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Por favor, Ingresar solo números", "Error", JOptionPane.ERROR_MESSAGE );
        }
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyTyped
         char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Por favor, Ingresar solo números", "Error", JOptionPane.ERROR_MESSAGE );
        }
    }//GEN-LAST:event_txtPrecioKeyTyped

    private void cbFuncionesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbFuncionesItemStateChanged
        if (cbFunciones.getSelectedIndex() != 0 && cbFunciones.getSelectedIndex() != -1) {
            FuncionDTO funcion = (FuncionDTO) cbFunciones.getSelectedItem();
            SalaDTO sala = controladorVentanaCompraBoletas.buscarSala(funcion.getId_sala());
            labelSala.setText(sala.toString());
            cargarSala(sala, funcion.getId_funcion());
        } else {
            labelSala.setText("");
        }
    }//GEN-LAST:event_cbFuncionesItemStateChanged

    private void borrarContenidoPanel() {
    	panelMatrizSillas.removeAll();  // Elimina todos los componentes del panel
    	panelMatrizSillas.revalidate(); // Revalida el diseño del panel
    	panelMatrizSillas.repaint();    // Vuelve a pintar el panel
    }
    
    private boolean validarCampos() {
        String cantidad = txtCantidad.getText();
        String precio = txtPrecio.getText();

        if (cbFunciones.getSelectedIndex() == 0 || cbPeliculas.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Por favor, selecciona una película y una función.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (cantidad.isEmpty() || cantidad.equals("0")) {
            JOptionPane.showMessageDialog(null, "Por favor, selecciona al menos una silla.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (precio.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, introduce el precio de la boleta.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }
    
    private void limpiarCampos() {
    	cbPeliculas.setSelectedIndex(0);
    	cbFunciones.setSelectedIndex(0);
    	txtCantidad.setText("0");
        txtPrecio.setText("0");
    	txtCorreo.setText("");
    	
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnComprar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<Object> cbFunciones;
    private javax.swing.JComboBox<Object> cbPeliculas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel labelSala;
    private javax.swing.JPanel panelMatrizSillas;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables

    public void actionPerformed(ActionEvent e) {
        System.out.println("silla seleccionada: ");
    }

}
