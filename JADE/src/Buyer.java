
import jade.core.Agent;

public class Buyer extends Agent {
  static final long serialVersionUID = 3;
  
  @Override
  protected void setup(){
  }
  
  @Override
  protected void takeDown(){
        System.out.println("Mediator " + getAID().getName() + " terminating.");
  }
}
