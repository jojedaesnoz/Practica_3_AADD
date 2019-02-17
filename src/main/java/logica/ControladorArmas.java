package logica;

import datos.Modelo;
import org.bson.types.ObjectId;
import pojos.Arma;
import pojos.Personaje;
import ui.ArmasUI;

import java.util.ArrayList;
import java.util.List;

public class ControladorArmas extends ControladorCRUD<Arma> {

    public ArmasUI vista;
    private Controlador controlador;

    public ControladorArmas(Modelo modelo, ArmasUI vista, Controlador controlador) {
        super(modelo.modeloArmas, vista);
        this.vista = vista;
        this.controlador = controlador;
    }

    @Override
    public Arma nuevoDatoVacio() {
        return new Arma();
    }


    @Override
    public boolean cargarDatos() {
        vista.nombreTextField.setText(datoPantalla.getNombre());
        vista.ataqueTextField.setText(String.valueOf(datoPantalla.getAtaque()));
        vista.rarezaComboBox.setSelectedItem(datoPantalla.getRareza());
        vista.personajesMultiCombo.setListItems(datoPantalla.getPersonajes());
        return true;
    }

    public void cambioEnPersonajes(List<Personaje> personajes) {
        vista.personajesMultiCombo.setComboOptions(personajes);
    }

    @Override
    public void datosCambiados() {
        controlador.controladorPersonajes.cambioEnArmas(modeloCRUD.cogerTodo());
    }

    @Override
    public Arma extraerDatos(ObjectId id) {
        Arma arma = new Arma();
        arma.setId(id);

        String textoNombre = vista.nombreTextField.getText();
        String textoAtaque = vista.ataqueTextField.getText();

        arma.setNombre(!textoNombre.isEmpty() ? textoNombre : "Sin nombre");
        arma.setAtaque(!textoAtaque.isEmpty() ? Integer.parseInt(textoAtaque) : 0);
        arma.setRareza((Arma.Rareza) vista.rarezaComboBox.getSelectedItem());
        arma.setPersonajes(vista.personajesMultiCombo.getListItems());
        return arma;
    }

    @Override
    public void limpiarPantalla() {
        vista.nombreTextField.setText("");
        vista.ataqueTextField.setText("0");
        vista.rarezaComboBox.setSelectedIndex(0);
        vista.personajesMultiCombo.setListItems(new ArrayList<>());
    }
}
