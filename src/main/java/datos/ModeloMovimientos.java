package datos;

import pojos.Movimiento;

import java.util.List;

public class ModeloMovimientos implements ModeloCRUD<Movimiento> {
    @Override
    public boolean guardar(Movimiento dato) {
        return false;
    }

    @Override
    public List<Movimiento> cogerTodo() {
        return null;
    }

    @Override
    public List<Movimiento> coger(String busqueda) {
        return null;
    }

    @Override
    public boolean modificar(Movimiento dato) {
        return false;
    }

    @Override
    public Movimiento eliminar(Movimiento dato) {
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
