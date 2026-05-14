/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConexionMongo;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.codecs.configuration.CodecProvider;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 *
 * @author Jaime
 */
public class ManejadorConexiones {
    
    public static final String CADENA_CONEXION = "mongodb://localhost:27017";
    public static final String BASE_DATOS = "FITLIFEGYM";

    public static MongoClient crearConexion() {
        return MongoClients.create(CADENA_CONEXION);
    }

    public static CodecRegistry obtenerCodecs() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder()
                .automatic(true).build();
        return fromRegistries(
                getDefaultCodecRegistry(),
                fromProviders(pojoCodecProvider)
        );
    }
    
}
