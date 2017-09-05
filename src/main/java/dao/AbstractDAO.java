package dao;

import connections.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public abstract class AbstractDAO<T extends IdentifiedTable<PK>, PK> implements GenericDAO<T, PK> {
    private static SessionFactory sessionFactory;

    AbstractDAO() {
        sessionFactory = HibernateSessionFactory.getSessionFactory();
    }

    public abstract String getFindQuery();

    public abstract String getSelectQuery();

    public abstract String getUpdateQuery();

    protected abstract void prepareStatementForUpdate(Query query, T object);

    @Override
    public T getById(PK id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        T entity;
        try {
            Query query = session.createQuery(getFindQuery());
            query.setParameter("id", id);
            entity = (T) query.uniqueResult();
        } finally {
            transaction.commit();
            session.close();
        }
        return entity;
    }

    @Override
    public T add(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(entity);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return entity;
    }

    @Override
    public T update(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery(getUpdateQuery());
            prepareStatementForUpdate(query, entity);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return entity;
    }

    @Override
    public List<T> getAll() {
        List list = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            list = session.createQuery(getSelectQuery()).getResultList();
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public void delete(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(entity);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }
}
