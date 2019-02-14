package logica;

import datos.ModeloCRUD;
import pojos.Movimiento;
import pojos.Personaje;
import ui.MovimientosUI;

import java.util.List;

public class ControladorMovimientos extends ControladorCRUD<Movimiento> {

    public final MovimientosUI vista;
    public Controlador controlador;

    public ControladorMovimientos(ModeloCRUD<Movimiento> modelo, MovimientosUI vista, Controlador controlador) {
        super(modelo, vista);
        this.vista = vista;
        this.controlador = controlador;
    }

    public void cargarComboPersonajes(List<Personaje> personajes) {
        vista.personajeComboBox.removeAllItems();
        personajes.forEach(vista.personajeComboBox::addItem);
    }

    @Override
    public boolean clickEnGuardar() {
        if (origen.equals(Origen.MODIFICAR)) {
            Movimiento movimiento = extraerDatos();
            movimiento.setId(datoPantalla.getId());
            return modeloCRUD.modificar(movimiento);
        } else {
            Movimiento movimiento = extraerDatos();
            return modeloCRUD.guardar(movimiento);
        }
    }

    @Override
    public boolean cargarDatos() {
        vista.nombreTextField.setText(datoPantalla.getNombre());
        vista.energiaTextField.setText(String.valueOf(datoPantalla.getEnergia()));
        Personaje personaje = datoPantalla.getPersonaje();
        if (personaje != null) {
            vista.personajeComboBox.setSelectedItem(datoPantalla.getPersonaje());
        } else {
            vista.personajeComboBox.setSelectedIndex(0);
        }
        vista.nivelTextField.setText(String.valueOf(datoPantalla.getNivel()));
        return true;
    }

    @Override
    public Movimiento extraerDatos() {
        Movimiento movimiento = new Movimiento();

        String textoNombre = vista.nombreTextField.getText();
        String textoEnergia = vista.energiaTextField.getText();
        Personaje personaje = (Personaje) vista.personajeComboBox.getSelectedItem();
        String textoNivel = vista.nivelTextField.getText();

        movimiento.setNombre(!textoNombre.isEmpty() ? textoNombre : "Sin nombre");
        movimiento.setEnergia(!textoEnergia.isEmpty() ? Integer.parseInt(textoEnergia) : 0);
        movimiento.setPersonaje(personaje);
        movimiento.setNivel(!textoNivel.isEmpty() ? Integer.parseInt(textoNivel) : 0);

        return movimiento;
    }

    @Override
    public void limpiarPantalla() {
        vista.nombreTextField.setText("");
        vista.energiaTextField.setText("0");
//        vista.personajeComboBox.setSelectedIndex(0);
        vista.nivelTextField.setText("0");
    }

    @Override
    public void datosCambiados() {
        controlador.movimientosCambiados(listaDatos);
    }

    @Override
    public Movimiento nuevoDatoVacio() {
        return new Movimiento();
    }

    //    @Override
//    public void onDatosCambiados(ArrayList<Movimiento> listaDatos) {
//        controlador.movimientosCambiados(listaDatos);
//    }
//
//    @Override
//    public BotonesCRUD getBotonesCRUD() {
//        return vista.botones;
//    }
//
//    @Override
//    public BarraBusqueda getBarraBusqueda() {
//        return vista.barraBusqueda;
//    }
//
//    @Override
//    public JList<Movimiento> getLista() {
//        return vista.listaMovimientos;
//    }
//
//    @Override
//    public Movimiento extraerDatos() {
//        Movimiento movimiento = new Movimiento();
//        movimiento.setNombre(vista.nombreTextField.getText());
//        movimiento.setEnergia(Integer.parseInt(vista.energiaTextField.getText()));
//        movimiento.setPersonaje((Personaje) vista.personajeComboBox.getSelectedItem());
//        movimiento.setNivel(Integer.parseInt(vista.nivelTextField.getText()));
//        return movimiento;
//    }
//
//    @Override
//    public void cargarDatos(Movimiento dato) {
//        vista.nombreTextField.setText(dato.getNombre());
//        vista.energiaTextField.setText(String.valueOf(dato.getEnergia()));
//        vista.personajeComboBox.setSelectedItem(dato.getPersonaje());
//        vista.nivelTextField.setText(String.valueOf(dato.getNivel()));
//    }
//
//    @Override
//    public void limpiarPantalla() {
//        vista.nombreTextField.setText("");
//        vista.energiaTextField.setText("");
//        vista.personajeComboBox.setSelectedItem("");
//        vista.nivelTextField.setText("");
//    }
}
