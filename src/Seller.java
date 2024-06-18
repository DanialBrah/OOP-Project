public class Seller extends Account{
    public Seller(String username, String password, String name, String address, String email, String phone) {
        super(username, password, name, address, email, phone);
    }

    @Override
    public void createAccount() {
        System.out.println("Seller account created: " + username);
    }

    public void displayListSeller() {
        System.out.println("Displaying seller: " + name);
    }
}
