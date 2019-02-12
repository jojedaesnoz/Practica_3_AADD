package datos;

import pojos.Arma;
import pojos.Movimiento;
import pojos.Personaje;

import java.util.ArrayList;
import java.util.List;

public class Modelo {

    public void conectar() {

    }

    public void desconectar() {

    }

    // GUARDAR --- CREATE
    public void guardarPersonaje(Personaje personaje) {
        
    }
    
    public void guardarArma(Arma arma) {
        
    }
    
    public void guardarMovimiento(Movimiento movimiento) {
        
    }

    
    // LEER --- READ
    public List<Personaje> getPersonajes() {
        ArrayList<Personaje> personajes = new ArrayList<>();

        return personajes;
    }

    public List<Personaje> getPersonajes(String busqueda) {
        ArrayList<Personaje> personajes = new ArrayList<>();

        return personajes;
    }
    
    public List<Arma> getArmas() {
        ArrayList<Arma> personajes = new ArrayList<>();

        return personajes;
    }

    public List<Arma> getArmas(String busqueda) {
        ArrayList<Arma> personajes = new ArrayList<>();

        return personajes;
    }
    
    public List<Movimiento> getMovimientos() {
        ArrayList<Movimiento> personajes = new ArrayList<>();

        return personajes;
    }

    public List<Movimiento> getMovimientos(String busqueda) {
        ArrayList<Movimiento> personajes = new ArrayList<>();

        return personajes;
    }

    
    // MODIFICAR --- UPDATE
    public void modificarPersonaje(Personaje personaje) {
        
    }
    
    public void modificarArma(Arma arma) {
        
    }
    
    public void modificarMovimiento(Movimiento movimiento) {
        
    }
    
    
    // ELIMINAR --- DELETE
    public void eliminarPersonaje(Personaje personaje) {
        
    }
    
    public void eliminarArma(Arma arma) {
        
    }
    
    public void eliminarMovimiento(Movimiento movimiento) {
        
    }

    public void eliminarTodosLosPersonajes() {

    }

    public void eliminarTodasLasArmas() {

    }

    public void eliminarTodosLosMovimientos() {

    }
}
