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
        controladorArmas = new ControladorArmas(modelo.modeloArmas, vista.armasUI, this);
        controladorPersonajes = new ControladorPersonajes(modelo.modeloPersonajes, vista.personajesUI, this);
        controladorMovimientos = new ControladorMovimientos(modelo.modeloMovimientos, vista.movimientosUI, this);

        controladorArmas.datosCambiados();
        controladorMovimientos.datosCambiados();
        controladorPersonajes.datosCambiados();
    }

    public void armasCambiadas(ArrayList<Arma> armas) {
        controladorPersonajes.cargarMultiComboArmas(armas);
    }

    public void personajesCambiados(ArrayList<Personaje> personajes) {
        controladorMovimientos.cargarComboPersonajes(personajes);
    }

    public void movimientosCambiados(ArrayList<Movimiento> movimientos) {
        controladorPersonajes.cargarComboMovimientos(movimientos);
    }
}
