package ui;

import ui.BarraBusqueda;
import ui.BotonesCRUD;

import javax.swing.*;

public interface VistaCRUD<T> {

    BotonesCRUD getBotonesCRUD();
    BarraBusqueda getBarraBusqueda();
    JList<T> getLista();
}
