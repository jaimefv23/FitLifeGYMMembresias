/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fitlifegym_dtos;

import java.time.LocalDate;

/**
 *
 * @author Jaime
 */
public class NuevaSuscripcionDTO {
    
    private Long idUsuario;
    private Long idMembresia;
    private LocalDate fechaInicio;
    private LocalDate fechaVencimiento;
    private Float precioPagado;

    public NuevaSuscripcionDTO() {
    }

    public NuevaSuscripcionDTO(Long idUsuario, Long idMembresia, LocalDate fechaInicio, LocalDate fechaVencimiento, Float precioPagado) {
        this.idUsuario = idUsuario;
        this.idMembresia = idMembresia;
        this.fechaInicio = fechaInicio;
        this.fechaVencimiento = fechaVencimiento;
        this.precioPagado = precioPagado;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdMembresia() {
        return idMembresia;
    }

    public void setIdMembresia(Long idMembresia) {
        this.idMembresia = idMembresia;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Float getPrecioPagado() {
        return precioPagado;
    }

    public void setPrecioPagado(Float precioPagado) {
        this.precioPagado = precioPagado;
    }
}
