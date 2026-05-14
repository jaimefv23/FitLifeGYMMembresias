/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;

import Adapters.SuscripcionToDocumentAdapter;
import ConexionMongo.IBaseMongoDAO;
import ConexionMongo.ManejadorConexiones;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import Entidades.Estado;
import Entidades.HistorialSuscripcionesMembresias;
import Entidades.Suscripcion;
import java.time.LocalDate;
import java.util.List;
import org.bson.Document;
import static ConexionMongo.ManejadorConexiones.obtenerCodecs;
import com.mongodb.client.MongoCursor;
import java.util.LinkedList;


/**
 *
 * @author Jaime
 */
public class SuscripcionDAO implements ISuscripcionDAO, IBaseMongoDAO{

    private static final String NOMBRE_COLECCION = "suscripciones";

    @Override
    public MongoDatabase obtenerBaseDatos(MongoClient cliente) {
       return cliente.getDatabase(ManejadorConexiones.BASE_DATOS)
                .withCodecRegistry(obtenerCodecs());
    }

    @Override
    public MongoCollection obtenerColeccion(MongoDatabase baseDatos) {
        return baseDatos.getCollection(NOMBRE_COLECCION, Document.class);
    }
    
    @Override
    public Integer contarActivasPorMembresia(Long idMembresia) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoCollection<Document> col = this.obtenerColeccion(this.obtenerBaseDatos(cliente));
            Document filtro = new Document("idMembresia", idMembresia)
                    .append("estado", Estado.ACTIVA.name())
                    .append("tipo", "suscripcion");
            return (int) col.countDocuments(filtro);
        } catch (Exception e) {
            throw new PersistenciaException("Error al contar suscripciones activas", e);
        }
    }

    @Override
    public List<Suscripcion> obtenerPorMembresia(Long idMembresia) throws PersistenciaException {
       try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoCollection<Document> col = this.obtenerColeccion(this.obtenerBaseDatos(cliente));
            Document filtro = new Document("idMembresia", idMembresia)
                    .append("tipo", "suscripcion");
            List<Suscripcion> lista = new LinkedList<>();
            MongoCursor<Document> cursor = col.find(filtro).cursor();
            while (cursor.hasNext()) {
                lista.add(SuscripcionToDocumentAdapter.adaptar(cursor.next()));
            }
            return lista;
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener suscripciones por membresía", e);
        }
    }

    @Override
    public Suscripcion obtenerActivaPorUsuario(Long idUsuario) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoCollection<Document> col = this.obtenerColeccion(this.obtenerBaseDatos(cliente));
            Document filtro = new Document("idUsuario", idUsuario)
                    .append("estado", Estado.ACTIVA.name())
                    .append("tipo", "suscripcion");
            return SuscripcionToDocumentAdapter.adaptar(col.find(filtro).first());
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener suscripción activa del usuario", e);
        }
    }

    @Override
    public Boolean existeActiva(Long idUsuario, Long idMembresia) throws PersistenciaException {
       try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoCollection<Document> col = this.obtenerColeccion(this.obtenerBaseDatos(cliente));
            Document filtro = new Document("idUsuario", idUsuario)
                    .append("idMembresia", idMembresia)
                    .append("estado", Estado.ACTIVA.name())
                    .append("tipo", "suscripcion");
            return col.countDocuments(filtro) > 0;
        } catch (Exception e) {
            throw new PersistenciaException("Error al verificar suscripción activa", e);
        }
    }

    @Override
    public Suscripcion guardar(Suscripcion suscripcion) throws PersistenciaException {
         try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoCollection<Document> col = this.obtenerColeccion(this.obtenerBaseDatos(cliente));

            Document ultimo = col.find(new Document("tipo", "suscripcion"))
                    .sort(new Document("idSuscripcion", -1))
                    .first();
            long nuevoId = ultimo != null ? ultimo.getLong("idSuscripcion") + 1 : 1L;
            suscripcion.setIdSuscripcion(nuevoId);
            suscripcion.setEstado(Estado.ACTIVA);

            InsertOneResult resultado = col.insertOne(
                    SuscripcionToDocumentAdapter.adaptar(suscripcion));
            if (!resultado.wasAcknowledged()) {
                throw new PersistenciaException("No se pudo guardar la suscripción");
            }
            return suscripcion;
        } catch (PersistenciaException e) {
            throw e;
        } catch (Exception e) {
            throw new PersistenciaException("Error al guardar la suscripción", e);
        }
    }

    @Override
    public List<HistorialSuscripcionesMembresias> obtenerTodoHistorial() throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoCollection<Document> col = this.obtenerColeccion(this.obtenerBaseDatos(cliente));
            Document filtro = new Document("tipo", "historial");
            List<HistorialSuscripcionesMembresias> lista = new LinkedList<>();
            MongoCursor<Document> cursor = col.find(filtro).cursor();
            while (cursor.hasNext()) {
                lista.add(SuscripcionToDocumentAdapter.adaptarHistorial(cursor.next()));
            }
            return lista;
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener historial", e);
        }

    }

    @Override
    public List<HistorialSuscripcionesMembresias> obtenerHistorialPorMembresia(Long idMembresia) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoCollection<Document> col = this.obtenerColeccion(this.obtenerBaseDatos(cliente));
            Document filtro = new Document("idMembresia", idMembresia)
                    .append("tipo", "historial");
            List<HistorialSuscripcionesMembresias> lista = new LinkedList<>();
            MongoCursor<Document> cursor = col.find(filtro).cursor();
            while (cursor.hasNext()) {
                lista.add(SuscripcionToDocumentAdapter.adaptarHistorial(cursor.next()));
            }
            return lista;
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener historial por membresía", e);
        }
    }

    @Override
    public List<HistorialSuscripcionesMembresias> obtenerHistorialPorPeriodo(LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException {
       try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoCollection<Document> col = this.obtenerColeccion(this.obtenerBaseDatos(cliente));
            Document filtro = new Document("tipo", "historial")
                    .append("fechaInicio", new Document("$gte", fechaInicio))
                    .append("fechaFin", new Document("$lte", fechaFin));
            List<HistorialSuscripcionesMembresias> lista = new LinkedList<>();
            MongoCursor<Document> cursor = col.find(filtro).cursor();
            while (cursor.hasNext()) {
                lista.add(SuscripcionToDocumentAdapter.adaptarHistorial(cursor.next()));
            }
            return lista;
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener historial por período", e);
        }
    }

    @Override
    public HistorialSuscripcionesMembresias guardarHistorial(HistorialSuscripcionesMembresias historial) throws PersistenciaException {
            try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoCollection<Document> col = this.obtenerColeccion(this.obtenerBaseDatos(cliente));

            Document ultimo = col.find(new Document("tipo", "historial"))
                    .sort(new Document("idHistorialSuscripcion", -1))
                    .first();
            long nuevoId = ultimo != null ? ultimo.getLong("idHistorialSuscripcion") + 1 : 1L;
            historial.setIdHistorialSuscripcion(nuevoId);

            InsertOneResult resultado = col.insertOne(
                    SuscripcionToDocumentAdapter.adaptarHistorial(historial));
            if (!resultado.wasAcknowledged()) {
                throw new PersistenciaException("No se pudo guardar el historial");
            }
            return historial;
        } catch (PersistenciaException e) {
            throw e;
        } catch (Exception e) {
            throw new PersistenciaException("Error al guardar el historial", e);
        }
    }

    
    
}
