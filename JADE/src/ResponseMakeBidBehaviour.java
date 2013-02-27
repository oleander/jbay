import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;


public class ResponseMakeBidBehaviour extends CyclicBehaviour {

	@Override
	public void action() {
		ACLMessage msg = myAgent.receive();
		
		if (msg != null) {
			
			try {
				Bid newBid = (Bid) msg.getContentObject();
				if(newBid.getAmount() > ){
					
				} else {
					ACLMessagemsg.createReply()
				}
				
				
			} catch (UnreadableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} else {
			block();
		}
		

	}



}
