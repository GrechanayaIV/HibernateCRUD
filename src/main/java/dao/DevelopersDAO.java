package dao;

import connections.HibernateSessionFactory;
import entity.Developers;
import entity.Skills;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


public class DevelopersDAO extends AbstractDAO<Developers, Integer> {
    public DevelopersDAO() {
        super();
    }

    @Override
    public String getFindQuery() {
        return "from Developers d where d.id = :id";
    }

    @Override
    public String getSelectQuery() {
        return "from Developers";
    }

    @Override
    public String getUpdateQuery() {
        return "update Developers d set d.fname =:fname, d.lname =:lname, d.salary =:salary where d.id = :id";
    }

    @Override
    protected void prepareStatementForUpdate(Query query, Developers developers) {
        try {
            query.setParameter("fname", developers.getFname());
            query.setParameter("lname", developers.getLname());
            query.setParameter("salary", developers.getSalary());
            query.setParameter("id", developers.getId());
            query.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addObjects(Integer developer_id, Integer skill_id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Developers developer = session.get(Developers.class, developer_id);
            Skills skill = session.load(Skills.class, skill_id);
            developer.addSkills(skill);
            session.save(developer);
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
