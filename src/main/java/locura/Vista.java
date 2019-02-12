package locura;

import ui.BarraBusqueda;
import ui.BotonesCRUD;

import javax.swing.*;

public interface Vista<T> {

    BotonesCRUD getBotonesCRUD();
    BarraBusqueda getBarraBusqueda();
    JList<T> getLista();
}
