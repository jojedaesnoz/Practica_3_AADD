package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class BotonesCRUD2 implements ActionListener{
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
    private BotoneraListener botoneraListener;

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

    public BotoneraListener getBotoneraListener() {
        return botoneraListener;
    }

    public void setBotoneraListener(BotoneraListener botoneraListener) {
        this.botoneraListener = botoneraListener;
    }

    public void setModoEdicion(boolean modo) {
        guardarButton.setEnabled(modo);
        cancelarButton.setEnabled(modo);
    }

    public void itemSeleccionado(boolean seleccionado) {
        modificarButton.setEnabled(seleccionado);
        cancelarButton.setEnabled(seleccionado);
        eliminarTodoButton.setEnabled(seleccionado);
    }

    public interface BotoneraListener {
        void clickEnNuevo(ActionEvent evento);
        void clickEnGuardar(ActionEvent evento);
        void clickEnModificar(ActionEvent evento);
        void clickEnCancelar(ActionEvent evento);
        void clickEnEliminar(ActionEvent evento);
        void clickEnDeshacer(ActionEvent evento);
        void clickEnEliminarTodo(ActionEvent evento);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (Accion.valueOf(e.getActionCommand())) {
            case NUEVO:
                setModoEdicion(true);
                botoneraListener.clickEnNuevo(e);
                break;
            case GUARDAR:
                setModoEdicion(false);
                botoneraListener.clickEnGuardar(e);
                break;
            case MODIFICAR:
                setModoEdicion(true);
                botoneraListener.clickEnModificar(e);
                break;
            case CANCELAR:
                setModoEdicion(false);
                itemSeleccionado(false);
                botoneraListener.clickEnCancelar(e);
                break;
            case ELIMINAR:
                setModoEdicion(false);
                deshacerButton.setEnabled(true);
                botoneraListener.clickEnEliminar(e);
                break;
            case DESHACER:
                setModoEdicion(false);
                deshacerButton.setEnabled(false);
                botoneraListener.clickEnDeshacer(e);
                break;
            case ELIMINAR_TODO:
                setModoEdicion(false);
                botoneraListener.clickEnEliminarTodo(e);
                break;
        }
    }


}
