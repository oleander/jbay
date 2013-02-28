
import jade.core.Agent;

public class Buyer extends Agent {
  static final long serialVersionUID = 3;
  
  @Override
  protected void setup(){
	  System.out.println("Buyer started");
	  searchForItem();
  }
  
  protected void searchForItem(){
	  addBehaviour(new SearchForItemBehaviour());
  }
  
  protected void makeBid(){
	  addBehaviour(new MakeBidBehaviour());
  }
  
  @Override
  protected void takeDown(){
        System.out.println("Buyer " + getAID().getName() + " terminating.");
  }
}
