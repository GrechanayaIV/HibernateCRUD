package dao;

import entity.Skills;
import org.hibernate.query.Query;


public class SkillsDAO extends AbstractDAO<Skills, Integer> {
    public SkillsDAO() {
        super();
    }

    @Override
    public String getFindQuery() {
        return "from Skills s where s.id = :id";
    }

    @Override
    public String getSelectQuery() {
        return "from Skills";
    }

    @Override
    public String getUpdateQuery() {
        return "update Skills s set s.title=:title where s.id = :id";
    }

    @Override
    protected void prepareStatementForUpdate(Query query, Skills skills) {
        try {
            query.setParameter("title", skills.getTitle());
            query.setParameter("id", skills.getId());
            query.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
