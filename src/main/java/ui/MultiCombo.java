package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MultiCombo<T> implements ActionListener {
    public JPanel panel1;
    public JList<T> jList;
    public JComboBox<T> comboBox;
    public JButton button;
    public DefaultListModel<T> modelo;
    
    {
        modelo = new DefaultListModel<>();
        button.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        T item = (T) comboBox.getSelectedItem();
        if (!modelo.contains(item)) {
            modelo.addElement(item);
        }
    }

    public List<T> getItems() {
        return new ArrayList<>(Arrays.asList((T[]) modelo.toArray()));
    }

    public void setItems(List<T> items) {
        modelo.clear();
        items.forEach(modelo::addElement);
    }
}
