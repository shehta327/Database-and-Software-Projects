/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author pc
 */
import java.util.List;

public class AuthManager {

    public static Admin authenticateAdmin(int id, String name, List<Admin> admins) {
        for (Admin admin : admins) {
            if (admin.getId() == id && admin.getName().equalsIgnoreCase(name)) {
                return admin;
            }
        }
        return null;
    }

    public static Pharmacist authenticatePharmacist(int id, String name, List<Pharmacist> pharmacists) {
        for (Pharmacist pharmacist : pharmacists) {
            if (pharmacist.getId() == id && pharmacist.getName().equalsIgnoreCase(name)) {
                return pharmacist;
            }
        }
        return null;
    }

    public static Client authenticateClient(int id, String name, List<Client> clients) {
        for (Client client : clients) {
            if (client.getId() == id && client.getName().equalsIgnoreCase(name)) {
                return client;
            }
        }
        return null;
}
}
