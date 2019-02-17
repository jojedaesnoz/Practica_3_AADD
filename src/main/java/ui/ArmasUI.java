package ui;

import pojos.Arma;
import pojos.Personaje;

import javax.swing.*;

public class ArmasUI implements VistaCRUD<Arma> {
    public JPanel panelArmas;
    public JTextField nombreTextField;
    public JTextField ataqueTextField;
    public JList<Arma> listaArmas;
    public BotonesCRUD botones;
    public BarraBusqueda barraBusqueda;
    public MultiCombo<Personaje> personajesMultiCombo;
    public JComboBox<Arma.Rareza> rarezaComboBox;

    @Override
    public JList<Arma> getLista() {
        return listaArmas;
    }

    @Override
    public BotonesCRUD getBotones() {
        return botones;
    }

    @Override
    public BarraBusqueda getBarraBusqueda() {
        return barraBusqueda;
    }

    private void createUIComponents() {
        rarezaComboBox = new JComboBox<>(Arma.Rareza.values());
    }
}
