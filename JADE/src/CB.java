import java.io.IOException;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.util.leap.Serializable;

public abstract class CB extends CyclicBehaviour {
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

    protected void listenTo(String string, Message message) {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            try {
            	AuctionNotification an = (AuctionNotification) msg.getContentObject();
                if(an.getStatus().equals(string)){
                    message.execute(msg.getContentObject());
                }
            } catch (UnreadableException e) {
                say("Could not receive message");
            }
        } else {
            block();
        }
    }
}