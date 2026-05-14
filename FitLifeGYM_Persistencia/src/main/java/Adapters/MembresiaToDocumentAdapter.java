/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Adapters;

import Entidades.Estado;
import Entidades.Imagen;
import Entidades.Membresia;
import Entidades.PeriodoMembresia;
import java.time.LocalDate;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author Jaime
 */
public class MembresiaToDocumentAdapter {
    
    // Membresia keys
    private static final String ID_MEMBRESIA = "idMembresia";
    private static final String NOMBRE = "nombre";
    private static final String PRECIO = "precio";
    private static final String ESTADO = "estado";
    private static final String BENEFICIOS = "beneficios";
    private static final String FECHA_CREACION = "fechaCreacion";
    private static final String IMAGEN = "imagen";
    private static final String PERIODO = "periodo";

    // Imagen keys
    private static final String IMAGEN_NOMBRE = "nombre";
    private static final String IMAGEN_TAMANIO = "tamanio";
    private static final String IMAGEN_DATOS = "datos";

    // Periodo keys
    private static final String PERIODO_ID = "idPeriodo";
    private static final String PERIODO_ID_MEMBRESIA = "idMembresia";
    private static final String PERIODO_FECHA_INICIO = "fechaInicio";
    private static final String PERIODO_FECHA_FIN = "fechaFin";
    private static final String PERIODO_VIGENTE = "vigente";

    public static Membresia adaptar(Document doc) {
        if (doc == null) return null;
        Membresia m = new Membresia();
        m.setIdMembresia(doc.getLong(ID_MEMBRESIA));
        m.setNombre(doc.getString(NOMBRE));
        m.setPrecio(doc.getDouble(PRECIO).floatValue());
        m.setEstado(Estado.valueOf(doc.getString(ESTADO)));
        m.setBeneficios((List<String>) doc.get(BENEFICIOS));
        m.setFechaCreacion(doc.get(FECHA_CREACION, LocalDate.class));

        Document imagenDoc = (Document) doc.get(IMAGEN);
        if (imagenDoc != null) {
            Imagen img = new Imagen();
            img.setImagen((byte[]) imagenDoc.get(IMAGEN_DATOS));
            m.setImagen(img);
        }

        Document periodoDoc = (Document) doc.get(PERIODO);
        if (periodoDoc != null) {
            m.setPeriodo(adaptarPeriodo(periodoDoc));
        }
        return m;
    }

    public static Document adaptar(Membresia m) {
        Document doc = new Document()
                .append(ID_MEMBRESIA, m.getIdMembresia())
                .append(NOMBRE, m.getNombre())
                .append(PRECIO, m.getPrecio())
                .append(ESTADO, m.getEstado().name())
                .append(BENEFICIOS, m.getBeneficios())
                .append(FECHA_CREACION, m.getFechaCreacion());

        if (m.getImagen() != null) {
            doc.append(IMAGEN, new Document()
                    .append(IMAGEN_DATOS, m.getImagen().getImagen()));
        }
        if (m.getPeriodo() != null) {
            doc.append(PERIODO, adaptarPeriodo(m.getPeriodo()));
        }
        return doc;
    }

    public static PeriodoMembresia adaptarPeriodo(Document doc) {
        PeriodoMembresia p = new PeriodoMembresia();
        p.setIdPeriodo(doc.getLong(PERIODO_ID));
        p.setIdMembresia(doc.getLong(PERIODO_ID_MEMBRESIA));
        p.setFechaInicio(doc.get(PERIODO_FECHA_INICIO, LocalDate.class));
        p.setFechaFin(doc.get(PERIODO_FECHA_FIN, LocalDate.class));
        p.setVigente(doc.getBoolean(PERIODO_VIGENTE));
        return p;
    }

    public static Document adaptarPeriodo(PeriodoMembresia p) {
        return new Document()
                .append(PERIODO_ID, p.getIdPeriodo())
                .append(PERIODO_ID_MEMBRESIA, p.getIdMembresia())
                .append(PERIODO_FECHA_INICIO, p.getFechaInicio())
                .append(PERIODO_FECHA_FIN, p.getFechaFin())
                .append(PERIODO_VIGENTE, p.getVigente());
    }
    
}
