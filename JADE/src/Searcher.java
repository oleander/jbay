import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;

public class Searcher extends Agent {
  static final long serialVersionUID = 2;
  
  @Override
  protected void setup(){
      System.out.println("Searcher started");
      addBehaviour(new ResponseSearchForItemBehaviour());
      
       Catalog.register("searching", this);
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
