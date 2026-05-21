/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;

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
import org.bson.types.ObjectId;

/**
 *
 * @author Jaime
 */
public class MembresiaDAO implements IMembresiaDAO, IBaseMongoDAO{
    
    private static final String NOMBRE_COLECCION = "membresias";

    @Override
    public MongoDatabase obtenerBaseDatos(MongoClient cliente) {
       return cliente.getDatabase(ManejadorConexiones.BASE_DATOS).withCodecRegistry(obtenerCodecs());
    }

    @Override
    public MongoCollection obtenerColeccion(MongoDatabase baseDatos) {
        return baseDatos.getCollection(NOMBRE_COLECCION, Membresia.class);
    }

    /**
     * Obtiene todas las membresías sin importar su estado.
     * @return Lista de membresías
     * @throws PersistenciaException si falla la consulta
     */
    @Override
    public List<Membresia> obtenerTodas() throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoCollection<Membresia> col = this.obtenerColeccion(this.obtenerBaseDatos(cliente));
            List<Membresia> lista = new LinkedList<>();
            MongoCursor<Membresia> cursor = col.find().cursor();
            while (cursor.hasNext()) {
                lista.add(cursor.next());
            }
            return lista;
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener todas las membresías", e);
        }
    }

    /**
     * Obtiene solo las membresías con estado ACTIVA.
     * @return Lista de membresías activas
     * @throws PersistenciaException si falla la consulta
     */
    @Override
    public List<Membresia> obtenerActivas() throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoCollection<Membresia> col = this.obtenerColeccion(this.obtenerBaseDatos(cliente));
            Document filtro = new Document("estado", Estado.ACTIVA.name());
            List<Membresia> lista = new LinkedList<>();
            MongoCursor<Membresia> cursor = col.find(filtro).cursor();
            while (cursor.hasNext()) {
                lista.add(cursor.next());
            }
            return lista;
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener membresías activas", e);
        }
    }

    /**
     * Busca una membresía por su ObjectId.
     * @param  idMembresia ID de la membresía
     * @return Membresía encontrada o null si no existe
     * @throws PersistenciaException si falla la consulta
     */
    @Override
    public Membresia obtenerPorId(String idMembresia) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoCollection<Membresia> col = this.obtenerColeccion(this.obtenerBaseDatos(cliente));
            Document filtro = new Document("_id", new ObjectId(idMembresia));
            return col.find(filtro).first();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener membresía por ID", e);
        }
    }

    /**
     * Inserta una nueva membresía. 
     * @param  membresia Entidad con los datos a guardar
     * @return Membresía guardada 
     * @throws PersistenciaException si falla la inserción
     */
    @Override
    public Membresia guardar(Membresia membresia) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoCollection<Membresia> col = this.obtenerColeccion(this.obtenerBaseDatos(cliente));

            membresia.setFechaCreacion(LocalDate.now());

            InsertOneResult resultado = col.insertOne(membresia);
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

    /**
     * Actualiza nombre, precio, estado y beneficios de una membresía.
     * @param  membresia Entidad con los nuevos datos y el _id ya existente
     * @return Membresía actualizada
     * @throws PersistenciaException si falla la actualización
     */ 
    @Override
    public Membresia editar(Membresia membresia) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoCollection<Membresia> col = this.obtenerColeccion(this.obtenerBaseDatos(cliente));
            Document filtro = new Document("_id", new ObjectId(membresia.getIdMembresia()));
            Document actualizacion = new Document("$set", new Document()
                    .append("nombre", membresia.getNombre())
                    .append("precio", membresia.getPrecio())
                    .append("estado", membresia.getEstado().name())
                    .append("beneficios", membresia.getBeneficios()));
            col.updateOne(filtro, actualizacion);
            return obtenerPorId(membresia.getIdMembresia());
        } catch (Exception e) {
            throw new PersistenciaException("Error al editar la membresía", e);
        }
    }

    /**
     * Elimina una membresía por su ID.
     * @param  idMembresia ID de la membresía
     * @return true si se eliminó, false si no se encontró
     * @throws PersistenciaException si falla la eliminación
     */
    @Override
    public Boolean eliminar(String idMembresia) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoCollection<Membresia> col = this.obtenerColeccion(this.obtenerBaseDatos(cliente));
            Document filtro = new Document("_id", new ObjectId(idMembresia));
            return col.deleteOne(filtro).getDeletedCount() > 0;
        } catch (Exception e) {
            throw new PersistenciaException("Error al eliminar la membresía", e);
        }
    }

    /**
     * Obtiene el periodo embebido dentro del documento de la membresía.
     * @param  idMembresia ID de la membresía
     * @return PeriodoMembresia embebido o null si no tiene
     * @throws PersistenciaException si falla la conexión
     */
    @Override
    public PeriodoMembresia obtenerPeriodoPorMembresia(String idMembresia) throws PersistenciaException {
        Membresia m = obtenerPorId(idMembresia);
        return m != null ? m.getPeriodo() : null;
    }

     /**
     * Guarda un nuevo periodo embebido en la membresía.
     * @param  idMembresia ID de la membresía
     * @param  fechaInicio Fecha de inicio del periodo
     * @param  fechaFin Fecha de fin del periodo
     * @return PeriodoMembresia guardado
     * @throws PersistenciaException si falla la actualización
     */
    @Override
    public PeriodoMembresia guardarPeriodo(String idMembresia, LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoCollection<Membresia> col = this.obtenerColeccion(this.obtenerBaseDatos(cliente));

            boolean vigente = !LocalDate.now().isBefore(fechaInicio) && !LocalDate.now().isAfter(fechaFin);

            String nuevoIdPeriodo = new ObjectId().toHexString();

            PeriodoMembresia periodo = new PeriodoMembresia(nuevoIdPeriodo, idMembresia, fechaInicio, fechaFin, vigente);

            Document filtro = new Document("_id", new ObjectId(idMembresia));
            Document actualizacion = new Document("$set", new Document("periodo", periodo));
            col.updateOne(filtro, actualizacion);
            return periodo;
        } catch (Exception e) {
            throw new PersistenciaException("Error al guardar el período", e);
        }
    }

    /**
     * Edita el periodo embebido filtrando por el ID de la membresía.
     * Recalcula automáticamente si el periodo es vigente.
     * @param  idMembresia ID de la membresía
     * @param  fechaInicio Nueva fecha de inicio
     * @param  fechaFin Nueva fecha de fin
     * @return PeriodoMembresia actualizado
     * @throws PersistenciaException si falla la actualización
     */
    @Override
    public PeriodoMembresia editarPeriodo(String idMembresia, LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
        MongoCollection<Membresia> col = this.obtenerColeccion(this.obtenerBaseDatos(cliente));

        boolean vigente = !LocalDate.now().isBefore(fechaInicio) && !LocalDate.now().isAfter(fechaFin);

        Document filtro = new Document("_id", new ObjectId(idMembresia));
        Document actualizacion = new Document("$set", new Document()
                .append("periodo.fechaInicio", fechaInicio)
                .append("periodo.fechaFin", fechaFin)
                .append("periodo.vigente", vigente));

        col.updateOne(filtro, actualizacion);
        return col.find(filtro).first().getPeriodo();

        } catch (Exception e) {
            throw new PersistenciaException("Error al editar el período", e);
        }
    }

    /**
     * Verifica si la fecha actual está dentro del periodo de la membresía.
     * @param  idMembresia ID de la membresía
     * @return true si hoy está entre fechaInicio y fechaFin
     * @throws PersistenciaException si falla la consulta
     */
    @Override
        public Boolean estaVigente(String idMembresia) throws PersistenciaException {
        PeriodoMembresia periodo = obtenerPeriodoPorMembresia(idMembresia);
        if (periodo == null) 
            return false;
        LocalDate hoy = LocalDate.now();
        return !hoy.isBefore(periodo.getFechaInicio()) && !hoy.isAfter(periodo.getFechaFin());
    }
 }