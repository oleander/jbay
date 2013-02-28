import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class Searcher extends Agent {
  static final long serialVersionUID = 2;
  
  @Override
  protected void setup(){
	  System.out.println("Searcher started");
	  addBehaviour(new ResponseSearchForItemBehaviour());
	  
	  DFAgentDescription dfd = new DFAgentDescription();
	  dfd.setName(getAID());
	  ServiceDescription sd = new ServiceDescription();
	  sd.setType("searching");
	  sd.setName("Search for auctions");
	  dfd.addServices(sd);
	  try {
		  DFService.register(this, dfd);
	  } catch(FIPAException fe) {
		  fe.printStackTrace();
	  } 
  }
  
  @Override
  protected void takeDown(){
	  
	  try {
		  DFService.deregister(this);
	  } catch (FIPAException fe) {
		  fe.printStackTrace();
	  }

        System.out.println("Mediator " + getAID().getName() + " terminating.");
  }
}
