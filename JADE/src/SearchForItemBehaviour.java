import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

import java.util.List;


public class SearchForItemBehaviour extends B {
	private static final long serialVersionUID = 1L;
	private String item;
	private int maxPrice;
	
	public SearchForItemBehaviour(String item, int maxPrice){
		this.item = item;
		this.maxPrice = maxPrice;
	}
	
@Override
  public void action() {
	
	DFAgentDescription template = new DFAgentDescription();
	ServiceDescription sd = new ServiceDescription();
	sd.setType("searching");
	template.addServices(sd); 
	
	try {
		DFAgentDescription result = DFService.search(myAgent, template)[0];
		sendm
	} catch (FIPAException e1) {
		e1.printStackTrace();
	}
	
	
	String searchQuery = item;
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
