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
public class MembresiaDTO {
        
    private Long idMembresia;
    private String nombre;
    private Float precio;
    private String estado;
    private List<String> beneficios;
    private LocalDate fechaCreacion;

    public MembresiaDTO() {
    }

    public MembresiaDTO(String nombre, Float precio, String estado, List<String> beneficios, LocalDate fechaCreacion) {
        this.nombre = nombre;
        this.precio = precio;
        this.estado = estado;
        this.beneficios = beneficios;
        this.fechaCreacion = fechaCreacion;
    }

    public MembresiaDTO(Long idMembresia, String nombre, Float precio, String estado, List<String> beneficios, LocalDate fechaCreacion) {
        this.idMembresia = idMembresia;
        this.nombre = nombre;
        this.precio = precio;
        this.estado = estado;
        this.beneficios = beneficios;
        this.fechaCreacion = fechaCreacion;
    }

    public Long getIdMembresia() {
        return idMembresia;
    }

    public void setIdMembresia(Long idMembresia) {
        this.idMembresia = idMembresia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<String> getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(List<String> beneficios) {
        this.beneficios = beneficios;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
