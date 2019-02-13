package ui;

import pojos.Arma;

import javax.swing.*;

public class ArmasUI implements VistaCRUD<Arma> {
    public JPanel panelArmas;
    public JTextField nombreTextField;
    public JTextField ataqueTextField;
    public JTextField rarezaTextField;
    public JTextField durabilidadTextField;
    public JList<Arma> listaArmas;
    public BotonesCRUD botones;
    public BarraBusqueda barraBusqueda;

    @Override
    public BotonesCRUD getBotonesCRUD() {
        return botones;
    }

    @Override
    public BarraBusqueda getBarraBusqueda() {
        return barraBusqueda;
    }

    @Override
    public JList<Arma> getLista() {
        return listaArmas;
    }
}
