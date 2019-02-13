package ui;

import pojos.Arma;
import pojos.Movimiento;
import pojos.Personaje;

import javax.swing.*;
import java.util.ArrayList;

public class PersonajesUI {
    public JPanel panelPersonajes;
    public JTextField nombreTextField;
    public JTextField vidaTextField;
    public JComboBox<Movimiento> movimientoComboBox;
    public JList<Personaje> listaPersonajes;
    public MultiCombo<Arma> armasMultiCombo;
    public BarraBusqueda barraBusqueda;
    public BotonesCRUD botones;

}
