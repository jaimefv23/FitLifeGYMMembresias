
package Entidades;

import java.time.LocalDate;
import java.util.List;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

/**
 *
 * @author Jaime
 */
public class Membresia {
    
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String idMembresia;
    
    private String nombre;
    private Imagen imagen;
    private Float precio;
    private Estado estado;
    private List<String> beneficios;
    private LocalDate fechaCreacion;
    private PeriodoMembresia periodo;

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

    public Membresia(String idMembresia, String nombre, Imagen imagen, Float precio, Estado estado, List<String> beneficios, LocalDate fechaCreacion, PeriodoMembresia periodo) {
        this.idMembresia = idMembresia;
        this.nombre = nombre;
        this.imagen = imagen;
        this.precio = precio;
        this.estado = estado;
        this.beneficios = beneficios;
        this.fechaCreacion = fechaCreacion;
        this.periodo = periodo;
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

    public PeriodoMembresia getPeriodo() {
        return periodo;
    }

    public void setPeriodo(PeriodoMembresia periodo) {
        this.periodo = periodo;
    }

    @Override
    public String toString() {
        return "Membresia{" + "idMembresia=" + idMembresia + ", nombre=" + nombre + ", imagen=" + imagen + ", precio=" + precio + ", estado=" + estado + ", beneficios=" + beneficios + ", fechaCreacion=" + fechaCreacion + ", periodo=" + periodo + '}';
    }

}
