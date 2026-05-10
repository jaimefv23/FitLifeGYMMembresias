/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fitlifegym_persistencia.entidades;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Jaime
 */
public class HistorialSuscripcionesMembresias {
    
    private Long idHistorialSuscripcion;
    private Long idUsuario;
    private Long idMembresia;
    private List<SuscripcionUsuarioMembresia> suscripciones;
    private Float precioPagado;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalDate fechaVencimiento;

    public HistorialSuscripcionesMembresias() {
    }

    public HistorialSuscripcionesMembresias(Long idUsuario, Long idMembresia, List<SuscripcionUsuarioMembresia> suscripciones, Float precioPagado, LocalDate fechaInicio, LocalDate fechaFin, LocalDate fechaVencimiento) {
        this.idUsuario = idUsuario;
        this.idMembresia = idMembresia;
        this.suscripciones = suscripciones;
        this.precioPagado = precioPagado;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.fechaVencimiento = fechaVencimiento;
    }

    public HistorialSuscripcionesMembresias(Long idHistorialSuscripcion, Long idUsuario, Long idMembresia, List<SuscripcionUsuarioMembresia> suscripciones, Float precioPagado, LocalDate fechaInicio, LocalDate fechaFin, LocalDate fechaVencimiento) {
        this.idHistorialSuscripcion = idHistorialSuscripcion;
        this.idUsuario = idUsuario;
        this.idMembresia = idMembresia;
        this.suscripciones = suscripciones;
        this.precioPagado = precioPagado;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.fechaVencimiento = fechaVencimiento;
    }

    public Long getIdHistorialSuscripcion() {
        return idHistorialSuscripcion;
    }

    public void setIdHistorialSuscripcion(Long idHistorialSuscripcion) {
        this.idHistorialSuscripcion = idHistorialSuscripcion;
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

    public List<SuscripcionUsuarioMembresia> getSuscripciones() {
        return suscripciones;
    }

    public void setSuscripciones(List<SuscripcionUsuarioMembresia> suscripciones) {
        this.suscripciones = suscripciones;
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

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public String toString() {
        return "HistorialSuscripcionesMembresias{" + "idHistorialSuscripcion=" + idHistorialSuscripcion + ", idUsuario=" + idUsuario + ", idMembresia=" + idMembresia + ", suscripciones=" + suscripciones + ", precioPagado=" + precioPagado + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", fechaVencimiento=" + fechaVencimiento + '}';
    }
    
    
}
