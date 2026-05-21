/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.time.LocalDate;


/**
 *
 * @author Jaime
 */
public class PeriodoMembresia {
    
    private String idPeriodo;
    private String idMembresia;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Boolean vigente;

    public PeriodoMembresia() {
    }

    public PeriodoMembresia(String idMembresia, LocalDate fechaInicio, LocalDate fechaFin, Boolean vigente) {
        this.idMembresia = idMembresia;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.vigente = vigente;
    }

    public PeriodoMembresia(String idPeriodo, String idMembresia, LocalDate fechaInicio, LocalDate fechaFin, Boolean vigente) {
        this.idPeriodo = idPeriodo;
        this.idMembresia = idMembresia;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.vigente = vigente;
    }

    public String getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(String idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public String getIdMembresia() {
        return idMembresia;
    }

    public void setIdMembresia(String idMembresia) {
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

    @Override
    public String toString() {
        return "PeriodoMembresia{" + "idPeriodo=" + idPeriodo + ", idMembresia=" + idMembresia + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", vigente=" + vigente + '}';
    }
}
