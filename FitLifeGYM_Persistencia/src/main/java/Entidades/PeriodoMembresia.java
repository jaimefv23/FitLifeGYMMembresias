/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.time.LocalDate;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

/**
 *
 * @author Jaime
 */
public class PeriodoMembresia {
    
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private Long idPeriodo;
    private Long idMembresia;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Boolean vigente;

    public PeriodoMembresia() {
    }

    public PeriodoMembresia(Long idMembresia, LocalDate fechaInicio, LocalDate fechaFin, Boolean vigente) {
        this.idMembresia = idMembresia;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.vigente = vigente;
    }

    public PeriodoMembresia(Long idPeriodo, Long idMembresia, LocalDate fechaInicio, LocalDate fechaFin, Boolean vigente) {
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

    @Override
    public String toString() {
        return "PeriodoMembresia{" + "idPeriodo=" + idPeriodo + ", idMembresia=" + idMembresia + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", vigente=" + vigente + '}';
    }
}
