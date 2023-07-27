package User;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Agency.AgencyImpl;
import NamingService.NamingService;

public class Server {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: Server <agency_name> <port>");
            System.exit(1);
        }

        String agencyName = args[0];
        int port = Integer.parseInt(args[1]);
        try {
            AgencyImpl agency = new AgencyImpl(agencyName, port);
            Registry registry = LocateRegistry.createRegistry(port);
            Naming.rebind(agencyName, agency);
            
            NamingService namingService = (NamingService) Naming.lookup("rmi://localhost:8080/namingservice");
            namingService.registerAgency(agency);

            UserInterface.displayMessage("Server bound");
        } catch (Exception e) {
            UserInterface.displayError("Server Exception.", e);
        }
    }
}