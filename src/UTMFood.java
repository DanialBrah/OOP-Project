import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UTMFood {
    private static List<Customer> customers = new ArrayList<>();
    private static List<Seller> sellers = new ArrayList<>();
    private static List<Order> orders = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        loadMenuToAllSellers("menu.txt");

        while (true) {
            clearScreen();
            System.out.println("-----------------------------------------------------");
            System.out.println("                   Welcome to UTMFood");
            System.out.println("-----------------------------------------------------");
            System.out.println("1. Create Customer Account");
            System.out.println("2. Create Seller Account");
            System.out.println("3. Place Order");
            System.out.println("4. Display Customer Info");
            System.out.println("5. Display Seller Info");
            System.out.println("6. Add Menu for Seller");
            System.out.println("7. Display Menu to Customer");
            System.out.println("8. Load Menu from File for Seller");
            System.out.println("9. Display Order");
            System.out.println("0. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            clearScreen(); // Clear the screen before displaying the result of the choice

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
                    addMenuForSeller();
                    break;
                case 7:
                    displayMenuToCustomer();
                    break;
                case 8:
                    loadMenuFromFile();
                    break;
                case 9:
                    displayOrderToSeller();
                    break;
                case 0:
                    System.out.println("Thank you for using UTMFood. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println("Press Enter to continue...");
            scanner.nextLine(); // Wait for the user to press Enter
        }
    }

    private static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Error clearing screen: " + e.getMessage());
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

    private static void addMenuForSeller() {
        System.out.print("Enter Seller Username: ");
        String username = scanner.nextLine();
        Seller seller = findSellerByUsername(username);
        if (seller != null) {
            seller.putUpOrder();
        } else {
            System.out.println("Seller not found.");
        }
    }

    private static void displayMenuToCustomer() {
        System.out.print("Enter Seller Username: ");
        String username = scanner.nextLine();
        Seller seller = findSellerByUsername(username);
        if (seller != null) {
            System.out.println("Available Menus: \n");
            
            for (Menu menu : seller.getMenuList()) {

                menu.displayMenu();
            }
        } else {
            System.out.println("Seller not found.");
        }
    }

    private static void loadMenuFromFile() {
        System.out.print("Enter Seller Username: ");
        String username = scanner.nextLine();
        Seller seller = findSellerByUsername(username);
        if (seller != null) {
            System.out.print("Enter filename: ");
            String menu = scanner.nextLine();
            seller.menuFile(menu);
            System.out.println("Menu loaded from file successfully!");
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

    private static void loadMenuToAllSellers(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            List<Menu> globalMenuList = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] menuDetails = line.split(",");
                int menuId = Integer.parseInt(menuDetails[0]);
                String menuName = menuDetails[1];
                double price = Double.parseDouble(menuDetails[2]);
                String category = menuDetails[3];
                Menu menu = new Menu(menuId, menuName, price, category);
                globalMenuList.add(menu);
            }

            for (Seller seller : sellers) {
                seller.getMenuList().addAll(globalMenuList);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static void displayOrderToSeller()
    {
        // System.out.print("Enter Seller Username: ");
        // String username = scanner.nextLine();
        // Seller seller = findSellerByUsername(username);
        // if (seller != null) {
        //     System.out.println("Available Orders: \n");
            
        //     for (Order order : orders) {
        //         order.displayOrder();
        //     }
        // }
        System.out.println("Available Orders: \n");
            
            for (Order order : orders) {
                order.displayOrder();
            }
            
    }
}
