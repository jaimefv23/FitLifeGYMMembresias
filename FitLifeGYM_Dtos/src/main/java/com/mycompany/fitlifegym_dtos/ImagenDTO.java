/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fitlifegym_dtos;

/**
 *
 * @author Jaime
 */
public class ImagenDTO {
    
    private Long idImagen;
    private byte[] imagen;
    private Long tamanio;

    public ImagenDTO() {
    }

    public ImagenDTO(byte[] imagen, Long tamanio) {
        this.imagen = imagen;
        this.tamanio = tamanio;
    }

    public ImagenDTO(Long idImagen, byte[] imagen, Long tamanio) {
        this.idImagen = idImagen;
        this.imagen = imagen;
        this.tamanio = tamanio;
    }

    public Long getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(Long idImagen) {
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
}
