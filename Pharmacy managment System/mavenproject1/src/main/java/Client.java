//class client
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class Client extends Person implements Serializable {

    private static final long serialVersionUID = 1L;
    public static Client currentClient;
    List<Order> orderHistory = new ArrayList<>();
    public static  List<Client> clientCollection = new ArrayList<>();
    private List<Order> allOrders = new ArrayList<>() ; 

    public Client() {
        id = 0;
        name = "";
        phoneNum = "";
        address = "";
    }

    public Client(int ID, String name, String phoneNum, String address) {
        super(ID, name, phoneNum, address);
        saveClientToFile();
        this.orderHistory = new ArrayList<>();
    }

    public void createAccount(Client c) {
    clientCollection.add(c);
    c.saveClientToFile(); // Save only this client's info
    System.out.println("Account created for: " + c.name);
}

    public void manageAccount(int clientID, String newName, String newAddress, String clientPhoneNum) {
        for (Client c : clientCollection) {
            if (c.id == clientID) {
                c.name = newName;
                c.address = newAddress;
                c.phoneNum = clientPhoneNum;
                saveClientToFile(); // Save after update
                System.out.println("Account updated for " + c.name);
                return;
            }
        }
        System.out.println("Client not found!");
    }

    public void orderMedicine() {
    Scanner scanner = new Scanner(System.in);
    Medicine.dispalyAvailableMedicine();
    List<Medicine> selectedMedicines = new ArrayList<>();
    System.out.println("Enter the medicine name to order, enter 0 to finish: ");
    while (true) {
        String medName = scanner.nextLine();
        if (medName.equals("0")) {
            break;
        }
        Medicine selectedMed = Medicine.getMedbyName(medName);
        if (selectedMed != null) {
            System.out.println("Enter quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character left by nextInt()
            if (quantity > selectedMed.getQuantity()) {
                System.out.println("Not enough stock available. Try again.");
            } else {
                selectedMedicines.add(selectedMed);
                selectedMed.setQuantity(selectedMed.getQuantity() - quantity);
            }
        } else {
            System.out.println("Invalid medicine name. Try again.");
        }
    }
    

    if (!selectedMedicines.isEmpty()) {
        Order newOrder = new Order(orderHistory.size() + 1, this.id, selectedMedicines, "Processing");
        orderHistory.add(newOrder);
        allOrders.add(newOrder) ; 
        System.out.println("Order placed successfully!");
    } else {
        System.out.println("No valid medicines selected.");
    }
}
    
    

    public void cancelOrder(int OrderID) {
        for (int i = 0; i < orderHistory.size(); i++) {
            if (orderHistory.get(i).getOrderID() == OrderID) {
                Order.removeOrder(OrderID);
                orderHistory.remove(i);
                System.out.println("Order canceled successfully!");
                return;
            }
        }
        System.out.println("Order not found.");
    }

    public void updateOrder(int orderID) {
        cancelOrder(orderID);
        System.out.println("Please place a new order:");
        orderMedicine();
    }

    public void viewOrders() {
        if (orderHistory.isEmpty()) {
            System.out.println("No past orders.");
        } else {
            for (Order order : orderHistory) {
                System.out.println("Order ID: " + order.getOrderID() + ", Status: " + order.getStatus());
            }
        }
    }
    
    
    public static List<Client> getClientCollection() {
    return clientCollection;
    }
    
    public List<Order> getOrderHistory() {
    return orderHistory;
    }

    @Override
    public String getDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Client: ").append(getName()).append(", Phone: ").append(getPhoneNum()).append(", Address: ").append(getAddress()).append("\n");

        if (orderHistory.isEmpty()) {
            details.append("No past orders.");
        } else {
            details.append("Order History:\n");
            for (Order order : orderHistory) {
                details.append("Order ID: ").append(order.getOrderID())
                        .append(", Status: ").append(order.getStatus())
                        .append("\n");
            }
        }
        return details.toString();
    }

    // ==================== File Handling Methods ====================

    public void saveClientToFile() {
        String filename = "C:\\Users\\pc\\OneDrive - The British University in Egypt\\Desktop\\java final project\\pharmacy system final\\pharmacy system\\mavenproject1\\src\\main\\java\\client.txt";
        try (FileWriter fw = new FileWriter(filename, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.println("ID: " + this.id);
            out.println("Name: " + this.name);
            out.println("Phone: " + this.phoneNum);
            out.println("Address: " + this.address);
            out.println("--------");

        } catch (IOException e) {
            System.out.println("An error occurred while saving client to file: " + e.getMessage());
        }
    }

    public static void loadClientsFromFile() {
    String filename = "C:\\Users\\pc\\OneDrive - The British University in Egypt\\Desktop\\java final project\\pharmacy system final\\pharmacy system\\mavenproject1\\src\\main\\java\\client.txt";
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        Client client = null;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("ID: ")) {
                client = new Client();
                client.id = Integer.parseInt(line.substring(4));
            } else if (line.startsWith("Name: ")) {
                client.name = line.substring(6);
            } else if (line.startsWith("Phone: ")) {
                client.phoneNum = line.substring(7);
            } else if (line.startsWith("Address: ")) {
                client.address = line.substring(9);
                if (client != null) {
                    clientCollection.add(client);
                }
            }
        }
    } catch (IOException e) {
        System.out.println("Error loading clients: " + e.getMessage());
    }
}
}