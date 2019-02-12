package ui;

import pojos.Arma;
import pojos.Movimiento;
import pojos.Personaje;

import javax.swing.*;

public class Vista {
    public JPanel panelPrincipal;
    public JTabbedPane tabbedPane;
    public JTextField nombrePersonajeTextField;
    public JTextField vidaPersonajeTextField;
    public JTextField velocidadPersonajeTextField;
    public JTextField nombreArmaTextField;
    public JTextField ataqueArmaTextField;
    public JTextField rarezaArmasTextField;
    public JTextField durabilidadArmasTextField;
    public JTextField nombreMovimientoTextField;
    public JTextField nivelMovimientoTextField;
    public JTextField energiaMovimientoTextField;
    public JList<Arma> armasList;
    public JList<Movimiento> movimientosList;
    public JList<Personaje> personajeList;
    public BotonesCRUD botoneraMovimientos;
    public BotonesCRUD botoneraArmas;
    public BotonesCRUD botoneraPersonajes;
    public JPanel personajesPanel;
    public JPanel armasPanel;
    public JPanel movimientosPanel;
    private MultiCombo personajesArmasCombo;

    public DefaultListModel<Arma> armasPersonajeModelo;
    public DefaultListModel<Arma> armasModelo;
    public DefaultListModel<Movimiento> movimientosModelo;
    public DefaultListModel<Personaje> personajeModelo;

    {
        personajeModelo = new DefaultListModel<>();
        movimientosModelo = new DefaultListModel<>();
        armasPersonajeModelo = new DefaultListModel<>();
        armasModelo = new DefaultListModel<>();

        personajeList.setModel(personajeModelo);
        movimientosList.setModel(movimientosModelo);
        armasList.setModel(armasModelo);
    }
}
