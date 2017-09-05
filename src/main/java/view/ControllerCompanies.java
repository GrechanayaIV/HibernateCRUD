package view;

import Factory.FactoryDAO;
import dao.CompaniesDAO;
import entity.Companies;
import entity.Projects;

import java.io.IOException;
import java.util.List;

public class ControllerCompanies {
    private CompaniesDAO companies;
    private int choice = -1;

    ControllerCompanies(CompaniesDAO companies) {
        this.companies = companies;
    }

    public void companiesMenu() throws IOException {
        while (true) {
            ConcollHelper.writeMessage("====================================");
            ConcollHelper.writeMessage("Input the command number");
            ConcollHelper.writeMessage("====================================");
            ConcollHelper.writeMessage("1 - Create company");
            ConcollHelper.writeMessage("2 - Show company`s project");
            ConcollHelper.writeMessage("3 - Update data");
            ConcollHelper.writeMessage("4 - Get all data");
            ConcollHelper.writeMessage("5 - Delete company");
            ConcollHelper.writeMessage("6 - Get company by Id");
            ConcollHelper.writeMessage("7 - Add company`s project");
            ConcollHelper.writeMessage("0 - Return to main menu");
            try {
                choice = ConcollHelper.readInt();
                if (choice > 7) ConcollHelper.writeMessage("Error. There is no such command. Try again");
            } catch (IOException e) {
                ConcollHelper.writeMessage("Error. There is no such command.");
                companiesMenu();
            }

            switch (choice) {
                case 1:
                    create();
                    break;
                case 2:
                    showProjects();
                    break;
                case 3:
                    update();
                    break;
                case 4:
                    getAll();
                    break;
                case 5:
                    delete();
                    break;
                case 6:
                    getById();
                    break;
                case 7:
                    addCompanyProjects();
                    break;
                case 0:
                    MainController.mainMenu();
                    break;

            }
        }
    }

    private void create() throws IOException {
        ConcollHelper.writeMessage("Input id : ");
        Integer id = ConcollHelper.readInt();
        ConcollHelper.writeMessage("Input title: ");
        String title = ConcollHelper.readString();
        Companies companies = new Companies(id, title);
        FactoryDAO.getCompaniesDAO().add(companies);
        ConcollHelper.writeMessage("Companies create successfully.\n" +
                "You can see the result with the getAll command");

    }

    private void update() throws IOException {
        ConcollHelper.writeMessage("Input id : ");
        Integer id = ConcollHelper.readInt();
        ConcollHelper.writeMessage("Input title: ");
        String title = ConcollHelper.readString();
        Companies companies = new Companies(id, title);
        FactoryDAO.getCompaniesDAO().update(companies);
        ConcollHelper.writeMessage("Companies update successfully.\n" +
                "You can see the result with the getAll command");

    }

    private void getAll() {
        List<Companies> list = FactoryDAO.getCompaniesDAO().getAll();
        for (Companies c : list) {
            System.out.println(c);
        }
    }

    private void getById() throws IOException {
        ConcollHelper.writeMessage("Input id: ");
        Integer id = ConcollHelper.readInt();
        Companies companies = FactoryDAO.getCompaniesDAO().getById(id);
        ConcollHelper.writeMessage(companies.toString());
    }

    private void delete() throws IOException {
        ConcollHelper.writeMessage("Input id for delete: ");
        Integer id = ConcollHelper.readInt();
        Companies companies = FactoryDAO.getCompaniesDAO().getById(id);
        FactoryDAO.getCompaniesDAO().delete(companies);
        ConcollHelper.writeMessage("Companies deleted successfully.\n" +
                "You can see the result with the getAll command");
    }

    private void showProjects() throws IOException {
        ConcollHelper.writeMessage("Input company`s id: ");
        Integer id = ConcollHelper.readInt();
        Companies companies = FactoryDAO.getCompaniesDAO().getById(id);
        for (Projects projects : companies.getProjects()) {
            ConcollHelper.writeMessage(projects.toString());
        }
    }

    private void addCompanyProjects() throws IOException {
        ConcollHelper.writeMessage("Input company`s id: ");
        Integer company_id = ConcollHelper.readInt();
        ConcollHelper.writeMessage("Input project`s id");
        Integer project_id = ConcollHelper.readInt();
        FactoryDAO.getCompaniesDAO().addObjects(company_id, project_id);
        ConcollHelper.writeMessage("Project added successfully.\n" +
                "You can see the result with the showProjects command");
    }
}
