package User;

import java.rmi.registry.LocateRegistry;
import java.rmi.Naming;

import NamingService.NamingServiceImpl;

public class NamingServiceServer {
    public static void main(String[] args) {
        try {
            NamingServiceImpl namingService = new NamingServiceImpl();
            LocateRegistry.createRegistry(8080);
            Naming.rebind("rmi://localhost:8080/namingservice", namingService);
            UserInterface.displayMessage("NamingService bound");
        } catch (Exception e) {
            UserInterface.displayError("NamingService Exception.", e);
        }
    }
}