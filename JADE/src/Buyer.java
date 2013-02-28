
import java.util.Map;

import jade.core.Agent;

public class Buyer extends Agent {
  static final long serialVersionUID = 3;
  
  private Map<String, Integer>itemsMap;
  
  @Override
  protected void setup(){

	  System.out.println("Buyer started");
//	  searchForItem();
    // Makes it possible to search for auctions
	  addSearchBehaviours();  
	  

    // Gets notifications about winning auctions
	  this.addBehaviour(new ListenToWinnerOfAuctionBehaviour());

    // Gets notifications about losing auctions
	  this.addBehaviour(new ListenToLoserOfAuctionBehaviour());
  }
  
//  protected void searchForItem(){
//	  addBehaviour(new SearchForItemBehaviour());
//  }
  
  protected void addSearchBehaviours() {
	  for(String item : itemsMap.keySet()){
		  this.addBehaviour(new SearchForItemBehaviour(item, itemsMap.get(item)));  
	  }
  }
  
  protected void makeBid(){
	  addBehaviour(new MakeBidBehaviour());
  }
  
  @Override
  protected void takeDown(){
        System.out.println("Buyer " + getAID().getName() + " terminating.");
  }
}
