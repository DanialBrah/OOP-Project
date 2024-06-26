public abstract class Account implements User {
    private String username;
    private String password;
    private String name;
    private String address;
    private String email;
    private String phone;

    public Account(String username, String password, String name, String address, String email, String phone) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public abstract Account createAccount();

    @Override
    public abstract void displayInfo();
}
