package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MultiCombo<T> implements ActionListener {
    public JPanel panel1;
    public JList<T> jList;
    public JComboBox<T> comboBox;
    public JButton add;
    private JButton remove;
    public DefaultListModel<T> modelo;
    
    {
        modelo = new DefaultListModel<>();
        jList.setModel(modelo);
        add.addActionListener(this);
        remove.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        T item;
        if (e.getSource().equals(add)) {
            item = (T) comboBox.getSelectedItem();
            if (!modelo.contains(item)) {
                modelo.addElement(item);
            }
        } else if (e.getSource().equals(remove)) {
            item = jList.getSelectedValue();
            modelo.removeElement(item);
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
        return Collections.list(modelo.elements());
    }

    public void setListItems(List<T> items) {
        modelo.clear();
        items.forEach(modelo::addElement);
    }
}
