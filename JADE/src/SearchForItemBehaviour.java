
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

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
			Notification notifiction = new Notification("fisk", Mediator.SEARCHFORAUCTION);
	        this.sendMessageTo(result.getName(), notifiction);
	        this.addListeners(Mediator.SEARCHFORAUCTION, notifiction, new Message(){
	            public void execute(Object object, AID sender){
	                List<Auction> auctions = (List<Auction>) object;
	                say("We received " + auctions.size() + " auctions");
	            }
	        });
	        this.listen();
		} catch (FIPAException e1) {
			e1.printStackTrace();
		}	

        
        

    }

    @Override
    public boolean done() {
        return true;
    }
}
