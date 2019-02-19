package logica;

import datos.Modelo;
import org.bson.types.ObjectId;
import pojos.Arma;
import pojos.Personaje;
import ui.ArmasUI;

import java.util.ArrayList;
import java.util.List;

public class ControladorArmas extends ControladorCRUD<Arma> {

    private Modelo modelo;
    public ArmasUI vista;
    private Controlador controlador;

    public ControladorArmas(Modelo modelo, ArmasUI vista, Controlador controlador) {
        super(modelo.modeloArmas, vista);
        this.modelo = modelo;
        this.vista = vista;
        this.controlador = controlador;
    }

    @Override
    public Arma nuevoDatoVacio() {
        return new Arma();
    }


    @Override
    public void cargarDatos() {
        vista.nombreTextField.setText(datoPantalla.getNombre());
        vista.ataqueTextField.setText(String.valueOf(datoPantalla.getAtaque()));
        vista.rarezaComboBox.setSelectedItem(datoPantalla.getRareza());
        vista.personajesMultiCombo.setListItems(modelo.modeloPersonajes.buscarPorIds(datoPantalla.getPersonajes()));
    }

    public void cambioEnPersonajes(List<Personaje> personajes) {
        vista.personajesMultiCombo.setComboOptions(personajes);
        cargarLista(modeloCRUD.cogerTodo());
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

        propagarCambioPersonaje(arma);

        return arma;
    }

    private void propagarCambioPersonaje(Arma arma) {
        // Personajes asociados a este arma anteriormente y personajes que nos devuelve la vista
        List<Personaje> antiguos = modelo.modeloPersonajes.buscarPorIds(datoPantalla.getPersonajes());
        List<Personaje> nuevos = vista.personajesMultiCombo.getListItems();

        // Guardar los personajes que no estaban antes y propagar el cambio
        List<Personaje> paraGuardar = new ArrayList<>(nuevos);
        paraGuardar.removeAll(antiguos);
        for (Personaje personaje : paraGuardar) {
            personaje.getArmas().add(arma.getId());
            modelo.modeloPersonajes.modificar(personaje);
            datoPantalla.getPersonajes().add(personaje.getId());
        }

        // Borrar los personajes que ya no estan y propagar el cambio
        List<Personaje> paraBorrar = new ArrayList<>(antiguos);
        paraBorrar.removeAll(nuevos);
        for (Personaje personaje : paraBorrar) {
            personaje.getArmas().remove(arma.getId());
            modelo.modeloPersonajes.modificar(personaje);
            datoPantalla.getPersonajes().remove(personaje.getId());
        }

        // Asignar la nueva lista de personajes al arma
        arma.setPersonajes(datoPantalla.getPersonajes());
    }

    @Override
    public void limpiarPantalla() {
        vista.nombreTextField.setText("");
        vista.ataqueTextField.setText("0");
        vista.rarezaComboBox.setSelectedIndex(0);
        vista.personajesMultiCombo.setListItems(new ArrayList<>());
    }
}
