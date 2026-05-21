/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.fitlifegym_presentacion;

import ControlMembresias.ControlMembresias;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.mycompany.fitlifegym_dtos.EntradaReporteDTO;
import com.mycompany.fitlifegym_dtos.ReporteMembresiaDTO;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jaime
 */
public class PantallaReportePeriodo extends javax.swing.JFrame {

    private ControlMembresias control;
    private ReporteMembresiaDTO reporte;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    
    /**
     * Creates new form PantallaReportePeriodo
     */
    public PantallaReportePeriodo() {
        initComponents();
    }
    
    public PantallaReportePeriodo(ControlMembresias control, ReporteMembresiaDTO reporte, LocalDate fechaInicio, LocalDate fechaFin) {
        this.control = control;
        this.reporte = reporte;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        configurarTabla();
        cargarReporte();
    }
    
    private void configurarTabla() {
        tblReporte.setRowHeight(60);
        tblReporte.setBackground(new Color(44, 44, 44));
        tblReporte.setForeground(Color.WHITE);
        tblReporte.getTableHeader().setBackground(new Color(225, 6, 0));
        tblReporte.getTableHeader().setForeground(Color.WHITE);
        
        DefaultTableCellRenderer renderTexto = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    setBackground(isSelected ? new Color(225, 6, 0) : new Color(44, 44, 44));
                    setForeground(Color.WHITE);
                    setHorizontalAlignment(SwingConstants.CENTER);
                    return this;
                }
            };

        // imagen columna 0
        tblReporte.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = new JLabel();
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setBackground(isSelected ? new Color(225, 6, 0) : new Color(44, 44, 44));
                label.setOpaque(true);
                if (value instanceof ImageIcon) {
                    label.setIcon((ImageIcon) value);
                } else {
                    label.setText("Sin imagen");
                    label.setForeground(Color.WHITE);
                }
                return label;
            }
        });
        
        tblReporte.getColumnModel().getColumn(1).setCellRenderer(renderTexto);
        tblReporte.getColumnModel().getColumn(1).setPreferredWidth(100);

        tblReporte.getColumnModel().getColumn(2).setCellRenderer(renderTexto);
        tblReporte.getColumnModel().getColumn(2).setPreferredWidth(150);

        tblReporte.getColumnModel().getColumn(3).setCellRenderer(renderTexto);

        tblReporte.getColumnModel().getColumn(4).setCellRenderer(renderTexto);
    }

    private void cargarReporte() {
        DefaultTableModel modelo = (DefaultTableModel) tblReporte.getModel();
        modelo.setRowCount(0);

        if (reporte == null || reporte.getMembresias() == null || reporte.getMembresias().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay membresías disponibles en el período seleccionado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        for (EntradaReporteDTO entrada : reporte.getMembresias()) {
            // Imagen
            ImageIcon icono = null;
            if (entrada.getImagen() != null && entrada.getImagen().getImagen() != null) {
                ImageIcon original = new ImageIcon(entrada.getImagen().getImagen());
                Image escalada = original.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                icono = new ImageIcon(escalada);
            }

            modelo.addRow(new Object[]{
                icono,
                entrada.getNombre(),
                fechaInicio + " → " + fechaFin,
                entrada.getUsuarios(),
                "$" + entrada.getTotalGenerado()
            });
        }
    }
    

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        lblTitulo4 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        lblTitulo = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblReporte = new javax.swing.JTable();
        btnGenerarPDF = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(18, 18, 18));

        jPanel2.setBackground(new java.awt.Color(18, 18, 18));

        jSeparator2.setBackground(new java.awt.Color(225, 6, 0));
        jSeparator2.setForeground(new java.awt.Color(225, 6, 0));

        lblTitulo4.setBackground(new java.awt.Color(255, 255, 255));
        lblTitulo4.setFont(new java.awt.Font("Arial", 3, 36)); // NOI18N
        lblTitulo4.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo4.setText("FITLIFE GYM");

        jScrollPane5.setBorder(null);
        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        lblTitulo.setEditable(false);
        lblTitulo.setBackground(new java.awt.Color(18, 18, 18));
        lblTitulo.setColumns(20);
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setRows(5);
        lblTitulo.setText("Reportes General");
        jScrollPane5.setViewportView(lblTitulo);

        tblReporte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Imagen", "Nombre", "Fechas", "Usuarios", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblReporte);
        if (tblReporte.getColumnModel().getColumnCount() > 0) {
            tblReporte.getColumnModel().getColumn(0).setResizable(false);
            tblReporte.getColumnModel().getColumn(1).setResizable(false);
            tblReporte.getColumnModel().getColumn(2).setResizable(false);
            tblReporte.getColumnModel().getColumn(3).setResizable(false);
            tblReporte.getColumnModel().getColumn(4).setResizable(false);
        }

        btnGenerarPDF.setBackground(new java.awt.Color(255, 0, 51));
        btnGenerarPDF.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnGenerarPDF.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerarPDF.setText("Generar PDF");
        btnGenerarPDF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnGenerarPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarPDFActionPerformed(evt);
            }
        });

        btnRegresar.setBackground(new java.awt.Color(51, 51, 51));
        btnRegresar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresar.setText("Regresar");
        btnRegresar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 755, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(319, 319, 319)
                                .addComponent(btnGenerarPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 41, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 654, Short.MAX_VALUE)
                                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblTitulo4)
                .addGap(305, 305, 305))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(lblTitulo4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGenerarPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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

    private void btnGenerarPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarPDFActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar PDF");
        fileChooser.setSelectedFile(new File("ReportePeriodo_" + LocalDate.now() + ".pdf"));

        int resultado = fileChooser.showSaveDialog(this);
        if (resultado != JFileChooser.APPROVE_OPTION) 
            return;

        String ruta = fileChooser.getSelectedFile().getAbsolutePath();
        if (!ruta.endsWith(".pdf")) ruta += ".pdf";

        try {
            BaseColor colorFondo = new BaseColor(44, 44, 44);
            BaseColor colorHeader = new BaseColor(225, 6, 0);
            BaseColor colorFondoDoc = new BaseColor(18, 18, 18);
            
            Document documento = new Document();
            PdfWriter writer = PdfWriter.getInstance(documento, new FileOutputStream(ruta));
            documento.open();
            
            // Fondo oscuro
            PdfContentByte canvas = writer.getDirectContentUnder();
            canvas.setColorFill(colorFondoDoc);
            canvas.rectangle(0, 0, documento.getPageSize().getWidth(), documento.getPageSize().getHeight());
            canvas.fill();

            // Título
            Font fTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22, BaseColor.BLACK);
            Paragraph titulo = new Paragraph("FitLife GYM — Reporte por Período", fTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setSpacingAfter(5);
            documento.add(titulo);
            
            // Separador
            LineSeparator linea = new LineSeparator(2, 100, colorHeader, Element.ALIGN_CENTER, 0);
            documento.add(new Chunk(linea));

            // Info del reporte
            Font fInfo = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.GRAY);
            documento.add(new Paragraph("Período: " + fechaInicio + " al " + fechaFin, fInfo));
            documento.add(new Paragraph("Fecha de Descarga: " + LocalDate.now(), fInfo));
            documento.add(new Paragraph("Total usuarios: " + reporte.getTotalUsuarios(), fInfo));
            documento.add(new Paragraph("Total ventas: $" + reporte.getTotalVentas(), fInfo));
            documento.add(new Paragraph("\n"));

            // Tabla PDF
            PdfPTable tabla = new PdfPTable(5);
            tabla.setWidthPercentage(100);
            tabla.setWidths(new float[]{2f, 3f, 5f, 2f, 2f});

            // Encabezados
            Font fHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 13, BaseColor.WHITE);
            String[] headers = {"Imagen", "Nombre", "Fechas", "Usuarios", "Total"};
            for (String h : headers) {
                PdfPCell celda = new PdfPCell(new Phrase(h, fHeader));
                celda.setBackgroundColor(new BaseColor(225, 6, 0));
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setPadding(8);
                tabla.addCell(celda);
            }

            Font fCelda = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.WHITE);

            for (EntradaReporteDTO entrada : reporte.getMembresias()) {
               
                // Imagen
                PdfPCell celdaImagen = new PdfPCell();
                celdaImagen.setFixedHeight(60);
                celdaImagen.setBackgroundColor(colorFondo);
                celdaImagen.setHorizontalAlignment(Element.ALIGN_CENTER);
                celdaImagen.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celdaImagen.setBorderColor(new BaseColor(60, 60, 60));
                if (entrada.getImagen() != null && entrada.getImagen().getImagen() != null) {
                    com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance(entrada.getImagen().getImagen());
                    img.scaleToFit(50, 50);
                    celdaImagen.addElement(img);
                } else {
                    celdaImagen.addElement(new Phrase("Sin imagen", fCelda));
                }
                celdaImagen.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla.addCell(celdaImagen);

                // Nombre
                PdfPCell celdaNombre = new PdfPCell(new Phrase(entrada.getNombre(), fCelda));
                celdaNombre.setBackgroundColor(colorFondo);
                celdaNombre.setHorizontalAlignment(Element.ALIGN_CENTER);
                celdaNombre.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celdaNombre.setPadding(8);
                celdaNombre.setBorderColor(new BaseColor(60, 60, 60));
                tabla.addCell(celdaNombre);

                // Fechas
                PdfPCell celdaFechas = new PdfPCell(new Phrase(fechaInicio + " ---→ " + fechaFin, fCelda));
                celdaFechas.setBackgroundColor(colorFondo);
                celdaFechas.setHorizontalAlignment(Element.ALIGN_CENTER);
                celdaFechas.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celdaFechas.setPadding(8);
                celdaFechas.setBorderColor(new BaseColor(60, 60, 60));
                tabla.addCell(celdaFechas);

                // Usuarios
                PdfPCell celdaUsuarios = new PdfPCell(new Phrase(String.valueOf(entrada.getUsuarios()), fCelda));
                celdaUsuarios.setBackgroundColor(colorFondo);
                celdaUsuarios.setHorizontalAlignment(Element.ALIGN_CENTER);
                celdaUsuarios.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celdaUsuarios.setPadding(8);
                celdaUsuarios.setBorderColor(new BaseColor(60, 60, 60));
                tabla.addCell(celdaUsuarios);

                // Total
                PdfPCell celdaTotal = new PdfPCell(new Phrase("$" + entrada.getTotalGenerado(), fCelda));
                celdaTotal.setBackgroundColor(colorFondo);
                celdaTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
                celdaTotal.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celdaTotal.setPadding(8);
                celdaTotal.setBorderColor(new BaseColor(60, 60, 60));
                tabla.addCell(celdaTotal);

                }

                documento.add(tabla);
                documento.close();

                JOptionPane.showMessageDialog(this, "PDF generado correctamente en:\n" + ruta, "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al generar PDF: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGenerarPDFActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        dispose();
        control.mostrarPeriodosDeReporte();
    }//GEN-LAST:event_btnRegresarActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerarPDF;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextArea lblTitulo;
    private javax.swing.JLabel lblTitulo4;
    private javax.swing.JTable tblReporte;
    // End of variables declaration//GEN-END:variables

}