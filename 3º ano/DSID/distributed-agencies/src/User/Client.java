package User;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.rmi.Naming;
import java.util.LinkedList;

import Agency.Agency;
import Agent.Agent;
import NamingService.NamingService;

public class Client {
    private static final String Agent = null;
    private static final String Agency = null;
    static Agency currentAgency;
    static NamingService namingService;
    public static void main(String[] args) {
        try {
            bindNamingService();
            boolean running = true;
            String command;
            showCommands();
            while (running) {
                command = UserInterface.getUserCommand();
                switch (command) {
                    case "show-commands":
                        showCommands();
                        break;
                    case "bind":
                        UserInterface.displayMessage("Enter agency name:");
                        String agency = UserInterface.getUserCommand();
                        bindAgency(agency);
                        break;
                    case "list-agencies":
                        listAgencies();
                        break;
                    case "create-agent":
                        UserInterface.displayMessage("Enter agent ID:");
                        String agentID = UserInterface.getUserCommand();
                        UserInterface.displayMessage("Enter agency name:");
                        String agencyName = UserInterface.getUserCommand();
                        createAgent(agentID, namingService.getAgencyByName(agencyName));
                        break;
                    case "move-agent":
                        UserInterface.displayMessage("Enter agent ID:");
                        String agentToMessage = UserInterface.getUserCommand();
                        UserInterface.displayMessage("Enter agency name to move:");
                        String agencyDestination = UserInterface.getUserCommand();
                        moveAgent(agentToMessage, agencyDestination);
                        break;
                    case "list-agents":
                        listAgents();
                        break;
                    case "message":
                        UserInterface.displayMessage("Enter the message:");
                        String message = UserInterface.getUserCommand();
                        break;
                    case "quit":
                        UserInterface.displayMessage("Client terminated.");
                        running = false;
                        break;
                    default:
                        UserInterface.displayMessage("Invalid command.");
                        break;
                }
            }
        } catch (Exception e) {
            UserInterface.displayError("Client exception.", e);
        }
    }

    private static void showCommands() {
        UserInterface.printLine();
        UserInterface.displayMessage("Available commands:");
        UserInterface.printLine();
        UserInterface.displayMessage("'show-commands': Show available commands");
        UserInterface.displayMessage("'bind <agency_name>': Binds to the specified agency");
        UserInterface.displayMessage("'list-agencies': Lists all available agencies");
        UserInterface.displayMessage("'create-agent': Create a new agent for a specific agency");
        UserInterface.displayMessage("'move-agent': Move an agent to a different agency");
        UserInterface.displayMessage("'list-agents': Lists all available agents");
        UserInterface.displayMessage("'message': Send a message to some agency");
        UserInterface.displayMessage("'quit': Terminates the client");
        UserInterface.printLine();
        UserInterface.printLine();
    }

    private static void bindAgency(String agencyName) {
        try {
            currentAgency = (Agency) Naming.lookup(agencyName);
            UserInterface.displayMessage("Connected to agency: " + agencyName);
        } catch (Exception e) {
            UserInterface.displayError("bind Exception.", e);
        }
    }

    private static void bindNamingService() {
        try {
            namingService = (NamingService) Naming.lookup("rmi://localhost:8080/namingservice");
            UserInterface.displayMessage("Connected to NamingService");
        } catch (Exception e) {
            UserInterface.displayError("bind NamingService Exception.", e);
        }
    }

    public static void listAgencies() {
        try {
            LinkedList<Agency> agencies = namingService.getAgenciesList();
            UserInterface.printLine();
            UserInterface.displayMessage("Available Agencies:");
            for (Agency agency : agencies) {
                String agencyId = agency.getID();
                String agencyName = agency.getName();
                UserInterface.displayMessage("Agency: " + agencyName + " | ID: " + agencyId);
            }
            UserInterface.printLine();
        } catch (Exception e) {
            UserInterface.displayError("listAgencies Exception.", e);
        }
    }

    public static void listAgents() {
        try {
            NamingService nameService = namingService;

            
                LinkedList<Agency> agencies = nameService.getAgenciesList();
                int t;
                int repositoryLength = agencies.size();
                UserInterface.displayMessage("\nAgents:");
                for (t = 0; t < repositoryLength; t++) {
                    Agency currentAgency = agencies.get(t);
                    UserInterface.displayMessage("------------------------------------------------------");
                    UserInterface.displayMessage("Agency: " + currentAgency.getName());
                    UserInterface.displayMessage("Agency Address: " + currentAgency.getAddress());
                    UserInterface.displayMessage("| ID                  | Name                |");
                    UserInterface.displayMessage("|---------------------|---------------------|");

                    for (Agent agentRegistry : currentAgency.getAgentsList()) {
                        System.out.printf("| %-20s | %-20s |%n", agentRegistry.getID(), agentRegistry.getName());
                    }
                UserInterface.displayMessage(
                        "----------------------------------------------------------------------------------------------------------------------%n%n");
                }

        } catch (Exception e) {
            UserInterface.displayError("listAgents Exception.", e);
        }
    }

    public static void createAgent(String agentCodePath, Agency agency) {
        try {
            File folder = new File("distributed-agencies");
            URLClassLoader classLoader = new URLClassLoader(new URL[] { folder.toURI().toURL() },
                    Thread.currentThread().getContextClassLoader());
            Class<?> myClass = Class.forName("bin/Agent.AgentTest", true, classLoader);

            Object newAgent = (Object) myClass.getDeclaredConstructor(String.class)
                    .newInstance(agentCodePath);

            String agentID = agency.addAgent((Agent) newAgent);
            UserInterface.displayMessage("Agent added with ID: " + agentID);
        } catch (Exception e) {
            UserInterface.displayError("createAgent Exception.", e);
        }
    }

    public static void moveAgent(String agentID, String newAgencyName) {
        try {
            Agency agency = namingService.getAgencyByName(newAgencyName);
            Agent agent = namingService.getAgentByID(agentID);
            currentAgency.moveAgent(agentID, newAgencyName);
            UserInterface.displayMessage(newAgencyName + " | " + agentID);

        } catch (Exception e) {
            UserInterface.displayError("moveAgent Exception.", e);
        }
    }
}