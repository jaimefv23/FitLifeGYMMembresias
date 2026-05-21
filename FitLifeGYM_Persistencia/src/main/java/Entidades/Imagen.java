/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;


/**
 *
 * @author Jaime
 */
public class Imagen {
    
    private String idImagen;
    private byte[] imagen;  
    private Long tamanio;
    
    public Imagen() {
    }

    public Imagen(byte[] imagen, Long tamanio) {
        this.imagen = imagen;
        this.tamanio = tamanio;
    }

    public Imagen(String idImagen, byte[] imagen, Long tamanio) {
        this.idImagen = idImagen;
        this.imagen = imagen;
        this.tamanio = tamanio;
    }

    public String getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(String idImagen) {
        this.idImagen = idImagen;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public Long getTamanio() {
        return tamanio;
    }

    public void setTamanio(Long tamanio) {
        this.tamanio = tamanio;
    }
    
    

    @Override
    public String toString() {
        return "Imagen{" + "idImagen=" + idImagen + ", imagen=" + imagen + '}';
    } 
}
