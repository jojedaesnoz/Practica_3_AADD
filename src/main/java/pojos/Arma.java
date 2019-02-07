package pojos;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "armas")
public class Arma {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "ataque")
    private int ataque;

    @Column(name = "rareza")
    private int rareza;

    @Column(name = "durabilidad")
    private int durabilidad;

    public Arma() {
    }

    public Arma(long id, String nombre, int ataque) {
        this.id = id;
        this.nombre = nombre;
        this.ataque = ataque;
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

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }
}
