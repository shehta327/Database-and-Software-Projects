/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author pc
 */
import java.util.Scanner ;
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        LoginFrame startProgram = new LoginFrame();
        startProgram.setVisible(true);
        
        
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin(1, "Omar", "1234567890", "Masr elgededa", "omar123", "password");
        Client client = new Client(2, "abdelrahman", "0987654321", "elShabab Street");
        Client client1 = new Client(3, "Ebrahim", "01093743875", "cairo");
        Pharmacist pharmacist = new Pharmacist(3, "Ebrahim", "1122334455", "Qaluib", "LICENCE123");
        admin.addMedicine(new Medicine(101, "Paracetamol", 5.0, 50, "2025-06-01"));
        admin.addMedicine(new Medicine(102, "Ibuprofen", 7.0, 30, "2026-12-15"));
        admin.addMedicine(new Medicine(103, "Amoxicillin", 12.0, 30, "2025-12-20"));
        admin.addMedicine(new Medicine(104, "Loratadine", 8.0, 60, "2027-03-10"));
        admin.addMedicine(new Medicine(105, "Omeprazole", 15.5, 90, "2026-07-01"));
        admin.addMedicine(new Medicine(106, "Cetirizine", 9.0, 45, "2025-11-30"));
        admin.addMedicine(new Medicine(107, "Aspirin", 6.5, 120, "2028-04-05"));
        admin.addMedicine(new Medicine(108, "Metformin", 22.0, 60, "2027-09-22"));
        admin.addMedicine(new Medicine(109, "Simvastatin", 18.0, 30, "2026-05-18"));
        admin.addMedicine(new Medicine(110, "Ciprofloxacin", 25.0, 20, "2025-10-01"));
        admin.addMedicine(new Medicine(111, "Diazepam", 11.5, 50, "2027-01-25"));
        admin.addMedicine(new Medicine(112, "Prednisone", 14.0, 40, "2026-08-12"));
        admin.addMedicine(new Medicine(113, "Naproxen", 10.0, 80, "2028-02-14"));
        admin.addMedicine(new Medicine(114, "Ranitidine", 7.0, 75, "2025-12-05"));
        admin.addMedicine(new Medicine(115, "Fluoxetine", 19.5, 30, "2027-06-30"));
        admin.addMedicine(new Medicine(116, "Levothyroxine", 21.0, 90, "2026-11-20"));
        admin.addMedicine(new Medicine(117, "Albuterol", 16.0, 2, "2025-09-10")); 
        admin.addMedicine(new Medicine(118, "Warfarin", 13.0, 100, "2027-04-30"));
        admin.addMedicine(new Medicine(119, "Hydrochlorothiazide", 8.5, 50, "2026-03-01"));
        admin.addMedicine(new Medicine(120, "Amlodipine", 9.5, 60, "2028-01-01"));
        admin.addMedicine(new Medicine(121, "Tramadol", 17.0, 40, "2025-11-15"));
        
        

        System.out.println("Welcome to the Pharmacy Management System");
        System.out.println("Choose a role: ");
        System.out.println("1. Admin");
        System.out.println("2. Client");
        System.out.println("3. Pharmacist");
        System.out.print("Enter your choice: ");
        
        int roleChoice = scanner.nextInt();
        scanner.nextLine(); 

        switch (roleChoice) {
            case 1:
                System.out.print("Enter Admin Username: ");
                String username = scanner.nextLine();
                System.out.print("Enter Admin Password: ");
                String password = scanner.nextLine();
                
                if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                    System.out.println("Admin logged in successfully!");
                    boolean exit = false;
                    while (!exit) {
                        System.out.println("\nAdmin Menu:");
                        System.out.println("1. Add Medicine");
                        System.out.println("2. Remove Medicine");
                        System.out.println("3. Update Medicine");
                        System.out.println("4. Generate Report");
                        System.out.println("5. Add Supplier");
                        System.out.println("6. Delete Supplier");
                        System.out.println("7. Update Supplier");
                        System.out.println("8. Exit");
                        System.out.print("Enter your choice: ");
                        int adminChoice = scanner.nextInt();
                        scanner.nextLine(); 

                        switch (adminChoice) {
                            case 1:
                                System.out.print("Enter Medicine ID: ");
                                int medID = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Enter Medicine Name: ");
                                String medName = scanner.nextLine();
                                System.out.print("Enter Price: ");
                                double price = scanner.nextDouble();
                                System.out.print("Enter Quantity: ");
                                int quantity = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Enter Expiry Date (YYYY-MM-DD): ");
                                String expDate = scanner.nextLine();
                                
                                Medicine newMedicine = new Medicine(medID, medName, price, quantity, expDate);
                                admin.addMedicine(newMedicine);
                                 // Load existing medicines
                                Medicine.loadFromFile("medicines.txt");
                                Medicine m1 = new Medicine(medID, medName, price, quantity, expDate);

                                Medicine.appendToFile("medicines.txt", m1);
                                Medicine.dispalyAvailableMedicine();
                                break;

                            case 2:
                                System.out.print("Enter Medicine ID to Remove: ");
                                int removeID = scanner.nextInt();
                                scanner.nextLine();
                                admin.removeMedicine(removeID);
                                Medicine.removeMedicineById(removeID, "medicines.txt");
                                break;

                            case 3:
                                System.out.print("Enter Medicine ID to Update: ");
                                int updateID = scanner.nextInt();
                                scanner.nextLine();

                                System.out.print("Enter New Medicine Name: ");
                                String newName = scanner.nextLine();

                                System.out.print("Enter New Price: ");
                                double newPrice = scanner.nextDouble();

                                System.out.print("Enter New Quantity: ");
                                int newQuantity = scanner.nextInt();
                                scanner.nextLine();

                                System.out.print("Enter New Expiry Date (YYYY-MM-DD): ");
                                String newExpDate = scanner.nextLine();
                                Medicine.updateMedicineDetailsInFile(updateID, newName, newPrice, newQuantity, newExpDate, "medicines.txt");

                                break;

                            case 4:
                                System.out.print("Enter Pharmacist Name: ");
                                String pharmacistName = scanner.nextLine();
                                System.out.print("Enter Medicine Name: ");
                                String medicineName = scanner.nextLine();
                                admin.generateReport(pharmacistName, medicineName);
                                break;
                                
                            case 5:
                                System.out.print("Enter Supplier ID: ");
                                int supplierID = scanner.nextInt();
                                scanner.nextLine();

                                System.out.print("Enter Company Name: ");
                                String companyName = scanner.nextLine();
                                Supplier newSupplier = new Supplier(supplierID, companyName);
                                admin.addSupplier(newSupplier);

                                Supplier.appendToFile("suppliers.txt", newSupplier);

                                Supplier.displayAllSuppliers();
                                break;

                                
                            case 6:
                                System.out.print("Enter Supplier ID to Remove: ");
                                int removeSupplierID = scanner.nextInt();
                                scanner.nextLine();
                                Supplier.removeSupplierById(removeSupplierID, "suppliers.txt");
                                Supplier.displayAllSuppliers();
                                break;
                                
                            case 7:
                                System.out.print("Enter Supplier ID to Update: ");
                                int supplierUpdateID = scanner.nextInt();
                                scanner.nextLine();

                                System.out.print("Enter New Company Name: ");
                                String updatedName = scanner.nextLine();

                                Supplier.updateSupplierDetailsInFile(supplierUpdateID, updatedName, "suppliers.txt");
                                Supplier.displayAllSuppliers();
                                break;

                                    
                            case 8:
                                exit = true;
                                break;

                            default:
                                System.out.println("Invalid choice, try again.");
                        }
                    }
                } else {
                    System.out.println("Invalid Admin Credentials.");
                }
                break;

            case 2:
                System.out.println("Client logged in successfully!");
                boolean clientExit = false;
                while (!clientExit) {
                    System.out.println("\nClient Menu:");
                    System.out.println("1. Order Medicine");
                    System.out.println("2. Cancel Order");
                    System.out.println("3. View Orders");
                    System.out.println("4. Exit");
                    System.out.print("Enter your choice: ");
                    int clientChoice = scanner.nextInt();
                    scanner.nextLine();

                    switch (clientChoice) {
                        case 1:
                            client.orderMedicine();
                            break;

                        case 2:
                            System.out.print("Enter Order ID to Cancel: ");
                            int orderID = scanner.nextInt();
                            client.cancelOrder(orderID);
                            break;

                        case 3:
                            client.viewOrders();
                            break;

                        case 4:
                            clientExit = true;
                            break;

                        default:
                            System.out.println("Invalid choice, try again.");
                    }
                }
                break;

            case 3:
                System.out.println("Pharmacist logged in successfully!");
                boolean pharmacistExit = false;
                while (!pharmacistExit) {
                    System.out.println("\nPharmacist Menu:");
                    System.out.println("1. Update Medicine Stock");
                    System.out.println("2. View Details");
                    System.out.println("3. Exit");
                    System.out.print("Enter your choice: ");
                    int pharmacistChoice = scanner.nextInt();
                    scanner.nextLine();

                    switch (pharmacistChoice) {
                        case 1:
                            System.out.print("Enter Medicine ID: ");
                            int medID = scanner.nextInt();
                            System.out.print("Enter New Quantity: ");
                            int newQty = scanner.nextInt();
                            pharmacist.updateMedicineStock(medID, newQty);
                            break;

                        case 2:
                            System.out.println(pharmacist.getDetails());
                            break;

                        case 3:
                            pharmacistExit = true;
                            break;

                        default:
                            System.out.println("Invalid choice, try again.");
                    }
                }
                break;

            default:
                System.out.println("Invalid role selection.");
        }
       

        scanner.close();
 
       
    }
       
}