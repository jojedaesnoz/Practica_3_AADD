package datos;

import pojos.Arma;
import pojos.Movimiento;
import pojos.Personaje;

public class Modelo {

    public ModeloCRUD<Movimiento> modeloMovimientos;
    public ModeloCRUD<Personaje> modeloPersonajes;
    public ModeloCRUD<Arma> modeloArmas;

    public Modelo() {
        modeloArmas = new ModeloCRUD<>("armas", Arma.class);
        modeloPersonajes = new ModeloCRUD<>("personajes", Personaje.class);
        modeloMovimientos = new ModeloCRUD<>("movimientos", Movimiento.class);
    }
}
