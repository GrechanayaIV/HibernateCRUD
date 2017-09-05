package dao;

import connections.HibernateSessionFactory;
import entity.Companies;
import entity.Projects;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class CompaniesDAO extends AbstractDAO<Companies, Integer> {
    public CompaniesDAO() {
        super();
    }

    @Override
    public String getFindQuery() {
        return "from Companies c where c.id = :id";
    }

    @Override
    public String getSelectQuery() {
        return "from Companies";
    }

    @Override
    public String getUpdateQuery() {
        return "update Companies c set c.title =:title where c.id = :id";
    }

    @Override
    protected void prepareStatementForUpdate(Query query, Companies companies) {
        try {
            query.setParameter("title", companies.getTitle());
            query.setParameter("id", companies.getId());
            query.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addObjects(Integer company_id, Integer project_id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Companies company = session.get(Companies.class, company_id);
            Projects project = session.load(Projects.class, project_id);
            company.addProjects(project);
            session.save(company);
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
