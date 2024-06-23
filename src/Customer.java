import java.util.Scanner;

public class Customer extends Account {
    private Scanner scanner = new Scanner(System.in);

    public Customer(String username, String password, String name, String address, String email, String phone) {
        super(username, password, name, address, email, phone);
    }

    @Override
    public Customer createAccount() {
        System.out.println("Please fill in the following information to create a customer account:");

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

        Customer newCustomer = new Customer(username, password, name, address, email, phone);
        return newCustomer;
    }

    @Override
    public void displayInfo() {
        System.out.println("Customer Information:");
        System.out.println("Username: " + getUsername());
        System.out.println("Name: " + getName());
        System.out.println("Address: " + getAddress());
        System.out.println("Email: " + getEmail());
        System.out.println("Phone: " + getPhone());
    }
}
