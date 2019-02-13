package datos;

import org.hibernate.Session;
import org.hibernate.query.Query;
import pojos.Arma;
import pojos.Movimiento;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class ModeloArmas implements ModeloCRUD<Arma> {
    private Arma ultimoBorrado;

    @Override
    public void guardar(Arma dato) {
        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(dato);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Arma> cogerTodo() {
        Session session = HibernateUtil.getCurrentSession();
        Query query = session.createQuery("FROM Arma");
        ArrayList<Arma> armas = (ArrayList<Arma>) query.list() ;
        session.close();
        return armas;
    }

    @Override
    public List<Arma> coger(String busqueda) {
        Session session = HibernateUtil.getCurrentSession();
        Query query = session.createQuery("FROM Arma a WHERE a.nombre LIKE '%:busqueda%'");
        query.setParameter("busqueda", busqueda);
        ArrayList<Arma> armas = (ArrayList<Arma>) query.list();
        session.close();
        return armas;
    }

    @Override
    public void modificar(Arma dato) {
        guardar(dato);
    }

    @Override
    public Arma eliminar(Arma dato) {
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
        session.createQuery("DELETE FROM Arma").executeUpdate();
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
