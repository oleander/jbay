import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;


public abstract class B extends Behaviour {
    // Key = UUID
    HashMap<String, Package> listeners1 = new HashMap<String, Package>();
    // Key = Event name
    HashMap<String, Package> listeners2 = new HashMap<String, Package>();

    private static final long serialVersionUID = 1L;
    
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
        while(true){
            ACLMessage msg = myAgent.receive();
            if (msg != null) {
                try {
                    // Do we've any listeners without id?
                    if(!listeners2.isEmpty()) {
                        if(this.eventsWithoutId(msg)){
                            break;
                        }
                    } else {
                        if(this.eventsWithid(msg)){
                            break;
                        }
                    }
                } catch (UnreadableException e) {
                    say("Could not receive message");
                    break;
                }
            } else {
                block();
            }
        }
    }

    protected void addListeners(String status, Notification notifiction, Message message) {
        listeners1.put(notifiction.getUniqueId(), new Package(status, message));
    }

    protected void addListeners(String status, Message message) throws IllegalArgumentException {
        if(!listeners1.isEmpty()){
            throw new IllegalArgumentException("You can't have two type of listeners");
        }

        listeners2.put(status, new Package(status, message));
    }
    
    protected void say(String message) {
        System.out.println(this.myAgent.getAID().getName() + ": " + message);
    }
    
    private boolean eventsWithoutId(ACLMessage msg) throws UnreadableException{
        for(String status : this.listeners2.keySet()) {
            Package p = this.listeners2.get(status);
            Message message = p.getMessage();
            Notification notification = (Notification) msg.getContentObject();
            if(notification.getStatus().equals(status)){
                // Execute callback
                message.execute(notification.getObject(), msg.createReply());
                return true;
            }
        }
        
        return false;
    }
    
    private boolean eventsWithid(ACLMessage msg) throws UnreadableException{
        for(String id : this.listeners1.keySet()) {
            Package p = this.listeners1.get(id);
            String status = p.getStatus();
            Message message = p.getMessage();

            Notification notification = (Notification) msg.getContentObject();
            // This is the correct event we're looking for
            if(id.equals(notification.getUniqueId())){
                // Is this what we where looking for?
                if(notification.getStatus().equals(status)){
                    // Execute callback
                    message.execute(notification.getObject(), msg.createReply());
                }
                return true;
            }
        }
        
        return false;
    }
}
