package NamingService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;

import Agency.Agency;
import Agent.Agent;

public interface NamingService extends Remote {
    public Agency getAgencyByID(String agencyID) throws RemoteException;
    public Agency getAgencyByName(String agencyName) throws RemoteException;
    public LinkedList<Agency> getAgenciesList() throws RemoteException;
    public Agency getAgencyByAgentID(String agentID) throws RemoteException;
    public Agent getAgentByID(String agentID) throws RemoteException;
    public void registerAgency(Agency agency) throws RemoteException;
    public boolean removeAgent(String agentID) throws RemoteException;
    public boolean removeAgency(String agencyID) throws RemoteException;
    public boolean addNewAgent(String agentID, String agentName, String agencyID) throws RemoteException;
    public boolean moveAgent(String agentID, String oldAgencyID, String newAgencyID) throws RemoteException;
    public LinkedList<Agent> getAgentsByAgencyID(String agencyID) throws RemoteException;
}
