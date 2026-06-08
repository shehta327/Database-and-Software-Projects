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

public class SignupFrame extends JFrame {
    private JTextField idField;
    private JTextField nameField;
    private JPasswordField passwordField;
    private JTextField phoneField;
    private JTextField addressField;
    private JTextField usernameField; // For Admin
    private JTextField licenseField; // For Pharmacist
    private JComboBox<String> roleCombo;
    private JButton signupButton;
    private JButton backButton;
    private JPanel roleSpecificPanel;

    public SignupFrame() {
        setTitle("Pharmacy System Signup");
        setSize(450, 550);
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
        card.setPreferredSize(new Dimension(350, 470));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // Title at top center
        JLabel title = new JLabel("Sign Up", SwingConstants.CENTER);
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

        // Phone field
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        fieldsPanel.add(new JLabel("Phone:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1;
        phoneField = createRoundedTextField();
        fieldsPanel.add(phoneField, gbc);

        // Address field
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0;
        fieldsPanel.add(new JLabel("Address:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1;
        addressField = createRoundedTextField();
        fieldsPanel.add(addressField, gbc);

        // Role field (label on left, dropdown centered)
        JPanel rolePanel = new JPanel(new GridBagLayout());
        rolePanel.setOpaque(false);
        GridBagConstraints roleGbc = new GridBagConstraints();
        roleGbc.insets = new Insets(0, 5, 0, 5);
        roleGbc.fill = GridBagConstraints.NONE;

        roleGbc.gridx = 0;
        roleGbc.gridy = 0;
        roleGbc.weightx = 0;
        roleGbc.anchor = GridBagConstraints.WEST;
        rolePanel.add(new JLabel("Role:"), roleGbc);

        roleCombo = new JComboBox<>(new String[]{"Admin", "Pharmacist", "Client"});
        roleCombo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        roleCombo.setBackground(Color.WHITE);
        roleGbc.gridx = 1;
        roleGbc.weightx = 1;
        roleGbc.anchor = GridBagConstraints.CENTER;
        rolePanel.add(roleCombo, roleGbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        fieldsPanel.add(rolePanel, gbc);

        // Role-specific fields (username for Admin, licenseID for Pharmacist)
        roleSpecificPanel = new JPanel(new GridBagLayout());
        roleSpecificPanel.setOpaque(false);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        fieldsPanel.add(roleSpecificPanel, gbc);

        // Update role-specific fields based on role selection
        roleCombo.addActionListener(e -> updateRoleSpecificFields());

        // Initial call to set up fields
        updateRoleSpecificFields();

        // Add fieldsPanel to a JScrollPane for scrolling
        JScrollPane scrollPane = new JScrollPane(fieldsPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        card.add(scrollPane, BorderLayout.CENTER);

        // Buttons at bottom center
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setOpaque(false);
        signupButton = createRoundedButton("Sign Up");
        backButton = createRoundedButton("Back to Login");
        buttonPanel.add(signupButton);
        buttonPanel.add(backButton);
        card.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(card);

        // Add listeners with debug messages
        signupButton.addActionListener(e -> {
            System.out.println("Sign Up button clicked");
            handleSignup();
        });
        backButton.addActionListener(e -> {
            System.out.println("Back to Login button clicked");
            handleBackToLogin();
        });
    }

    private void updateRoleSpecificFields() {
        roleSpecificPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        String role = (String) roleCombo.getSelectedItem();
        if (role.equals("Admin")) {
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 0;
            roleSpecificPanel.add(new JLabel("Username:"), gbc);
            gbc.gridx = 1;
            gbc.weightx = 1;
            usernameField = createRoundedTextField();
            roleSpecificPanel.add(usernameField, gbc);
        } else if (role.equals("Pharmacist")) {
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 0;
            roleSpecificPanel.add(new JLabel("License ID:"), gbc);
            gbc.gridx = 1;
            gbc.weightx = 1;
            licenseField = createRoundedTextField();
            roleSpecificPanel.add(licenseField, gbc);
        } else {
            usernameField = null;
            licenseField = null;
        }

        roleSpecificPanel.revalidate();
        roleSpecificPanel.repaint();
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

    private void handleSignup() {
        try {
            // Get and trim all field values
            String id = idField.getText() != null ? idField.getText().trim() : "";
            String name = nameField.getText() != null ? nameField.getText().trim() : "";
            String password = new String(passwordField.getPassword() != null ? passwordField.getPassword() : new char[0]).trim();
            String phone = phoneField.getText() != null ? phoneField.getText().trim() : "";
            String address = addressField.getText() != null ? addressField.getText().trim() : "";
            String role = (String) roleCombo.getSelectedItem();

            System.out.println("Validating signup data: ID=" + id + ", Name=" + name + ", Role=" + role);

            // Check if any required field is empty
            if (id.isEmpty() || name.isEmpty() || password.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all required fields with valid data.");
                return;
            }

            int userId;
            try {
                userId = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "ID must be a valid number.");
                return;
            }

            // Check for role-specific fields
            String username = "";
            String licenseID = "";
            if (role.equals("Admin")) {
                username = usernameField != null && usernameField.getText() != null ? usernameField.getText().trim() : "";
                if (username.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter a username for Admin.");
                    return;
                }
            } else if (role.equals("Pharmacist")) {
                licenseID = licenseField != null && licenseField.getText() != null ? licenseField.getText().trim() : "";
                if (licenseID.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter a license ID for Pharmacist.");
                    return;
                }
            }

            // Add the new user to the appropriate list based on role
            switch (role) {
                case "Admin":
                    Admin admin = new Admin(userId, name, phone, address, username, password);
                    Admin.admincollection.add(admin);
                    JOptionPane.showMessageDialog(this, "Admin signup successful!\nID: " + id + "\nName: " + name);
                    break;

                case "Pharmacist":
                    Pharmacist pharmacist = new Pharmacist(userId, name, phone, address, licenseID);
                    Pharmacist.pharmacistcollection.add(pharmacist);
                    JOptionPane.showMessageDialog(this, "Pharmacist signup successful!\nID: " + id + "\nName: " + name);
                    break;

                case "Client":
                    Client client = new Client(userId, name, phone, address);
                    Client.clientCollection.add(client);
                    JOptionPane.showMessageDialog(this, "Client signup successful!\nID: " + id + "\nName: " + name);
                    break;

                default:
                    JOptionPane.showMessageDialog(this, "Invalid role selected.");
                    return;
            }

            // After successful signup, redirect to login frame
            System.out.println("Redirecting to LoginFrame after signup");
            this.dispose();
            SwingUtilities.invokeLater(() -> {
                try {
                    new LoginFrame().setVisible(true);
                } catch (Exception ex) {
                    System.err.println("Failed to open LoginFrame: " + ex.getMessage());
                    JOptionPane.showMessageDialog(null, "Error opening Login screen. Please try again.");
                }
            });
        } catch (Exception ex) {
            System.err.println("Error in handleSignup: " + ex.getMessage());
            JOptionPane.showMessageDialog(this, "An error occurred during signup: " + ex.getMessage());
        }
    }

    private void handleBackToLogin() {
        try {
            System.out.println("Disposing SignupFrame and opening LoginFrame");
            this.dispose();
            SwingUtilities.invokeLater(() -> {
                try {
                    new LoginFrame().setVisible(true);
                } catch (Exception ex) {
                    System.err.println("Failed to open LoginFrame: " + ex.getMessage());
                    JOptionPane.showMessageDialog(null, "Error opening Login screen. Please try again.");
                }
            });
        } catch (Exception ex) {
            System.err.println("Error in handleBackToLogin: " + ex.getMessage());
            JOptionPane.showMessageDialog(this, "An error occurred while returning to login: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SignupFrame().setVisible(true);
        });
    }
}

