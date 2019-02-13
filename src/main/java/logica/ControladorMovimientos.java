package logica;

import datos.ModeloCRUD;
import datos.ModeloMovimientos;
import pojos.Movimiento;
import pojos.Personaje;
import ui.BarraBusqueda;
import ui.BotonesCRUD;
import ui.MovimientosUI;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ControladorMovimientos extends ControladorCRUD<Movimiento> {

    public ModeloMovimientos modelo;
    public final MovimientosUI vista;
    public ControladorPersonajes controladorPersonajes;

    public ControladorMovimientos(ModeloMovimientos modelo, MovimientosUI vista, ControladorPersonajes controladorPersonajes) {
        super(modelo);
        this.modelo = modelo;
        this.vista = vista;
        this.controladorPersonajes = controladorPersonajes;
    }

    public void cargarComboPersonajes(List<Personaje> personajes) {
        vista.personajeComboBox.removeAllItems();
        personajes.forEach(vista.personajeComboBox::addItem);
    }

    @Override
    protected void datosCambiados(ArrayList<Movimiento> listaDatos) {
        /*
        Cuando cambie la lista de movimientos especiales, deberemos
        actualizar las opciones del combobox en personajes
         */
        controladorPersonajes.cargarComboMovimientos(listaDatos);
    }

    @Override
    public BotonesCRUD getBotonesCRUD() {
        return vista.botones;
    }

    @Override
    public BarraBusqueda getBarraBusqueda() {
        return vista.barraBusqueda;
    }

    @Override
    public JList<Movimiento> getLista() {
        return vista.listaMovimientos;
    }

    @Override
    public Movimiento extraerDatos() {
        Movimiento movimiento = new Movimiento();
        movimiento.setNombre(vista.nombreTextField.getText());
        movimiento.setEnergia(Integer.parseInt(vista.energiaTextField.getText()));
        movimiento.setPersonaje((Personaje) vista.personajeComboBox.getSelectedItem());
        movimiento.setNivel(Integer.parseInt(vista.nivelTextField.getText()));
        return movimiento;
    }

    @Override
    public void cargarDatos(Movimiento dato) {
        vista.nombreTextField.setText(dato.getNombre());
        vista.energiaTextField.setText(String.valueOf(dato.getEnergia()));
        vista.personajeComboBox.setSelectedItem(dato.getPersonaje());
        vista.nivelTextField.setText(String.valueOf(dato.getNivel()));
    }

    @Override
    public void limpiarPantalla() {
        vista.nombreTextField.setText("");
        vista.energiaTextField.setText("");
        vista.personajeComboBox.setSelectedItem("");
        vista.nivelTextField.setText("");
    }
}
