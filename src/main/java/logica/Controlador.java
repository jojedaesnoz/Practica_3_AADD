package logica;

import datos.Modelo;
import ui.Vista;

public class Controlador {

    ControladorArmas controladorArmas;
    ControladorPersonajes controladorPersonajes;
    ControladorMovimientos controladorMovimientos;

    public Controlador(Modelo modelo, Vista vista) {
        controladorArmas = new ControladorArmas(modelo, vista.armasUI, this);
        controladorPersonajes = new ControladorPersonajes(modelo, vista.personajesUI, this);
        controladorMovimientos = new ControladorMovimientos(modelo, vista.movimientosUI, this);

        controladorArmas.datosCambiados();
        controladorMovimientos.datosCambiados();
        controladorPersonajes.datosCambiados();
    }
}
