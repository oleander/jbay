import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.io.Serializable;


public abstract class WB extends WakerBehaviour {
    public WB(Agent a, Integer time) {
        super(a, time);
    }

    /**
     * 
     */
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
    
    protected void say(String message) {
        System.out.println(this.myAgent.getAID().getName() + ": " + message);
    }
}
