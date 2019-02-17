package pojos;

import java.util.ArrayList;
import java.util.List;

public class Personaje extends Pojo {
    private String nombre;
    private int vida;
    private Movimiento movimiento;
    private List<Arma> armas;


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

    public List<Arma> getArmas() {
        return armas;
    }

    public void setArmas(List<Arma> armas) {
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
}
