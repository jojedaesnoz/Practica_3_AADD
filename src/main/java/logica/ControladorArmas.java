package logica;

import datos.Modelo;
import org.bson.types.ObjectId;
import pojos.Arma;
import pojos.Personaje;
import ui.ArmasUI;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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
    public boolean cargarDatos() {
        vista.nombreTextField.setText(datoPantalla.getNombre());
        vista.ataqueTextField.setText(String.valueOf(datoPantalla.getAtaque()));
        vista.rarezaComboBox.setSelectedItem(datoPantalla.getRareza());
//        vista.personajesMultiCombo.setListItems(datoPantalla.getPersonajes());

//        ArrayList<Personaje> personajes = new ArrayList<>();
//        for (ObjectId personajeId : datoPantalla.getPersonajes()) {
//            Personaje personaje = modelo.modeloPersonajes.buscarPorId(personajeId);
//            personajes.add(personaje);
//        }
//        vista.personajesMultiCombo.setListItems(personajes);


        vista.personajesMultiCombo.setListItems(
                datoPantalla.getPersonajes().stream()
                .sorted()
                .map(id -> modelo.modeloPersonajes.buscarPorId(id))
                .collect(Collectors.toList()));
        datoPantalla.getPersonajes().forEach(System.out::println);

        return true;
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
//        arma.setPersonajes(vista.personajesMultiCombo.getListItems()
//                .stream().map(Personaje::getId).collect(Collectors.toList()));

        /*
        Asignar este arma a los personajes
        Modificar los personajes para que se guarde el cambio
        Asignar a este arma los ids de los personajes
         */

        for (Personaje personaje : vista.personajesMultiCombo.getListItems()) {
            if (!personaje.getArmas().contains(id)) {
                personaje.getArmas().add(id);
                modelo.modeloPersonajes.modificar(personaje);
                arma.getPersonajes().add(personaje.getId());
            }
        }
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
