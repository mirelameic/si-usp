package Agency;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import Agent.Agent;
import NamingService.NamingService;
import User.UserInterface;

public class AgencyImpl extends UnicastRemoteObject implements Agency {
    final static String ADDRESS = "rmi://localhost";
    final static String NAMING_SERVICE_ADDRESS = "rmi://localhost:8080/namingservice";

    private String agencyID;
    private String agencyName;
    private int agencyPort;
    private LinkedList<Agent> agentsList;
    private NamingService namingService;
    private Map<String, LinkedBlockingQueue<Message>> receivedMessages;
    private Map<String, LinkedBlockingQueue<Message>> answeredMessages;
    
    public AgencyImpl(String agencyName, int agencyPort) throws RemoteException {
        super();
        this.agencyID = generateUniqueCode();
        this.agencyName = agencyName;
        this.agencyPort = agencyPort;
        this.agentsList = new LinkedList<Agent>();
        this.receivedMessages = new ConcurrentHashMap<String, LinkedBlockingQueue<Message>>();
        this.answeredMessages = new ConcurrentHashMap<String, LinkedBlockingQueue<Message>>();
    }

    @Override
    public String addAgent(Agent agent) throws RemoteException {
        String agentID = agent.getID();

        if (agentID == null) {
            agentID = this.generateNewAgentID();
            agent.setID(agentID);
        }
        agent.setAgency(this);

        String agentName = agent.getName();
        String agencyID = this.agencyID;
        boolean registeredAgent = this.namingService.addNewAgent(agentID, agentName, agencyID);

        if (!registeredAgent) {
            UserInterface.displayMessage("Agent " + agentID + " already registered.");
        }

        this.agentsList.add(agent);
        this.answeredMessages.put(agentID, new LinkedBlockingQueue<Message>());
        this.receivedMessages.put(agentID, new LinkedBlockingQueue<Message>());

        Thread newAgentThread = new Thread(agent);
        newAgentThread.setName(agentID);
        newAgentThread.start();

        return agentID;
    }
    
    @Override
    public boolean moveAgent(String agentID, String newAgencyName) throws RemoteException {
        try {
            Agency currentAgency = this.namingService.getAgencyByAgentID(agentID);

            if (!currentAgency.getID().equals(this.agencyID)) {
                String currentAddress = currentAgency.getAddress();
                currentAgency = (Agency) Naming.lookup(currentAddress);
                return currentAgency.moveAgent(agentID, newAgencyName);
            }

            Agency newAgency = this.namingService.getAgencyByName(newAgencyName);
            String agencyAddress = newAgency.getAddress();
            newAgency = (Agency) Naming.lookup(agencyAddress);

            Agent agent = this.getAgentByID(agentID);

            this.destroyAgent(agentID);
            newAgency.addAgent(agent);
            return true;

        } catch (Exception e) {
            UserInterface.displayError("Error while moving agent.", e);
            return false;
        }
    }

    @Override
    public boolean destroyAgent(String agentID) throws RemoteException {
        try {
            Agent agent = this.getAgentByID(agentID);
            agent.getThread().interrupt();
            this.agentsList.remove(agent);
            agent.setAgency(null);
            this.namingService.removeAgent(agentID);
            this.answeredMessages.remove(agentID);
            this.receivedMessages.remove(agentID);
            UserInterface.displayMessage("Agent " + agentID + " destroyed.");
            return true;
        } catch (Exception e) {
            UserInterface.displayError("Error while destroying agent.", e);
            return false;
        }
    }

    private String generateNewAgentID() {
        return Math.round(Math.random() * 100) + "";
    }

    private void bindNamingService() {
        try {
            this.namingService = (NamingService) Naming.lookup("rmi://localhost:8080/namingservice");
            UserInterface.displayMessage("Connected to NamingService");
        } catch (Exception e) {
            UserInterface.displayError("bind NamingService Exception.", e);
        }
    }

    @Override
    public String sendMessageToAgent(String originAgentID, String destinationAgentID, String messageContent) {
        try {
            Agency destinationAgency = this.namingService.getAgencyByAgentID(destinationAgentID);

            String agencyAddress = destinationAgency.getAddress();
            destinationAgency = (Agency) Naming.lookup(agencyAddress);

            Message message = new Message(originAgentID, destinationAgentID, messageContent);
            String response = destinationAgency.receiveMessage(message);
            return response;

        } catch (Exception e) {
            UserInterface.displayError("Error while sending message.", e);
            return "";
        }
    }

    @Override
    public Map<String, LinkedBlockingQueue<Message>> getReceivedMessages() {
        return this.receivedMessages;
    }

    @Override
    public String receiveMessage(Message message) {
        try {
            this.receivedMessages.get(message.getDestinationAgentID()).put(message);

            String messageID = message.getId();
            String response = null;

            while (response == null) {
                Message responseMessage = this.answeredMessages.get(message.getDestinationAgentID()).take();

                if (responseMessage.getId().equals(messageID))
                    response = message.getResponse();
            }

            return response;

        } catch (InterruptedException e) {
            UserInterface.displayError("Error while receiving message.", e);
            return "";
        }
    }

    @Override
    public void answerMessage(Message message) {
        try {
            this.answeredMessages.get(message.getDestinationAgentID()).put(message);

        } catch (InterruptedException e) {
            UserInterface.displayError("Error while answering message.", e);
        }
    }

    @Override
    public String getID() throws RemoteException{
        return this.agencyID;
    }

    @Override
    public String getName() throws RemoteException{
        return this.agencyName;
    }

    @Override
    public String getAddress() {
        return this.ADDRESS;
    }

    @Override
    public Agent getAgentByID(String agentID) throws RemoteException{
        for (Agent agent : this.agentsList) {
            if (agent.getID().equals(agentID)) {
                return agent;
            }
        }
        return null;
    }

    @Override
    public LinkedList<Agent> getAgentsList() throws RemoteException{
        return this.agentsList;
    }

    private String generateUniqueCode() throws RemoteException{
        return UUID.randomUUID().toString();
    }   
}
