package logica;

import datos.Modelo;
import org.bson.types.ObjectId;
import pojos.Movimiento;
import pojos.Personaje;
import ui.MovimientosUI;

import java.util.List;

public class ControladorMovimientos extends ControladorCRUD<Movimiento> {

    private Modelo modelo;
    public final MovimientosUI vista;
    public Controlador controlador;

    public ControladorMovimientos(Modelo modelo, MovimientosUI vista, Controlador controlador) {
        super(modelo.modeloMovimientos, vista);
        this.modelo = modelo;
        this.vista = vista;
        this.controlador = controlador;
    }

    public void cambioEnPersonajes(List<Personaje> personajes) {
        // Cargar combobox
        vista.personajeComboBox.removeAllItems();
        personajes.forEach(vista.personajeComboBox::addItem);

        // Refrescar la lista
        cargarLista(modeloCRUD.cogerTodo());
    }

    @Override
    public void cargarDatos() {
        vista.nombreTextField.setText(datoPantalla.getNombre());
        vista.energiaTextField.setText(String.valueOf(datoPantalla.getEnergia()));
        vista.personajeComboBox.setSelectedItem(modelo.buscarPersonajePorIdMovimiento(datoPantalla.getId()));
        vista.nivelTextField.setText(String.valueOf(datoPantalla.getNivel()));
    }

    @Override
    public Movimiento extraerDatos(ObjectId id) {
        Movimiento movimiento = new Movimiento();
        movimiento.setId(id);

        String textoNombre = vista.nombreTextField.getText();
        String textoEnergia = vista.energiaTextField.getText();
        String textoNivel = vista.nivelTextField.getText();

        movimiento.setNombre(!textoNombre.isEmpty() ? textoNombre : "Sin nombre");
        movimiento.setEnergia(!textoEnergia.isEmpty() ? Integer.parseInt(textoEnergia) : 0);
        movimiento.setNivel(!textoNivel.isEmpty() ? Integer.parseInt(textoNivel) : 0);

        propagarCambioPersonaje(movimiento);

        return movimiento;
    }

    private void propagarCambioPersonaje(Movimiento movimiento) {
        Personaje personaje = (Personaje) vista.personajeComboBox.getSelectedItem();
        if (personaje != null) {
            /* Si el movimiento anteriormente pertenecia a otro personaje,
             deja de hacerlo y es asignado al nuevo personaje */
            Personaje antiguo = modelo.buscarPersonajePorIdMovimiento(movimiento.getId());
            if (antiguo != null && !antiguo.equals(personaje)) {
                antiguo.setMovimiento(null);
                modelo.modeloPersonajes.modificar(antiguo);
            }
            personaje.setMovimiento(movimiento);
            modelo.modeloPersonajes.modificar(personaje);
        }
    }

    @Override
    public void limpiarPantalla() {
        vista.nombreTextField.setText("");
        vista.energiaTextField.setText("0");
        vista.personajeComboBox.setSelectedItem(null);
        vista.nivelTextField.setText("0");
    }

    @Override
    public void datosCambiados() {
        controlador.controladorPersonajes.cambioEnMovimientos(modeloCRUD.cogerTodo());
    }

    @Override
    public Movimiento nuevoDatoVacio() {
        return new Movimiento();
    }

}
