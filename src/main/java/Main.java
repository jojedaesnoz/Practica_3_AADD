import datos.Modelo;
import logica.Controlador;
import ui.Vista;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Vista vista = new Vista();
        Modelo modelo = new Modelo();

        JFrame ventana = new JFrame();
        ventana.add(vista.panelPrincipal);
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ventana.pack();
        Controlador controlador = new Controlador(modelo, vista);
    }
}
