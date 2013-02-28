import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.util.leap.Serializable;

import java.io.IOException;
import java.util.HashMap;

public abstract class CB extends CyclicBehaviour {
	private static final long serialVersionUID = 1L;
	HashMap<String, Message> listeners = new HashMap<String, Message>();

    public void say(String message) {
        System.out.println(this.myAgent.getAID().getName() + ": " + message);
    }

    
    protected void sendMessageTo(Agent agent, Notification notification) {
        this.sendMessageTo(agent.getName(), notification);
    }

    protected void sendMessageTo(String name, Notification notification) {
        ACLMessage senderMessage = new ACLMessage(ACLMessage.REQUEST); 
        senderMessage.addReceiver(new AID(name, AID.ISLOCALNAME));
        try {
            senderMessage.setContentObject((Serializable) notification);
            myAgent.send(senderMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void listen() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            try {
                for(String status : this.listeners.keySet()) {
                    Message message = this.listeners.get(status);
                    Notification notification = (Notification) msg.getContentObject();
                    if(notification.getStatus().equals(status)){
                        message.execute(notification.getObject(), msg.createReply());
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