package pojos;

import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class Personaje extends Pojo implements Comparable<Personaje> {
    private String nombre;
    private int vida;
    private Movimiento movimiento;
    private List<ObjectId> armas;

    {
        armas = new ArrayList<>();
    }


    public Personaje() {
    }

    public Personaje(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public Movimiento getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(Movimiento movimiento) {
        this.movimiento = movimiento;
    }

    public List<ObjectId> getArmas() {
        return armas;
    }

    public void setArmas(List<ObjectId> armas) {
        this.armas = armas;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof Personaje))
            return false;
        return ((Personaje) obj).getId().equals(this.id);
    }

    @Override
    public int compareTo(Personaje o) {
        return nombre.compareTo(o.getNombre());
    }
}
