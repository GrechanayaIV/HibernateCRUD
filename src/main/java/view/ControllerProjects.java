package view;

import Factory.FactoryDAO;
import dao.ProjectsDAO;
import entity.Developers;
import entity.Projects;

import java.io.IOException;
import java.util.List;

public class ControllerProjects {
    private ProjectsDAO projectsDAO;
    private int choice = -1;

    ControllerProjects(ProjectsDAO projectsDAO) {
        this.projectsDAO = projectsDAO;
    }

    public void projectsMenu() throws IOException {
        while (true) {
            ConcollHelper.writeMessage("====================================");
            ConcollHelper.writeMessage("Input the command number");
            ConcollHelper.writeMessage("====================================");
            ConcollHelper.writeMessage("1 - Create project");
            ConcollHelper.writeMessage("2 - Show project`s developers");
            ConcollHelper.writeMessage("3 - Update data");
            ConcollHelper.writeMessage("4 - Get all data");
            ConcollHelper.writeMessage("5 - Delete project");
            ConcollHelper.writeMessage("6 - Get project by Id");
            ConcollHelper.writeMessage("7 - Add project`s developer");
            ConcollHelper.writeMessage("0 - Return to main menu");
            try {
                choice = ConcollHelper.readInt();
                if (choice > 7) {
                    ConcollHelper.writeMessage("Error. There is no such command. Try again");
                    projectsMenu();
                }
            } catch (IOException e) {
                ConcollHelper.writeMessage("Error. There is no such command.");
                projectsMenu();
            }

            switch (choice) {
                case 1:
                    create();
                    break;
                case 2:
                    showDevelopers();
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
                    addProjectDevelopers();
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
        ConcollHelper.writeMessage("Input cost: ");
        int cost = ConcollHelper.readInt();
        Projects projects = new Projects(id, title, cost);
        FactoryDAO.getProjectsDAO().add(projects);
        ConcollHelper.writeMessage("Projects create successfully.\n" +
                "You can see the result with the getAll command");

    }

    private void update() throws IOException {
        ConcollHelper.writeMessage("Input id : ");
        Integer id = ConcollHelper.readInt();
        ConcollHelper.writeMessage("Input title: ");
        String title = ConcollHelper.readString();
        ConcollHelper.writeMessage("Input cost: ");
        int cost = ConcollHelper.readInt();
        Projects projects = new Projects(id, title, cost);
        FactoryDAO.getProjectsDAO().update(projects);
        ConcollHelper.writeMessage("Projects update successfully.\n" +
                "You can see the result with the getAll command");

    }

    private void getAll() {
        List<Projects> list = FactoryDAO.getProjectsDAO().getAll();
        for (Projects p : list) {
            System.out.println(p);
        }
    }

    private void getById() throws IOException {
        ConcollHelper.writeMessage("Input id: ");
        Integer id = ConcollHelper.readInt();
        Projects projects = FactoryDAO.getProjectsDAO().getById(id);
        ConcollHelper.writeMessage(projects.toString());
    }

    private void delete() throws IOException {
        ConcollHelper.writeMessage("Input id for delete: ");
        Integer id = ConcollHelper.readInt();
        Projects projects = FactoryDAO.getProjectsDAO().getById(id);
        FactoryDAO.getProjectsDAO().delete(projects);
        ConcollHelper.writeMessage("Project deleted successfully.\n" +
                "You can see the result with the getAll command");
    }

    private void showDevelopers() throws IOException {
        ConcollHelper.writeMessage("Input project`s id: ");
        Integer id = ConcollHelper.readInt();
        Projects projects = FactoryDAO.getProjectsDAO().getById(id);
        for (Developers developers : projects.getDevelopers()) {
            ConcollHelper.writeMessage(developers.toString());
        }
    }

    private void addProjectDevelopers() throws IOException {
        ConcollHelper.writeMessage("Input project`s id: ");
        Integer project_id = ConcollHelper.readInt();
        ConcollHelper.writeMessage("Input developer`s id");
        Integer developer_id = ConcollHelper.readInt();
        FactoryDAO.getProjectsDAO().addObjects(project_id, developer_id);
        ConcollHelper.writeMessage("Developer added successfully.\n" +
                "You can see the result with the showDevelopers command");
    }
}
