
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.util.List;


public class SearchForItemBehaviour extends B {
    private static final long serialVersionUID = 1L;
    private String id = Helper.getUUID();
    private String item;
    private int maxPrice;
    private int step;
    
    public SearchForItemBehaviour(String item, int maxPrice){
        this.item = item;
        this.maxPrice = maxPrice;
    }

    @Override
    public void action() {
        switch(step){
        case 0:
//        	DFAgentDescription template = new DFAgentDescription();
//            ServiceDescription sd = new ServiceDescription();
//            sd.setType("searching");
//            template.addServices(sd); 
//            DFAgentDescription searcher = null;
//            
//            try {
//                searcher = DFService.search(myAgent, template)[0];
//            } catch (FIPAException e1) {
//                e1.printStackTrace();
//            }   
//
//            if(searcher == null){
//                say("No searcher found");
//                return;
//            }

            //this.sendMessageTo(searcher.getName(), id , Mediator.SEARCHFORAUCTION, ACLMessage.PROPOSE, "Fisk");
			this.sendMessageTo("searcher", id , Mediator.SEARCHFORAUCTION, ACLMessage.PROPOSE, "Fisk");
            
            step = 1;
            break;
        case 1:
        	
	        this.listen(id, Mediator.SEARCHFORAUCTION, new Message(){
	            public void execute(ACLMessage message, Object object, AID seller, String id){
	                List<Auction> auctions = (List<Auction>) object;
	                say("We received " + auctions.size() + " auctions");
	                step = 2;
	            }
	        });
	        
	        break;
        }
    }

    @Override
    public boolean done() {
        return step == 2;
    }
}
