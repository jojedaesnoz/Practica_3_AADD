import logica.ControladorArmas;
import logica.ControladorMovimientos;
import logica.ControladorPersonajes;
import pojos.Arma;

public class Main {

    public static void main(String[] args) {

//        Modelo
//        ControladorPersonajes controladorPersonajes = new ControladorPersonajes();
//        ControladorMovimientos controladorMovimientos = new ControladorMovimientos();
//        ControladorArmas controladorArmas = new ControladorArmas();

        Prueba<Arma> prueba = new Prueba<>();
        prueba.setCosa(new Arma());
        System.out.println(prueba.cosa.getClass().getSimpleName());
    }
}
