/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.fitlifegym_presentacion;

import BOS.NegocioException;
import ControlMembresias.ControlMembresias;
import com.mycompany.fitlifegym_dtos.MembresiaDTO;
import com.mycompany.fitlifegym_dtos.PeriodoMembresiaDTO;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
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
public class PantallaGestionMembresias extends javax.swing.JFrame {

    private ControlMembresias control;
    
    /**
     * Creates new form PantallaGestionMembresias
     */
    public PantallaGestionMembresias(ControlMembresias control) {
        this.control = control;
        this.setResizable(false);
        initComponents();
        this.setLocationRelativeTo(null);
        configurarTabla();
        cargarMembresias();
 
    }
    
    private void cargarMembresias() {
        try {
            List<MembresiaDTO> membresias = control.listarMembresias();
            DefaultTableModel modelo = (DefaultTableModel) tblMembresias.getModel();
            modelo.setRowCount(0); 
            
            for (MembresiaDTO m : membresias) {
                // Imagen
                ImageIcon icono = null;
                if (m.getImagen() != null && m.getImagen().getImagen() != null) {
                    ImageIcon original = new ImageIcon(m.getImagen().getImagen());
                    Image escalada = original.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                    icono = new ImageIcon(escalada);
                }

                // Vigencia
                String vigencia = "Sin periodo";
                try {
                    PeriodoMembresiaDTO periodo = control.obtenerPeriodoDeMembresia(m.getIdMembresia());
                    if (periodo != null) {
                        vigencia = periodo.getFechaInicio() + " → " + periodo.getFechaFin();
                    }
                } catch (NegocioException ex) {
                    vigencia = "Error";
                }

                // Contar usuarios suscritos
                int usuarios = 0;
                try {
                    usuarios = control.contarSuscripcionesActivas(m.getIdMembresia());
                } catch (NegocioException ex) {
                    usuarios = 0;
                }

                modelo.addRow(new Object[]{
                    m.getIdMembresia(),
                    icono,
                    m.getNombre(),
                    "$" + m.getPrecio(),
                    vigencia,
                    usuarios,
                    m.getEstado()
                });
            }
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar membresías: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void configurarTabla() {
        tblMembresias.setRowHeight(60);
        tblMembresias.setBackground(new Color(44, 44, 44));
        tblMembresias.setForeground(Color.WHITE);
        tblMembresias.setFont(new Font("Arial", 0, 14));
        tblMembresias.getTableHeader().setBackground(new Color(225, 6, 0));
        tblMembresias.getTableHeader().setForeground(Color.WHITE);
        tblMembresias.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        tblMembresias.getColumnModel().getColumn(0).setMinWidth(0);
        tblMembresias.getColumnModel().getColumn(0).setMaxWidth(0);
        tblMembresias.getColumnModel().getColumn(0).setWidth(0);
        
        tblMembresias.getColumnModel().getColumn(4).setPreferredWidth(150);
        
        
        
        tblMembresias.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer() {
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
        
        tblMembresias.getColumnModel().getColumn(2).setCellRenderer(renderTexto);
        tblMembresias.getColumnModel().getColumn(3).setCellRenderer(renderTexto);
        tblMembresias.getColumnModel().getColumn(4).setCellRenderer(renderTexto);
        tblMembresias.getColumnModel().getColumn(5).setCellRenderer(renderTexto);
        
        tblMembresias.getColumnModel().getColumn(6).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String estado = value != null ? value.toString() : "";
                setHorizontalAlignment(SwingConstants.CENTER);
                setForeground(Color.WHITE);
                setFont(new Font("Arial", Font.BOLD, 13));
                if ("ACTIVA".equals(estado)) {
                    setBackground(isSelected ? new Color(0, 150, 0) : new Color(0, 180, 0));
                } else {
                    setBackground(isSelected ? new Color(180, 0, 0) : new Color(225, 6, 0));
                }
                return this;
            }
        });
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        lblTitulo4 = new javax.swing.JLabel();
        lblTitulo5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMembresias = new javax.swing.JTable();
        btnCrear = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(18, 18, 18));

        jPanel3.setBackground(new java.awt.Color(18, 18, 18));

        jSeparator3.setBackground(new java.awt.Color(225, 6, 0));
        jSeparator3.setForeground(new java.awt.Color(225, 6, 0));

        lblTitulo4.setBackground(new java.awt.Color(255, 255, 255));
        lblTitulo4.setFont(new java.awt.Font("Arial", 3, 36)); // NOI18N
        lblTitulo4.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo4.setText("Admin - Membresias");

        lblTitulo5.setBackground(new java.awt.Color(255, 255, 255));
        lblTitulo5.setFont(new java.awt.Font("Arial", 3, 36)); // NOI18N
        lblTitulo5.setForeground(new java.awt.Color(255, 255, 255));

        tblMembresias.setBackground(new java.awt.Color(51, 51, 51));
        tblMembresias.setForeground(new java.awt.Color(204, 204, 0));
        tblMembresias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Imagen", "Nombre", "Precio", "Vigencia", "Usuarios", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMembresias.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(tblMembresias);
        if (tblMembresias.getColumnModel().getColumnCount() > 0) {
            tblMembresias.getColumnModel().getColumn(0).setResizable(false);
            tblMembresias.getColumnModel().getColumn(1).setResizable(false);
            tblMembresias.getColumnModel().getColumn(2).setResizable(false);
            tblMembresias.getColumnModel().getColumn(3).setResizable(false);
            tblMembresias.getColumnModel().getColumn(4).setResizable(false);
            tblMembresias.getColumnModel().getColumn(5).setResizable(false);
            tblMembresias.getColumnModel().getColumn(6).setResizable(false);
        }

        btnCrear.setBackground(new java.awt.Color(255, 0, 51));
        btnCrear.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnCrear.setForeground(new java.awt.Color(255, 255, 255));
        btnCrear.setText("Crear");
        btnCrear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        btnEditar.setBackground(new java.awt.Color(255, 0, 51));
        btnEditar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setText("Editar");
        btnEditar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(255, 0, 51));
        btnEliminar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnReportes.setBackground(new java.awt.Color(255, 0, 51));
        btnReportes.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnReportes.setForeground(new java.awt.Color(255, 255, 255));
        btnReportes.setText("Reportes");
        btnReportes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitulo5)
                .addGap(60, 60, 60)
                .addComponent(lblTitulo4)
                .addGap(365, 365, 365))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(btnReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 918, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(47, 71, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTitulo5, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitulo4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        btnVolver.setBackground(new java.awt.Color(255, 0, 51));
        btnVolver.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnVolver.setForeground(new java.awt.Color(255, 255, 255));
        btnVolver.setText(">");
        btnVolver.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(892, 892, 892)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 581, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 59, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 937, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        dispose();
        control.mostrarCrearMembresia();
    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int filaSeleccionada = tblMembresias.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una membresía de la tabla para editar", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String idMembresia = tblMembresias.getValueAt(filaSeleccionada, 0).toString();
        dispose();
        control.mostrarEditarMembresia(idMembresia);
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int filaSeleccionada = tblMembresias.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una membresía de la tabla para eliminar", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String idMembresia = tblMembresias.getValueAt(filaSeleccionada, 0).toString();
        String nombreMembresia = tblMembresias.getValueAt(filaSeleccionada, 2).toString();
        control.mostrarEliminarMembresia(idMembresia, nombreMembresia);
        dispose();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesActionPerformed
        dispose();
        control.mostrarReportesMembresia();
    }//GEN-LAST:event_btnReportesActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        dispose();
        control.mostrarMenuAdministrativo();
    }//GEN-LAST:event_btnVolverActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnVolver;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblTitulo4;
    private javax.swing.JLabel lblTitulo5;
    private javax.swing.JTable tblMembresias;
    // End of variables declaration//GEN-END:variables
}
