import jade.core.Agent;

public class Searcher extends Agent {
  static final long serialVersionUID = 2;
  
  @Override
  protected void setup(){
    
  }
  
  @Override
  protected void takeDown(){
        System.out.println("Mediator " + getAID().getName() + " terminating.");
  }
}
