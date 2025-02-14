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
public class MenuInicioCliente extends javax.swing.JFrame {

    /**
     * Creates new form MenuInicioCliente
     */
    
    private UsuarioDTO usuario;
	
    public MenuInicioCliente(UsuarioDTO usuario) {
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
        btnCerrarSesion = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnPeliculas = new javax.swing.JButton();
        btnHistorial = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

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
        jLabel4.setText("¡BIENVENIDO A CINESOFT!");

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

        btnHistorial.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHistorial.setForeground(new java.awt.Color(51, 51, 255));
        btnHistorial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/clock (1).png"))); // NOI18N
        btnHistorial.setText("HISTORIAL");
        btnHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistorialActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCerrarSesion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(47, 47, 47))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnHistorial)
                    .addComponent(btnPeliculas, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnCerrarSesion)))
                .addGap(32, 32, 32)
                .addComponent(btnPeliculas)
                .addGap(18, 18, 18)
                .addComponent(btnHistorial)
                .addGap(0, 47, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
    	LoginUsuarios loginUsuarios = new LoginUsuarios();
        loginUsuarios.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnPeliculasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPeliculasActionPerformed
        PeliculasCliente vistaPeliculasCliente = new PeliculasCliente(usuario);
        vistaPeliculasCliente.setVisible(true); 
        this.dispose();
    }//GEN-LAST:event_btnPeliculasActionPerformed

    private void btnHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistorialActionPerformed
        HistorialCompras historialCompras = new HistorialCompras(usuario);
        historialCompras.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHistorialActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnHistorial;
    private javax.swing.JButton btnPeliculas;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
