package ui;

import javax.swing.*;
import java.awt.*;

public class Vista {

    public JFrame ventana;
    public PersonajesUI personajesUI;
    public MovimientosUI movimientosUI;
    public ArmasUI armasUI;

    public Vista() {
        inicializar();
        addTabs();
        mostrarVentana();
    }

    private void mostrarVentana() {
        ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ventana.pack();
        ventana.setVisible(true);
    }

    private void inicializar() {
        ventana = new JFrame("Creador de personajes");
        ventana.setLayout(new BorderLayout());

        personajesUI = new PersonajesUI();
        armasUI = new ArmasUI();
        movimientosUI = new MovimientosUI();

    }

    private void addTabs() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Personajes", personajesUI.panelPersonajes);
        tabbedPane.addTab("Armas", armasUI.panelArmas);
        tabbedPane.addTab("Movimientos Especiales", movimientosUI.panelMovimientos);
        ventana.add(tabbedPane, BorderLayout.CENTER);
    }
}
