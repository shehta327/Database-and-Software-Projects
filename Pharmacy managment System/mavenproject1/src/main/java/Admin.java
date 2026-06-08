//class admin
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.*;

public class Admin extends Person {
    private String username;
    private String password;
    private List<Medicine> medicines;
    private List<Supplier> suppliers = new ArrayList<>(); 
    
    private static final String FILE_PATH = "admins.txt";
    public static  List<Admin> admincollection=new ArrayList<>();

    public Admin() {
        super();
        this.username = "";
        this.password = "";
        this.medicines = new ArrayList<>();
        this.suppliers = new ArrayList<>();
        
    }

    public Admin(int id, String name, String phoneNum, String address, String username, String password) {
        super(id, name, phoneNum, address);
        this.username = username;
        this.password = password;
        this.medicines = new ArrayList<>();
        this.suppliers = new ArrayList<>();
        admincollection.add(this);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // ✅ Medicine Management
    public void addMedicine(Medicine med) {
        medicines.add(med);
        System.out.println("Medicine " + med.getName() + " added.");
    }

    public void removeMedicine(int medID) {
        for (int i = 0; i < medicines.size(); i++) {
            if (medicines.get(i).getMedID() == medID) {
                medicines.remove(i);
                System.out.println("Medicine with ID " + medID + " removed.");
                return;
            }
        }
        System.out.println("Medicine with ID " + medID + " not found.");
    }

    public void updateMedicine(int medID, Medicine newData) {
        for (Medicine med : medicines) {
            if (med.getMedID() == medID) {
                med.setName(newData.getName());
                med.setPrice(newData.getPrice());
                med.setQuantity(newData.getQuantity());
                med.setExpDate(newData.getExpDate());
                System.out.println("Medicine with ID " + medID + " updated.");
                return;
            }
        }
        System.out.println("Medicine with ID " + medID + " not found.");
    }

    public void generateReport(String pharmacistName, String medName) {
        System.out.println("Pharmacist: " + pharmacistName);
        System.out.println("Medicine: " + medName);

        int totalQuantity = 0;
        double totalRevenue = 0.0;
        boolean found = false;

        for (Medicine med : medicines) {
            if (med.getName().equalsIgnoreCase(medName)) {
                totalQuantity += med.getQuantity();
                totalRevenue += med.getQuantity() * med.getPrice();
                found = true;
            }
        }

        if (found) {
            System.out.println("Total Quantity Available: " + totalQuantity);
            System.out.println("Total Revenue from " + medName + ": $" + totalRevenue);
        } else {
            System.out.println("No records found for this medicine.");
        }
    }

    @Override
    public String getDetails() {
        return "Admin: " + getName() + ", Username: " + username + ", Phone: " + getPhoneNum();
    }

    public void assignTask(Pharmacist pharmacist, String task) {
        pharmacist.receiveTask(task);
        System.out.println("Task assigned to Pharmacist: " + task);
    }

    // ✅ Supplier Management

    public void addSupplier(Supplier supplier) {
    suppliers.add(supplier);
    System.out.println("Supplier " + supplier.getCompanyName() + " added.");
}

    public void removeSupplier(int supplierID) {
        for (int i = 0; i < suppliers.size(); i++) {
            if (suppliers.get(i).getSupplierID() == supplierID) {
                suppliers.remove(i);
                System.out.println("Supplier with ID " + supplierID + " removed.");
                return;
            }
        }
        System.out.println("Supplier with ID " + supplierID + " not found.");
    }

    public void updateSupplier(int supplierID, Supplier updatedSupplier) {
        for (Supplier supplier : suppliers) {
            if (supplier.getSupplierID() == supplierID) {
                supplier.setCompanyName(updatedSupplier.getCompanyName());
                System.out.println("Supplier with ID " + supplierID + " updated.");
                return;
            }
        }
        System.out.println("Supplier with ID " + supplierID + " not found.");
    }

    public void displaySuppliers() {
        if (suppliers.isEmpty()) {
            System.out.println("No suppliers available.");
        } else {
            System.out.println("List of Suppliers:");
            for (Supplier supplier : suppliers) {
                supplier.displayDetails();
            }
        }
    }
}
    
    
