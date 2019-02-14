package logica;

import datos.ModeloCRUD;
import pojos.Arma;
import ui.ArmasUI;

import java.util.ArrayList;

public class ControladorArmas extends ControladorCRUD<Arma> {

    public ArmasUI vista;
    private Controlador controlador;

    public ControladorArmas(ModeloCRUD<Arma> modelo, ArmasUI vista, Controlador controlador) {
        super(modelo, vista);
        this.vista = vista;
        this.controlador = controlador;
    }

    @Override
    public Arma nuevoDatoVacio() {
        return new Arma();
    }

    @Override
    public boolean clickEnGuardar() {
        if (origen.equals(Origen.MODIFICAR)) {
            Arma arma = extraerDatos();
            arma.setId(datoPantalla.getId());
            return modeloCRUD.modificar(arma);
        } else {
            Arma arma = extraerDatos();
            return modeloCRUD.guardar(arma);
        }
    }

    @Override
    public boolean cargarDatos() {
        vista.nombreTextField.setText(datoPantalla.getNombre());
        vista.ataqueTextField.setText(String.valueOf(datoPantalla.getAtaque()));
        vista.rarezaTextField.setText(String.valueOf(datoPantalla.getRareza()));
        vista.durabilidadTextField.setText(String.valueOf(datoPantalla.getDurabilidad()));
        return true;
    }

    @Override
    public void datosCambiados() {
        controlador.armasCambiadas((ArrayList<Arma>) modeloCRUD.cogerTodo());
    }

    @Override
    public Arma extraerDatos() {
        Arma arma = new Arma();

        String textoNombre = vista.nombreTextField.getText();
        String textoAtaque = vista.ataqueTextField.getText();
        String textoRareza = vista.rarezaTextField.getText();
        String textoDurabilidad = vista.durabilidadTextField.getText();

        arma.setNombre(!textoNombre.isEmpty() ? textoNombre : "Sin nombre");
        arma.setAtaque(!textoAtaque.isEmpty() ? Integer.parseInt(textoAtaque) : 0);
        arma.setRareza(!textoRareza.isEmpty() ? Integer.parseInt(textoRareza) : 0);
        arma.setDurabilidad(!textoDurabilidad.isEmpty() ? Integer.parseInt(textoDurabilidad) : 0);
        return arma;
    }

    @Override
    public void limpiarPantalla() {
        vista.nombreTextField.setText("");
        vista.ataqueTextField.setText("0");
        vista.rarezaTextField.setText("0");
        vista.durabilidadTextField.setText("0");
    }
}
