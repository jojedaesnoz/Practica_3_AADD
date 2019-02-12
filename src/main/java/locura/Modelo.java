package locura;

import java.util.List;

public interface Modelo<T> {

    // CREATE
    void nuevo(T dato);

    // READ
    List<T> cogerTodo();
    T coger(String busqueda);

    // UPDATE
    void modificar(T dato);

    // DELETE
    T eliminar(T dato);
    boolean eliminarTodo();
}
