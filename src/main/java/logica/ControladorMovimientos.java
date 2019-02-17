package logica;

import datos.Modelo;
import org.bson.types.ObjectId;
import pojos.Movimiento;
import pojos.Personaje;
import ui.MovimientosUI;

import java.util.ArrayList;
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
    public boolean cargarDatos() {
        vista.nombreTextField.setText(datoPantalla.getNombre());
        vista.energiaTextField.setText(String.valueOf(datoPantalla.getEnergia()));
//        vista.personajeComboBox.setSelectedItem(datoPantalla.getPersonaje());
        Personaje personaje;

        // TODO: coger el personaje que tiene este movimiento como suyo, tal vez se pueda hacer desde personajes?
//        personaje = modelo.modeloPersonajes.co
        vista.personajeComboBox.setSelectedItem(modelo.buscarPersonajeSegunMovimiento(datoPantalla));
        vista.nivelTextField.setText(String.valueOf(datoPantalla.getNivel()));
        return true;
    }

    @Override
    public Movimiento extraerDatos(ObjectId id) {
        Movimiento movimiento = new Movimiento();
        movimiento.setId(id);

        String textoNombre = vista.nombreTextField.getText();
        String textoEnergia = vista.energiaTextField.getText();
        Personaje personaje = (Personaje) vista.personajeComboBox.getSelectedItem();
        String textoNivel = vista.nivelTextField.getText();

        movimiento.setNombre(!textoNombre.isEmpty() ? textoNombre : "Sin nombre");
        movimiento.setEnergia(!textoEnergia.isEmpty() ? Integer.parseInt(textoEnergia) : 0);
        movimiento.setNivel(!textoNivel.isEmpty() ? Integer.parseInt(textoNivel) : 0);

        if (personaje != null) {
            /* Si el movimiento anteriormente pertenecia a otro personaje,
             deja de hacerlo y es asignado al nuevo personaje */
            Personaje antiguo = modelo.buscarPersonajeSegunMovimiento(movimiento);
            if (antiguo != null && !antiguo.equals(personaje)) {
                antiguo.setMovimiento(null);
                modelo.modeloPersonajes.modificar(antiguo);
            }
            personaje.setMovimiento(movimiento);
            modelo.modeloPersonajes.modificar(personaje);
        }

        return movimiento;
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
