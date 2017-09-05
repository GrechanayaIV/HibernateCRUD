package view;

import Factory.FactoryDAO;
import dao.DevelopersDAO;
import entity.Developers;
import entity.Skills;

import java.io.IOException;
import java.util.List;

public class ControllerDevelopers {
    private DevelopersDAO developersDAO;
    private int choice = -1;

    ControllerDevelopers(DevelopersDAO developersDAO) {
        this.developersDAO = developersDAO;
    }

    void developersMenu() throws IOException {
        while (true) {
            ConcollHelper.writeMessage("====================================");
            ConcollHelper.writeMessage("Input the command number");
            ConcollHelper.writeMessage("====================================");
            ConcollHelper.writeMessage("1 - Create developer");
            ConcollHelper.writeMessage("2 - Show developer`s skills");
            ConcollHelper.writeMessage("3 - Update data");
            ConcollHelper.writeMessage("4 - Get all data");
            ConcollHelper.writeMessage("5 - Delete developer");
            ConcollHelper.writeMessage("6 - Get developer by Id");
            ConcollHelper.writeMessage("7 - Add developer`s skill");
            ConcollHelper.writeMessage("0 - Return to main menu");
            try {
                choice = ConcollHelper.readInt();
                if (choice > 7) ConcollHelper.writeMessage("Error. There is no such command. Try again");
            } catch (IOException e) {
                ConcollHelper.writeMessage("Error. There is no such command.");
            }

            switch (choice) {
                case 1:
                    create();
                    break;
                case 2:
                    showSkills();
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
                    addDeveloperSkills();
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
        ConcollHelper.writeMessage("Input first name: ");
        String fname = ConcollHelper.readString();
        ConcollHelper.writeMessage("Input last name: ");
        String lname = ConcollHelper.readString();
        ConcollHelper.writeMessage("Input salary: ");
        int salary = ConcollHelper.readInt();
        Developers developers = new Developers(id, fname, lname, salary);
        FactoryDAO.getDevelopersDAO().add(developers);
        ConcollHelper.writeMessage("Developers created successfully.\n" +
                "You can see the result with the getAll command");

    }

    private void update() throws IOException {
        ConcollHelper.writeMessage("Input id : ");
        Integer id = ConcollHelper.readInt();
        ConcollHelper.writeMessage("Input first name: ");
        String fname = ConcollHelper.readString();
        ConcollHelper.writeMessage("Input last name: ");
        String lname = ConcollHelper.readString();
        ConcollHelper.writeMessage("Input salary: ");
        int salary = ConcollHelper.readInt();
        Developers developers = new Developers(id, fname, lname, salary);
        FactoryDAO.getDevelopersDAO().update(developers);
        ConcollHelper.writeMessage("Developers update successfully.\n" +
                "You can see the result with the getAll command");

    }

    private void getAll() {
        List<Developers> list = FactoryDAO.getDevelopersDAO().getAll();
        for (Developers d : list) {
            System.out.println(d);
        }
    }

    private void getById() throws IOException {
        ConcollHelper.writeMessage("Input id: ");
        Integer id = ConcollHelper.readInt();
        Developers developers = FactoryDAO.getDevelopersDAO().getById(id);
        ConcollHelper.writeMessage(developers.toString());
    }

    private void delete() throws IOException {
        ConcollHelper.writeMessage("Input skill`s id for delete: ");
        Integer id = ConcollHelper.readInt();
        Developers developers = FactoryDAO.getDevelopersDAO().getById(id);
        FactoryDAO.getDevelopersDAO().delete(developers);
        ConcollHelper.writeMessage("SDeveloper deleted successfully.\n" +
                "You can see the result with the getAll command");
    }

    private void showSkills() throws IOException {
        ConcollHelper.writeMessage("Input developr`s id: ");
        Integer id = ConcollHelper.readInt();
        Developers developers = FactoryDAO.getDevelopersDAO().getById(id);
        for (Skills skills : developers.getSkills()) {
            ConcollHelper.writeMessage(skills.toString());
        }
    }

    private void addDeveloperSkills() throws IOException {
        ConcollHelper.writeMessage("Input developer`s id: ");
        Integer developer_id = ConcollHelper.readInt();
        ConcollHelper.writeMessage("Input skill`s id");
        Integer skills_id = ConcollHelper.readInt();
        FactoryDAO.getDevelopersDAO().addObjects(developer_id, skills_id);
        ConcollHelper.writeMessage("Skills added successfully.\n" +
                "You can see the result with the shoeSkills command");

    }
}
