import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;


public class ResponseSearchForItemBehaviour extends CyclicBehaviour {
	private static final long serialVersionUID = 844913931022530879L;

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
