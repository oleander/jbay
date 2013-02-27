import jade.core.Agent;

public class Searcher extends Agent {
  static final long serialVersionUID = 2;
  
  @Override
  protected void setup(){
	  System.out.println("Searcher started");
	  addBehaviour(new ResponseSearchForItemBehaviour());
  }
  
  @Override
  protected void takeDown(){
        System.out.println("Mediator " + getAID().getName() + " terminating.");
  }
}
