package datos;

import pojos.Personaje;

import java.util.List;

public class ModeloPersonajes implements ModeloCRUD<Personaje> {
    @Override
    public boolean guardar(Personaje dato) {
        return false;
    }

    @Override
    public List<Personaje> cogerTodo() {
        return null;
    }

    @Override
    public List<Personaje> coger(String busqueda) {
        return null;
    }

    @Override
    public boolean modificar(Personaje dato) {
        return false;
    }

    @Override
    public Personaje eliminar(Personaje dato) {
        return null;
    }

    @Override
    public boolean eliminarTodo() {
        return false;
    }

    @Override
    public boolean deshacer() {
        return false;
    }
}
