import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Supplier {
    private int supplierID;
    private String companyName;

    public static List<Supplier> supplierList = new ArrayList<>();

    public Supplier(int supplierID, String companyName) {
        setSupplierID(supplierID);
        setCompanyName(companyName);
        supplierList.add(this);
    }

    public int getSupplierID() {
        return supplierID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setSupplierID(int supplierID) {
        if (supplierID <= 0) throw new IllegalArgumentException("Supplier ID must be positive.");
        this.supplierID = supplierID;
    }

    public void setCompanyName(String companyName) {
        if (companyName == null || companyName.isBlank())
            throw new IllegalArgumentException("Company name cannot be empty.");
        this.companyName = companyName;
    }

    public void displayDetails() {
        System.out.println("Supplier ID: " + supplierID);
        System.out.println("Company Name: " + companyName);
    }

    public static void displayAllSuppliers() {
        if (supplierList.isEmpty()) {
            System.out.println("No suppliers available.");
        } else {
            System.out.println("Available Suppliers:");
            for (Supplier s : supplierList) {
                System.out.println("ID: " + s.getSupplierID() + ", Name: " + s.getCompanyName());
            }
        }
    }

    public static void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader("suppliers.txt"))) {
            String line;
            supplierList.clear();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 2) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    new Supplier(id, name);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading suppliers: " + e.getMessage());
        }
    }
    
    public static void saveToFile(String filename) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
        for (Supplier s : supplierList) {
            writer.write(s.getSupplierID() + "|" + s.getCompanyName());
            writer.newLine();
        }
        System.out.println("✅ Supplier list saved to file.");
    } catch (IOException e) {
        System.out.println("❌ Error saving suppliers: " + e.getMessage());
    }
}


    public static void appendToFile(String filename, Supplier supplier) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("suppliers.txt", true))) {
            writer.write(supplier.getSupplierID() + "|" + supplier.getCompanyName());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error appending supplier: " + e.getMessage());
        }
    }
    
    public static void removeSupplierById(int id, String filename) {
    Supplier toRemove = null;

    // Find the supplier by ID
    for (Supplier s : supplierList) {
        if (s.getSupplierID() == id) {
            toRemove = s;
            break;
        }
    }

    if (toRemove != null) {
        supplierList.remove(toRemove); // remove from memory
        saveToFile("suppliers.txt");          // overwrite file with updated list
        System.out.println("✅ Supplier with ID " + id + " removed.");
    } else {
        System.out.println("❌ Supplier ID " + id + " not found.");
    }
}
    
    public static void updateSupplierDetailsInFile(int id, String newCompanyName, String filename) {
    boolean found = false;

    for (Supplier s : supplierList) {
        if (s.getSupplierID() == id) {
            s.setCompanyName(newCompanyName);
            found = true;
            break;
        }
    }

    if (found) {
        saveToFile("suppliers.txt");  // Overwrite the file with updated supplier list
        System.out.println("✅ Supplier with ID " + id + " updated successfully.");
    } else {
        System.out.println("❌ Supplier with ID " + id + " not found.");
    }
}

    
}