
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
      
      System.out.println("Buyer started");
      
  //    Object[] args = getArguments();
  //    for (int i = 0; i < args.length; i+=2){
  //      itemsMap.put((String) args[i], Integer.parseInt((String) args[i + 1]));
  //    }
  //    for (String key : itemsMap.keySet()){
  //      System.out.println(key + itemsMap.get(key));
  //    }

      itemsMap.put("Car", 13);
      itemsMap.put("saab", 29);
      itemsMap.put("tall", 34);
      

      // Gets notifications about winning auctions
      this.addBehaviour(new ListenToWinnerOfAuctionBehaviour());

      // Gets notifications about losing auctions
      this.addBehaviour(new ListenToLoserOfAuctionBehaviour());

      addBehaviour(new WakerBehaviour(this,  2000L) {
          @Override
          protected void handleElapsedTimeout() {
              System.out.println("Add search items");
              addSearchBehaviours();
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
