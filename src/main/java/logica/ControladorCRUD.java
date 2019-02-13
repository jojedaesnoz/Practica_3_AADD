package logica;


import datos.ModeloCRUD;
import ui.BotonesCRUD;
import ui.VistaCRUD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import static logica.ControladorCRUD.Origen.*;

public abstract class ControladorCRUD<T> implements ActionListener, MouseListener, ui.BarraBusqueda.ListenerBusqueda {

    public enum Origen {
        NUEVO, MODIFICAR
    }

    Origen origen;
    private ui.BotonesCRUD botonesCRUD;
    private JList<T> jList;
    private DefaultListModel<T> modeloLista;
    private ui.BarraBusqueda barraBusqueda;
    private ModeloCRUD<T> modeloCRUD;
    private T pojo;

    public ControladorCRUD(ModeloCRUD modeloCRUD, VistaCRUD vistaCRUD) {
        this.modeloCRUD = modeloCRUD;
        this.botonesCRUD = vistaCRUD.getBotonesCRUD();
        this.jList = vistaCRUD.getLista();

        // Inicializar el modelo de la lista
        if (jList.getModel() != null) {
            modeloLista = (DefaultListModel<T>) jList.getModel();
        } else {
            modeloLista = new DefaultListModel<>();
            jList.setModel(modeloLista);
        }
        this.barraBusqueda = vistaCRUD.getBarraBusqueda();

        ponerListeners();
    }

    /*
    TODO: posibilidad
    que la vista sea la que tiene los metodos de construir pojos y pintarlos etc.
     */

    private void ponerListeners() {
        botonesCRUD.botones.forEach(boton -> boton.addActionListener(this));
        jList.addMouseListener(this);
        barraBusqueda.setListenerBusqueda(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (BotonesCRUD.Accion.valueOf(e.getActionCommand())) {
            case NUEVO:
                setModoEdicion(true);
                limpiarPantalla();
                origen = NUEVO;
                break;
            case GUARDAR:
                setModoEdicion(false);
                extraerPojo();
                if (origen.equals(MODIFICAR)) {
                    modeloCRUD.modificar(pojo);
                } else {
                    modeloCRUD.guardar(pojo);
                }
                break;
            case MODIFICAR:
                setModoEdicion(true);
                origen = MODIFICAR;
                break;
            case CANCELAR:
                setModoEdicion(false);
                itemSeleccionado(false);
                break;
            case ELIMINAR:
                setModoEdicion(false);
                botonesCRUD.deshacerButton.setEnabled(true);
                modeloCRUD.eliminar(pojo);
                break;
            case DESHACER:
                setModoEdicion(false);
                botonesCRUD.deshacerButton.setEnabled(false);
                modeloCRUD.deshacer();
                break;
            case ELIMINAR_TODO:
                setModoEdicion(false);
                modeloCRUD.eliminarTodo();
                break;
        }
    }

    private void refrescarLista(List<T> lista) {
        modeloLista.clear();
        lista.forEach(modeloLista::addElement);
    }

    @Override
    public void buscar(String cadena) {
        refrescarLista(modeloCRUD.coger(cadena));
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
        // Click en la lista
        if (e.getComponent().equals(jList)) {
            botonesCRUD.modificarButton.setEnabled(true);
            botonesCRUD.cancelarButton.setEnabled(true);
            botonesCRUD.eliminarTodoButton.setEnabled(true);
            pojo = jList.getSelectedValue();
        }
    }

    // METODOS ABSTRACTOS
    /**
     * Metodo para limpiar la pantalla (vaciar cajas de texto etc)
     */
    abstract void limpiarPantalla();

    /**
     * Metodo para formar un objeto a partir de los datos introducidos por pantalla
     * @return
     */
    abstract T extraerPojo();

    /**
     * Metodo para cargar en pantalla los datos del objeto seleccionado
     * @param pojo
     */
    abstract void ponerEnPantalla(T pojo);


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
