package datos;

import java.util.List;

public interface ModeloCRUD<T> {

    // CREATE
    void guardar(T dato);

    // READ
    List<T> cogerTodo();
    List<T> coger(String busqueda);

    // UPDATE
    void modificar(T dato);

    // DELETE
    T eliminar(T dato);
    void eliminarTodo();
    void deshacer();
}
