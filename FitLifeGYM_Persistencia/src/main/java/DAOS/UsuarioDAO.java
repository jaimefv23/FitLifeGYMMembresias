/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;

import ConexionMongo.IBaseMongoDAO;
import ConexionMongo.ManejadorConexiones;
import static ConexionMongo.ManejadorConexiones.obtenerCodecs;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import Entidades.Usuario;
import java.time.LocalDate;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Jaime
 */
public class UsuarioDAO implements IUsuarioDAO, IBaseMongoDAO{

    private static final String NOMBRE_COLECCION = "usuarios";
    
    @Override
    public MongoDatabase obtenerBaseDatos(MongoClient cliente) {
       return cliente.getDatabase(ManejadorConexiones.BASE_DATOS).withCodecRegistry(obtenerCodecs());
    }

    @Override
    public MongoCollection obtenerColeccion(MongoDatabase baseDatos) {
        return baseDatos.getCollection(NOMBRE_COLECCION, Usuario.class);
    }
    
    /**
     * Busca un usuario por su ID.
     * @param  idUsuario ID del Usuario
     * @return Usuario encontrado o null si no existe
     * @throws PersistenciaException si falla la consulta
     */
    @Override
    public Usuario obtenerPorId(String idUsuario) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoCollection<Usuario> col = this.obtenerColeccion(this.obtenerBaseDatos(cliente));
            Document filtro = new Document("_id", new ObjectId(idUsuario));
            return col.find(filtro).first();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener usuario por ID", e);
        }
    }

    /**
     * Busca un usuario por su nombre.
     * @param  nombre Nombre del usuario
     * @return Usuario encontrado o null si no existe
     * @throws PersistenciaException si falla la consulta
     */
    @Override
    public Usuario buscarPorNombre(String nombre) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoCollection<Usuario> col = this.obtenerColeccion(this.obtenerBaseDatos(cliente));
            Document filtro = new Document("nombre", nombre);
            return col.find(filtro).first();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar usuario por nombre", e);
        }
    }

    /**
     * Inserta un nuevo usuario y
     * asigna fechaRegistro con la fecha actual.
     * @param  usuario Entidad con nombre, contraseña y rol
     * @return Usuario guardado
     * @throws PersistenciaException si falla la inserción
     */
    @Override
    public Usuario guardar(Usuario usuario) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoCollection<Usuario> col = this.obtenerColeccion(this.obtenerBaseDatos(cliente));

            usuario.setFechaRegistro(LocalDate.now());

            InsertOneResult resultado = col.insertOne(usuario);
            if (!resultado.wasAcknowledged()) {
                throw new PersistenciaException("No se pudo guardar el usuario");
            }
            return usuario;
        } catch (PersistenciaException e) {
            throw e;
        } catch (Exception e) {
            throw new PersistenciaException("Error al guardar el usuario", e);
        }
    }

    /**
     * Verifica si existe un usuario con ese nombre y contraseña.
     * @param  nombre Nombre del usuario
     * @param  contrasenia Contraseña del usuario
     * @return true si las credenciales son correctas
     * @throws PersistenciaException si falla la consulta
     */
    @Override
    public Boolean validarCredenciales(String nombre, String contrasenia) throws PersistenciaException {
            try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoCollection<Usuario> col = this.obtenerColeccion(this.obtenerBaseDatos(cliente));
            Document filtro = new Document("nombre", nombre)
                                   .append("contrasenia", contrasenia);
            return col.countDocuments(filtro) > 0;
        } catch (Exception e) {
            throw new PersistenciaException("Error al validar credenciales", e);
        }

    }
}
