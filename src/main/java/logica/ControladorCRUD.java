package logica;


import datos.ModeloCRUD;
import ui.BarraBusqueda;
import ui.BotonesCRUD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import static logica.ControladorCRUD.Origen.*;
import static ui.BotonesCRUD.Accion;

public abstract class ControladorCRUD<T> implements ActionListener, MouseListener, ui.BarraBusqueda.ListenerBusqueda {

    public enum Origen {
        NUEVO, MODIFICAR
    }

    Origen origen;
    BotonesCRUD.Accion accion;
    public ui.BotonesCRUD botonesCRUD;
    public JList<T> jList;
    public DefaultListModel<T> modeloLista;
    public ui.BarraBusqueda barraBusqueda;
    public ModeloCRUD<T> modeloCRUD;
    public ArrayList<T> listaDatos;
    public T dato;

    public ControladorCRUD(ModeloCRUD modeloCRUD) {
        this.modeloCRUD = modeloCRUD;
        this.botonesCRUD = getBotonesCRUD();
        this.jList = getLista();

        // Inicializar el modelo de la lista
        if (jList.getModel() != null) {
            modeloLista = (DefaultListModel<T>) jList.getModel();
        } else {
            modeloLista = new DefaultListModel<>();
            jList.setModel(modeloLista);
        }
        this.barraBusqueda =getBarraBusqueda();

        listaDatos = new ArrayList<>();
        ponerListeners();
    }

    public void ponerListeners() {
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
                origen = Origen.NUEVO;
                accion = Accion.NUEVO;
                break;
            case GUARDAR:
                setModoEdicion(false);
                extraerDatos();
                if (origen.equals(MODIFICAR)) {
                    modeloCRUD.modificar(dato);
                } else {
                    modeloCRUD.guardar(dato);
                }
                accion = Accion.GUARDAR;
                break;
            case MODIFICAR:
                setModoEdicion(true);
                origen = MODIFICAR;
                accion = Accion.MODIFICAR;
                break;
            case CANCELAR:
                setModoEdicion(false);
                itemSeleccionado(false);
                accion = Accion.CANCELAR;
                break;
            case ELIMINAR:
                setModoEdicion(false);
                botonesCRUD.deshacerButton.setEnabled(true);
                modeloCRUD.eliminar(dato);
                accion = Accion.ELIMINAR;
                break;
            case DESHACER:
                setModoEdicion(false);
                botonesCRUD.deshacerButton.setEnabled(false);
                modeloCRUD.deshacer();
                accion = Accion.DESHACER;
                break;
            case ELIMINAR_TODO:
                setModoEdicion(false);
                modeloCRUD.eliminarTodo();
                accion = Accion.ELIMINAR_TODO;
                break;
        }

        listaDatos = (ArrayList<T>) modeloCRUD.cogerTodo();
        barraBusqueda.getTfBusqueda().setText("");
    }

    public void refrescarLista(List<T> lista) {
        modeloLista.clear();
        lista.forEach(modeloLista::addElement);
        listaDatos.clear();
        listaDatos.addAll(lista);
        datosCambiados(listaDatos);
    }

    protected abstract void datosCambiados(ArrayList<T> listaDatos);


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
            cargarDatos(dato = jList.getSelectedValue());
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
    abstract T extraerDatos();

    /**
     * Metodo para cargar en pantalla los datos del objeto seleccionado
     * @param pojo
     */
    abstract void cargarDatos(T pojo);
    abstract BotonesCRUD getBotonesCRUD();
    abstract BarraBusqueda getBarraBusqueda();
    abstract JList<T> getLista();


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
