/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fitlifegym_dtos;

/**
 *
 * @author Jaime
 */
public class EntradaReporteDTO {
    
    private String idMembresia;
    private String nombre;
    private ImagenDTO imagen;
    private Integer usuarios;
    private Double totalGenerado;

    public EntradaReporteDTO(String idMembresia, String nombre, ImagenDTO imagen, Integer usuarios, Double totalGenerado) {
        this.idMembresia = idMembresia;
        this.nombre = nombre;
        this.imagen = imagen;
        this.usuarios = usuarios;
        this.totalGenerado = totalGenerado;
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

    public ImagenDTO getImagen() {
        return imagen;
    }

    public void setImagen(ImagenDTO imagen) {
        this.imagen = imagen;
    }

    public Integer getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Integer usuarios) {
        this.usuarios = usuarios;
    }

    public Double getTotalGenerado() {
        return totalGenerado;
    }

    public void setTotalGenerado(Double totalGenerado) {
        this.totalGenerado = totalGenerado;
    }
    
    
}
