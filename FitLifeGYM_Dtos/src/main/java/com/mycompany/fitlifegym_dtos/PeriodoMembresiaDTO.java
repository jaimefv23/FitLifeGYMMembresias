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
public class PeriodoMembresiaDTO {
    
    private Long idPeriodo;
    private Long idMembresia;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Boolean vigente;

    public PeriodoMembresiaDTO() {
    }

    public PeriodoMembresiaDTO(Long idPeriodo, Long idMembresia, LocalDate fechaInicio, LocalDate fechaFin, Boolean vigente) {
        this.idPeriodo = idPeriodo;
        this.idMembresia = idMembresia;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.vigente = vigente;
    }

    public Long getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Long idPeriodo) {
        this.idPeriodo = idPeriodo;
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

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Boolean getVigente() {
        return vigente;
    }

    public void setVigente(Boolean vigente) {
        this.vigente = vigente;
    }
}
