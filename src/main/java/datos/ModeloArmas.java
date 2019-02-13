package datos;

import pojos.Arma;

import java.util.List;

public class ModeloArmas implements ModeloCRUD<Arma> {
    @Override
    public boolean guardar(Arma dato) {
        return false;
    }

    @Override
    public List<Arma> cogerTodo() {
        return null;
    }

    @Override
    public List<Arma> coger(String busqueda) {
        return null;
    }

    @Override
    public boolean modificar(Arma dato) {
        return false;
    }

    @Override
    public Arma eliminar(Arma dato) {
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
