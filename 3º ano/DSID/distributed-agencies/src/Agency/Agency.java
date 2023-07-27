package Agency;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import Agent.Agent;

public interface Agency extends Remote{
    public String addAgent(Agent agent) throws RemoteException;
    public boolean moveAgent(String agentID, String newAgencyName) throws RemoteException ;
    public boolean destroyAgent(String agentID) throws RemoteException;
    public String sendMessageToAgent(String originAgentID, String destinationAgentID, String messageContent);
    public Map<String, LinkedBlockingQueue<Message>> getReceivedMessages();
    public String receiveMessage(Message message);
    public void answerMessage(Message message);
    public String getID() throws RemoteException;
    public String getName() throws RemoteException;
    public Agent getAgentByID(String agentID) throws RemoteException;
    public String getAddress();
    public LinkedList<Agent> getAgentsList() throws RemoteException;
}