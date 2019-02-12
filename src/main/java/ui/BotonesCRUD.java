package ui;

import locura.Controlador;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class BotonesCRUD {
    public JButton nuevoButton;
    public JPanel panel1;
    public JButton cancelarButton;
    public JButton guardarButton;
    public JButton eliminarButton;
    public JButton eliminarTodoButton;
    public JButton modificarButton;
    public JButton deshacerButton;

    public ArrayList<JButton> botones;

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
}
