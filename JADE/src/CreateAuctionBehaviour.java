import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;


public class CreateAuctionBehaviour extends Behaviour {
	private static final String VALIDMESSAGE = "valid-message";
	private static final String INVALIDMESSAGE = "invalid-message";

	@Override
	public void action() {
		// Fetch message
		ACLMessage msg = myAgent.receive();
		
		// Fetch sender
//		String sender = msg.getSender().getName();
//		ACLMessage senderMessage = new ACLMessage(ACLMessage.INFORM); 
//		senderMessage.addReceiver(new AID(sender, AID.ISLOCALNAME));
		ACLMessage sellerChannel = msg.createReply();
		
		if (msg != null) {
			// Create auction
			Auction auction = new Auction(msg.getContent());
			
			// Is auction valid?
			if(auction.isValid()){
				// 
				sellerChannel.setContent(VALIDMESSAGE);
			} else {
				sellerChannel.setContent(INVALIDMESSAGE);
			}
			myAgent.send((sellerChannel));
			
			((Mediator) myAgent).getAuctionQueue().add(auction);
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
