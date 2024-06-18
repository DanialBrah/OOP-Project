public abstract class Account{
    protected String username;
    protected String password;
    protected String name;
    protected String address;
    protected String email;
    protected String phone;

    public Account(String username, String password, String name, String address, String email, String phone) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public abstract void createAccount();
}
