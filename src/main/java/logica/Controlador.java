package logica;

import datos.Modelo;
import ui.Vista;

public class Controlador {
    private final Modelo modelo;
    private final Vista vista;

    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }
}
