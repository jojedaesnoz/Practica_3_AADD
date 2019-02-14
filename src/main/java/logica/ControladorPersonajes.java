package logica;

import datos.ModeloCRUD;
import pojos.Arma;
import pojos.Movimiento;
import pojos.Personaje;
import ui.PersonajesUI;

import java.util.ArrayList;
import java.util.List;

public class ControladorPersonajes extends ControladorCRUD<Personaje> {

    public PersonajesUI vista;
    private Controlador controlador;

    public ControladorPersonajes(ModeloCRUD<Personaje> modelo, PersonajesUI vista, Controlador controlador) {
        super(modelo, vista);
        this.vista = vista;
        this.controlador = controlador;
    }

    public void cargarComboMovimientos(List<Movimiento> movimientos) {
        vista.movimientoComboBox.removeAllItems();
        movimientos.forEach(vista.movimientoComboBox::addItem);
    }

    public void cargarMultiComboArmas(ArrayList<Arma> listaDatos) {
        vista.armasMultiCombo.setComboOptions(listaDatos);
        listaDatos.forEach(System.out::println);
    }

    @Override
    public boolean clickEnGuardar() {
        if (origen.equals(Origen.MODIFICAR)) {
            Personaje personaje = extraerDatos();
            personaje.setId(datoPantalla.getId());
            return modeloCRUD.modificar(personaje);
        } else {
            Personaje personaje = extraerDatos();
            return modeloCRUD.guardar(personaje);
        }
    }

    @Override
    public boolean cargarDatos() {
        vista.nombreTextField.setText(datoPantalla.getNombre());
        vista.movimientoComboBox.setSelectedItem(datoPantalla.getMovimiento());
        vista.armasMultiCombo.setListItems(datoPantalla.getArmas());
        vista.vidaTextField.setText(String.valueOf(datoPantalla.getVida()));
        return true;
    }

    @Override
    public Personaje extraerDatos() {
        Personaje personaje = new Personaje();

        String textoNombre = vista.nombreTextField.getText();
        Movimiento movimiento = (Movimiento) vista.movimientoComboBox.getSelectedItem();
        List<Arma> armasList = vista.armasMultiCombo.getListItems();
        String textoVida = vista.vidaTextField.getText();

        personaje.setNombre(!textoNombre.isEmpty() ? textoNombre : "Sin nombre");
        personaje.setArmas(armasList);
        personaje.setMovimiento(movimiento);
        personaje.setVida(!textoVida.isEmpty() ? Integer.parseInt(textoVida) : 0);

        return personaje;
    }

    @Override
    public void limpiarPantalla() {
        vista.nombreTextField.setText("");
//        vista.movimientoComboBox.setSelectedIndex(0);
        vista.armasMultiCombo.setListItems(new ArrayList<>());
        vista.vidaTextField.setText("0");
    }

    @Override
    public void datosCambiados() {
        controlador.personajesCambiados((ArrayList<Personaje>) modeloCRUD.cogerTodo());
    }

    @Override
    public Personaje nuevoDatoVacio() {
        return new Personaje();
    }

    //    @Override
//    public Personaje extraerDatos() {
//        Personaje personaje = new Personaje();
//        personaje.setNombre(vista.nombreTextField.getText());
//        personaje.setMovimiento((Movimiento) vista.movimientoComboBox.getSelectedItem());
//        personaje.setArmas(vista.armasMultiCombo.getListItems());
//        personaje.setVida(Integer.parseInt(vista.vidaTextField.getText()));
//        return personaje;
//    }
//
//    @Override
//    public void cargarDatos(Personaje dato) {
//        vista.nombreTextField.setText(dato.getNombre());
//        vista.movimientoComboBox.setSelectedItem(dato.getMovimiento());
//        vista.armasMultiCombo.setListItems(dato.getArmas());
//        vista.vidaTextField.setText(String.valueOf(dato.getVida()));
//    }
//
//    @Override
//    public void limpiarPantalla() {
//        vista.nombreTextField.setText("");
//        vista.movimientoComboBox.removeAllItems();
//        vista.armasMultiCombo.setListItems(new ArrayList<>());
//        vista.vidaTextField.setText("");
//    }
//
//    @Override
//    BotonesCRUD getBotonesCRUD() {
//        return vista.botones;
//    }
//
//    @Override
//    BarraBusqueda getBarraBusqueda() {
//        return vista.barraBusqueda;
//    }
//
//    @Override
//    JList<Personaje> getLista() {
//        return vista.listaPersonajes;
//    }
//
//    public void cargarMultiComboArmas(ArrayList<Arma> listaDatos) {
//        vista.armasMultiCombo.setListItems(listaDatos);
//    }
}
