
import java.util.ArrayList;
import java.util.List;

//class pharmacist
public class Pharmacist extends Person {
    private String licenseID;
    public static List<Pharmacist> pharmacistcollection=new ArrayList<>();
     public Pharmacist(int id, String name, String phoneNum, String address, String licenseID) {
        super(id, name, phoneNum, address);
        this.licenseID = licenseID;
        pharmacistcollection.add(this);
    }
     
     
        public String getLicenseID() {
        return licenseID;
    }
        
        
        public void setLicenseID(String licenseID) {
        this.licenseID = licenseID;
    }
        
        
        public void updateMedicineStock(int medID, int newQty) {
        System.out.println("Updating medicine stock: Medicine ID = " + medID + ", New Quantity = " + newQty);
        }
        
        
        public void receiveTask(String task) {
        System.out.println("Pharmacist received task: " + task);
    }
        
        @Override
         public String getDetails() {
          return "Pharmacist: " + getName() + ", License ID: " + licenseID + ", Phone: " + getPhoneNum();
    }
}