package view;

import Factory.FactoryDAO;
import dao.CustomersDAO;
import entity.Customers;
import entity.Projects;

import java.io.IOException;
import java.util.List;

public class ControllerCustomers {
    private CustomersDAO customers;
    private int choice = -1;
    ControllerCustomers(CustomersDAO customers) {
        this.customers = customers;
    }
    public void customersMenu() throws IOException {
        while (true) {
            ConcollHelper.writeMessage("====================================");
            ConcollHelper.writeMessage("Input the command number");
            ConcollHelper.writeMessage("====================================");
            ConcollHelper.writeMessage("1 - Create customer");
            ConcollHelper.writeMessage("2 - Show customer`s project");
            ConcollHelper.writeMessage("3 - Update data");
            ConcollHelper.writeMessage("4 - Get all data");
            ConcollHelper.writeMessage("5 - Delete customer");
            ConcollHelper.writeMessage("6 - Get customer by Id");
            ConcollHelper.writeMessage("7 - Add customer`s project");
            ConcollHelper.writeMessage("0 - Return to main menu");
            try {
                choice = ConcollHelper.readInt();
                if (choice > 7)
                {
                    ConcollHelper.writeMessage("Error. There is no such command. Try again");
                    customersMenu();
                }
            } catch (IOException e) {
                ConcollHelper.writeMessage("Error. There is no such command.");
                customersMenu();
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
                 addCustomerProjects();
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
        Customers customers = new Customers(id, title);
        FactoryDAO.getCustomersDAO().add(customers);
        ConcollHelper.writeMessage("Customers create successfully.\n" +
                "You can see the result with the getAll command");

    }
    private void update() throws IOException {
        ConcollHelper.writeMessage("Input id : ");
        Integer id = ConcollHelper.readInt();
        ConcollHelper.writeMessage("Input title: ");
        String title = ConcollHelper.readString();
        Customers customers = new Customers(id, title);
        FactoryDAO.getCustomersDAO().update(customers);
        ConcollHelper.writeMessage("Customers update successfully.\n" +
                "You can see the result with the getAll command");

    }

    private void getAll() {
        List<Customers> list = FactoryDAO.getCustomersDAO().getAll();
        for (Customers c : list) {
            System.out.println(c);
        }
    }
    private void getById() throws IOException {
        ConcollHelper.writeMessage("Input id: ");
        Integer id = ConcollHelper.readInt();
        Customers customers = FactoryDAO.getCustomersDAO().getById(id);
        ConcollHelper.writeMessage(customers.toString());
    }
    private void delete() throws IOException {
        ConcollHelper.writeMessage("Input id for delete:e ");
        Integer id = ConcollHelper.readInt();
        Customers customers = FactoryDAO.getCustomersDAO().getById(id);
        FactoryDAO.getCustomersDAO().delete(customers);
        ConcollHelper.writeMessage("Customers deleted successfully.\n"  +
                "You can see the result with the getAll command");
    }
    private void showProjects() throws IOException {
        ConcollHelper.writeMessage("Input customer`s id: ");
        Integer id = ConcollHelper.readInt();
        Customers customer = FactoryDAO.getCustomersDAO().getById(id);
        for (Projects projects : customer.getProjects()) {
            ConcollHelper.writeMessage(projects.toString());
        }
    }
    private  void addCustomerProjects() throws IOException {
        ConcollHelper.writeMessage("Input customer`s id: ");
        Integer customer_id = ConcollHelper.readInt();
        ConcollHelper.writeMessage("Input project`s id");
        Integer project_id = ConcollHelper.readInt();
        FactoryDAO.getCustomersDAO().addObjects(customer_id, project_id);
        ConcollHelper.writeMessage("Project added successfully.\n" +
                "You can see the result with the showProjects command");
    }
}
