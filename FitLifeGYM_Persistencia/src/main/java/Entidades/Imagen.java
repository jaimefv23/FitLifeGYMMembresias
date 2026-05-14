/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

/**
 *
 * @author Jaime
 */
public class Imagen {
    
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private Long idImagen;
    private byte[] imagen;    
    
    public Imagen() {
    }

    public Imagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public Imagen(Long idImagen, byte[] imagen) {
        this.idImagen = idImagen;
        this.imagen = imagen;
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

    @Override
    public String toString() {
        return "Imagen{" + "idImagen=" + idImagen + ", imagen=" + imagen + '}';
    } 
}
