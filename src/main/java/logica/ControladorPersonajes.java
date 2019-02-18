package logica;

import datos.Modelo;
import org.bson.types.ObjectId;
import pojos.Arma;
import pojos.Movimiento;
import pojos.Personaje;
import ui.PersonajesUI;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ControladorPersonajes extends ControladorCRUD<Personaje> {

    private Modelo modelo;
    public PersonajesUI vista;
    private Controlador controlador;

    public ControladorPersonajes(Modelo modelo, PersonajesUI vista, Controlador controlador) {
        super(modelo.modeloPersonajes, vista);
        this.modelo = modelo;
        this.vista = vista;
        this.controlador = controlador;
    }

    public void cambioEnMovimientos(List<Movimiento> movimientos) {
        // Cargar combobox de movimientos
        vista.movimientoComboBox.removeAllItems();
        movimientos.forEach(vista.movimientoComboBox::addItem);

        // Refrescar la lista
        cargarLista(modeloCRUD.cogerTodo());
    }

    public void cambioEnArmas(List<Arma> listaDatos) {
        vista.armasMultiCombo.setComboOptions(listaDatos);
        cargarLista(modeloCRUD.cogerTodo());
    }

    @Override
    public boolean cargarDatos() {
        vista.nombreTextField.setText(datoPantalla.getNombre());
        vista.movimientoComboBox.setSelectedItem(datoPantalla.getMovimiento());

//        vista.armasMultiCombo.setListItems(datoPantalla.getArmas());

        vista.armasMultiCombo.setListItems(
                datoPantalla.getArmas().stream()
                .map(idArma -> modelo.modeloArmas.buscarPorId(idArma))
                .sorted()
                .collect(Collectors.toList()));

        vista.vidaTextField.setText(String.valueOf(datoPantalla.getVida()));
        return true;
    }

    @Override
    public Personaje extraerDatos(ObjectId id) {
        Personaje personaje = new Personaje();
        personaje.setId(id);

        String textoNombre = vista.nombreTextField.getText();
        Movimiento movimiento = (Movimiento) vista.movimientoComboBox.getSelectedItem();
        String textoVida = vista.vidaTextField.getText();

        /* Si el movimiento anteriormente pertenecia a otro personaje,
        deja de hacerlo y es asignado al nuevo personaje */
        if (movimiento != null) {
            Personaje antiguo = modelo.getPersonajeWhereIdMovimiento(movimiento.getId());
            if (antiguo != null && !antiguo.equals(personaje)) {
                antiguo.setMovimiento(null);
                modeloCRUD.modificar(antiguo);
            }
            personaje.setMovimiento(movimiento);
        }

        personaje.setNombre(!textoNombre.isEmpty() ? textoNombre : "Sin nombre");
//        List<Arma> armasList = vista.armasMultiCombo.getListItems();
//        personaje.setArmas(armasList);

//        personaje.setArmas(vista.armasMultiCombo.getListItems().stream()
//                .map(Arma::getId).collect(Collectors.toList()));

        for (Arma arma : vista.armasMultiCombo.getListItems()) {
            if (!arma.getPersonajes().contains(id)) {
                arma.getPersonajes().add(personaje.getId());
                modelo.modeloArmas.modificar(arma);
                personaje.getArmas().add(arma.getId());
            }
        }

        personaje.setVida(!textoVida.isEmpty() ? Integer.parseInt(textoVida) : 0);

        return personaje;
    }

    @Override
    public void limpiarPantalla() {
        vista.nombreTextField.setText("");
        vista.movimientoComboBox.setSelectedItem(null);
        vista.armasMultiCombo.setListItems(new ArrayList<>());
        vista.vidaTextField.setText("0");
    }

    @Override
    public void datosCambiados() {
        List<Personaje> personajes = modeloCRUD.cogerTodo();
        controlador.controladorArmas.cambioEnPersonajes(personajes);
        controlador.controladorMovimientos.cambioEnPersonajes(personajes);
    }

    @Override
    public Personaje nuevoDatoVacio() {
        return new Personaje();
    }
}
