package pojos;

import javax.persistence.*;

@Entity
@Table(name = "movimientos_especiales")
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "energia")
    private int energia;

    @Column(name = "nivel")
    private int nivel;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Personaje personaje;

    public Movimiento() {
    }

    public Movimiento(long id, String nombre, int energia, Personaje personaje) {
        this.id = id;
        this.nombre = nombre;
        this.energia = energia;
        this.personaje = personaje;
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

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public Personaje getPersonaje() {
        return personaje;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }
}
