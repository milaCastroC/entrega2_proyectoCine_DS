/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas;

import DTOs.UsuarioDTO;

/**
 *
 * @author servi
 */
public class MenuInicioAdmin extends javax.swing.JFrame {

    /**
     * Creates new form MenuInicioAdmin
     */
	
	UsuarioDTO usuario;
	
    public MenuInicioAdmin(UsuarioDTO usuario) {
        initComponents();
        setLocationRelativeTo(this);
        this.usuario = usuario;
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
        btnPeliculas = new javax.swing.JButton();
        btnFunciones = new javax.swing.JButton();
        btnSalas = new javax.swing.JButton();
        btnCompraBoletas = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnPeliculas.setBackground(new java.awt.Color(51, 51, 255));
        btnPeliculas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPeliculas.setForeground(new java.awt.Color(255, 255, 255));
        btnPeliculas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/video-camera.png"))); // NOI18N
        btnPeliculas.setText("PELICULAS");
        btnPeliculas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPeliculasActionPerformed(evt);
            }
        });

        btnFunciones.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnFunciones.setForeground(new java.awt.Color(51, 51, 255));
        btnFunciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cinema (1).png"))); // NOI18N
        btnFunciones.setText("FUNCIONES");
        btnFunciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFuncionesActionPerformed(evt);
            }
        });

        btnSalas.setBackground(new java.awt.Color(51, 51, 255));
        btnSalas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSalas.setForeground(new java.awt.Color(255, 255, 255));
        btnSalas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/recommendation.png"))); // NOI18N
        btnSalas.setText("SALAS");
        btnSalas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalasActionPerformed(evt);
            }
        });

        btnCompraBoletas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCompraBoletas.setForeground(new java.awt.Color(51, 51, 255));
        btnCompraBoletas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/travel (1).png"))); // NOI18N
        btnCompraBoletas.setText("COMPRA BOLETAS");
        btnCompraBoletas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompraBoletasActionPerformed(evt);
            }
        });

        btnCerrarSesion.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        btnCerrarSesion.setForeground(new java.awt.Color(51, 51, 255));
        btnCerrarSesion.setText("X");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 255));
        jLabel4.setText("GESTIONA EL CINE AQUÍ:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCerrarSesion)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnFunciones, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSalas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCompraBoletas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnPeliculas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(119, 119, 119))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel4)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(7, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPeliculas)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnCerrarSesion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(btnFunciones)
                .addGap(18, 18, 18)
                .addComponent(btnSalas)
                .addGap(18, 18, 18)
                .addComponent(btnCompraBoletas)
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCompraBoletasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompraBoletasActionPerformed
        CompraBoletas compraBoletas = new CompraBoletas(usuario);
        compraBoletas.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCompraBoletasActionPerformed

    private void btnFuncionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFuncionesActionPerformed
        GestionFunciones gestionFunciones = new GestionFunciones(usuario);
        gestionFunciones.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnFuncionesActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
    	LoginUsuarios loginUsuarios = new LoginUsuarios();
        loginUsuarios.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnPeliculasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPeliculasActionPerformed
        GestionPeliculas gestionPeliculas = new GestionPeliculas(usuario);
        gestionPeliculas.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnPeliculasActionPerformed

    private void btnSalasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalasActionPerformed
        GestionSalas gestionSalas = new GestionSalas(usuario);
        gestionSalas.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSalasActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnCompraBoletas;
    private javax.swing.JButton btnFunciones;
    private javax.swing.JButton btnPeliculas;
    private javax.swing.JButton btnSalas;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
