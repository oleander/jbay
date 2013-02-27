
import jade.core.Agent;

public class Buyer extends Agent {
  static final long serialVersionUID = 3;
  
  @Override
  protected void setup(){
	  System.out.println("Buyer started");
	  addBehaviour(new SearchForItemBehaviour());
  }
  
  @Override
  protected void takeDown(){
        System.out.println("Buyer " + getAID().getName() + " terminating.");
  }
}
