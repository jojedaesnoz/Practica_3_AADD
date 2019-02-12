package logica;

import pojos.Personaje;
import ui.BotonesCRUD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class PersControlador extends ControladorCRUD<Personaje> {

    public PersControlador(BotonesCRUD botonesCRUD, JList<Personaje> lista, Controlador controlador) {
        super(botonesCRUD, lista, controlador);
    }

    @Override
    void clickEnLista(MouseEvent e, JList<Personaje> lista) {
        
    }

    @Override
    void clickEnNuevo(ActionEvent evento) {

    }

    @Override
    void clickEnGuardar(ActionEvent evento) {

    }

    @Override
    void clickEnModificar(ActionEvent evento) {

    }

    @Override
    void clickEnCancelar(ActionEvent evento) {

    }

    @Override
    void clickEnEliminar(ActionEvent evento) {

    }

    @Override
    void clickEnDeshacer(ActionEvent evento) {

    }

    @Override
    void clickEnEliminarTodo(ActionEvent evento) {

    }
}
