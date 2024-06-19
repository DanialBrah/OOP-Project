import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UTMFood {
    private static List<Customer> customers = new ArrayList<>();
    private static List<Seller> sellers = new ArrayList<>();
    private static List<Order> orders = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Welcome to UTMFood");
            System.out.println("1. Create Customer Account");
            System.out.println("2. Create Seller Account");
            System.out.println("3. Place Order");
            System.out.println("4. Display Customer Info");
            System.out.println("5. Display Seller Info");
            System.out.println("6. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    createCustomerAccount();
                    break;
                case 2:
                    createSellerAccount();
                    break;
                case 3:
                    placeOrder();
                    break;
                case 4:
                    displayCustomerInfo();
                    break;
                case 5:
                    displaySellerInfo();
                    break;
                case 6:
                    System.out.println("Thank you for using UTMFood. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createCustomerAccount() {
        Customer customer = new Customer("", "", "", "", "", "");
        customer = customer.createAccount();
        customers.add(customer);
        System.out.println("Customer account created successfully!");
    }

    private static void createSellerAccount() {
        Seller seller = new Seller("", "", "", "", "", "");
        seller = seller.createAccount();
        sellers.add(seller);
        System.out.println("Seller account created successfully!");
    }

    private static void placeOrder() {
        System.out.print("Enter Customer Username: ");
        String customerUsername = scanner.nextLine();

        Customer customer = findCustomerByUsername(customerUsername);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.print("Enter Order ID: ");
        String orderId = scanner.nextLine();
        Order order = new Order(orderId);

        while (true) {
            System.out.print("Enter Seller Username to add menu items from: ");
            String sellerUsername = scanner.nextLine();
            Seller seller = findSellerByUsername(sellerUsername);
            if (seller == null) {
                System.out.println("Seller not found.");
                continue;
            }

            System.out.println("Available Menus:");
            for (Menu menu : seller.getMenuList()) {
                menu.displayMenu();
            }

            System.out.print("Enter Menu ID to add to order (or 0 to finish): ");
            int menuId = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (menuId == 0) {
                break;
            }

            Menu menu = findMenuById(seller, menuId);
            if (menu != null) {
                order.addMenuItem(menu);
                System.out.println("Menu item added to order.");
            } else {
                System.out.println("Menu item not found.");
            }
        }

        orders.add(order);
        System.out.println("Order placed successfully. Total Price: $" + order.calculateTotal());
    }

    private static void displayCustomerInfo() {
        System.out.print("Enter Customer Username: ");
        String username = scanner.nextLine();
        Customer customer = findCustomerByUsername(username);
        if (customer != null) {
            customer.displayInfo();
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static void displaySellerInfo() {
        System.out.print("Enter Seller Username: ");
        String username = scanner.nextLine();
        Seller seller = findSellerByUsername(username);
        if (seller != null) {
            seller.displayInfo();
        } else {
            System.out.println("Seller not found.");
        }
    }

    private static Customer findCustomerByUsername(String username) {
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username)) {
                return customer;
            }
        }
        return null;
    }

    private static Seller findSellerByUsername(String username) {
        for (Seller seller : sellers) {
            if (seller.getUsername().equals(username)) {
                return seller;
            }
        }
        return null;
    }

    private static Menu findMenuById(Seller seller, int menuId) {
        for (Menu menu : seller.getMenuList()) {
            if (menu.getMenuId() == menuId) {
                return menu;
            }
        }
        return null;
    }
}
