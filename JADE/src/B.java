import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;


public abstract class B extends Behaviour {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	
    protected void say(String message) {
        System.out.println(this.myAgent.getAID().getName() + ": " + message);
    }
}
