//medicine class
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
public class Medicine {
    private int medID;
    private String medname;
    private double price;
    private int quantity;
    private LocalDate expDate;
    public static List<Medicine> availableMedicines = new ArrayList<>();

    Medicine(String name, int quantity, double price) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Medicine> getAvailableMedicines() {
        return availableMedicines;
    }

    public void setAvailableMedicines(List<Medicine> availableMedicines) {
        this.availableMedicines = availableMedicines;
    }

    // Constructor using the safe setters
    public Medicine(int medID, String medname, double price, int quantity, String expDate) throws IllegalArgumentException {
        setMedID(medID);
        setName(medname);
        setPrice(price);
        setQuantity(quantity);
        setExpDate(expDate);
        availableMedicines.add(this);
        
        
    } 
    

    public int getMedID() {
        return medID;
    }

    public String getName() {
        return medname;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getExpDate() {
        return expDate.toString();
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        this.price = price;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        this.quantity = quantity;
    }


    public void setExpDate(String expDate){
        try{
            this.expDate = LocalDate.parse(expDate,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }catch(DateTimeParseException e){
            throw new IllegalArgumentException("Invalid date format. Please use yyyy-MM-dd");
        }
    }

    public void setMedID(int medID) {
        if (medID <= 0) {
            throw new IllegalArgumentException("Medicine ID must be positive.");
        }
        this.medID = medID;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Medicine name cannot be empty.");
        }
        this.medname = name;
    }

    public void updateQuantity(int newQty) {
        this.quantity += newQty;
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(expDate);
    }

    public static Medicine getMedbyName(String n) {
        for (Medicine m : availableMedicines) {
            if (m.getName().equals(n)) {
                return m;
            }
        }
        return null;
    }

    public void displayDetails() {
        System.out.println("Medicine ID: " + medID);
        System.out.println("Name: " + medname);
        System.out.println("Price: $" + price);
        System.out.println("Quantity: " + quantity);
        System.out.println("Expiration Date: " + expDate);
        System.out.println("Is Expired: " + (isExpired() ? "Yes" : "No"));
    }

    public static void dispalyAvailableMedicine() {
        if (availableMedicines.isEmpty()) {
            System.out.println("No available medicines.");
            return;
        }
        System.out.println("Available Medicines:");
        for (Medicine m : availableMedicines) {
            System.out.println("ID: " + m.getMedID() + ", Name: " + m.getName() + ", Price: $" + m.price);
        }
    }

    public static void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\pc\\OneDrive - The British University in Egypt\\Desktop\\java final project\\pharmacy system final\\pharmacy system\\mavenproject1\\src\\main\\java\\medicines.txt"))) {
            for (Medicine med : availableMedicines) {
                writer.write(med.getMedID() + "|" +
                        med.getName() + "|" +
                        med.getPrice() + "|" +
                        med.getQuantity() + "|" +
                        med.getExpDate());
                writer.newLine();
            }
            System.out.println("Medicines saved successfully to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving medicines: " + e.getMessage());
        }
    }

    public static void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\pc\\OneDrive - The British University in Egypt\\Desktop\\java discussion\\pharmacy system Last\\pharmacy system Last\\pharmacy system\\mavenproject1\\src\\main\\java\\medicines.txt"))) {
            String line;
            availableMedicines.clear();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    try {
                        int id = Integer.parseInt(parts[0]);
                        String name = parts[1];
                        double price = Double.parseDouble(parts[2]);
                        int quantity = Integer.parseInt(parts[3]);
                        String expDate = parts[4];
                        new Medicine(id, name, price, quantity, expDate);
                    } catch (Exception e) {
                        System.out.println("Skipping invalid line: " + line);
                    }
                }
            }
            System.out.println("Medicines loaded successfully from " + filename);
        } catch (IOException e) {
            System.out.println("Error loading medicines: " + e.getMessage());
        }
    }

    public static void appendToFile(String filename, Medicine med) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\pc\\OneDrive - The British University in Egypt\\Desktop\\java final project\\pharmacy system final\\pharmacy system\\mavenproject1\\src\\main\\java\\medicines.txt", true))) {
            writer.write(med.getMedID() + "|" +
                    med.getName() + "|" +
                    med.getPrice() + "|" +
                    med.getQuantity() + "|" +
                    med.getExpDate());
            writer.newLine();
            System.out.println("Medicine " + med.getName() + " appended successfully to " + filename);
        } catch (IOException e) {
            System.out.println("Error appending medicine: " + e.getMessage());
        }
    }
    
    public static void removeMedicineById(int id, String filename) {
    Medicine toRemove = null;

    for (Medicine med : availableMedicines) {
        if (med.getMedID() == id) {
            toRemove = med;
            break;
        }
    }

    if (toRemove != null) {
        availableMedicines.remove(toRemove);
        System.out.println("Medicine with ID " + id + " removed successfully.");

        // Now update the file
        saveToFile("medicines.txt");
    } else {
        System.out.println("Medicine ID " + id + " not found.");
    }
}
  public static void updateMedicineDetailsInFile(int id, String newName, double newPrice, int newQuantity, String newExpDate, String filename) {
    boolean found = false;

    for (Medicine med : availableMedicines) {
        if (med.getMedID() == id) {
            med.setName(newName);
            med.setPrice(newPrice);
            med.setQuantity(newQuantity);
            med.setExpDate(newExpDate);
            found = true;
            break;
        }
    }

    if (found) {
        saveToFile(filename);  // Rewrite whole file, but only this medicine updated
        System.out.println("Medicine with ID " + id + " updated successfully.");
    } else {
        System.out.println("Medicine ID " + id + " not found.");
    }
}
      
    
}