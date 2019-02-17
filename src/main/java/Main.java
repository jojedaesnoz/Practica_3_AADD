import datos.Modelo;
import datos.ModeloCRUD;
import logica.Controlador;
import logica.ControladorArmas;
import pojos.Arma;
import ui.Vista;

public class Main {

    public static void main(String[] args) {
        Modelo modelo = new Modelo();
        Vista vista = new Vista();
        Controlador controlador = new Controlador(modelo, vista);
    }
}
