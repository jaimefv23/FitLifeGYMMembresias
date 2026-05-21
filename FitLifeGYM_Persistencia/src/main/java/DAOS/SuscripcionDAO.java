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
import com.mongodb.client.result.InsertOneResult;
import Entidades.Estado;
import Entidades.Suscripcion;
import java.time.LocalDate;
import java.util.List;
import org.bson.Document;
import static ConexionMongo.ManejadorConexiones.obtenerCodecs;
import Entidades.HistorialSuscripcion;
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
       return cliente.getDatabase(ManejadorConexiones.BASE_DATOS).withCodecRegistry(obtenerCodecs());
    }

    @Override
    public MongoCollection obtenerColeccion(MongoDatabase baseDatos) {
        return baseDatos.getCollection(NOMBRE_COLECCION, Suscripcion.class);
    }
    
    /**
     * Inserta una nueva suscripción y asigna estado ACTIVA automáticamente.
     * @param  suscripcion Entidad con idUsuario, idMembresia, fechas, estado y precio
     * @return Suscripción guardada 
     * @throws PersistenciaException si falla la inserción
     */
    @Override
    public Suscripcion guardar(Suscripcion suscripcion) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoCollection<Suscripcion> col = this.obtenerColeccion( this.obtenerBaseDatos(cliente));

            suscripcion.setEstado(Estado.ACTIVA);

            InsertOneResult resultado = col.insertOne(suscripcion);
            if (!resultado.wasAcknowledged()) {
                throw new PersistenciaException("No se pudo guardar la suscripción");
            }
            
            return suscripcion;
        } catch (Exception e) {
            throw new PersistenciaException("Error al guardar la suscripción", e);
        }
    }

    
    /**
     * Busca la suscripción activa de un usuario.
     * @param  idUsuario ID del usuario
     * @return Suscripción activa o null si no tiene
     * @throws PersistenciaException si falla la consulta
     */
    @Override
    public Suscripcion obtenerActivaPorUsuario(String idUsuario) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoCollection<Suscripcion> col = this.obtenerColeccion(this.obtenerBaseDatos(cliente));
            Document filtro = new Document("idUsuario", idUsuario)
                    .append("estado", Estado.ACTIVA.name());
            return col.find(filtro).first();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener suscripción activa", e);
        }
    }

    /**
     * Obtiene todas las suscripciones de una membresía específica.
     * @param  idMembresia ID de la membresía
     * @return Lista de suscripciones de esa membresía
     * @throws PersistenciaException si falla la consulta
     */
    @Override
    public List<Suscripcion> obtenerPorMembresia(String idMembresia) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoCollection<Suscripcion> col = this.obtenerColeccion(this.obtenerBaseDatos(cliente));
            Document filtro = new Document("idMembresia", idMembresia);
            List<Suscripcion> lista = new LinkedList<>();
            MongoCursor<Suscripcion> cursor = col.find(filtro).cursor();
            while (cursor.hasNext()) {
                lista.add(cursor.next());
            }
            return lista;
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener suscripciones por membresía", e);
        }
    }

    /**
     * Cuenta cuántas suscripciones activas tiene una membresía.
     * @param  idMembresia ID de la membresía
     * @return Número de suscripciones activas
     * @throws PersistenciaException si falla la consulta
     */
    @Override
    public Integer contarActivasPorMembresia(String idMembresia) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoCollection<Suscripcion> col = this.obtenerColeccion(this.obtenerBaseDatos(cliente));
            Document filtro = new Document("idMembresia", idMembresia)
                    .append("estado", Estado.ACTIVA.name());
            return (int) col.countDocuments(filtro);
        } catch (Exception e) {
            throw new PersistenciaException("Error al contar suscripciones activas", e);
        }
    }

     /**
     * Verifica si existe una suscripción activa para un usuario y membresía.
     * @param  idUsuario ID del usuario
     * @param  idMembresia ID de la membresía
     * @return true si existe una suscripción activa
     * @throws PersistenciaException si falla la consulta
     */
    @Override
    public Boolean existeActiva(String idUsuario, String idMembresia) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoCollection<Suscripcion> col = this.obtenerColeccion(this.obtenerBaseDatos(cliente));
            Document filtro = new Document("idUsuario", idUsuario)
                    .append("idMembresia", idMembresia)
                    .append("estado", Estado.ACTIVA.name());
            return col.countDocuments(filtro) > 0;
        } catch (Exception e) {
            throw new PersistenciaException("Error al verificar suscripción activa", e);
        }
    }
    
    /**
     * Agrega un Array "historial" embebido en el documento de suscripción.
     * @param  idUsuario ID del usuario dueño de la suscripción
     * @param  historial Array con membresía, fechas, precio y estado
     * @return Suscripción actualizada con el nuevo historial
     * @throws PersistenciaException si falla la actualización
     */
    @Override
    public Suscripcion agregarAlHistorial(String idUsuario, HistorialSuscripcion historial)
            throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoCollection<Suscripcion> col = this.obtenerColeccion(this.obtenerBaseDatos(cliente));
            Document filtro = new Document("idUsuario", idUsuario);
            Document actualizacion = new Document("$push", new Document("historial", historial));
            col.updateOne(filtro, actualizacion);
            return col.find(filtro).first();
        } catch (Exception e) {
            throw new PersistenciaException("Error al agregar al historial", e);
        }
    }

    
    /**
     * Obtiene suscripciones cuya fechaInicio y fechaVencimiento
     * caen dentro del rango indicado.
     * @param  fechaInicio Inicio del rango de búsqueda
     * @param  fechaFin Fin del rango de búsqueda
     * @return Lista de suscripciones en ese periodo
     * @throws PersistenciaException si falla la conexión
     */
    @Override
    public List<Suscripcion> obtenerHistorialPorPeriodo(LocalDate fechaInicio, LocalDate fechaFin)
            throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoCollection<Suscripcion> col = this.obtenerColeccion(this.obtenerBaseDatos(cliente));
            Document filtro = new Document("fechaInicio", new Document("$gte", fechaInicio))
                              .append("fechaVencimiento", new Document("$lte", fechaFin));
            List<Suscripcion> lista = new LinkedList<>();
            MongoCursor<Suscripcion> cursor = col.find(filtro).cursor();
            while (cursor.hasNext()) {
                lista.add(cursor.next());
            }
            return lista;
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener historial por período", e);
        }
    }
     
}
