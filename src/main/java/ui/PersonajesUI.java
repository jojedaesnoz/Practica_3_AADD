package ui;

import pojos.Arma;
import pojos.Movimiento;
import pojos.Personaje;

import javax.swing.*;

public class PersonajesUI implements VistaCRUD<Personaje> {
    public JPanel panelPersonajes;
    public JTextField nombreTextField;
    public JTextField vidaTextField;
    public JComboBox<Movimiento> movimientoComboBox;
    public JList<Personaje> listaPersonajes;
    public MultiCombo<Arma> armasMultiCombo;
    public BarraBusqueda barraBusqueda;
    public BotonesCRUD botones;

    @Override
    public JList<Personaje> getLista() {
        return listaPersonajes;
    }

    @Override
    public BotonesCRUD getBotones() {
        return botones;
    }

    @Override
    public BarraBusqueda getBarraBusqueda() {
        return barraBusqueda;
    }
}
