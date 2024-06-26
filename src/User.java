public interface User {
    String getUsername();
    String getPassword();
    String getName();
    String getAddress();
    String getEmail();
    String getPhone();
    
    Account createAccount();
    void displayInfo();
}
