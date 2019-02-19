package logica;


import datos.ModeloCRUD;
import org.bson.types.ObjectId;
import pojos.Pojo;
import ui.BarraBusqueda;
import ui.BotonesCRUD;
import ui.VistaCRUD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import static logica.ControladorCRUD.Origen.MODIFICAR;
import static ui.BotonesCRUD.Accion;

public abstract class ControladorCRUD<T extends Pojo> implements ActionListener, MouseListener, BarraBusqueda.ListenerBusqueda {

    public enum Origen {
        NUEVO, MODIFICAR
    }

    public Origen origen;
    public Accion accion;
    public BotonesCRUD botonesCRUD;
    public JList<T> jList;
    public DefaultListModel<T> modeloLista;
    public BarraBusqueda barraBusqueda;
    public ModeloCRUD<T> modeloCRUD;
    public VistaCRUD<T> vistaCRUD;
    public T datoPantalla;

    public ControladorCRUD(ModeloCRUD<T> modeloCRUD, VistaCRUD<T> vistaCRUD) {
        this.modeloCRUD = modeloCRUD;
        this.vistaCRUD = vistaCRUD;

        inicializar();
        ponerListeners();
    }

    public void ponerListeners() {
        botonesCRUD.botones.forEach(boton -> boton.addActionListener(this));
        jList.addMouseListener(this);
        barraBusqueda.setListenerBusqueda(this);
    }

    public void inicializar() {
        botonesCRUD = vistaCRUD.getBotones();
        barraBusqueda = vistaCRUD.getBarraBusqueda();
        jList = vistaCRUD.getLista();
        modeloLista = new DefaultListModel<>();
        jList.setModel(modeloLista);
        datoPantalla = nuevoDatoVacio();

        cargarLista(modeloCRUD.cogerTodo());
        itemSeleccionado(false);
        setModoEdicion(false);
    }

    /* Estos metodos se utilizan para comunicar a la clase hija de los eventos
     que ocurren. Se les ha dado una implementacion por defecto. */

    public boolean clickEnGuardar() {
        return origen.equals(MODIFICAR) ?
                modeloCRUD.modificar(extraerDatos(datoPantalla.getId())) :
                modeloCRUD.guardar(extraerDatos(null));
    }

    public boolean clickEnCancelar() {
        return true;
    }

    public boolean clickEnNuevo(){
        return true;
    }

    public boolean clickEnModificar() {
        return true;
    }

    public boolean clickEnEliminar() {
        return modeloCRUD.eliminar(datoPantalla) != null;
    }

    public boolean clickEnEliminarTodo() {
        return modeloCRUD.eliminarTodo();
    }

    public T clickEnDeshacer() {
        return modeloCRUD.deshacer();
    }

    // METODOS PARA INTERACTUAR CON LA INTERFAZ DE USUARIO
    // Cargar datos en la pantalla
    public abstract void cargarDatos();

    // Vaciar la pantalla de informacion
    public abstract void limpiarPantalla();

    // Convertir la informacion de la pantalla en un objeto
    public abstract T extraerDatos(ObjectId id);

    // Metodo para notificar de los cambios realizados a entidades relacionadas
    public abstract void datosCambiados();


    // Otros metodos utiles para la subclase
    public abstract T nuevoDatoVacio();


    // CLICK EN BOTONES
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (Accion.valueOf(e.getActionCommand())) {
            case NUEVO:
                if (clickEnNuevo()) {
                    datoPantalla = nuevoDatoVacio();
                    setModoEdicion(true);
                    limpiarPantalla();
                    origen = Origen.NUEVO;
                    accion = Accion.NUEVO;
                }
                break;
            case GUARDAR:
                if (clickEnGuardar()) {
                    setModoEdicion(false);
                    accion = Accion.GUARDAR;
                }
                break;
            case MODIFICAR:
                if (clickEnModificar()) {
                    setModoEdicion(true);
                    origen = MODIFICAR;
                    accion = Accion.MODIFICAR;
                }
                break;
            case CANCELAR:
                if (clickEnCancelar()) {
                    datoPantalla = nuevoDatoVacio();
                    setModoEdicion(false);
                    itemSeleccionado(false);
                    accion = Accion.CANCELAR;
                    limpiarPantalla();
                }
                break;
            case ELIMINAR:
                if (clickEnEliminar()) {
                    setModoEdicion(false);
                    botonesCRUD.deshacerButton.setEnabled(true);
                    accion = Accion.ELIMINAR;
                    limpiarPantalla();
                }
                break;
            case DESHACER:
                T dato;
                if ((dato = clickEnDeshacer()) != null) {
                    setModoEdicion(false);
                    botonesCRUD.deshacerButton.setEnabled(false);
                    accion = Accion.DESHACER;
                    datoPantalla = dato;
                    cargarDatos();
                }
                break;
            case ELIMINAR_TODO:
                if (clickEnEliminarTodo()) {
                    setModoEdicion(false);
                    accion = Accion.ELIMINAR_TODO;
                    limpiarPantalla();
                }
                break;
        }

        cargarLista(modeloCRUD.cogerTodo());
        datosCambiados();
    }

    public void cargarLista(List<T> lista) {
        modeloLista.clear();
        lista.forEach(modeloLista::addElement);
    }

    @Override
    public void buscar(String cadena) {
        cargarLista(modeloCRUD.buscarPorNombre(cadena));
    }

    public void setModoEdicion(boolean modo) {
        botonesCRUD.guardarButton.setEnabled(modo);
        botonesCRUD.cancelarButton.setEnabled(modo);
    }

    public void itemSeleccionado(boolean seleccionado) {
        botonesCRUD.modificarButton.setEnabled(seleccionado);
        botonesCRUD.cancelarButton.setEnabled(seleccionado);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Click en la lista
        if (e.getComponent().equals(jList)) {
            datoPantalla = jList.getSelectedValue();
            cargarDatos();
            itemSeleccionado(true);
        }
    }

    // LISTENERS VACIOS
    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
