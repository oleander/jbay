
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.WakerBehaviour;

public class Buyer extends Agent {
    static final long serialVersionUID = 3;
    
    private Map<String, Integer>itemsMap = new HashMap<String, Integer>();
    private int maxBid;
    
    @Override
    protected void setup(){

      final ArrayList<Auction> auctions = Auctions.getInstance().getAll();
      System.out.println("Buyer started");

      itemsMap.put("Car", 13);
      itemsMap.put("saab", 29);
      itemsMap.put("tall", 34);
      
      // Gets notifications about winning auctions
      this.addBehaviour(new ListenToWinnerOfAuctionBehaviour());

      // Gets notifications about losing auctions
      this.addBehaviour(new ListenToLoserOfAuctionBehaviour());

      this.addBehaviour(new WakerBehaviour(this,  2000) {
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

    
    public int getMaxBid() {
    return maxBid;
  }

  //protected void makeBid(){
  //    addBehaviour(new MakeBidBehaviour());
  //  }
    
    @Override
    protected void takeDown(){
          System.out.println("Buyer " + getAID().getName() + " terminating.");
  }
}
