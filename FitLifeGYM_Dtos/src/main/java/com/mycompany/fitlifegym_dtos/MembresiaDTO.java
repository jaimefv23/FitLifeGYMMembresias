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
        
    private String idMembresia;
    private String nombre;
    private Float precio;
    private ImagenDTO imagen;
    private String estado;
    private List<String> beneficios;
    private LocalDate fechaCreacion;

    public MembresiaDTO() {
    }

    public MembresiaDTO(String idMembresia, String nombre, Float precio, String estado, List<String> beneficios, LocalDate fechaCreacion) {
        this.idMembresia = idMembresia;
        this.nombre = nombre;
        this.precio = precio;
        this.estado = estado;
        this.beneficios = beneficios;
        this.fechaCreacion = fechaCreacion;
    }
    
   
    public MembresiaDTO(String idMembresia, String nombre, Float precio, ImagenDTO imagen, String estado, List<String> beneficios, LocalDate fechaCreacion) {
        this.idMembresia = idMembresia;
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
        this.estado = estado;
        this.beneficios = beneficios;
        this.fechaCreacion = fechaCreacion;
    }

    public ImagenDTO getImagen() {
        return imagen;
    }

    public void setImagen(ImagenDTO imagen) {
        this.imagen = imagen;
    }

    

    public String getIdMembresia() {
        return idMembresia;
    }

    public void setIdMembresia(String idMembresia) {
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
