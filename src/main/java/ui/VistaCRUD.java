package ui;

import javax.swing.*;

public interface VistaCRUD<T> {

    JList<T> getLista();
    BotonesCRUD getBotones();
    BarraBusqueda getBarraBusqueda();
}
