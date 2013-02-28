import java.io.IOException;
import java.util.HashMap;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.util.leap.Serializable;

public abstract class CB extends CyclicBehaviour {
    HashMap<String, Message> listeners = new HashMap<String, Message>();

    public void say(String message) {
        System.out.println(this.myAgent.getAID().getName() + ": " + message);
    }

    
    protected void sendMessageTo(Agent agent, Object item) {
        ACLMessage senderMessage = new ACLMessage(ACLMessage.REQUEST); 
        senderMessage.addReceiver(new AID(agent.getName(), AID.ISLOCALNAME));
        try {
            senderMessage.setContentObject((Serializable) item);
            myAgent.send(senderMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void listen() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            try {
                for(String status : this.listeners.keySet()){
                    Message message = this.listeners.get(status);
                    AuctionNotification an = (AuctionNotification) msg.getContentObject();
                    if(an.getStatus().equals(status)){
                        message.execute(msg.getContentObject(), msg.createReply());
                    }
                }
            } catch (UnreadableException e) {
                say("Could not receive message");
            }
        } else {
            block();
        }
    }

    protected void addListeners(String status, Message message) {
        listeners.put(status, message);
    }
}