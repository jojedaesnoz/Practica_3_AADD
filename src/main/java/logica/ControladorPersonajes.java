package logica;

import datos.ModeloCRUD;
import datos.ModeloPersonajes;
import pojos.Arma;
import pojos.Movimiento;
import pojos.Personaje;
import ui.BarraBusqueda;
import ui.BotonesCRUD;
import ui.PersonajesUI;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ControladorPersonajes extends ControladorCRUD<Personaje> {

    private ModeloPersonajes modelo;
    public PersonajesUI vista;
    private final ControladorMovimientos controladorMovimientos;
    private final ControladorArmas controladorArmas;

    public ControladorPersonajes(ModeloPersonajes modelo, PersonajesUI vista, ControladorMovimientos controladorMovimientos, ControladorArmas controladorArmas) {
        super(modelo);
        this.modelo = modelo;
        this.vista = vista;
        this.controladorMovimientos = controladorMovimientos;
        this.controladorArmas = controladorArmas;
    }

    public void cargarComboMovimientos(List<Movimiento> movimientos) {
        vista.movimientoComboBox.removeAllItems();
        movimientos.forEach(vista.movimientoComboBox::addItem);
    }

    @Override
    protected void datosCambiados(ArrayList<Personaje> listaDatos) {
        /*
        Cuando cambie la lista de personajes, deberemos
        actualizar las opciones del combobox en movimientos
         */
        controladorMovimientos.cargarComboPersonajes(listaDatos);
    }

    @Override
    public Personaje extraerDatos() {
        Personaje personaje = new Personaje();
        personaje.setNombre(vista.nombreTextField.getText());
        personaje.setMovimiento((Movimiento) vista.movimientoComboBox.getSelectedItem());
        personaje.setArmas(vista.armasMultiCombo.getItems());
        personaje.setVida(Integer.parseInt(vista.vidaTextField.getText()));
        return personaje;
    }

    @Override
    public void cargarDatos(Personaje dato) {
        vista.nombreTextField.setText(dato.getNombre());
        vista.movimientoComboBox.setSelectedItem(dato.getMovimiento());
        vista.armasMultiCombo.setItems(dato.getArmas());
        vista.vidaTextField.setText(String.valueOf(dato.getVida()));
    }

    @Override
    public void limpiarPantalla() {
        vista.nombreTextField.setText("");
        vista.movimientoComboBox.removeAllItems();
        vista.armasMultiCombo.setItems(new ArrayList<>());
        vista.vidaTextField.setText("");
    }

    @Override
    BotonesCRUD getBotonesCRUD() {
        return vista.botones;
    }

    @Override
    BarraBusqueda getBarraBusqueda() {
        return vista.barraBusqueda;
    }

    @Override
    JList<Personaje> getLista() {
        return vista.listaPersonajes;
    }

    public void cargarMultiComboArmas(ArrayList<Arma> listaDatos) {
        vista.armasMultiCombo.setItems(listaDatos);
    }
}
