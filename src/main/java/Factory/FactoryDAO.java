package Factory;

import connections.HibernateSessionFactory;
import dao.*;


public class FactoryDAO{
        private static DevelopersDAO developersDAO;
        private static SkillsDAO skillsDAO;
        private static CompaniesDAO companiesDAO;
        private static CustomersDAO customersDAO;
        private static ProjectsDAO projectsDAO;

        public static DevelopersDAO getDevelopersDAO() {
            if(developersDAO == null){
                developersDAO = new DevelopersDAO();
            }
            return developersDAO;
        }

        public static SkillsDAO getSkillsDAO(){
            if(skillsDAO == null){
                skillsDAO = new SkillsDAO();
            }
            return skillsDAO;
        }

        public static CompaniesDAO getCompaniesDAO(){
            if(companiesDAO == null){
                companiesDAO = new CompaniesDAO();
            }
            return companiesDAO;
        }

        public static CustomersDAO getCustomersDAO(){
            if(customersDAO == null){
                customersDAO = new CustomersDAO();
            }
            return customersDAO;
        }

        public static ProjectsDAO getProjectsDAO(){
            if(projectsDAO == null){
                projectsDAO = new ProjectsDAO();
            }
            return projectsDAO;
        }
}
