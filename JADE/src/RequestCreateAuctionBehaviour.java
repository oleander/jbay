import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;


public class RequestCreateAuctionBehaviour extends Behaviour {
	
	private static final String VALIDMESSAGE = "valid-message";
	private static final String INVALIDMESSAGE = "invalid-message";
	
	@Override
	public void action() {
		
		Auction auction = new Auction("fisk");
		ACLMessage senderMessage = new ACLMessage(ACLMessage.REQUEST); 
		senderMessage.addReceiver(new AID("mediator", AID.ISLOCALNAME));
		senderMessage.setContent(auction.toString());
		myAgent.send(senderMessage);
		
		ACLMessage msg = myAgent.receive();
		
		if (msg != null) {			
			if(msg.getContent().equals(VALIDMESSAGE)){
				System.out.println("Auction was created");
			} else {
				System.out.println("Auction could not be created");
			}
			
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
