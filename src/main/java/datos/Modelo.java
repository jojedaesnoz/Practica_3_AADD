package datos;

import org.bson.types.ObjectId;
import pojos.Arma;
import pojos.Movimiento;
import pojos.Personaje;

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

    public Personaje buscarPersonajePorIdMovimiento(ObjectId movimientoId) {
        return modeloPersonajes.collection.find(eq("movimiento._id", movimientoId)).first();
    }
}
