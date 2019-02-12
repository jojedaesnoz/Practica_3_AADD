package locura;

import java.util.List;

public interface Modelo<T> {

    // CREATE
    boolean guardar(T dato);

    // READ
    List<T> cogerTodo();
    List<T> coger(String busqueda);

    // UPDATE
    boolean modificar(T dato);

    // DELETE
    T eliminar(T dato);
    boolean eliminarTodo();
    boolean deshacer();
}
