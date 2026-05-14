/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;

import Adapters.UsuarioToDocumentAdapter;
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

/**
 *
 * @author Jaime
 */
public class UsuarioDAO implements IUsuarioDAO, IBaseMongoDAO{

    private static final String NOMBRE_COLECCION = "usuarios";
    
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
    public Usuario obtenerPorId(Long idUsuario) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoCollection<Document> col = this.obtenerColeccion(this.obtenerBaseDatos(cliente));
            Document filtro = new Document("idUsuario", idUsuario);
            return UsuarioToDocumentAdapter.adaptar(col.find(filtro).first());
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener usuario por ID", e);
        }
    }

    @Override
    public Usuario buscarPorNombre(String nombre) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoCollection<Document> col = this.obtenerColeccion(this.obtenerBaseDatos(cliente));
            Document filtro = new Document("nombre", nombre);
            return UsuarioToDocumentAdapter.adaptar(col.find(filtro).first());
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar usuario por nombre", e);
        }  
    }

    @Override
    public Usuario guardar(Usuario usuario) throws PersistenciaException {
         try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoCollection<Document> col = this.obtenerColeccion(this.obtenerBaseDatos(cliente));

            Document ultimo = col.find()
                    .sort(new Document("idUsuario", -1))
                    .first();
            long nuevoId = ultimo != null ? ultimo.getLong("idUsuario") + 1 : 1L;
            usuario.setIdUsuario(nuevoId);
            usuario.setFechaRegistro(LocalDate.now());

            InsertOneResult resultado = col.insertOne(
                    UsuarioToDocumentAdapter.adaptar(usuario));
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

    @Override
    public Boolean validarCredenciales(String nombre, String contrasenia) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
         MongoCollection<Document> col = this.obtenerColeccion(this.obtenerBaseDatos(cliente));
         Document filtro = new Document("nombre", nombre)
                 .append("contrasenia", contrasenia);
         return col.countDocuments(filtro) > 0;
     } catch (Exception e) {
         throw new PersistenciaException("Error al validar credenciales", e);
     }
    }

    
    
}
