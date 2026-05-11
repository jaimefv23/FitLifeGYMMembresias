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
public class SuscripcionDTO {
    
    private Long idSuscripcion;
    private Long idUsuario;
    private Long idMembresia;
    private Float precioPagado;
    private LocalDate fechaInicio;
    private LocalDate fechaVencimiento;
    private String estado;

    public SuscripcionDTO() {
    }

    public SuscripcionDTO(Long idSuscripcion, Long idUsuario, Long idMembresia, Float precioPagado, LocalDate fechaInicio, LocalDate fechaVencimiento, String estado) {
        this.idSuscripcion = idSuscripcion;
        this.idUsuario = idUsuario;
        this.idMembresia = idMembresia;
        this.precioPagado = precioPagado;
        this.fechaInicio = fechaInicio;
        this.fechaVencimiento = fechaVencimiento;
        this.estado = estado;
    }

    public Long getIdSuscripcion() {
        return idSuscripcion;
    }

    public void setIdSuscripcion(Long idSuscripcion) {
        this.idSuscripcion = idSuscripcion;
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

    public Float getPrecioPagado() {
        return precioPagado;
    }

    public void setPrecioPagado(Float precioPagado) {
        this.precioPagado = precioPagado;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
