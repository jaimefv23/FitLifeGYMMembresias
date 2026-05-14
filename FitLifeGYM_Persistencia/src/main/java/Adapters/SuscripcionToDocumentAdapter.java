/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Adapters;

import Entidades.Estado;
import Entidades.HistorialSuscripcionesMembresias;
import Entidades.Suscripcion;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;


/**
 *
 * @author Jaime
 */
public class SuscripcionToDocumentAdapter {
    
    // Suscripcion keys
    private static final String ID_SUSCRIPCION = "idSuscripcion";
    private static final String ID_USUARIO = "idUsuario";
    private static final String ID_MEMBRESIA = "idMembresia";
    private static final String PRECIO_PAGADO = "precioPagado";
    private static final String ESTADO = "estado";
    private static final String FECHA_INICIO = "fechaInicio";
    private static final String FECHA_VENCIMIENTO = "fechaVencimiento";
    private static final String TIPO = "tipo";
    private static final String TIPO_SUSCRIPCION = "suscripcion";

    // Historial keys
    private static final String ID_HISTORIAL = "idHistorialSuscripcion";
    private static final String FECHA_FIN = "fechaFin";
    private static final String SUSCRIPCIONES = "suscripciones";
    private static final String TIPO_HISTORIAL = "historial";

    public static Suscripcion adaptar(Document doc) {
        if (doc == null) return null;
        Suscripcion s = new Suscripcion();
        s.setIdSuscripcion(doc.getLong(ID_SUSCRIPCION));
        s.setIdUsuario(doc.getLong(ID_USUARIO));
        s.setIdMembresia(doc.getLong(ID_MEMBRESIA));
        s.setPrecioPagado(doc.getDouble(PRECIO_PAGADO).floatValue());
        s.setEstado(Estado.valueOf(doc.getString(ESTADO)));
        s.setFechaInicio(doc.get(FECHA_INICIO, LocalDate.class));
        s.setFechaVencimiento(doc.get(FECHA_VENCIMIENTO, LocalDate.class));
        return s;
    }

    public static Document adaptar(Suscripcion s) {
        return new Document()
                .append(ID_SUSCRIPCION, s.getIdSuscripcion())
                .append(ID_USUARIO, s.getIdUsuario())
                .append(ID_MEMBRESIA, s.getIdMembresia())
                .append(PRECIO_PAGADO, s.getPrecioPagado())
                .append(ESTADO, s.getEstado().name())
                .append(FECHA_INICIO, s.getFechaInicio())
                .append(FECHA_VENCIMIENTO, s.getFechaVencimiento())
                .append(TIPO, TIPO_SUSCRIPCION);
    }

    public static HistorialSuscripcionesMembresias adaptarHistorial(Document doc) {
        if (doc == null) return null;
        HistorialSuscripcionesMembresias h = new HistorialSuscripcionesMembresias();
        h.setIdHistorialSuscripcion(doc.getLong(ID_HISTORIAL));
        h.setIdUsuario(doc.getLong(ID_USUARIO));
        h.setIdMembresia(doc.getLong(ID_MEMBRESIA));
        h.setPrecioPagado(doc.getDouble(PRECIO_PAGADO).floatValue());
        h.setFechaInicio(doc.get(FECHA_INICIO, LocalDate.class));
        h.setFechaFin(doc.get(FECHA_FIN, LocalDate.class));
        h.setFechaVencimiento(doc.get(FECHA_VENCIMIENTO, LocalDate.class));

        List<Document> suscDocs = (List<Document>) doc.get(SUSCRIPCIONES);
        List<Suscripcion> suscripciones = new ArrayList<>();
        if (suscDocs != null) {
            for (Document s : suscDocs) {
                suscripciones.add(adaptar(s));
            }
        }
        h.setSuscripciones(suscripciones);
        return h;
    }

    public static Document adaptarHistorial(HistorialSuscripcionesMembresias h) {
        List<Document> suscDocs = new ArrayList<>();
        if (h.getSuscripciones() != null) {
            for (Suscripcion s : h.getSuscripciones()) {
                suscDocs.add(adaptar(s));
            }
        }
        return new Document()
                .append(ID_HISTORIAL, h.getIdHistorialSuscripcion())
                .append(ID_USUARIO, h.getIdUsuario())
                .append(ID_MEMBRESIA, h.getIdMembresia())
                .append(PRECIO_PAGADO, h.getPrecioPagado())
                .append(FECHA_INICIO, h.getFechaInicio())
                .append(FECHA_FIN, h.getFechaFin())
                .append(FECHA_VENCIMIENTO, h.getFechaVencimiento())
                .append(SUSCRIPCIONES, suscDocs)
                .append(TIPO, TIPO_HISTORIAL);
    }
    
}
