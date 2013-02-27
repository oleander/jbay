import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;


public class MakeBidBehaviour extends Behaviour {

  @Override
  public void action() {	  
	  ACLMessage senderMessage = new ACLMessage(ACLMessage.REQUEST); 
	  senderMessage.addReceiver(new AID("mediator", AID.ISLOCALNAME));
	  Date timeStamp = new Date(System.currentTimeMillis());
	  Bid bid = new Bid(0, 10, (Buyer) myAgent, timeStamp);
	  try {
		senderMessage.setContentObject((Serializable) bid);
		myAgent.send(senderMessage);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
    
  }

  @Override
  public boolean done() {
    // TODO Auto-generated method stub
    return false;
  }

}
