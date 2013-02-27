import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;


public class ResponseSearchForItemBehaviour extends CyclicBehaviour {

	@Override
	public void action() {
		ACLMessage msg = myAgent.receive();
		
		if (msg != null) {
			String searchQuery = msg.getContent();
			Auctions auctions = Auctions.getInstance();
			List<Auction> searchResults = auctions.search(searchQuery);
			
			ACLMessage searchResultsMessage = msg.createReply();
			try {
				searchResultsMessage.setContentObject((Serializable) searchResults);
				myAgent.send(searchResultsMessage);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else {
			block();
		}
		
	}





}
