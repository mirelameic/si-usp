package Agency;

import java.io.Serializable;
import java.util.UUID;

public class Message implements Serializable {
    private String messageId;
    private String messageString;
    private String responseString;
    private String originAgentID;
    private String destinationAgentID;

    public Message(String originAgentID, String destinationAgentID, String message) {
        this.originAgentID = originAgentID;
        this.destinationAgentID = destinationAgentID;
        this.messageString = message;
        this.messageId = UUID.randomUUID().toString();
    }

    public String getDestinationAgentID() {
        return this.destinationAgentID;
    }

    public String getMessage() {
        return this.messageString;
    }

    public String getOriginAgentID() {
        return this.originAgentID;
    }

    public String getId() {
        return this.messageId;
    }

    public String getResponse() {
        return this.responseString;
    }

    public void setResponse(String response) {
        this.responseString = response;
    }
}