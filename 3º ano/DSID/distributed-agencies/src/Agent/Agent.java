package Agent;

import java.io.Serializable;
import java.rmi.RemoteException;

import Agency.Agency;
import Agency.Message;
import User.UserInterface;

public class Agent implements Serializable, Runnable {
    private String id;
    private String name;
    private Agency agency;

    public Agent(String name, Agency agency) {
        super();
        this.id = null;
        this.name = name;
        this.agency = agency;
    }

    public String getID() {
        return this.id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public String getName() {
        return this.name;
    }

    public Thread getThread() {
        return Thread.currentThread();
    }

    public Agency getAgency() {
        return this.agency;
    }

    public Message getNextMessage() {
        try {
            Message message = this.agency.getReceivedMessages().get(this.id).take();
            return message;
        } catch (Exception e) {
            UserInterface.displayMessage("Error while getting next message.");
            return null;
        }
    }

    public void returnMessageAnswer(Message answer) {
        try {
            this.agency.answerMessage(answer);
        } catch (Exception e) {
            UserInterface.displayMessage("Error while returning message answer.");
        }
    }

    public void sendMessageToAgent(String agentID, String message) {
        this.agency.sendMessageToAgent(this.id, agentID, message);
    }

    public boolean moveToAnotherAgency(String agencyID) throws RemoteException {
        boolean moved = this.agency.moveAgent(this.id, agencyID);
        return moved;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }
}
