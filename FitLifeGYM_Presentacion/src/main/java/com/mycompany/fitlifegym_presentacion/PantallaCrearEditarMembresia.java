/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.fitlifegym_presentacion;

import BOS.NegocioException;
import ControlMembresias.ControlMembresias;
import com.mycompany.fitlifegym_dtos.EditarMembresiaDTO;
import com.mycompany.fitlifegym_dtos.ImagenDTO;
import com.mycompany.fitlifegym_dtos.MembresiaDTO;
import com.mycompany.fitlifegym_dtos.NuevaMembresiaDTO;
import com.mycompany.fitlifegym_dtos.PeriodoMembresiaDTO;
import com.toedter.calendar.JDateChooser;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Jaime
 */
public class PantallaCrearEditarMembresia extends javax.swing.JFrame {
    
    private ControlMembresias control;
    private final String idMembresia;
    private byte[] imagenSeleccionada = null;
    private String nombreImagenSeleccionada = null;
    
    // Constructor CREAR
    public PantallaCrearEditarMembresia(ControlMembresias control) {
        this.control = control;
        this.idMembresia = null;
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        configurarModo();
        configurarCheckBoxes();
    }

    // Constructor EDITAR
    public PantallaCrearEditarMembresia(ControlMembresias control, String idMembresia) {
        this.control = control;
        this.idMembresia = idMembresia;
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        configurarModo();
        configurarCheckBoxes();
    }
    
    private void configurarModo() {
        if (idMembresia == null) {
            // CREAR
            lblTitulo.setText("Crear Membresía");
            chkActiva.setSelected(true);
            chkNoActiva.setSelected(false);
            
            // Imagen visible solo en crear
            lblImagen.setVisible(true);
            btnSeleccionarImagen.setVisible(true);
            lblVistaPrevia.setVisible(true);
        } else {
            
            // EDITAR
            lblTitulo.setText("Editar Membresía");
            lblImagen.setVisible(false);
            btnSeleccionarImagen.setVisible(false);
            lblVistaPrevia.setVisible(false);
            cargarDatosMembresia();
        }
    }
    
    private void cargarDatosMembresia() {
       try {
            MembresiaDTO membresia = control.consultarMembresiaPorID(idMembresia);
            if (membresia == null) {
                JOptionPane.showMessageDialog(this, "No se encontró la membresía", "Error", JOptionPane.ERROR_MESSAGE);
                dispose();
                return;
            }

            // Pre-llenar campos
            txtNombre.setText(membresia.getNombre());
            txtPrecio.setText(String.valueOf(membresia.getPrecio()));

            // Estado
            if ("ACTIVA".equals(membresia.getEstado())) {
                chkActiva.setSelected(true);
                chkNoActiva.setSelected(false);
            } else {
                chkActiva.setSelected(false);
                chkNoActiva.setSelected(false);
            }

            // Beneficios
            List<String> beneficios = membresia.getBeneficios();
            if (beneficios != null) {
                for (String b : beneficios) {
                    String bl = b.toLowerCase();
                    if (bl.contains("acceso") || bl.contains("instalacion"))
                        jCheckBoxAcceso.setSelected(true);
                    if (bl.contains("nutrici") || bl.contains("centro"))
                        jCheckBoxCentro.setSelected(true);
                    if (bl.contains("progreso") || bl.contains("físico") || bl.contains("fisico"))
                        jCheckBoxProgreso.setSelected(true);
                    if (bl.contains("curso"))
                        jCheckBoxCursos.setSelected(true);
                    if (bl.contains("musical") || bl.contains("ambiente"))
                        jCheckBoxAmbiente.setSelected(true);
                }
            }

            // Pre-llenar fechas desde el periodo
            PeriodoMembresiaDTO periodo = control.obtenerPeriodoDeMembresia(idMembresia);
            if (periodo != null) {
                if (periodo.getFechaInicio() != null) {
                    dateChooserInicio.setDate(Date.from(periodo.getFechaInicio().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                }
                if (periodo.getFechaFin() != null) {
                    dateChooserFin.setDate(Date.from(periodo.getFechaFin().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                }
            }

        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar membresía: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
     private void configurarCheckBoxes() {
        chkActiva.addActionListener(e -> {
            if (chkActiva.isSelected()) {
                chkNoActiva.setSelected(false);
            }
        });
        chkNoActiva.addActionListener(e -> {
            if (chkNoActiva.isSelected()) {
                chkActiva.setSelected(false);
            }
        });
      
     
    }   
    
    private LocalDate obtenerFecha(JDateChooser dateChooser) {
        if (dateChooser.getDate() == null) 
            return null;
        return dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jPanel1 = new javax.swing.JPanel();
        lblTitulo4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane5 = new javax.swing.JScrollPane();
        lblTitulo = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        textAreaInstalaciones1 = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        textAreaInstalaciones2 = new javax.swing.JTextArea();
        txtNombre = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane8 = new javax.swing.JScrollPane();
        textAreaInstalaciones3 = new javax.swing.JTextArea();
        jScrollPane9 = new javax.swing.JScrollPane();
        textAreaInstalaciones4 = new javax.swing.JTextArea();
        jScrollPane10 = new javax.swing.JScrollPane();
        textAreaInstalaciones5 = new javax.swing.JTextArea();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane11 = new javax.swing.JScrollPane();
        txtAreaBeneficios = new javax.swing.JTextArea();
        jCheckBoxAcceso = new javax.swing.JCheckBox();
        jCheckBoxCentro = new javax.swing.JCheckBox();
        jCheckBoxProgreso = new javax.swing.JCheckBox();
        jCheckBoxCursos = new javax.swing.JCheckBox();
        jCheckBoxAmbiente = new javax.swing.JCheckBox();
        jSeparator4 = new javax.swing.JSeparator();
        jScrollPane12 = new javax.swing.JScrollPane();
        textAreaInstalaciones7 = new javax.swing.JTextArea();
        chkActiva = new javax.swing.JCheckBox();
        chkNoActiva = new javax.swing.JCheckBox();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        dateChooserInicio = new com.toedter.calendar.JDateChooser();
        dateChooserFin = new com.toedter.calendar.JDateChooser();
        jScrollPane13 = new javax.swing.JScrollPane();
        lblImagen = new javax.swing.JTextArea();
        btnSeleccionarImagen = new javax.swing.JButton();
        lblVistaPrevia = new javax.swing.JLabel();

        jScrollPane1.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(18, 18, 18));

        lblTitulo4.setBackground(new java.awt.Color(255, 255, 255));
        lblTitulo4.setFont(new java.awt.Font("Arial", 3, 36)); // NOI18N
        lblTitulo4.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo4.setText("Admin - Membresias");

        jSeparator1.setBackground(new java.awt.Color(225, 6, 0));
        jSeparator1.setForeground(new java.awt.Color(225, 6, 0));

        jScrollPane5.setBorder(null);
        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        lblTitulo.setEditable(false);
        lblTitulo.setBackground(new java.awt.Color(18, 18, 18));
        lblTitulo.setColumns(20);
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setRows(5);
        lblTitulo.setText("Crear Membresia");
        jScrollPane5.setViewportView(lblTitulo);

        jScrollPane6.setBorder(null);
        jScrollPane6.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane6.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        textAreaInstalaciones1.setEditable(false);
        textAreaInstalaciones1.setBackground(new java.awt.Color(18, 18, 18));
        textAreaInstalaciones1.setColumns(20);
        textAreaInstalaciones1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        textAreaInstalaciones1.setForeground(new java.awt.Color(255, 255, 255));
        textAreaInstalaciones1.setRows(5);
        textAreaInstalaciones1.setText("Precio:");
        jScrollPane6.setViewportView(textAreaInstalaciones1);

        jScrollPane7.setBorder(null);
        jScrollPane7.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane7.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        textAreaInstalaciones2.setEditable(false);
        textAreaInstalaciones2.setBackground(new java.awt.Color(18, 18, 18));
        textAreaInstalaciones2.setColumns(20);
        textAreaInstalaciones2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        textAreaInstalaciones2.setForeground(new java.awt.Color(255, 255, 255));
        textAreaInstalaciones2.setRows(5);
        textAreaInstalaciones2.setText("Nombre:");
        jScrollPane7.setViewportView(textAreaInstalaciones2);

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        jSeparator2.setBackground(new java.awt.Color(225, 6, 0));
        jSeparator2.setForeground(new java.awt.Color(225, 6, 0));

        jScrollPane8.setBorder(null);
        jScrollPane8.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane8.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        textAreaInstalaciones3.setEditable(false);
        textAreaInstalaciones3.setBackground(new java.awt.Color(18, 18, 18));
        textAreaInstalaciones3.setColumns(20);
        textAreaInstalaciones3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        textAreaInstalaciones3.setForeground(new java.awt.Color(255, 255, 255));
        textAreaInstalaciones3.setRows(5);
        textAreaInstalaciones3.setText("Fecha Vencimiento");
        jScrollPane8.setViewportView(textAreaInstalaciones3);

        jScrollPane9.setBorder(null);
        jScrollPane9.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane9.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        textAreaInstalaciones4.setEditable(false);
        textAreaInstalaciones4.setBackground(new java.awt.Color(18, 18, 18));
        textAreaInstalaciones4.setColumns(20);
        textAreaInstalaciones4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        textAreaInstalaciones4.setForeground(new java.awt.Color(255, 255, 255));
        textAreaInstalaciones4.setRows(5);
        textAreaInstalaciones4.setText("Fecha Inicio:");
        jScrollPane9.setViewportView(textAreaInstalaciones4);

        jScrollPane10.setBorder(null);
        jScrollPane10.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane10.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        textAreaInstalaciones5.setEditable(false);
        textAreaInstalaciones5.setBackground(new java.awt.Color(18, 18, 18));
        textAreaInstalaciones5.setColumns(20);
        textAreaInstalaciones5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        textAreaInstalaciones5.setForeground(new java.awt.Color(255, 255, 255));
        textAreaInstalaciones5.setRows(5);
        textAreaInstalaciones5.setText("Fecha Fin:");
        jScrollPane10.setViewportView(textAreaInstalaciones5);

        jSeparator3.setBackground(new java.awt.Color(225, 6, 0));
        jSeparator3.setForeground(new java.awt.Color(225, 6, 0));

        jScrollPane11.setBorder(null);
        jScrollPane11.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane11.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtAreaBeneficios.setEditable(false);
        txtAreaBeneficios.setBackground(new java.awt.Color(18, 18, 18));
        txtAreaBeneficios.setColumns(20);
        txtAreaBeneficios.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtAreaBeneficios.setForeground(new java.awt.Color(255, 255, 255));
        txtAreaBeneficios.setRows(5);
        txtAreaBeneficios.setText("Beneficios");
        jScrollPane11.setViewportView(txtAreaBeneficios);

        jCheckBoxAcceso.setBackground(new java.awt.Color(18, 18, 18));
        jCheckBoxAcceso.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBoxAcceso.setText("Acceso Instalaciones");
        jCheckBoxAcceso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxAccesoActionPerformed(evt);
            }
        });

        jCheckBoxCentro.setBackground(new java.awt.Color(18, 18, 18));
        jCheckBoxCentro.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBoxCentro.setText("Centro de Nutrición");

        jCheckBoxProgreso.setBackground(new java.awt.Color(18, 18, 18));
        jCheckBoxProgreso.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBoxProgreso.setText("Progreso Físico");

        jCheckBoxCursos.setBackground(new java.awt.Color(18, 18, 18));
        jCheckBoxCursos.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBoxCursos.setText("Cursos Especiales");

        jCheckBoxAmbiente.setBackground(new java.awt.Color(18, 18, 18));
        jCheckBoxAmbiente.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBoxAmbiente.setText("Ambiente Musical Dinámico");

        jSeparator4.setBackground(new java.awt.Color(225, 6, 0));
        jSeparator4.setForeground(new java.awt.Color(225, 6, 0));

        jScrollPane12.setBorder(null);
        jScrollPane12.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane12.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        textAreaInstalaciones7.setEditable(false);
        textAreaInstalaciones7.setBackground(new java.awt.Color(18, 18, 18));
        textAreaInstalaciones7.setColumns(20);
        textAreaInstalaciones7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        textAreaInstalaciones7.setForeground(new java.awt.Color(255, 255, 255));
        textAreaInstalaciones7.setRows(5);
        textAreaInstalaciones7.setText("Estado");
        jScrollPane12.setViewportView(textAreaInstalaciones7);

        chkActiva.setBackground(new java.awt.Color(18, 18, 18));
        chkActiva.setForeground(new java.awt.Color(255, 255, 255));
        chkActiva.setText("Activa");

        chkNoActiva.setBackground(new java.awt.Color(18, 18, 18));
        chkNoActiva.setForeground(new java.awt.Color(255, 255, 255));
        chkNoActiva.setText("No Activa");

        btnGuardar.setBackground(new java.awt.Color(255, 0, 51));
        btnGuardar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(255, 0, 51));
        btnCancelar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        dateChooserInicio.setBackground(new java.awt.Color(102, 102, 102));
        dateChooserInicio.setForeground(new java.awt.Color(102, 102, 102));
        dateChooserInicio.setToolTipText("");

        dateChooserFin.setBackground(new java.awt.Color(18, 18, 18));

        jScrollPane13.setBorder(null);
        jScrollPane13.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane13.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        lblImagen.setEditable(false);
        lblImagen.setBackground(new java.awt.Color(18, 18, 18));
        lblImagen.setColumns(20);
        lblImagen.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblImagen.setForeground(new java.awt.Color(255, 255, 255));
        lblImagen.setRows(5);
        lblImagen.setText("Imagen");
        jScrollPane13.setViewportView(lblImagen);

        btnSeleccionarImagen.setBackground(new java.awt.Color(51, 51, 51));
        btnSeleccionarImagen.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnSeleccionarImagen.setForeground(new java.awt.Color(255, 255, 255));
        btnSeleccionarImagen.setText("Seleccionar Imagen");
        btnSeleccionarImagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnSeleccionarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarImagenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(199, 199, 199)
                                        .addComponent(lblTitulo4))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(33, 33, 33)
                                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 58, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(dateChooserFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jCheckBoxCentro)
                                            .addComponent(jCheckBoxAcceso)
                                            .addComponent(jCheckBoxProgreso)
                                            .addComponent(jCheckBoxCursos)
                                            .addComponent(jCheckBoxAmbiente)))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(dateChooserInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(chkActiva, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)
                                        .addComponent(chkNoActiva))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(35, 35, 35)
                                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 392, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombre)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                .addGap(128, 128, 128)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSeleccionarImagen, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                    .addComponent(lblVistaPrevia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lblTitulo4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                            .addComponent(txtNombre)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblVistaPrevia, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSeleccionarImagen, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txtPrecio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(dateChooserInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateChooserFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxAcceso)
                .addGap(18, 18, 18)
                .addComponent(jCheckBoxCentro)
                .addGap(18, 18, 18)
                .addComponent(jCheckBoxProgreso)
                .addGap(18, 18, 18)
                .addComponent(jCheckBoxCursos)
                .addGap(18, 18, 18)
                .addComponent(jCheckBoxAmbiente)
                .addGap(18, 18, 18)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(chkActiva, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(chkNoActiva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void jCheckBoxAccesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxAccesoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxAccesoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // Validar nombre 
        String nombre = txtNombre.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar precio 
        String precioTexto = txtPrecio.getText().trim();
        if (precioTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El precio es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
      
        Float precio;
        try {
            precio = Float.parseFloat(precioTexto);
            if (precio <= 0) {
                JOptionPane.showMessageDialog(this, "El precio debe ser mayor a 0", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El precio debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar beneficios 
        if (!jCheckBoxAcceso.isSelected() && !jCheckBoxCentro.isSelected() && !jCheckBoxProgreso.isSelected() && !jCheckBoxCursos.isSelected() && !jCheckBoxAmbiente.isSelected()) {
            JOptionPane.showMessageDialog(this, "Selecciona al menos un beneficio", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar estado 
        if (!chkActiva.isSelected() && !chkNoActiva.isSelected()) {
            JOptionPane.showMessageDialog(this, "Selecciona el estado de la membresía", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar fechas 
        LocalDate fechaInicio = obtenerFecha(dateChooserInicio);
        LocalDate fechaFin = obtenerFecha(dateChooserFin);

        if (fechaInicio == null) {
            JOptionPane.showMessageDialog(this, "Selecciona la fecha de inicio", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (fechaFin == null) {
            JOptionPane.showMessageDialog(this, "Selecciona la fecha fin", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!fechaFin.isAfter(fechaInicio)) {
            JOptionPane.showMessageDialog(this, "La fecha fin debe ser posterior a la fecha inicio", "Error", JOptionPane.ERROR_MESSAGE);
            return;
       } 

        // Armar lista de beneficios 
        List<String> beneficios = new ArrayList<>();
        if (jCheckBoxAcceso.isSelected()) 
            beneficios.add("Acceso Instalaciones");
        if (jCheckBoxCentro.isSelected())
            beneficios.add("Centro de Nutrición");
        if (jCheckBoxProgreso.isSelected()) 
            beneficios.add("Progreso Físico");
        if (jCheckBoxCursos.isSelected()) 
            beneficios.add("Cursos Especiales");
        if (jCheckBoxAmbiente.isSelected()) 
            beneficios.add("Ambiente Musical Dinámico");

        // Estado 
        String estado = chkActiva.isSelected() ? "ACTIVA" : "NO_ACTIVA";

        try {
            if (idMembresia == null) {
              // CREAR 
              ImagenDTO imagenDTO = null;
              if (imagenSeleccionada != null) {
                  imagenDTO = new ImagenDTO(imagenSeleccionada,(long) imagenSeleccionada.length
                  );
              }

              NuevaMembresiaDTO dto = new NuevaMembresiaDTO(nombre, precio, estado, beneficios, imagenDTO, fechaInicio, fechaFin);
              control.agregarMembresia(dto);
              JOptionPane.showMessageDialog(this, "Membresía creada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                
              // EDITAR 
              EditarMembresiaDTO dto = new EditarMembresiaDTO(idMembresia, nombre, precio, estado, beneficios, fechaInicio, fechaFin);
              control.editarMembresia(idMembresia, dto);
              JOptionPane.showMessageDialog(this, "Membresía editada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
              dispose();
              control.mostrarGestionMembresias();
            } catch (NegocioException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
       dispose();
       control.mostrarGestionMembresias();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSeleccionarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarImagenActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar imagen");
        fileChooser.setFileFilter(new FileNameExtensionFilter( "Imágenes (jpg, png, gif)", "jpg", "jpeg", "png", "gif"));

        int resultado = fileChooser.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            try {
                imagenSeleccionada = Files.readAllBytes(archivo.toPath());
                nombreImagenSeleccionada = archivo.getName();
                
                ImageIcon iconoOriginal = new ImageIcon(imagenSeleccionada);
                Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
                lblVistaPrevia.setIcon(new ImageIcon(imagenEscalada));
                lblVistaPrevia.setText("");

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al leer la imagen", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnSeleccionarImagenActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSeleccionarImagen;
    private javax.swing.JCheckBox chkActiva;
    private javax.swing.JCheckBox chkNoActiva;
    private com.toedter.calendar.JDateChooser dateChooserFin;
    private com.toedter.calendar.JDateChooser dateChooserInicio;
    private javax.swing.JCheckBox jCheckBoxAcceso;
    private javax.swing.JCheckBox jCheckBoxAmbiente;
    private javax.swing.JCheckBox jCheckBoxCentro;
    private javax.swing.JCheckBox jCheckBoxCursos;
    private javax.swing.JCheckBox jCheckBoxProgreso;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextArea lblImagen;
    private javax.swing.JTextArea lblTitulo;
    private javax.swing.JLabel lblTitulo4;
    private javax.swing.JLabel lblVistaPrevia;
    private javax.swing.JTextArea textAreaInstalaciones1;
    private javax.swing.JTextArea textAreaInstalaciones2;
    private javax.swing.JTextArea textAreaInstalaciones3;
    private javax.swing.JTextArea textAreaInstalaciones4;
    private javax.swing.JTextArea textAreaInstalaciones5;
    private javax.swing.JTextArea textAreaInstalaciones7;
    private javax.swing.JTextArea txtAreaBeneficios;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
