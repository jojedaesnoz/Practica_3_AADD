package logica;

import datos.Modelo;
import org.bson.types.ObjectId;
import pojos.Arma;
import pojos.Movimiento;
import pojos.Personaje;
import ui.PersonajesUI;

import java.util.ArrayList;
import java.util.List;

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
    public void cargarDatos() {
        vista.nombreTextField.setText(datoPantalla.getNombre());
        vista.movimientoComboBox.setSelectedItem(datoPantalla.getMovimiento());
        vista.armasMultiCombo.setListItems(modelo.modeloArmas.buscarPorIds(datoPantalla.getArmas()));
        vista.vidaTextField.setText(String.valueOf(datoPantalla.getVida()));
    }

    @Override
    public Personaje extraerDatos(ObjectId id) {
        Personaje personaje = new Personaje();
        personaje.setId(id);

        String textoNombre = vista.nombreTextField.getText();
        String textoVida = vista.vidaTextField.getText();

        personaje.setNombre(!textoNombre.isEmpty() ? textoNombre : "Sin nombre");
        personaje.setVida(!textoVida.isEmpty() ? Integer.parseInt(textoVida) : 0);

        propagarCambioMovimiento(personaje);
        propagarCambioArmas(personaje);

        return personaje;
    }

    private void propagarCambioMovimiento(Personaje personaje) {
        /* Si el movimiento anteriormente pertenecia a otro personaje,
        deja de hacerlo y es asignado al nuevo personaje */
        Movimiento movimiento = (Movimiento) vista.movimientoComboBox.getSelectedItem();
        if (movimiento != null) {
            Personaje antiguo = modelo.buscarPersonajePorIdMovimiento(movimiento.getId());
            if (antiguo != null && !antiguo.equals(personaje)) {
                antiguo.setMovimiento(null);
                modeloCRUD.modificar(antiguo);
            }
            personaje.setMovimiento(movimiento);
        }
    }


    private void propagarCambioArmas(Personaje personaje) {
        // Listas de armas que tenia el personaje antes y que nos ha devuelto la vista
        List<Arma> nuevas = vista.armasMultiCombo.getListItems();
        List<Arma> antiguas = modelo.modeloArmas.buscarPorIds(datoPantalla.getArmas());

        // Guardar los personajes que antes no estaban y propagar el cambio
        List<Arma> paraGuardar = new ArrayList<>(nuevas);
        paraGuardar.removeAll(antiguas);
        for (Arma arma : paraGuardar) {
            arma.getPersonajes().add(personaje.getId());
            modelo.modeloArmas.modificar(arma);
            datoPantalla.getArmas().add(arma.getId());
        }

        // Borrar las armas que ya no estan y propagar el cambio
        List<Arma> paraBorrar = new ArrayList<>(antiguas);
        paraBorrar.removeAll(nuevas);
        for (Arma arma : paraBorrar) {
            arma.getPersonajes().remove(personaje.getId());
            modelo.modeloArmas.modificar(arma);
            datoPantalla.getArmas().remove(arma.getId());
        }

        // Asignar la nueva lista al personaje
        personaje.setArmas(datoPantalla.getArmas());
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
