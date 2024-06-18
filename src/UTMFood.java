import java.io.*;
import java.util.ArrayList;

public class UTMFood {
    private static ArrayList<Account> accounts = new ArrayList<>();
    private static ArrayList<Menu> menuItems = new ArrayList<>();

    public static void main(String[] args) {
        // Create some accounts
        Customer customer1 = new Customer("john_doe", "password123", "John Doe", "123 Main St", "john@example.com", "555-1234");
        Seller seller1 = new Seller("jane_doe", "password456", "Jane Doe", "456 Elm St", "jane@example.com", "555-5678");
        
        accounts.add(customer1);
        accounts.add(seller1);

        // Create some menu items
        Menu menu1 = new Menu(1, "Cheeseburger", 5.99, "Main Course");
        Menu menu2 = new Menu(2, "Caesar Salad", 3.99, "Appetizer");

        menuItems.add(menu1);
        menuItems.add(menu2);

        // Display accounts and menu items
        for (Account account : accounts) {
            account.createAccount();
        }

        for (Menu menu : menuItems) {
            menu.displayMenu();
        }

        // Example Order
        Order order = new Order("order001");
        order.addMenuItem(menu1);
        order.addMenuItem(menu2);

        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Total Price: $" + order.calculateTotal());
    }
}
