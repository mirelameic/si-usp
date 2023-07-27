package NamingService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

import Agency.Agency;
import Agent.Agent;
import User.UserInterface;

public class NamingServiceImpl extends UnicastRemoteObject implements NamingService {
    public LinkedList<Agency> registeredAgencies;

    public NamingServiceImpl() throws RemoteException {
        super();
        this.registeredAgencies = new LinkedList<Agency>();
    }

    @Override
    public Agency getAgencyByID(String agencyID) throws RemoteException{
        for (Agency agency : registeredAgencies) {
            if (agency.getID().equals(agencyID)) {
                return agency;
            }
        }
        return null;
    }

    @Override
    public Agency getAgencyByName(String agencyName) throws RemoteException {
        for (Agency agency : registeredAgencies) {
            if (agency.getName().equals(agencyName)) {
                return agency;
            }
        }
        return null;
    }

    @Override
    public LinkedList<Agency> getAgenciesList() throws RemoteException {
        return this.registeredAgencies;
    }


    @Override
    public Agency getAgencyByAgentID(String agentID) throws RemoteException {

        for (Agency agency : this.registeredAgencies) {
            LinkedList<Agent> agents = agency.getAgentsList();

            for (Agent agent : agents) {
                if (agent.getID().equals(agentID)) {
                    return agency;
                }
            }
        }
        return null;
    }

    @Override
    public Agent getAgentByID(String agentID) throws RemoteException {
                for (Agency agency : this.registeredAgencies) {
            LinkedList<Agent> agents = agency.getAgentsList();

            for (Agent agent : agents) {
                if (agent.getID().equals(agentID)) {
                    return agent;
                }
            }
        }
        return null;
    }

    @Override
    public void registerAgency(Agency agency) throws RemoteException {
        registeredAgencies.add(agency);
        UserInterface.displayMessage("Registered agency: " + agency.getName());
    }

    @Override
    public boolean removeAgent(String agentID) throws RemoteException {
        for (Agency agency : registeredAgencies) {
            LinkedList<Agent> agents = agency.getAgentsList();

            for (int i = 0; i < agents.size(); i++) {
                if (agents.get(i).getID().equals(agentID)) {
                    agents.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean removeAgency(String agencyID) throws RemoteException {
        int numberOfAgencies = this.registeredAgencies.size();

        for (int i = 0; i < numberOfAgencies; i++) {
            if (registeredAgencies.get(i).getID().equals(agencyID)) {
                registeredAgencies.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addNewAgent(String agentID, String agentName, String agencyID) throws RemoteException {
        Agent newAgent = new Agent(agentName, this.getAgencyByAgentID(agencyID));

        for (Agency agency : this.registeredAgencies) {
            if (agency.getID().equals(agencyID)) {
                agency.addAgent(newAgent);
                UserInterface.displayMessage("Added agent " + agentID + " to agency " + agencyID);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean moveAgent(String agentID, String oldAgencyID, String newAgencyID) throws RemoteException {
        Agency agency = this.getAgencyByID(oldAgencyID);
        Agent agent = agency.getAgentByID(agentID);

        this.removeAgent(agentID);
        this.addNewAgent(agentID, agent.getName(), newAgencyID);

        return true;
    }

    @Override
    public LinkedList<Agent> getAgentsByAgencyID(String agencyID) throws RemoteException {
        for (Agency agency : this.registeredAgencies) {
            if (agency.getID().equals(agencyID)) {
                return agency.getAgentsList();
            }
        }
        return new LinkedList<>();
    }
}