
import jade.core.Agent;

public class Buyer extends Agent {
  static final long serialVersionUID = 3;
  
  @Override
  protected void setup(){
    // Makes it possible to search for auctions
	  this.addBehaviour(new SearchForItemBehaviour());

    // Gets notifications about winning auctions
    this.addBehaviour(new ListenToWinnerOfAuctionBehaviour());

    // Gets notifications about losing auctions
    this.addBehaviour(new ListenToLoserOfAuctionBehaviour());
  }
  
  @Override
  protected void takeDown(){
        System.out.println("Buyer " + getAID().getName() + " terminating.");
  }
}
