/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fitlifegym_dtos;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Jaime
 */
public class ReporteMembresiaDTO {
    
    private String idReporte;
    private String idMembresia;
    private String tipo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Integer totalUsuarios;
    private Double totalVentas;
    private LocalDate fechaGeneracion;
    private List<EntradaReporteDTO> membresias;

    public ReporteMembresiaDTO() {
    }

    public ReporteMembresiaDTO(String idReporte, String idMembresia, String tipo, LocalDate fechaInicio, LocalDate fechaFin, Integer totalUsuarios, Double totalVentas, LocalDate fechaGeneracion, List<EntradaReporteDTO> membresias) {
        this.idReporte = idReporte;
        this.idMembresia = idMembresia;
        this.tipo = tipo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.totalUsuarios = totalUsuarios;
        this.totalVentas = totalVentas;
        this.fechaGeneracion = fechaGeneracion;
        this.membresias = membresias;
    }

    

    public String getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(String idReporte) {
        this.idReporte = idReporte;
    }

    public String getIdMembresia() {
        return idMembresia;
    }

    public void setIdMembresia(String idMembresia) {
        this.idMembresia = idMembresia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getTotalUsuarios() {
        return totalUsuarios;
    }

    public void setTotalUsuarios(Integer totalUsuarios) {
        this.totalUsuarios = totalUsuarios;
    }

    public Double getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(Double totalVentas) {
        this.totalVentas = totalVentas;
    }

    public LocalDate getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(LocalDate fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    } 

    public List<EntradaReporteDTO> getMembresias() {
        return membresias;
    }

    public void setMembresias(List<EntradaReporteDTO> membresias) {
        this.membresias = membresias;
    }
    
    
}
