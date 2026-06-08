/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author pc
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {
    private JTextField idField;
    private JTextField nameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleCombo;
    private JButton loginButton;
    private JButton signupButton;

    public LoginFrame() {
        setTitle("Pharmacy System Login");
        setSize(450, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();
    }

    private void initComponents() {
        // Main container
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(245, 245, 245));
        add(mainPanel);

        // Card panel
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(350, 320));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // Title at top center
        JLabel title = new JLabel("Welcome", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        card.add(title, BorderLayout.NORTH);

        // Center panel for fields
        JPanel fieldsPanel = new JPanel(new GridBagLayout());
        fieldsPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ID field
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        fieldsPanel.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1;
        idField = createRoundedTextField();
        fieldsPanel.add(idField, gbc);

        // Name field
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        fieldsPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1;
        nameField = createRoundedTextField();
        fieldsPanel.add(nameField, gbc);

        // Password field
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        fieldsPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1;
        passwordField = createRoundedPasswordField();
        fieldsPanel.add(passwordField, gbc);

        // Role field (label on right)
       // Role field (label on left, dropdown centered)
JPanel rolePanel = new JPanel(new GridBagLayout());
rolePanel.setOpaque(false);
GridBagConstraints roleGbc = new GridBagConstraints();
roleGbc.insets = new Insets(0, 5, 0, 5); // Add some padding between components
roleGbc.fill = GridBagConstraints.NONE; // Prevent components from stretching

// Add the "Role:" label on the left
roleGbc.gridx = 0;
roleGbc.gridy = 0;
roleGbc.weightx = 0; // No extra space for the label
roleGbc.anchor = GridBagConstraints.WEST; // Align to the left
rolePanel.add(new JLabel("Role:"), roleGbc);

// Add the roleCombo (dropdown) and center it
roleCombo = new JComboBox<>(new String[]{"Admin", "Pharmacist", "Client"});
roleCombo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
roleCombo.setBackground(Color.WHITE);
roleGbc.gridx = 1;
roleGbc.weightx = 1; // Allow the dropdown to take extra space
roleGbc.anchor = GridBagConstraints.CENTER; // Center the dropdown
rolePanel.add(roleCombo, roleGbc);

// Add the rolePanel to the fieldsPanel
gbc.gridx = 0;
gbc.gridy = 3;
gbc.gridwidth = 2;
fieldsPanel.add(rolePanel, gbc);
       

        card.add(fieldsPanel, BorderLayout.CENTER);

        // Buttons at bottom center
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setOpaque(false);
        loginButton = createRoundedButton("Login");
        signupButton = createRoundedButton("Sign Up");
        buttonPanel.add(loginButton);
        buttonPanel.add(signupButton);
        card.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(card);

        // Add listeners
        loginButton.addActionListener(e -> handleLogin());
        signupButton.addActionListener(e -> handleSignup());
    }

    private JTextField createRoundedTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        field.setBackground(new Color(245, 245, 245));
        return field;
    }

    private JPasswordField createRoundedPasswordField() {
        JPasswordField field = new JPasswordField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        field.setBackground(new Color(245, 245, 245));
        return field;
    }

    private JButton createRoundedButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBackground(new Color(0, 123, 255));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void handleLogin() {
        String role = (String) roleCombo.getSelectedItem();
        int id = Integer.parseInt(idField.getText().trim());
        String name = nameField.getText().trim();

        switch (role) {
            case "Admin":
                Admin admin = AuthManager.authenticateAdmin(id, name, Admin.admincollection);
                if (admin != null) {
                    JOptionPane.showMessageDialog(this, "Welcome Admin " + admin.getName());
                    AdminDashboardFrame dashBoard = new AdminDashboardFrame(); 
                    dashBoard.setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Admin not found. Login failed.");
                }
                break;

            case "Pharmacist":
                Pharmacist pharmacist = AuthManager.authenticatePharmacist(id, name, Pharmacist.pharmacistcollection);
                if (pharmacist != null) {
                    JOptionPane.showMessageDialog(this, "Welcome Pharmacist " + pharmacist.getName());
                    PharmacistFrame Pharmacy = new PharmacistFrame() ; 
                    Pharmacy.setVisible(true); 
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Pharmacist not found. Login failed.");
                }
                break;

            case "Client":
                Client client = AuthManager.authenticateClient(id, name, Client.clientCollection);
                if (client != null) {
                    JOptionPane.showMessageDialog(this, "Welcome Client " + client.getName());
                    ClientDashboard cDashboard = new ClientDashboard() ; 
                    cDashboard.setVisible(true); 
                    this.dispose();
                    
                } else {
                    JOptionPane.showMessageDialog(this, "Client not found. Login failed.");
                }
                break;
        }
    }

    private void handleSignup() {
        
        this.dispose();
        new SignupFrame().setVisible(true);
       
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}