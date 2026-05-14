/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;

import Adapters.MembresiaToDocumentAdapter;
import ConexionMongo.IBaseMongoDAO;
import ConexionMongo.ManejadorConexiones;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import static ConexionMongo.ManejadorConexiones.obtenerCodecs;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.InsertOneResult;
import Entidades.Estado;
import Entidades.Membresia;
import Entidades.PeriodoMembresia;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Jaime
 */
public class MembresiaDAO implements IMembresiaDAO, IBaseMongoDAO{
    
    private static final String NOMBRE_COLECCION = "membresias";

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
    public List<Membresia> obtenerTodas() throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoCollection<Document> col = this.obtenerColeccion(this.obtenerBaseDatos(cliente));
            List<Membresia> lista = new LinkedList<>();
            MongoCursor<Document> cursor = col.find().cursor();
            while (cursor.hasNext()) {
                lista.add(MembresiaToDocumentAdapter.adaptar(cursor.next()));
            }
            return lista;
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener todas las membresías", e);
        }
    }

    @Override
    public List<Membresia> obtenerActivas() throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoCollection<Document> col = this.obtenerColeccion(this.obtenerBaseDatos(cliente));
            Document filtro = new Document("estado", Estado.ACTIVA.name());
            List<Membresia> lista = new LinkedList<>();
            MongoCursor<Document> cursor = col.find(filtro).cursor();
            while (cursor.hasNext()) {
                lista.add(MembresiaToDocumentAdapter.adaptar(cursor.next()));
            }
            return lista;
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener membresías activas", e);
        }
    }

    @Override
    public Membresia obtenerPorId(Long idMembresia) throws PersistenciaException {
       try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoCollection<Document> col = this.obtenerColeccion(this.obtenerBaseDatos(cliente));
            Document filtro = new Document("idMembresia", idMembresia);
            return MembresiaToDocumentAdapter.adaptar(col.find(filtro).first());
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener membresía por ID", e);
        }
    }   

    @Override
    public Membresia guardar(Membresia membresia) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoCollection<Document> col = this.obtenerColeccion(this.obtenerBaseDatos(cliente));

            Document ultimo = col.find()
                    .sort(new Document("idMembresia", -1))
                    .first();
            long nuevoId = ultimo != null ? ultimo.getLong("idMembresia") + 1 : 1L;
            membresia.setIdMembresia(nuevoId);
            membresia.setFechaCreacion(LocalDate.now());

            InsertOneResult resultado = col.insertOne(
                    MembresiaToDocumentAdapter.adaptar(membresia));
            if (!resultado.wasAcknowledged()) {
                throw new PersistenciaException("No se pudo guardar la membresía");
            }
            return membresia;
        } catch (PersistenciaException e) {
            throw e;
        } catch (Exception e) {
            throw new PersistenciaException("Error al guardar la membresía", e);
        }
    }

    @Override
    public Membresia editar(Membresia membresia) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoCollection<Document> col = this.obtenerColeccion(this.obtenerBaseDatos(cliente));
            Document filtro = new Document("idMembresia", membresia.getIdMembresia());
            Document actualizacion = new Document("$set",
                    MembresiaToDocumentAdapter.adaptar(membresia));
            col.updateOne(filtro, actualizacion);
            return obtenerPorId(membresia.getIdMembresia());
        } catch (Exception e) {
            throw new PersistenciaException("Error al editar la membresía", e);
        }
    }

    @Override
    public Boolean eliminar(Long idMembresia) throws PersistenciaException {
       try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoCollection<Document> col = this.obtenerColeccion(this.obtenerBaseDatos(cliente));
            Document filtro = new Document("idMembresia", idMembresia);
            return col.deleteOne(filtro).getDeletedCount() > 0;
        } catch (Exception e) {
            throw new PersistenciaException("Error al eliminar la membresía", e);
        }
    }

    @Override
    public PeriodoMembresia obtenerPeriodoPorMembresia(Long idMembresia) throws PersistenciaException {
        Membresia m = obtenerPorId(idMembresia);
        return m != null ? m.getPeriodo() : null;
    }

    @Override
    public PeriodoMembresia guardarPeriodo(Long idMembresia, LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoCollection<Document> col = this.obtenerColeccion(this.obtenerBaseDatos(cliente));

            Document ultimo = col.find()
                    .sort(new Document("periodo.idPeriodo", -1))
                    .first();
            long nuevoId = 1L;
            if (ultimo != null && ultimo.get("periodo") != null) {
                Document periodoDoc = (Document) ultimo.get("periodo");
                nuevoId = periodoDoc.getLong("idPeriodo") + 1;
            }

            boolean vigente = !LocalDate.now().isBefore(fechaInicio)
                    && !LocalDate.now().isAfter(fechaFin);
            PeriodoMembresia periodo = new PeriodoMembresia(
                    nuevoId, idMembresia, fechaInicio, fechaFin, vigente);

            Document filtro = new Document("idMembresia", idMembresia);
            Document actualizacion = new Document("$set",
                    new Document("periodo",
                            MembresiaToDocumentAdapter.adaptarPeriodo(periodo)));
            col.updateOne(filtro, actualizacion);
            return periodo;
        } catch (Exception e) {
            throw new PersistenciaException("Error al guardar el período", e);
        }
    }

    @Override
    public PeriodoMembresia editarPeriodo(Long idPeriodo, LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException {
         try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoCollection<Document> col = this.obtenerColeccion(this.obtenerBaseDatos(cliente));
            boolean vigente = !LocalDate.now().isBefore(fechaInicio)
                    && !LocalDate.now().isAfter(fechaFin);
            Document filtro = new Document("periodo.idPeriodo", idPeriodo);
            Document actualizacion = new Document("$set",
                    new Document("periodo.fechaInicio", fechaInicio)
                            .append("periodo.fechaFin", fechaFin)
                            .append("periodo.vigente", vigente));
            col.updateOne(filtro, actualizacion);
            Document doc = col.find(filtro).first();
            return doc != null
                    ? MembresiaToDocumentAdapter.adaptarPeriodo((Document) doc.get("periodo"))
                    : null;
        } catch (Exception e) {
            throw new PersistenciaException("Error al editar el período", e);
        }
    }

    @Override
    public Boolean estaVigente(Long idMembresia) throws PersistenciaException {
       PeriodoMembresia periodo = obtenerPeriodoPorMembresia(idMembresia);
        if (periodo == null) return false;
        LocalDate hoy = LocalDate.now();
        return !hoy.isBefore(periodo.getFechaInicio()) && !hoy.isAfter(periodo.getFechaFin());
    }
}
