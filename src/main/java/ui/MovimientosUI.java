package ui;

import pojos.Movimiento;
import pojos.Personaje;

import javax.swing.*;

public class MovimientosUI implements VistaCRUD<Movimiento> {
    public JPanel panelMovimientos;
    public JTextField nombreTextField;
    public JTextField nivelTextField;
    public JTextField energiaTextField;
    public JComboBox<Personaje> personajeComboBox;
    public JList<Movimiento> listaMovimientos;
    public BarraBusqueda barraBusqueda;
    public BotonesCRUD botones;

    @Override
    public JList<Movimiento> getLista() {
        return listaMovimientos;
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
