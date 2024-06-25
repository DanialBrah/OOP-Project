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

        createCustomerAccount();
        createSellerAccount();

        boolean exit = false;
        while (!exit) {
            System.out.println("-----------------------------------------------------");
            System.out.println("                   Welcome to UTMFood");
            System.out.println("-----------------------------------------------------");
            System.out.println("1. Customer Menu");
            System.out.println("2. Seller Menu");
            System.out.println("0. Exit");

            System.out.print("Choose an option: ");
            int mainChoice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            clearScreen(); // Clear the screen before displaying the result of the choice

            switch (mainChoice) {
                case 1:
                    customerMenu();
                    break;
                case 2:
                    sellerMenu();
                    break;
                case 0:
                    System.out.println("Thank you for using UTMFood. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            if (!exit) {
                System.out.println("Press Enter to continue...");
                scanner.nextLine(); // Wait for the user to press Enter
                clearScreen();
            }
        }
    }

    private static void customerMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("-----------------------------------------------------");
            System.out.println("                    Customer Menu");
            System.out.println("-----------------------------------------------------");
            System.out.println("1. Create Customer Account");
            System.out.println("2. Place Order");
            System.out.println("3. Display Customer Info");
            System.out.println("4. Display Menu to Customer");
            System.out.println("0. Back to Main Menu");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            clearScreen();

            switch (choice) {
                case 1:
                    Customer customer = new Customer("", "", "", "", "", "");
                    customers.add(customer.createAccount());
                    System.out.println("Customer account created successfully!");
                    break;
                case 2:
                    placeOrder();
                    break;
                case 3:
                    displayCustomerInfo();
                    break;
                case 4:
                    displayMenuToCustomer();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            if (!back) {
                System.out.println("Press Enter to continue...");
                scanner.nextLine(); // Wait for the user to press Enter
                clearScreen();
            }
        }
    }

    private static void sellerMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("-----------------------------------------------------");
            System.out.println("                     Seller Menu");
            System.out.println("-----------------------------------------------------");
            System.out.println("1. Create Seller Account");
            System.out.println("2. Display Seller Info");
            System.out.println("3. Add Menu for Seller");
            System.out.println("4. Load Menu from File for Seller");
            System.out.println("5. Display Order");
            System.out.println("0. Back to Main Menu");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            clearScreen();

            switch (choice) {
                case 1:
                    Seller seller = new Seller("", "", "", "", "", "");
                    sellers.add(seller.createAccount());
                    System.out.println("Seller account created successfully!");
                    break;
                case 2:
                    displaySellerInfo();
                    break;
                case 3:
                    addMenuForSeller();
                    break;
                case 4:
                    loadMenuFromFile();
                    break;
                case 5:
                    displayOrderToSeller();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            if (!back) {
                System.out.println("Press Enter to continue...");
                scanner.nextLine(); // Wait for the user to press Enter
                clearScreen();
            }
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
        try (BufferedReader br = new BufferedReader(new FileReader("customer.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] customerDetails = line.split(",");
                String username = customerDetails[0];
                String password = customerDetails[1];
                String name = customerDetails[2];
                String address = customerDetails[3];
                String email = customerDetails[4];
                String phone = customerDetails[5];
                customers.add(new Customer(username, password, name, address, email, phone));
            }
        } catch (IOException e) {
            System.out.println("Error reading customers from file: " + e.getMessage());
        }
        /*Customer customer = new Customer("", "", "", "", "", "");
        customer = customer.createAccount();
        customers.add(customer);
        System.out.println("Customer account created successfully!");*/
    }

    private static void createSellerAccount() {
        try (BufferedReader br = new BufferedReader(new FileReader("seller.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] sellerDetails = line.split(",");
                String username = sellerDetails[0];
                String password = sellerDetails[1];
                String name = sellerDetails[2];
                String address = sellerDetails[3];
                String email = sellerDetails[4];
                String phone = sellerDetails[5];
                sellers.add(new Seller(username, password, name, address, email, phone));
            }
        } catch (IOException e) {
            System.out.println("Error reading sellers from file: " + e.getMessage());
        }
        /*Seller seller = new Seller("", "", "", "", "", "");
        seller = seller.createAccount();
        sellers.add(seller);
        System.out.println("Seller account created successfully!");*/
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
            System.out.print("Enter Seller Username to add menu items from (n to finish): ");
            String sellerUsername = scanner.nextLine();
            Seller seller = findSellerByUsername(sellerUsername);
            if (sellerUsername.equals("n")){
                break;
            }
            else if (seller == null) {
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

    //to input menu from keyboard
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
        System.out.println("Available Sellers:");
        for (Seller seller : sellers) {
            System.out.println("- " + seller.getUsername());
        }

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

    /*private static void loadMenuToAllSellers(String filename) {
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
    }*/

   //Display order to seller
   private static void displayOrderToSeller()
   {
        System.out.print("Enter Seller Username: ");
       String username = scanner.nextLine();
       Seller seller = findSellerByUsername(username);
       if (seller != null) {
           System.out.println("Available Orders: \n");
           
           for (Order order : orders) {
               order.displayOrder();
               System.out.println(seller.getUsername());
           }
       }
    }
}
