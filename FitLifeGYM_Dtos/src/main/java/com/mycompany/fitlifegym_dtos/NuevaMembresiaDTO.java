/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fitlifegym_dtos;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Diego
 */
public class NuevaMembresiaDTO {
    
    private String nombre;
    private Float precio;
    private String estado;
    private List<String> beneficios;
    private ImagenDTO imagen;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public NuevaMembresiaDTO() {
    }

    public NuevaMembresiaDTO(String nombre, Float precio, String estado, List<String> beneficios, ImagenDTO imagen, LocalDate fechaInicio, LocalDate fechaFin) {
        this.nombre = nombre;
        this.precio = precio;
        this.estado = estado;
        this.beneficios = beneficios;
        this.imagen = imagen;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
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

    public ImagenDTO getImagen() {
        return imagen;
    }

    public void setImagen(ImagenDTO imagen) {
        this.imagen = imagen;
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
}
