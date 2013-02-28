
import java.util.HashMap;
import java.util.Map;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

public class Buyer extends Agent {
  static final long serialVersionUID = 3;
  
  private Map<String, Integer>itemsMap = new HashMap<String, Integer>();
  private int maxBid;
  
  @Override
  protected void setup(){
	  
	  System.out.println("Buyer started");
	  
//	  Object[] args = getArguments();
//	  for (int i = 0; i < args.length; i+=2){
//		  itemsMap.put((String) args[i], Integer.parseInt((String) args[i + 1]));
//	  }
//	  for (String key : itemsMap.keySet()){
//		  System.out.println(key + itemsMap.get(key));
//	  }

	  addBehaviour(new TickerBehaviour(this,  2000L) {
		
		@Override
		protected void onTick() {
			System.out.println("Väntaaaar");
			
		}
	});
	  itemsMap.put("makrill", 13);
	  itemsMap.put("saab", 29);
	  itemsMap.put("tall", 34);
	  
	  
	  
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
  
  
  
  public int getMaxBid() {
	return maxBid;
}

//protected void makeBid(){
//	  addBehaviour(new MakeBidBehaviour());
//  }
  
  @Override
  protected void takeDown(){
        System.out.println("Buyer " + getAID().getName() + " terminating.");
  }
}
