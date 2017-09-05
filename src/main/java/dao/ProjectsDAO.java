package dao;

import connections.HibernateSessionFactory;
import entity.Developers;
import entity.Projects;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class ProjectsDAO extends AbstractDAO<Projects, Integer> {
    public ProjectsDAO() {
        super();
    }

    @Override
    public String getFindQuery() {
        return "from Projects p where p.id = :id";
    }

    @Override
    public String getSelectQuery() {
        return "from Projects";
    }

    @Override
    public String getUpdateQuery() {
        return "update Projects p set p.title =:title, p.cost =:cost where p.id = :id";
    }

    @Override
    protected void prepareStatementForUpdate(Query query, Projects projects) {
        try {
            query.setParameter("title", projects.getTitle());
            query.setParameter("cost", projects.getCost());
            query.setParameter("id", projects.getId());
            query.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addObjects(Integer project_id, Integer developer_id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Projects project = session.get(Projects.class, project_id);
            Developers developer = session.load(Developers.class, developer_id);
            project.addDevelopers(developer);
            session.save(project);
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
