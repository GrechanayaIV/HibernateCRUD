package dao;

import connections.HibernateSessionFactory;
import entity.Customers;
import entity.Projects;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class CustomersDAO extends AbstractDAO<Customers, Integer> {
    public CustomersDAO() {
        super();
    }

    @Override
    public String getFindQuery() {
        return "from Customers c where c.id = :id";
    }

    @Override
    public String getSelectQuery() {
        return "from Customers";
    }

    @Override
    public String getUpdateQuery() {
        return "update Customers c set c.title =:title where c.id = :id";
    }

    @Override
    protected void prepareStatementForUpdate(Query query, Customers customers) {
        try {
            query.setParameter("title", customers.getTitle());
            query.setParameter("id", customers.getId());
            query.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addObjects(Integer customer_id, Integer project_id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Customers customer = session.get(Customers.class, customer_id);
            Projects project = session.load(Projects.class, project_id);
            customer.addProjects(project);
            session.save(customer);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            assert transaction != null;
            transaction.rollback();
        } finally {
            assert session != null;
            session.close();
        }
    }
}
