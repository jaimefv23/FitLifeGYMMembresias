/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    private String idSuscripcion;
    
    private String idUsuario;
    private String idMembresia;
    private LocalDate fechaInicio;
    private LocalDate fechaVencimiento;
    private Float precioPagado;
    private Estado estado;
    private List<HistorialSuscripcion> historial;

    public Suscripcion() {
        this.historial = new ArrayList<>();
    }
    

     public Suscripcion(String idUsuario, String idMembresia, Estado estado, LocalDate fechaInicio, LocalDate fechaVencimiento, Float precioPagado) {
        this.idUsuario = idUsuario;
        this.idMembresia = idMembresia;
        this.estado = estado;
        this.fechaInicio = fechaInicio;
        this.fechaVencimiento = fechaVencimiento;
        this.precioPagado = precioPagado;
        this.historial = new ArrayList<>();
    }

    public Suscripcion(String idSuscripcion, String idUsuario, String idMembresia, LocalDate fechaInicio, LocalDate fechaVencimiento, Float precioPagado, Estado estado) {
        this.idSuscripcion = idSuscripcion;
        this.idUsuario = idUsuario;
        this.idMembresia = idMembresia;
        this.fechaInicio = fechaInicio;
        this.fechaVencimiento = fechaVencimiento;
        this.precioPagado = precioPagado;
        this.estado = estado;
        this.historial = new ArrayList<>();
    }

    public String getIdSuscripcion() {
        return idSuscripcion;
    }

    public void setIdSuscripcion(String idSuscripcion) {
        this.idSuscripcion = idSuscripcion;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
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

    public List<HistorialSuscripcion> getHistorial() {
        return historial;
    }

    public void setHistorial(List<HistorialSuscripcion> historial) {
        this.historial = historial;
    }

    @Override
    public String toString() {
        return "Suscripcion{" + "idSuscripcion=" + idSuscripcion + ", idUsuario=" + idUsuario + ", idMembresia=" + idMembresia + ", fechaInicio=" + fechaInicio + ", fechaVencimiento=" + fechaVencimiento + ", precioPagado=" + precioPagado + ", estado=" + estado + ", historial=" + historial + '}';
    }
    
    

    
}
