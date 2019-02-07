import datos.Modelo;
import logica.Controlador;
import ui.Vista;

public class Main {

    public static void main(String[] args) {
        Vista vista = new Vista();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(modelo, vista);
    }
}
