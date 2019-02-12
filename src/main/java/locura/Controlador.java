package locura;

import ui.BarraBusqueda;
import ui.BotonesCRUD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import static locura.Controlador.Origen.MODIFICAR;
import static locura.Controlador.Origen.NUEVO;
import static ui.BotonesCRUD.Accion;

public abstract class Controlador<T> implements ActionListener, MouseListener, BarraBusqueda.ListenerBusqueda {

    public enum Origen {
        NUEVO, MODIFICAR
    }

    Origen origen;
    private BotonesCRUD botonesCRUD;
    private JList<T> jList;
    private DefaultListModel<T> modeloLista;
    private BarraBusqueda barraBusqueda;
    private Function<Vista, T> extractorPojo;
    private Vista vista;
    private Modelo<T> modelo;
    private T pojo;
    private Consumer<Vista> limpiador;

    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.botonesCRUD = vista.getBotonesCRUD();
        this.jList = vista.getLista();
        if (jList.getModel() != null) {
            modeloLista = (DefaultListModel<T>) jList.getModel();
        } else {
            modeloLista = new DefaultListModel<>();
            jList.setModel(modeloLista);
        }
        this.barraBusqueda = vista.getBarraBusqueda();

        ponerListeners();
    }

    private void ponerListeners() {
        botonesCRUD.botones.forEach(boton -> boton.addActionListener(this));
        jList.addMouseListener(this);
        barraBusqueda.setListenerBusqueda(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (Accion.valueOf(e.getActionCommand())) {
            case NUEVO:
                setModoEdicion(true);
                limpiarPantalla();
                origen = NUEVO;
                break;
            case GUARDAR:
                setModoEdicion(false);
                extraerPojo();
                if (origen.equals(MODIFICAR)) {
                    modelo.modificar(pojo);
                } else {
                    modelo.guardar(pojo);
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
                modelo.eliminar(pojo);
                break;
            case DESHACER:
                setModoEdicion(false);
                botonesCRUD.deshacerButton.setEnabled(false);
                modelo.deshacer();
                break;
            case ELIMINAR_TODO:
                setModoEdicion(false);
                modelo.eliminarTodo();
                break;
        }
    }

    private void limpiarPantalla() {
        limpiador.accept(vista);
    }

    private void refrescarLista(List<T> lista) {
        modeloLista.clear();
        lista.forEach(modeloLista::addElement);
    }

    @Override
    public void buscar(String cadena) {
        refrescarLista(modelo.coger(cadena));
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
        clickEnLista(e, jList);
    }

    // METODOS ABSTRACTOS
    public void clickEnLista(MouseEvent e, JList<T> lista){
        botonesCRUD.modificarButton.setEnabled(true);
        botonesCRUD.cancelarButton.setEnabled(true);
        botonesCRUD.eliminarTodoButton.setEnabled(true);
    }

    public void setLimpiadorPantalla(Consumer<Vista> limpiador) {
        this.limpiador = limpiador;
    }

    public Consumer<Vista> getLimpiador() {
        return limpiador;
    }

    public Function<Vista, T> getExtractorPojo() {
        return extractorPojo;
    }

    public void setExtractorPojo(Function<Vista, T> extractorPojo) {
        this.extractorPojo = extractorPojo;
    }

    public T extraerPojo() {
        return pojo = extractorPojo.apply(vista);
    }


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
