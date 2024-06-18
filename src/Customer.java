public class Customer extends Account {
    public Customer(String username, String password, String name, String address, String email, String phone) {
        super(username, password, name, address, email, phone);
    }

    @Override
    public void createAccount() {
        System.out.println("Customer account created: " + username);
    }
}
