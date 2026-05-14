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
public class Suscripcion {
    
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private Long idSuscripcion;
    private Long idUsuario;
    private Long idMembresia;
    private LocalDate fechaInicio;
    private LocalDate fechaVencimiento;
    private Float precioPagado;
    private Estado estado;

    public Suscripcion() {
    }

    public Suscripcion(Long idUsuario, Long idMembresia, LocalDate fechaInicio, LocalDate fechaVencimiento, Float precioPagado, Estado estado) {
        this.idUsuario = idUsuario;
        this.idMembresia = idMembresia;
        this.fechaInicio = fechaInicio;
        this.fechaVencimiento = fechaVencimiento;
        this.precioPagado = precioPagado;
        this.estado = estado;
    }

    public Suscripcion(Long idSuscripcion, Long idUsuario, Long idMembresia, LocalDate fechaInicio, LocalDate fechaVencimiento, Float precioPagado, Estado estado) {
        this.idSuscripcion = idSuscripcion;
        this.idUsuario = idUsuario;
        this.idMembresia = idMembresia;
        this.fechaInicio = fechaInicio;
        this.fechaVencimiento = fechaVencimiento;
        this.precioPagado = precioPagado;
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "SuscripcionUsuarioMembresia{" + "idSuscripcion=" + idSuscripcion + ", idUsuario=" + idUsuario + ", idMembresia=" + idMembresia + ", fechaInicio=" + fechaInicio + ", fechaVencimiento=" + fechaVencimiento + ", precioPagado=" + precioPagado + ", estado=" + estado + '}';
    }
}
