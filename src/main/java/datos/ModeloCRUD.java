package datos;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import pojos.Pojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class ModeloCRUD<T extends Pojo> {
    public static final String NOMBRE_DB = "CREADOR";
    public MongoClient mongoClient;
    public MongoDatabase db;
    public MongoCollection<T> collection;
    public T ultimoBorrado;
    
    public ModeloCRUD(String nombreColeccion, Class<T> clase) {
        conectar();
        collection = db.getCollection(nombreColeccion, clase);
    }
    
    private void conectar() {
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        mongoClient = new MongoClient("localhost", MongoClientOptions.builder()
                .codecRegistry(pojoCodecRegistry).build());
        db = mongoClient.getDatabase(NOMBRE_DB);
    }

    public boolean guardar(T dato) {
        collection.insertOne(dato);
        return true;
    }

    public boolean guardar(List<T> datos) {
        collection.insertMany(datos);
        return true;
    }
    
    public List<T> cogerTodo() {
        return collection.find().into(new ArrayList<>());
    }

    public List<T> buscarPorNombre(String busqueda) {
        // Devuelve una lista con documentos cuyo nombre contenga la cadena de busqueda (case insensitive)
        return collection.find(regex("nombre", ".*" + busqueda + ".*", "i"))
                .into(new ArrayList<>());
    }

    public T buscarPorId(ObjectId id) {
        return collection.find(eq("_id", id)).first();
    }

    public List<T> buscarPorIds(ObjectId... ids) {
        return buscarPorIds(Arrays.asList(ids));
    }

    public List<T> buscarPorIds(List<ObjectId> ids) {
        return collection.find(in("_id", ids)).into(new ArrayList<>());
    }

    public boolean modificar(T dato) {
        return collection.replaceOne(eq("_id", dato.getId()), dato).wasAcknowledged();
    }
    
    public T eliminar(T dato) {
        collection.deleteOne(eq(dato.getId()));
        ultimoBorrado = dato;
        return dato;
    }
    
    public boolean eliminarTodo() {
        return collection.deleteMany(exists("nombre")).wasAcknowledged();
    }
    
    public T deshacer() {
        if (ultimoBorrado != null) {
            collection.insertOne(ultimoBorrado);
        }
        return ultimoBorrado;
    }
}
