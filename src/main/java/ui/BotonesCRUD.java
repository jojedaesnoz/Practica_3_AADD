package ui;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import static ui.BotonesCRUD.Accion.*;

public class BotonesCRUD {
    private JButton nuevoButton;
    private JPanel panel1;
    private JButton cancelarButton;
    private JButton guardarButton;
    private JButton eliminarButton;
    private JButton eliminarTodoButton;
    private JButton modificarButton;
    private JButton deshacerButton;

    private ArrayList<JButton> botones;
    private ActionListener actionListener;

    {
        botones = new ArrayList<>(Arrays.asList(nuevoButton, cancelarButton, guardarButton, eliminarButton,
                eliminarTodoButton, modificarButton, deshacerButton));
        Accion[] acciones = Accion.values();
        for (int i = 0; i < acciones.length; i++) {
            botones.get(i).setActionCommand(acciones[i].name());
        }
    }

    public enum Accion {
        NUEVO, CANCELAR, GUARDAR, ELIMINAR,
        ELIMINAR_TODO, MODIFICAR, DESHACER
    }

    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
        for (JButton boton : botones) {
            boton.addActionListener(actionListener);
        }
    }


}
