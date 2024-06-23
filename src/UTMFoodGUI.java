 import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UTMFoodGUI extends JFrame {
    private JTextField usernameField, passwordField, nameField, addressField, emailField, phoneField;
    private JButton createAccountButton;

    public UTMFoodGUI() {
        setTitle("UTMFood Account Creation");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 2));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Address:"));
        addressField = new JTextField();
        add(addressField);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Phone:"));
        phoneField = new JTextField();
        add(phoneField);

        createAccountButton = new JButton("Create Account");
        add(createAccountButton);

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = passwordField.getText();
                String name = nameField.getText();
                String address = addressField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();

                Customer newCustomer = new Customer(username, password, name, address, email, phone);
                newCustomer.createAccount();

                JOptionPane.showMessageDialog(null, "Account Created Successfully!");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UTMFoodGUI().setVisible(true);
            }
        });
    }
}
