package logica;

import datos.ModeloArmas;
import datos.ModeloCRUD;
import pojos.Arma;
import ui.ArmasUI;
import ui.BarraBusqueda;
import ui.BotonesCRUD;

import javax.swing.*;
import java.util.ArrayList;

public class ControladorArmas extends ControladorCRUD<Arma> {

    public ModeloArmas modelo;
    public ArmasUI vista;
    private final ControladorPersonajes controladorPersonajes;

    public ControladorArmas(ModeloArmas modelo, ArmasUI vista, ControladorPersonajes controladorPersonajes) {
        super(modelo);
        this.modelo = modelo;
        this.vista = vista;
        this.controladorPersonajes = controladorPersonajes;
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
    public JList<Arma> getLista() {
        return vista.listaArmas;
    }

    @Override
    public Arma extraerDatos() {
        Arma arma = new Arma();
        arma.setNombre(vista.nombreTextField.getText());
        arma.setAtaque(Integer.parseInt(vista.ataqueTextField.getText()));
        arma.setRareza(Integer.parseInt(vista.rarezaTextField.getText()));
        arma.setDurabilidad(Integer.parseInt(vista.durabilidadTextField.getText()));
        return arma;
    }

    @Override
    public void cargarDatos(Arma dato) {
        vista.nombreTextField.setText(dato.getNombre());
        vista.ataqueTextField.setText(String.valueOf(dato.getAtaque()));
        vista.rarezaTextField.setText(String.valueOf(dato.getRareza()));
        vista.durabilidadTextField.setText(String.valueOf(dato.getDurabilidad()));
    }

    @Override
    protected void datosCambiados(ArrayList<Arma> listaDatos) {
        /*
        Cuando cambia la lista de armas, hay que actualizar
        el multiCombo en personajes
         */
        controladorPersonajes.cargarMultiComboArmas(listaDatos);
    }

    @Override
    public void limpiarPantalla() {
        vista.nombreTextField.setText("");
        vista.ataqueTextField.setText("");
        vista.rarezaTextField.setText("");
        vista.durabilidadTextField.setText("");
    }
}
