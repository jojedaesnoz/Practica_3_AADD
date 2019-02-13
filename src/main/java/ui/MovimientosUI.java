package ui;

import pojos.Movimiento;

import javax.swing.*;

public class MovimientosUI implements VistaCRUD<Movimiento> {
    public JPanel panelMovimientos;
    public JTextField nombreTextField;
    public JTextField nivelTextField;
    public JTextField energiaTextField;
    public JComboBox personajeComboBox;
    public JList<Movimiento> listaMovimientos;
    public BarraBusqueda barraBusqueda;
    public BotonesCRUD botones;

    @Override
    public BotonesCRUD getBotonesCRUD() {
        return botones;
    }

    @Override
    public BarraBusqueda getBarraBusqueda() {
        return barraBusqueda;
    }

    @Override
    public JList<Movimiento> getLista() {
        return listaMovimientos;
    }
}
