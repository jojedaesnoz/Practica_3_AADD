package datos;

import org.hibernate.Session;
import org.hibernate.query.Query;
import pojos.Movimiento;
import pojos.Personaje;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class ModeloMovimientos implements ModeloCRUD<Movimiento> {

    public Movimiento ultimoBorrado;

    @Override
    public void guardar(Movimiento dato) {
        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(dato);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Movimiento> cogerTodo() {
        Session session = HibernateUtil.getCurrentSession();
        Query query = session.createQuery("FROM Movimiento");
        ArrayList<Movimiento> movimientos = (ArrayList<Movimiento>) query.list() ;
        session.close();
        return movimientos;
    }

    @Override
    public List<Movimiento> coger(String busqueda) {
        Session session = HibernateUtil.getCurrentSession();
        Query query = session.createQuery("FROM Movimiento m WHERE m.nombre LIKE '%:busqueda%'");
        query.setParameter("busqueda", busqueda);
        ArrayList<Movimiento> movimientos = (ArrayList<Movimiento>) query.list();
        session.close();
        return movimientos;
    }

    @Override
    public void modificar(Movimiento dato) {
        guardar(dato);
    }

    @Override
    public Movimiento eliminar(Movimiento dato) {
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
        session.createQuery("DELETE FROM Movimiento").executeUpdate();
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
