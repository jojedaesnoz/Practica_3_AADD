package pojos;

public class Movimiento extends Pojo {
    private String nombre;
    private int energia;
    private int nivel;

    public Movimiento() {
    }
    
    public Movimiento(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return nombre;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof Movimiento))
            return false;
        return ((Movimiento) obj).getId().equals(this.id);
    }
}
