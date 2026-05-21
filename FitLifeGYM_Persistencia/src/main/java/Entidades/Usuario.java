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
public class Usuario {
    
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String idUsuario;
    
    private String nombre;
    private String contrasenia;
    private LocalDate fechaRegistro;
    private String rol;

    public Usuario() {
    }

    public Usuario(String nombre, String contrasenia, LocalDate fechaRegistro, String rol) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.fechaRegistro = fechaRegistro;
        this.rol = rol;
    }

    public Usuario(String idUsuario, String nombre,String contrasenia, LocalDate fechaRegistro, String rol) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.fechaRegistro = fechaRegistro;
        this.rol = rol;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", nombre=" + nombre + ", contrasenia=" + contrasenia + ", fechaRegistro=" + fechaRegistro + ", rol=" + rol + '}';
    }

   
    
}
