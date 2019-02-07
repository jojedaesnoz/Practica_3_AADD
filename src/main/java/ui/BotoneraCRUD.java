package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BotoneraCRUD extends JPanel implements ActionListener {
    private final String[] actionCommands = {
            "Nuevo", "Guardar", "Modificar", "Cancelar",
            "Eliminar", "Deshacer", "Eliminar Todo"
    };

    private JButton btNuevo, btGuardar, btModificar, btCancelar, btEliminar, btDeshacer, btEliminarTodo;
    private BotoneraListener botoneraListener;
    private ArrayList<JButton> botones;
    private boolean modoEdicion;



    public BotoneraCRUD() {
        super();
        botones = new ArrayList<>();
        modoEdicion = true;
        colocarBotones();
    }

    private void colocarBotones() {
        JButton[] botonesArray = new JButton[] {
                btNuevo, btGuardar, btModificar, btCancelar,
                btEliminar, btDeshacer, btEliminarTodo
        };

        for (int i = 0; i < botonesArray.length; i++) {
            botonesArray[i] = new JButton(actionCommands[i]);
            botonesArray[i].addActionListener(this);
            botones.add(botonesArray[i]);
        }

        setModoEdicion(false);
        btDeshacer.setEnabled(false);
        btModificar.setEnabled(false);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
    }

    public List<JButton> getBotones() {
        return botones;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Nuevo":
                setModoEdicion(true);
                botoneraListener.clickEnNuevo(e);
                break;
            case "Guardar":
                setModoEdicion(false);
                botoneraListener.clickEnGuardar(e);
                break;
            case "Modificar":
                setModoEdicion(true);
                botoneraListener.clickEnModificar(e);
                break;
            case "Cancelar":
                setModoEdicion(false);
                itemSeleccionado(false);
                botoneraListener.clickEnCancelar(e);
                break;
            case "Eliminar":
                setModoEdicion(false);
                btDeshacer.setEnabled(true);
                botoneraListener.clickEnEliminar(e);
                break;
            case "Deshacer":
                setModoEdicion(false);
                btDeshacer.setEnabled(false);
                botoneraListener.clickEnDeshacer(e);
                break;
            case "Eliminar Todo":
                setModoEdicion(false);
                botoneraListener.clickEnEliminarTodo(e);
                break;
        }
    }

    public BotoneraListener getBotoneraListener() {
        return botoneraListener;
    }

    public void setBotoneraListener(BotoneraListener botoneraListener) {
        this.botoneraListener = botoneraListener;
    }

    public void setModoEdicion(boolean modo) {
        btGuardar.setEnabled(modo);
        btCancelar.setEnabled(modo);
    }

    public void itemSeleccionado(boolean seleccionado) {
        btModificar.setEnabled(seleccionado);
        btCancelar.setEnabled(seleccionado);
        btEliminar.setEnabled(seleccionado);
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

}
