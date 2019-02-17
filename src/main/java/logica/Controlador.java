package logica;

import datos.Modelo;
import pojos.Arma;
import pojos.Movimiento;
import pojos.Personaje;
import ui.Vista;

import java.util.ArrayList;

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

    public void armasCambiadas(ArrayList<Arma> armas) {
        controladorPersonajes.cambioEnArmas(armas);
    }

    public void personajesCambiados(ArrayList<Personaje> personajes) {
        controladorMovimientos.cambioEnPersonajes(personajes);
    }

    public void movimientosCambiados(ArrayList<Movimiento> movimientos) {
        controladorPersonajes.cambioEnMovimientos(movimientos);
    }
}
