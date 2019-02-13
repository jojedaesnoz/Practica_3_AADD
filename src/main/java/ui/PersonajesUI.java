package ui;

import pojos.Personaje;

import javax.swing.*;

public class PersonajesUI implements VistaCRUD<Personaje> {
    public JPanel panelPersonajes;
    public JTextField nombreTextField;
    public JTextField vidaTextField;
    public JTextField velocidadTextField;
    public JList<Personaje> listaPersonajes;
    public MultiCombo armasMultiCombo;
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
    public JList<Personaje> getLista() {
        return listaPersonajes;
    }
}
