package datos;

import org.hibernate.Session;
import org.hibernate.query.Query;
import pojos.Personaje;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class ModeloPersonajes implements ModeloCRUD<Personaje> {
    public Personaje ultimoBorrado;

    @Override
    public void guardar(Personaje dato) {
        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(dato);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Personaje> cogerTodo() {
        Session session = HibernateUtil.getCurrentSession();
        Query query = session.createQuery("FROM Personaje");
        ArrayList<Personaje> personajes = (ArrayList<Personaje>) query.list() ;
        session.close();
        return personajes;
    }

    @Override
    public List<Personaje> coger(String busqueda) {
        Session session = HibernateUtil.getCurrentSession();
        Query query = session.createQuery("FROM Personaje p WHERE p.nombre LIKE '%:busqueda%'");
        query.setParameter("busqueda", busqueda);
        ArrayList<Personaje> personajes = (ArrayList<Personaje>) query.list();
        session.close();
        return personajes;
    }

    @Override
    public void modificar(Personaje dato) {
        guardar(dato);
    }

    @Override
    public Personaje eliminar(Personaje dato) {
        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.delete(dato);
        session.getTransaction().commit();
        session.close();
        ultimoBorrado = dato;
        return dato;
    }

    @Override
    public void eliminarTodo() {
        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM Personaje").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deshacer() {
        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(ultimoBorrado);
        session.getTransaction().commit();
        session.close();
    }
}
