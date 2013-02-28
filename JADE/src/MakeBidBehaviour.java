import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;


public class MakeBidBehaviour extends Behaviour {
	private static final long serialVersionUID = -1288633840082856281L;
	
	private Auction auction;
	private Bid bid;
	

public MakeBidBehaviour(Auction auction, Bid bid) {
		super();
		this.auction = auction;
		this.bid = bid;
	}

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
