import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

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

        newSeller.saveSellerToFile("seller.txt");//save seller to seller.txt

        // Load menu from file
        System.out.print("Do you want to load menu from file? (yes/no): ");
        String loadMenu = scanner.nextLine();
        if (loadMenu.equalsIgnoreCase("yes")) {
            /*System.out.print("Enter filename: ");
            String menuFileName = scanner.nextLine();
            newSeller.menuFile(menuFileName);
            System.out.println("Menu loaded from file successfully!");*/
            newSeller.menuFile("menu.txt");
            System.out.println("Menu loaded from file successfully!");
        }

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
        saveMenuToFile(newMenu, "menu.txt");

        System.out.println("Menu added successfully!");
    }

    public void menuFile(String menu){
        try(BufferedReader bReader=new BufferedReader(new FileReader(menu))){
            String line;
            while((line=bReader.readLine())!=null){
                String[] menuDetails=line.split(",");
                int menuId=Integer.parseInt(menuDetails[0]);
                String menuName=menuDetails[1];
                double price=Double.parseDouble(menuDetails[2]);
                String category=menuDetails[3];
                Menu newMenu = new Menu(menuId, menuName, price, category);
                menuList.add(newMenu);
            }
        } catch(IOException e){
            System.out.println("Error reading file: "+e.getMessage());
        }
    }

    private void saveMenuToFile(Menu menu, String filename) {
        try (FileWriter fw = new FileWriter(filename, true)) {
            fw.write(menu.getMenuId() + "," + menu.getMenuName() + "," + menu.getPrice() + "," + menu.getCategory() + "\n");
        } catch (IOException e) {
            System.out.println("Error writing MENU to file: " + e.getMessage());
        }
    }

    private void saveSellerToFile(String filename){
        try (FileWriter fw = new FileWriter(filename, true)) {
            fw.write(getUsername() + "," + getPassword() + "," + getName() + "," + getAddress() + "," + getEmail() + "," + getPhone() + "\n");
            } catch (IOException e) {
                System.out.println("Error writing SELLER to file: " + e.getMessage());
            }
    }

    public List<Menu> getMenuList(){return menuList;}

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
