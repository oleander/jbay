import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;


public class RequestCreateAuctionBehaviour extends Behaviour {

	@Override
	public void action() {
		ACLMessage msg = myAgent.receive();
		if (msg != null) {			
			((Mediator) myAgent).getAuctionQueue().add(new Auction(msg.getContent()));
		} else { 
			block(); 
		} 
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}

}
