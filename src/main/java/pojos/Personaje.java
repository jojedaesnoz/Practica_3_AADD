package pojos;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "personajes")
public class Personaje implements Comparable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "vida")
    private int vida;

    @Column(name = "velocidad")
    private double velocidad;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Movimiento movimiento;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "personaje_arma",
            joinColumns = {@JoinColumn(name = "id_personaje")},
            inverseJoinColumns = {@JoinColumn(name = "id_arma")})
    private List<Arma> armas;

    public Personaje() {
    }

    public Personaje(long id, String nombre, int vida, double velocidad, Movimiento movimiento, List<Arma> armas) {
        this.id = id;
        this.nombre = nombre;
        this.vida = vida;
        this.velocidad = velocidad;
        this.movimiento = movimiento;
        this.armas = armas;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
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
    public boolean equals(Object obj) {
        if (!(obj instanceof Personaje))
            return false;
        return ((Personaje) obj).id == this.id;
    }

    @Override
    public int hashCode() {
        return Long.valueOf(this.id).hashCode();
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public int compareTo(Object o) {
        return this.toString().compareTo(o.toString());
    }
}
