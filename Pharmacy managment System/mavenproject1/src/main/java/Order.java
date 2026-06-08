//order class
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Order{
 
      private int orderID;
    private int clientID;
    private List<Medicine> medicineList;
    private String status;
    private static final List<Order> allOrders = new ArrayList<>();
     
    public Order(int orderID, int clientID, List<Medicine> medicineList, String status) {
     
        this.orderID = orderID;
        this.clientID = clientID;
        this.medicineList = medicineList;
        this.status = status;
        allOrders.add(this);
    }
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public List<Medicine> getMedicineList() {
        return medicineList;
    }

    public void setMedicineList(List<Medicine> medicineList) {
        this.medicineList = medicineList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double calculateTotalPrice() {
        double total = 0;
    for (Medicine med : medicineList) {
        total += med.getPrice() * med.getQuantity(); 
    }
    return total;
    }
    
    public static void removeOrder(int orderID) {
        allOrders.removeIf(order -> order.getOrderID() == orderID);
    }
    
    public static  List<Order> getAllOrders() {
    return allOrders;
}
      private static final String FILE_NAME = "Order.txt";

    // Save all orders to file
    public  void saveOrders(List<Order> orders) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME,true))) {
            for (Order order : orders) {
                // Save order as: orderID,clientID,status,medicineName1:quantity1:price1;medicineName2:quantity2:price2;...
                writer.write(order.getOrderID() + "," + order.getClientID() + "," + order.getStatus() + ",");
                List<Medicine> meds = order.getMedicineList();
                for (int i = 0; i < meds.size(); i++) {
                    Medicine m = meds.get(i);
                    writer.write(m.getName() + ":" + m.getQuantity() + ":" + m.getPrice());
                    if (i < meds.size() - 1) {
                        writer.write(";"); // separator between medicines
                    }
                }
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error saving orders: " + e.getMessage());
        }
    }

    // Load all orders from file
    public static List<Order> loadOrders() {
         allOrders.clear();
        List<Order> loadedOrders = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Parse line: orderID,clientID,status,med1:qty:price;med2:qty:price;...
                String[] parts = line.split(",", 4);
                int orderID = Integer.parseInt(parts[0]);
                int clientID = Integer.parseInt(parts[1]);
                String status = parts[2];
                String medData = parts[3];

                List<Medicine> meds = new ArrayList<>();
                String[] medEntries = medData.split(";");
                for (String entry : medEntries) {
                    String[] medParts = entry.split(":");
                    String name = medParts[0];
                    int quantity = Integer.parseInt(medParts[1]);
                    double price = Double.parseDouble(medParts[2]);
                    // Create medicine object (expDate dummy for now because we don't have it)
                    Medicine m = new Medicine(0, name, price, quantity, "2030-12-31");
                    meds.add(m);
                }

                // Create order object and add it
                Order o = new Order(orderID, clientID, meds, status);
                loadedOrders.add(o);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No previous order file found.");
        } catch (IOException e) {
            System.out.println("Error loading orders: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing order data: " + e.getMessage());
        }

        return loadedOrders;
    }
    public List<Medicine> getMedicines() {
    return medicineList;
}
}  