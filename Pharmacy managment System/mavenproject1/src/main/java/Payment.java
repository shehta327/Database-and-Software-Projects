
import java.io.BufferedWriter;
import java.io.FileWriter;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


public class Payment {
  private int paymentID;
 private double amount;
  private String paymentMethod;
   private String paymentDate;

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }
 public Payment(int paymentID, double amount, String paymentMethod,String paymentDate ) {
        this.paymentID = paymentID;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
   public boolean processPayment(Order order) {
    double totalOrderPrice = order.calculateTotalPrice();

    if (amount < totalOrderPrice) {
        System.out.println("Insufficient payment. Total order price: " + totalOrderPrice + ". Payment failed.");
        return false;
    }

    if (amount <= 0) {
        System.out.println("Invalid payment amount. Payment failed.");
        return false;
    }

    if (paymentMethod == null || paymentMethod.isEmpty()) {
        System.out.println("Invalid payment method. Payment failed.");
        return false;
    }

    System.out.println("Payment of " + amount + " via " + paymentMethod + " on " + paymentDate + " processed successfully.");
    System.out.println("Total order price: " + totalOrderPrice);
    return true;
}
 public void logPayment(String status, String message) {
        String fileName = "C:\\Users\\user\\Desktop\\payment_log.txt"; 

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write("Payment ID: " + paymentID);
            writer.newLine();
            writer.write("Amount: " + amount);
            writer.newLine();
            writer.write("Payment Method: " + paymentMethod);
            writer.newLine();
            writer.write("Payment Date: " + paymentDate);
            writer.newLine();
            writer.write("Status: " + status);
            writer.newLine();
            writer.write("Message: " + message);
            writer.newLine();
        } catch (Exception e) {
            System.out.println("Error writing payment log: " + e.getMessage());
        }
    }
  
   
}
