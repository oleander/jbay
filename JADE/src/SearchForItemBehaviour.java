import java.util.List;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.tools.testagent.ReceiveCyclicBehaviour;


public class SearchForItemBehaviour extends Behaviour {

  @Override
  public void action() {
	String searchQuery = "fisk";
	ACLMessage senderMessage = new ACLMessage(ACLMessage.REQUEST); 
    senderMessage.addReceiver(new AID("searcher", AID.ISLOCALNAME));
    senderMessage.setContent(searchQuery);
    myAgent.send(senderMessage);
    
    while (true) {
    	ACLMessage msg = myAgent.receive();
    	if (msg != null) {    		
    		try {
				List<Auction> searchResults = (List<Auction>) msg.getContentObject();				
				for(Auction auction : searchResults){
					System.out.println(auction);
				}
				
			} catch (UnreadableException e) {
				e.printStackTrace();
			}
    		
    	} else {
    		block();
    	}
    }
    
  }

  @Override
  public boolean done() {
    // TODO Auto-generated method stub
    return false;
  }

}
