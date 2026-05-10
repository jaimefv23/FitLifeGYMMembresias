
package com.mycompany.fitlifegym_persistencia.entidades;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Jaime
 */
public class Membresia {
    
    private Long idMembresia;
    private String nombre;
    private Imagen imagen;
    private Float precio;
    private Estado estado;
    private List<String> beneficios;
    private LocalDate fechaCreacion;

    public Membresia() {
    }

    public Membresia(String nombre, Imagen imagen, Float precio, Estado estado, List<String> beneficios, LocalDate fechaCreacion) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.precio = precio;
        this.estado = estado;
        this.beneficios = beneficios;
        this.fechaCreacion = fechaCreacion;
    }

    public Membresia(Long idMembresia, String nombre, Imagen imagen, Float precio, Estado estado, List<String> beneficios, LocalDate fechaCreacion) {
        this.idMembresia = idMembresia;
        this.nombre = nombre;
        this.imagen = imagen;
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

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
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

    @Override
    public String toString() {
        return "Membresia{" + "idMembresia=" + idMembresia + ", nombre=" + nombre + ", imagen=" + imagen + ", precio=" + precio + ", estado=" + estado + ", beneficios=" + beneficios + ", fechaCreacion=" + fechaCreacion + '}';
    }
}
