import datos.Modelo;
import datos.ModeloCRUD;
import logica.Controlador;
import logica.ControladorArmas;
import pojos.Arma;
import ui.Vista;

public class Main {

    public static void main(String[] args) {
//        ModeloCRUD<Arma> modeloArmas = new ModeloCRUD<>("armasCollection", Arma.class);
//        Vista vista = new Vista();
        Modelo modelo = new Modelo();
        Vista vista = new Vista();
        Controlador controlador = new Controlador(modelo, vista);
    }
}
