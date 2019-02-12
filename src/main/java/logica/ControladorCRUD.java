package logica;

import ui.BotonesCRUD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static ui.BotonesCRUD.Accion;

public abstract class ControladorCRUD<T> implements ActionListener, MouseListener {

    private final BotonesCRUD botonesCRUD;
    private final JList<T> lista;
    private Controlador controlador;

    public ControladorCRUD(BotonesCRUD botonesCRUD, JList<T> lista) {
        this.botonesCRUD = botonesCRUD;
        this.lista = lista;

        botonesCRUD.botones.forEach(boton -> boton.addActionListener(this));
        lista.addMouseListener(this);
    }

    public ControladorCRUD(BotonesCRUD botonesCRUD, JList<T> lista, Controlador controlador) {
        this.botonesCRUD = botonesCRUD;
        this.lista = lista;
        this.controlador = controlador;

        botonesCRUD.botones.forEach(boton -> boton.addActionListener(this));
        lista.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (Accion.valueOf(e.getActionCommand())) {
            case NUEVO:
                setModoEdicion(true);
                clickEnNuevo(e);
                break;
            case GUARDAR:
                setModoEdicion(false);
                clickEnGuardar(e);
                break;
            case MODIFICAR:
                setModoEdicion(true);
                clickEnModificar(e);
                break;
            case CANCELAR:
                setModoEdicion(false);
                itemSeleccionado(false);
                clickEnCancelar(e);
                break;
            case ELIMINAR:
                setModoEdicion(false);
                botonesCRUD.deshacerButton.setEnabled(true);
                clickEnEliminar(e);
                break;
            case DESHACER:
                setModoEdicion(false);
                botonesCRUD.deshacerButton.setEnabled(false);
                clickEnDeshacer(e);
                break;
            case ELIMINAR_TODO:
                setModoEdicion(false);
                clickEnEliminarTodo(e);
                break;
        }
    }

    public void setModoEdicion(boolean modo) {
        botonesCRUD.guardarButton.setEnabled(modo);
        botonesCRUD.cancelarButton.setEnabled(modo);
    }

    public void itemSeleccionado(boolean seleccionado) {
        botonesCRUD.modificarButton.setEnabled(seleccionado);
        botonesCRUD.cancelarButton.setEnabled(seleccionado);
        botonesCRUD.eliminarTodoButton.setEnabled(seleccionado);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        botonesCRUD.modificarButton.setEnabled(true);
        botonesCRUD.cancelarButton.setEnabled(true);
        botonesCRUD.eliminarTodoButton.setEnabled(true);
        clickEnLista(e, lista);
    }

    // METODOS ABSTRACTOS
    abstract void clickEnLista(MouseEvent e, JList<T> lista);
    abstract void clickEnNuevo(ActionEvent evento);
    abstract void clickEnGuardar(ActionEvent evento);
    abstract void clickEnModificar(ActionEvent evento);
    abstract void clickEnCancelar(ActionEvent evento);
    abstract void clickEnEliminar(ActionEvent evento);
    abstract void clickEnDeshacer(ActionEvent evento);
    abstract void clickEnEliminarTodo(ActionEvent evento);

    // LISTENERS VACIOS
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
