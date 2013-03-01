
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.WakerBehaviour;

public class Buyer extends Agent {
    static final long serialVersionUID = 3;
    
    private Map<String, Integer>itemsMap = new HashMap<String, Integer>();
    
    @Override
    protected void setup(){

      final ArrayList<Auction> auctions = Auctions.getInstance().getAll();
      System.out.println("Buyer started");
      
      
      
      Object[] args = getArguments();
  	
  	for (int i = 0; i < args.length; i+=2){    		
  		String item =  (String) args[i];
      	int maxPrice = Integer.parseInt((String) args[i + 1]);
      	itemsMap.put(item, maxPrice);
  	}
      
      
//      itemsMap.put("Car", 13);


      addSearchBehaviours();
      // Gets notifications about winning auctions
      this.addBehaviour(new ListenToWinnerOfAuctionBehaviour());

      // Gets notifications about losing auctions
      this.addBehaviour(new ListenToLoserOfAuctionBehaviour());

      this.addBehaviour(new WakerBehaviour(this,  (long) (Math.random() * 2000.0)) {
          @Override
          protected void handleElapsedTimeout() {
              System.out.println("Add search items");
              // addSearchBehaviours();

              // Make bid on items
              addBehaviour(new MakeBidBehaviour(auctions.get(0), 1000, 5));
          }
      });
    }

      protected void addSearchBehaviours() {
        for(String item : itemsMap.keySet()){
          this.addBehaviour(new SearchForItemBehaviour(item, itemsMap.get(item)));  
        }
      }
      
      public int getMaxPrice(String item){
    	  System.out.println(item);
    	  return itemsMap.get(item);
      }


  //protected void makeBid(){
  //    addBehaviour(new MakeBidBehaviour());
  //  }
    
    @Override
    protected void takeDown(){
          System.out.println("Buyer " + getAID().getName() + " terminating.");
  }
}
