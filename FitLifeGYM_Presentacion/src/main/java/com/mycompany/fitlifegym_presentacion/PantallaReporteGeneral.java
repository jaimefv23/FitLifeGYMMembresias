/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.fitlifegym_presentacion;

import BOS.NegocioException;
import ControlMembresias.ControlMembresias;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.mycompany.fitlifegym_dtos.MembresiaDTO;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;
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
public class PantallaReporteGeneral extends javax.swing.JFrame {

    private ControlMembresias control;
    private List<MembresiaDTO> membresias;

    /**
     * Creates new form PantallaReporteGeneral
     */
    public PantallaReporteGeneral() {
        initComponents();
    }

    public PantallaReporteGeneral(ControlMembresias control) {
        this.control = control;
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
        tblReporte.setFont(new java.awt.Font("Arial", 0, 14));
        tblReporte.setSelectionBackground(new Color(225, 6, 0));
        tblReporte.setSelectionForeground(Color.WHITE);
        tblReporte.getTableHeader().setBackground(new Color(225, 6, 0));
        tblReporte.getTableHeader().setForeground(Color.WHITE);
        tblReporte.getTableHeader().setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        
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

        tblReporte.getColumnModel().getColumn(3).setCellRenderer(renderTexto);
    }
    
    private void cargarReporte() {
        try {
            membresias = control.listarMembresias();
            DefaultTableModel modelo = (DefaultTableModel) tblReporte.getModel();
            modelo.setRowCount(0);

            for (MembresiaDTO m : membresias) {
                ImageIcon icono = null;
                if (m.getImagen() != null && m.getImagen().getImagen() != null) {
                    ImageIcon original = new ImageIcon(m.getImagen().getImagen());
                    Image escalada = original.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                    icono = new ImageIcon(escalada);
                }

                // Contar usuarios suscritos
                int usuarios = 0;
                try {
                    usuarios = control.contarSuscripcionesActivas(m.getIdMembresia());
                } catch (NegocioException ex) {
                    usuarios = 0;
                }

                modelo.addRow(new Object[]{
                    icono,
                    m.getNombre(),
                    "$" + m.getPrecio(),
                    usuarios
                });
            }
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar reporte: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

        jPanel1 = new javax.swing.JPanel();
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
                "Imagen", "Nombre", "Precio", "Usuarios"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 755, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(319, 319, 319)
                                .addComponent(btnGenerarPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 41, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTitulo4)
                        .addGap(299, 299, 299))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarPDFActionPerformed

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar PDF");
        fileChooser.setSelectedFile(new File("ReporteGeneral_" + LocalDate.now() + ".pdf"));

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
            Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22, BaseColor.BLACK);
            Paragraph titulo = new Paragraph("FitLife GYM — Reporte General", fuenteTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setSpacingAfter(5);
            documento.add(titulo);
            
            // Separador
            LineSeparator linea = new LineSeparator(2, 100, colorHeader, Element.ALIGN_CENTER, 0);
            documento.add(new Chunk(linea));

            // Fecha generación
            Font fuenteFecha = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.GRAY);
            Paragraph fecha = new Paragraph("Fecha de Descarga: " + LocalDate.now(), fuenteFecha);
            fecha.setAlignment(Element.ALIGN_CENTER);
            fecha.setSpacingBefore(8);
            fecha.setSpacingAfter(15);
            documento.add(fecha);
            documento.add(new Paragraph("\n"));

            // Tabla PDF
            PdfPTable tabla = new PdfPTable(4);
            tabla.setWidthPercentage(100);
            tabla.setWidths(new float[]{2f, 4f, 2f, 2f});

            // Encabezados
            Font fuenteHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 13, BaseColor.WHITE);
            String[] headers = {"Imagen", "Nombre", "Precio", "Usuarios Activos"};
            for (String h : headers) {
                PdfPCell celda = new PdfPCell(new Phrase(h, fuenteHeader));
                celda.setBackgroundColor(colorHeader);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setPadding(8);
                tabla.addCell(celda);
            }

            // Filas
            Font fuenteCelda = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.WHITE);
            for (MembresiaDTO m : membresias) {
                // Imagen
                PdfPCell celdaImagen = new PdfPCell();
                celdaImagen.setFixedHeight(60);
                celdaImagen.setBackgroundColor(colorFondo);
                celdaImagen.setHorizontalAlignment(Element.ALIGN_CENTER);
                celdaImagen.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celdaImagen.setBorderColor(new BaseColor(60, 60, 60));
                if (m.getImagen() != null && m.getImagen().getImagen() != null) {
                    com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance(m.getImagen().getImagen());
                    img.scaleToFit(50, 50);
                    celdaImagen.addElement(img);
                } else {
                    celdaImagen.addElement(new Phrase("Sin imagen", fuenteCelda));
                }
                celdaImagen.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla.addCell(celdaImagen);

                // Nombre
                PdfPCell celdaNombre = new PdfPCell(new Phrase(m.getNombre(), fuenteCelda));
                celdaNombre.setBackgroundColor(colorFondo);
                celdaNombre.setHorizontalAlignment(Element.ALIGN_CENTER);
                celdaNombre.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celdaNombre.setPadding(8);
                celdaNombre.setBorderColor(new BaseColor(60, 60, 60));
                tabla.addCell(celdaNombre);

                // Precio
                PdfPCell celdaPrecio = new PdfPCell(new Phrase("$" + m.getPrecio(), fuenteCelda));
                celdaPrecio.setBackgroundColor(colorFondo);
                celdaPrecio.setHorizontalAlignment(Element.ALIGN_CENTER);
                celdaPrecio.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celdaPrecio.setPadding(8);
                celdaPrecio.setBorderColor(new BaseColor(60, 60, 60));
                tabla.addCell(celdaPrecio);

                // Usuarios
                int usuarios = 0;
                try {
                    usuarios = control.contarSuscripcionesActivas(m.getIdMembresia());
                } catch (NegocioException ex) { }
                PdfPCell celdaUsuarios = new PdfPCell(new Phrase(String.valueOf(usuarios), fuenteCelda));
                celdaUsuarios.setBackgroundColor(colorFondo);
                celdaUsuarios.setHorizontalAlignment(Element.ALIGN_CENTER);
                celdaUsuarios.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celdaUsuarios.setPadding(8);
                celdaUsuarios.setBorderColor(new BaseColor(60, 60, 60));
                tabla.addCell(celdaUsuarios);
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
        control.mostrarReportesMembresia();
    }//GEN-LAST:event_btnRegresarActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerarPDF;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextArea lblTitulo;
    private javax.swing.JLabel lblTitulo4;
    private javax.swing.JTable tblReporte;
    // End of variables declaration//GEN-END:variables
}
