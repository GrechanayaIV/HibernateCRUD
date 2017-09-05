package view;

import Factory.FactoryDAO;

import java.io.IOException;

public class MainController {
    public static void main(String[] args) throws IOException {
        mainMenu();
    }
    private static int choice ;
    public static String mainMenu() throws IOException {
        while (true) {
            ConcollHelper.writeMessage("====================================");
            ConcollHelper.writeMessage("Select a table to work with the database");
            ConcollHelper.writeMessage("====================================");
            ConcollHelper.writeMessage("1 - Developers");
            ConcollHelper.writeMessage("2 - Skills");
            ConcollHelper.writeMessage("3 - Companies");
            ConcollHelper.writeMessage("4 - Customers");
            ConcollHelper.writeMessage("5 - Projects");
            ConcollHelper.writeMessage("6 - Exit");
            try {
                choice = ConcollHelper.readInt();
                if (choice > 7)
                {
                    ConcollHelper.writeMessage("Error. There is no such command. Try again");
                    mainMenu();
                }
            } catch (IOException e) {
                ConcollHelper.writeMessage("Error. There is no such command.");
                mainMenu();
            }
            switch (choice) {
                case 1:
                    new ControllerDevelopers(FactoryDAO.getDevelopersDAO()).developersMenu();
                    break;
                case 2:
                    new ControllerSkills(FactoryDAO.getSkillsDAO()).skillsMenu();
                    break;
                case 3:
                    new ControllerCompanies(FactoryDAO.getCompaniesDAO()).companiesMenu();
                    break;
                case 4:
                    new ControllerCustomers(FactoryDAO.getCustomersDAO()).customersMenu();
                    break;
                case 5:
                    new ControllerProjects(FactoryDAO.getProjectsDAO()).projectsMenu();
                    break;
                case 6:
                    ConcollHelper.writeMessage("Good bay!");
                    System.exit(0);
                    break;

            }
        }
    }
}
