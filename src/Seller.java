import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Seller extends Account {
    private List<Menu> menuList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public Seller(String username, String password, String name, String address, String email, String phone) {
        super(username, password, name, address, email, phone);
    }

    @Override
    public Seller createAccount() {
        System.out.println("Please fill in the following information to create a seller account:");

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();

        // Create a new Seller object with the entered information
        Seller newSeller = new Seller(username, password, name, address, email, phone);
        System.out.println("Seller account created: " + username);
        return newSeller;
    }

    public void putUpOrder() {
        System.out.println("Enter menu details:");
        System.out.print("Menu ID: ");
        int menuId = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        System.out.print("Menu Name: ");
        String menuName = scanner.nextLine();

        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline left-over

        System.out.print("Category: ");
        String category = scanner.nextLine();

        Menu newMenu = new Menu(menuId, menuName, price, category);
        menuList.add(newMenu);

        System.out.println("Menu added successfully!");
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    @Override
    public void displayInfo() {
        System.out.println("Displaying seller: " + getName());
        System.out.println("Address: " + getAddress());
        System.out.println("Email: " + getEmail());
        System.out.println("Phone: " + getPhone());
        System.out.println("Menu List:");
        for (Menu menu : menuList) {
            System.out.println("Menu ID: " + menu.getMenuId());
            System.out.println("Menu Name: " + menu.getMenuName());
            System.out.println("Price: " + menu.getPrice());
            System.out.println("Category: " + menu.getCategory());
            System.out.println();
        }
    }
}
