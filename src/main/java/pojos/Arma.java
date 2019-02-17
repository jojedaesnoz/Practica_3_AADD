package pojos;

import java.util.ArrayList;
import java.util.List;

public class Arma extends Pojo{


    public enum Rareza {
        COMUN, EXCEPCIONAL, EPICA, LEGENDARIA;
    }
    private String nombre;
    private int ataque;
    private Rareza rareza;
    private List<Personaje> personajes;

    {
        personajes = new ArrayList<>();
    }

    public Arma() {
    }
    
    public Arma(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public Rareza getRareza() {
        return rareza;
    }

    public void setRareza(Rareza rareza) {
        this.rareza = rareza;
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(List<Personaje> personajes) {
        this.personajes = personajes;
    }

    @Override
    public String toString() {
        return nombre;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof Arma))
            return false;
        return ((Arma) obj).getId().equals(this.id);
    }
}
