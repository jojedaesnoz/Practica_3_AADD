package datos;

import com.mongodb.client.FindIterable;
import org.bson.types.ObjectId;
import pojos.Arma;
import pojos.Movimiento;
import pojos.Personaje;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.elemMatch;
import static com.mongodb.client.model.Filters.eq;

public class Modelo {

    public ModeloCRUD<Movimiento> modeloMovimientos;
    public ModeloCRUD<Personaje> modeloPersonajes;
    public ModeloCRUD<Arma> modeloArmas;

    public Modelo() {
        modeloArmas = new ModeloCRUD<>("armas", Arma.class);
        modeloPersonajes = new ModeloCRUD<>("personajes", Personaje.class);
        modeloMovimientos = new ModeloCRUD<>("movimientos", Movimiento.class);
    }

    public Personaje getPersonajeWhereIdMovimiento(ObjectId movimientoId) {
        return modeloPersonajes.collection.find(eq("movimiento._id", movimientoId)).first();
    }

    public List<Personaje> getPersonajeWhereIdArma(ObjectId armaId) {
        return modeloPersonajes.collection.find(elemMatch("armas", eq(armaId))).into(new ArrayList<>());
    }
}
