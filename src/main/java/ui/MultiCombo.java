package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultiCombo<T> implements ActionListener {
    public JPanel panel1;
    public JList<T> jList;
    public JComboBox<T> comboBox;
    public JButton button;
    public DefaultListModel<T> modelo;
    
    {
        modelo = new DefaultListModel<>();
        jList.setModel(modelo);
        button.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        T item = (T) comboBox.getSelectedItem();
        if (!modelo.contains(item)) {
            modelo.addElement(item);
        }
    }

    public List<T> getComboOptions() {
        ArrayList<T> lista = new ArrayList<>();
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            lista.add(comboBox.getItemAt(i));
        }
        return lista;
    }

    public void setComboOptions(List<T> items) {
        comboBox.removeAllItems();
        items.forEach(comboBox::addItem);
    }

    public List<T> getListItems() {
        if (!modelo.isEmpty())
            return new ArrayList<>(Arrays.asList((T[]) modelo.toArray()));
        else
            return new ArrayList<>();
    }

    public void setListItems(List<T> items) {
        modelo.clear();
        items.forEach(modelo::addElement);
    }
}
