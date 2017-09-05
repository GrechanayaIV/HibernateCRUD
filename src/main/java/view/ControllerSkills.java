package view;

import Factory.FactoryDAO;
import dao.SkillsDAO;
import entity.Skills;

import java.io.IOException;
import java.util.List;

public class ControllerSkills {
    private SkillsDAO skillsDAO;
    private int choice = -1;

    ControllerSkills(SkillsDAO skillsDAO) {
        this.skillsDAO = skillsDAO;
    }

    void skillsMenu() throws IOException {
        while (true) {
            ConcollHelper.writeMessage("====================================");
            ConcollHelper.writeMessage("Input the command number");
            ConcollHelper.writeMessage("====================================");
            ConcollHelper.writeMessage("1 - Create skills");
            ConcollHelper.writeMessage("2 - Update data");
            ConcollHelper.writeMessage("3 - Get all data");
            ConcollHelper.writeMessage("4 - Delete skills");
            ConcollHelper.writeMessage("5 - Get skill by id");
            ConcollHelper.writeMessage("0 - Return to main menu");
            try {
                choice = ConcollHelper.readInt();
            } catch (IOException e) {
                ConcollHelper.writeMessage("Error. There is no such command.");
            }

            switch (choice) {
                case 1:
                    create();
                    break;
                case 2:
                    update();
                    break;
                case 3:
                    getAll();
                    break;
                case 4:
                    delete();
                    break;
                case 5:
                    getById();
                    break;
                case 0:
                    MainController.mainMenu();
                    break;
                default:
            }
        }

    }

    private void create() throws IOException {
        ConcollHelper.writeMessage("Input id: ");
        Integer id = ConcollHelper.readInt();
        ConcollHelper.writeMessage("Input title: ");
        String title = ConcollHelper.readString();
        Skills skills = new Skills(id, title);
        FactoryDAO.getSkillsDAO().add(skills);
        ConcollHelper.writeMessage("Skills created successfully.\n" +
                "You can see the result with the getAll command");

    }

    private void update() throws IOException {
        ConcollHelper.writeMessage("Input id : ");
        Integer id = ConcollHelper.readInt();
        ConcollHelper.writeMessage("Input title: ");
        String title = ConcollHelper.readString();
        Skills skills = new Skills(id, title);
        FactoryDAO.getSkillsDAO().update(skills);
        ConcollHelper.writeMessage("Skills update successfully.\n" +
                "You can see the result with the getAll command");

    }

    private void getAll() {
        List<Skills> list = FactoryDAO.getSkillsDAO().getAll();
        for (Skills s : list) {
            System.out.println(s);
        }
    }

    private void getById() throws IOException {
        ConcollHelper.writeMessage("Input id: ");
        Integer id = ConcollHelper.readInt();
        Skills skills = FactoryDAO.getSkillsDAO().getById(id);
        ConcollHelper.writeMessage(skills.toString());
    }

    private void delete() throws IOException {
        ConcollHelper.writeMessage("Input skill`s id for delete: ");
        Integer id = ConcollHelper.readInt();
        Skills skills = FactoryDAO.getSkillsDAO().getById(id);
        FactoryDAO.getSkillsDAO().delete(skills);
        ConcollHelper.writeMessage("Skills deleted successfully.\n" +
                "You can see the result with the getAll command");
    }
}
