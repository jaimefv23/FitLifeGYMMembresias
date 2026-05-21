/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.fitlifegym_presentacion;

import BOS.NegocioException;
import com.mycompany.fitlifegym_dtos.MembresiaDTO;
import ControlMembresias.ControlMembresias;
import com.mycompany.fitlifegym_dtos.SuscripcionDTO;
import java.awt.Color;
import java.awt.Image;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class PantallaBeneficios extends javax.swing.JFrame {

    private ControlMembresias control;
    private List<MembresiaDTO> membresiasDisponibles;
    private MembresiaDTO membresiaSeleccionada;

    public PantallaBeneficios(ControlMembresias control) {
        this.control = control;
        this.setTitle("Beneficios");
        initComponents();
        ComboBoxMembresia.setFocusable(false);
        this.setLocationRelativeTo(null);
        cargarMembresias();
        setearEditablesFalsosCheckBox();
        actualizarBeneficios();
    }

    private void cargarMembresias() {
        try {
            SuscripcionDTO suscripcionActiva = control.obtenerSuscripcionActiva();

            if (suscripcionActiva != null) {
                MembresiaDTO membresiaActual = control.obtenerMembresiaActivaDeUsuario();
                String nombreMembresia = membresiaActual != null ? membresiaActual.getNombre() : "Membresía Activa";

                ComboBoxMembresia.setModel(new DefaultComboBoxModel<>(new String[]{"Membresía Actual: " + nombreMembresia}));

                // Calcular días restantes
                long diasRestantes = ChronoUnit.DAYS.between(LocalDate.now(),suscripcionActiva.getFechaVencimiento());
                btnPrecio.setText(diasRestantes + " días restantes");
                btnSuscribirse.setEnabled(false);

                // Mostrar beneficios de su membresía actual
                if (membresiaActual != null) {
                    membresiaSeleccionada = membresiaActual;
                    mostrarBeneficiosDe(membresiaActual.getBeneficios());
                    mostrarImagenMembresia(membresiaActual);
                }

            } else {
                // Usuario sin membresía — mostrar todas las disponibles
                membresiasDisponibles = control.listarMembresiasActivas();

                if (membresiasDisponibles == null || membresiasDisponibles.isEmpty()) {
                    ComboBoxMembresia.setModel(new DefaultComboBoxModel<>(new String[]{"Sin membresías disponibles"}));
                    btnSuscribirse.setEnabled(false);
                    btnPrecio.setText("$0");
                    return;
                }

                String[] nombres = new String[membresiasDisponibles.size()];
                for (int i = 0; i < membresiasDisponibles.size(); i++) {
                    nombres[i] = membresiasDisponibles.get(i).getNombre();
                }
                ComboBoxMembresia.setModel(new DefaultComboBoxModel<>(nombres));
                btnSuscribirse.setEnabled(true);
                actualizarBeneficios();
            }

        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarBeneficios() {
        Object seleccionado = ComboBoxMembresia.getSelectedItem();
        if (seleccionado == null) return;

        int index = ComboBoxMembresia.getSelectedIndex();
        if (index < 0 || membresiasDisponibles == null
                || membresiasDisponibles.isEmpty()) return;

        membresiaSeleccionada = membresiasDisponibles.get(index);
        mostrarBeneficiosDe(membresiaSeleccionada.getBeneficios());
        btnPrecio.setText("$" + membresiaSeleccionada.getPrecio());

        mostrarImagenMembresia(membresiaSeleccionada);
    }
    
    private void mostrarImagenMembresia(MembresiaDTO membresia) {
        if (membresia.getImagen() != null && membresia.getImagen().getImagen() != null) {
            ImageIcon original = new ImageIcon(membresia.getImagen().getImagen());
            Image escalada = original.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            lblImagen.setIcon(new ImageIcon(escalada));
            lblImagen.setText("");
        } else {
            lblImagen.setIcon(null);
            lblImagen.setText("Sin imagen");
            lblImagen.setForeground(new Color(180, 180, 180));
        }
    }
    
    private void mostrarBeneficiosDe(List<String> beneficios) {
        checkBoxInstalaciones.setSelected(false);
        checkBoxNutricion.setSelected(false);
        checkBoxMusica.setSelected(false);
        checkBoxFisico.setSelected(false);
        checkBoxCursos.setSelected(false);

        if (beneficios == null) return;
        for (String b : beneficios) {
            String bl = b.toLowerCase();
            if (bl.contains("instalacion")) checkBoxInstalaciones.setSelected(true);
            if (bl.contains("nutrici")) checkBoxNutricion.setSelected(true);
            if (bl.contains("musical")) checkBoxMusica.setSelected(true);
            if (bl.contains("progreso") || bl.contains("físico")) checkBoxFisico.setSelected(true);
            if (bl.contains("curso")) checkBoxCursos.setSelected(true);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel = new javax.swing.JPanel();
        btnPrecio = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        checkBoxCursos = new javax.swing.JCheckBox();
        jSeparator4 = new javax.swing.JSeparator();
        lblTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaCursos = new javax.swing.JTextArea();
        checkBoxMusica = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        textAreaMusica = new javax.swing.JTextArea();
        jSeparator5 = new javax.swing.JSeparator();
        checkBoxFisico = new javax.swing.JCheckBox();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtAreaProgresoFisico = new javax.swing.JTextArea();
        jSeparator6 = new javax.swing.JSeparator();
        checkBoxInstalaciones = new javax.swing.JCheckBox();
        jScrollPane5 = new javax.swing.JScrollPane();
        textAreaInstalaciones = new javax.swing.JTextArea();
        checkBoxNutricion = new javax.swing.JCheckBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        textAreaCentroNutricion = new javax.swing.JTextArea();
        lblPrecio = new javax.swing.JLabel();
        ComboBoxMembresia = new javax.swing.JComboBox<>();
        btnVolver = new javax.swing.JButton();
        btnSuscribirse = new javax.swing.JButton();
        lblImagen = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel.setBackground(new java.awt.Color(18, 18, 18));
        jPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnPrecio.setBackground(new java.awt.Color(44, 44, 44));
        btnPrecio.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnPrecio.setForeground(new java.awt.Color(255, 255, 255));
        btnPrecio.setText("precio");
        btnPrecio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnPrecio.addActionListener(this::btnPrecioActionPerformed);
        jPanel.add(btnPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 390, 187, 54));

        jSeparator1.setBackground(new java.awt.Color(225, 6, 0));
        jSeparator1.setForeground(new java.awt.Color(225, 6, 0));
        jPanel.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 93, 960, 10));

        jSeparator3.setBackground(new java.awt.Color(225, 6, 0));
        jSeparator3.setForeground(new java.awt.Color(225, 6, 0));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 100, -1, 140));

        checkBoxCursos.addActionListener(this::checkBoxCursosActionPerformed);
        jPanel.add(checkBoxCursos, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 400, 20, 30));

        jSeparator4.setBackground(new java.awt.Color(225, 6, 0));
        jSeparator4.setForeground(new java.awt.Color(225, 6, 0));
        jPanel.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 370, -1));

        lblTitulo.setBackground(new java.awt.Color(255, 255, 255));
        lblTitulo.setFont(new java.awt.Font("Arial", 3, 70)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("Beneficios");
        jPanel.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 9, 360, 94));

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        textAreaCursos.setEditable(false);
        textAreaCursos.setBackground(new java.awt.Color(18, 18, 18));
        textAreaCursos.setColumns(20);
        textAreaCursos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        textAreaCursos.setForeground(new java.awt.Color(255, 255, 255));
        textAreaCursos.setRows(5);
        textAreaCursos.setText("Cursos Especiales:");
        jScrollPane1.setViewportView(textAreaCursos);

        jPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 370, 140));

        checkBoxMusica.addActionListener(this::checkBoxMusicaActionPerformed);
        jPanel.add(checkBoxMusica, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 100, 20, 30));

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        textAreaMusica.setEditable(false);
        textAreaMusica.setBackground(new java.awt.Color(18, 18, 18));
        textAreaMusica.setColumns(20);
        textAreaMusica.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        textAreaMusica.setForeground(new java.awt.Color(255, 255, 255));
        textAreaMusica.setRows(5);
        textAreaMusica.setText("Ambiente Musical Dinamico:");
        jScrollPane2.setViewportView(textAreaMusica);

        jPanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 100, 280, 140));

        jSeparator5.setBackground(new java.awt.Color(225, 6, 0));
        jSeparator5.setForeground(new java.awt.Color(225, 6, 0));
        jPanel.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 950, -1));

        checkBoxFisico.addActionListener(this::checkBoxFisicoActionPerformed);
        jPanel.add(checkBoxFisico, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 250, 20, 30));

        jScrollPane4.setBorder(null);
        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtAreaProgresoFisico.setEditable(false);
        txtAreaProgresoFisico.setBackground(new java.awt.Color(18, 18, 18));
        txtAreaProgresoFisico.setColumns(20);
        txtAreaProgresoFisico.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtAreaProgresoFisico.setForeground(new java.awt.Color(255, 255, 255));
        txtAreaProgresoFisico.setRows(5);
        txtAreaProgresoFisico.setText("Progreso físico:");
        jScrollPane4.setViewportView(txtAreaProgresoFisico);

        jPanel.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 251, 370, -1));

        jSeparator6.setBackground(new java.awt.Color(225, 6, 0));
        jSeparator6.setForeground(new java.awt.Color(225, 6, 0));
        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(383, 5, 10, 530));

        checkBoxInstalaciones.addActionListener(this::checkBoxInstalacionesActionPerformed);
        jPanel.add(checkBoxInstalaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, 20, 20));

        jScrollPane5.setBorder(null);
        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        textAreaInstalaciones.setEditable(false);
        textAreaInstalaciones.setBackground(new java.awt.Color(18, 18, 18));
        textAreaInstalaciones.setColumns(20);
        textAreaInstalaciones.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        textAreaInstalaciones.setForeground(new java.awt.Color(255, 255, 255));
        textAreaInstalaciones.setRows(5);
        textAreaInstalaciones.setText("Acceso a nuestras instalaciones:");
        textAreaInstalaciones.setDragEnabled(true);
        jScrollPane5.setViewportView(textAreaInstalaciones);

        jPanel.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 370, 130));

        checkBoxNutricion.addActionListener(this::checkBoxNutricionActionPerformed);
        jPanel.add(checkBoxNutricion, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 110, 20, -1));

        jScrollPane3.setBorder(null);
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        textAreaCentroNutricion.setEditable(false);
        textAreaCentroNutricion.setBackground(new java.awt.Color(18, 18, 18));
        textAreaCentroNutricion.setColumns(20);
        textAreaCentroNutricion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        textAreaCentroNutricion.setForeground(new java.awt.Color(255, 255, 255));
        textAreaCentroNutricion.setRows(5);
        textAreaCentroNutricion.setText("Centro de Nutrición:");
        jScrollPane3.setViewportView(textAreaCentroNutricion);

        jPanel.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, 290, 140));

        lblPrecio.setBackground(new java.awt.Color(255, 255, 255));
        lblPrecio.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        lblPrecio.setForeground(new java.awt.Color(255, 255, 255));
        lblPrecio.setText("Todo esto a tan solo!!!:");
        jPanel.add(lblPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 340, 210, 40));

        ComboBoxMembresia.setBackground(new java.awt.Color(44, 44, 44));
        ComboBoxMembresia.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        ComboBoxMembresia.setForeground(new java.awt.Color(255, 255, 255));
        ComboBoxMembresia.addActionListener(this::ComboBoxMembresiaActionPerformed);
        jPanel.add(ComboBoxMembresia, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, 460, 80));

        btnVolver.setBackground(new java.awt.Color(102, 102, 102));
        btnVolver.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnVolver.setForeground(new java.awt.Color(255, 255, 255));
        btnVolver.setText("Volver");
        btnVolver.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnVolver.addActionListener(this::btnVolverActionPerformed);
        jPanel.add(btnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 480, 187, 54));

        btnSuscribirse.setBackground(new java.awt.Color(255, 0, 51));
        btnSuscribirse.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnSuscribirse.setForeground(new java.awt.Color(255, 255, 255));
        btnSuscribirse.setText("Suscribirse");
        btnSuscribirse.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnSuscribirse.addActionListener(this::btnSuscribirseActionPerformed);
        jPanel.add(btnSuscribirse, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 390, 187, 54));
        jPanel.add(lblImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, 60, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 960, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrecioActionPerformed

    }//GEN-LAST:event_btnPrecioActionPerformed

    private void checkBoxCursosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxCursosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBoxCursosActionPerformed

    private void checkBoxMusicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxMusicaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBoxMusicaActionPerformed

    private void checkBoxFisicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxFisicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBoxFisicoActionPerformed

    private void checkBoxInstalacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxInstalacionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBoxInstalacionesActionPerformed

    private void checkBoxNutricionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxNutricionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBoxNutricionActionPerformed

    private void ComboBoxMembresiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxMembresiaActionPerformed
        actualizarBeneficios();
    }//GEN-LAST:event_ComboBoxMembresiaActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed

            dispose();
            control.mostrarPantallaBienvenida();
      
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnSuscribirseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuscribirseActionPerformed
        if (membresiaSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Selecciona una membresía", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        dispose();
        control.mostrarPantallaConfirmarSuscripcion(membresiaSeleccionada);
    }//GEN-LAST:event_btnSuscribirseActionPerformed

  
    private void setearEditablesFalsosCheckBox() {
        checkBoxCursos.setEnabled(false);
        checkBoxFisico.setEnabled(false);
        checkBoxInstalaciones.setEnabled(false);
        checkBoxMusica.setEnabled(false);
        checkBoxNutricion.setEnabled(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxMembresia;
    private javax.swing.JButton btnPrecio;
    private javax.swing.JButton btnSuscribirse;
    private javax.swing.JButton btnVolver;
    private javax.swing.JCheckBox checkBoxCursos;
    private javax.swing.JCheckBox checkBoxFisico;
    private javax.swing.JCheckBox checkBoxInstalaciones;
    private javax.swing.JCheckBox checkBoxMusica;
    private javax.swing.JCheckBox checkBoxNutricion;
    private javax.swing.JPanel jPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextArea textAreaCentroNutricion;
    private javax.swing.JTextArea textAreaCursos;
    private javax.swing.JTextArea textAreaInstalaciones;
    private javax.swing.JTextArea textAreaMusica;
    private javax.swing.JTextArea txtAreaProgresoFisico;
    // End of variables declaration//GEN-END:variables
}
