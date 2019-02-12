package logica;

import datos.Modelo;
import pojos.Arma;
import pojos.Movimiento;
import pojos.Personaje;
import ui.Vista;

public class Controlador {
    private final Modelo modelo;
    private final Vista vista;
    ArmControlador armListener;
    MovControlador movListener;
    PersControlador persListener;

    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;

        ponerListeners();
    }

    // Sacar los datos introducidos por pantalla
    public Personaje extraerDatosPersonaje() {
        Personaje personaje = new Personaje();

        return personaje;
    }

    public Arma extraerDatosArma() {
        Arma personaje = new Arma();

        return personaje;
    }

    public Movimiento extraerDatosMovimiento() {
        Movimiento personaje = new Movimiento();

        return personaje;
    }

    // Cargar datos en la pantalla
    public void introducirDatosPersonaje() {

    }

    public void introducirDatosArma() {

    }

    public void introducirDatosMovimiento() {

    }

    private void ponerListeners() {
        armListener = new ArmControlador(vista.botoneraArmas, vista.armasList, this);
        movListener = new MovControlador(vista.botoneraMovimientos, vista.movimientosList, this);
        persListener = new PersControlador(vista.botoneraPersonajes, vista.personajeList, this);
    }
}
