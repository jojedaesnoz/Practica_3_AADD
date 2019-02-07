package ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;


public class BarraBusqueda extends JPanel implements DocumentListener {
    private GridBagConstraints gbc;
    private JTextField tfBusqueda;
    private JLabel lbBusqueda;
    private ControlBusqueda controlBusqueda;

    public BarraBusqueda() {
        super();
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        inicializar();

        add(lbBusqueda, gbc);
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 1;
        add(tfBusqueda, gbc);
    }

    private void inicializar() {
        lbBusqueda = new JLabel();
        ImageIcon imageIcon = new ImageIcon("search_icon.png");
        Image image = imageIcon.getImage().getScaledInstance(25, -1, Image.SCALE_DEFAULT);
        lbBusqueda.setIcon(new ImageIcon(image));

        tfBusqueda = new JTextField();
        tfBusqueda.getDocument().addDocumentListener(this);
    }

    // Interfaz para avisar de cuando se ha modificado la caja de busqueda
    public interface ControlBusqueda {
        void buscar(String cadena);
    }

    public JTextField getTfBusqueda() {
        return tfBusqueda;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        controlBusqueda.buscar(tfBusqueda.getText());
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        controlBusqueda.buscar(tfBusqueda.getText());
    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }

    public ControlBusqueda getControlBusqueda() {
        return controlBusqueda;
    }

    public void setControlBusqueda(ControlBusqueda controlBusqueda) {
        this.controlBusqueda = controlBusqueda;
    }

}
