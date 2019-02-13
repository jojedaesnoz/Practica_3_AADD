import pojos.Arma;

public class Prueba<T> {
    T cosa;

    public Prueba() {
    }

    public T getCosa() {
        return cosa;
    }

    public void setCosa(T cosa) {
        this.cosa = cosa;
    }
}
