// Environment code for project Auction2.mas2j

import jason.asSyntax.*;
import jason.environment.*;
import java.util.Map.Entry;
import java.util.logging.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

public class Auction2 extends Environment {
    private Logger logger = Logger.getLogger("Auction2.mas2j." + Auction2.class.getName());
    private HashMap<Integer, AuctionObject> map = new HashMap<Integer, AuctionObject>();
    
    class AuctionObject {
        private int id;
        private String description;
        private String type;
        private int price;
        
        public AuctionObject(int id, String description, String type, int price){
            this.id = id;
            this.description = description;
            this.type = type;
            this.price = price;
        }
            
        
        public int getId(){
            return id;
        }
        
        public String getDescription(){
            return description;
        }
        
        public String getType(){
            return type;
        }
        
        public int getPrice(){
            return price    ;
        }
        
        @Override
        public String toString() {
            return "{id: " + id + ", description: " + description + ", type: " + type + ", price: " + price + "}";
        }
        
    }



    /** Called before the MAS execution with the args informed in .mas2j */

    @Override

    public void init(String[] args) {



//        addPercept(Literal.parseLiteral("new_item(volvo, car, 100)"));
//      
//        addPercept(Literal.parseLiteral("new_item(mackerel, fish, 20)"));
//        
//        addPercept(Literal.parseLiteral("new_item_to_buy(car, 120)"));

    }
    
    public AuctionObject addAuction(List<Term> terms){      
        int id = map.size();
        String description = terms.get(0).toString();
        String type = terms.get(1).toString();
        int price = Integer.parseInt(terms.get(2).toString());
        
        AuctionObject auctionObject = new AuctionObject(id, description, type, price);
        map.put(id, auctionObject);
        logger.info("Auction added:" + (auctionObject.toString()));
        return auctionObject;
    }
    
    public List<AuctionObject> findAuctions(List<Term> terms){
        String query = terms.get(0).toString();
        int maxPrice = Integer.parseInt(terms.get(1).toString());
        
        List<AuctionObject> auctions = new LinkedList<AuctionObject>();
        
        for(AuctionObject auction : map.values()) {
                        
            //either type or description matches search query and price is not greater than maxPrice
            if((auction.getType().contains(query) || auction.getDescription().contains(query))
                    && auction.getPrice() <= maxPrice) {
                auctions.add(auction);
            }
        }
		
		
        //addPercept("searcher", Literal.parseLiteral("search_result(" + auctions.get(0).getDescription() + "," + auctions.get(0).getPrice()));
        addPercept("searcher", Literal.parseLiteral("auction(0, volvo, car, 50)"));
		return auctions;
    }


    @Override

    public boolean executeAction(String agName, Structure action) {
        List<Term> terms = action.getTerms();
        switch(action.getFunctor()){
            case "add_auction":
                addAuction(terms);
                break;
            case "search_auctions": 
                findAuctions(terms);
                break;
            default:
                logger.info("executing: "+action+", but not implemented!");
                break;
        }

        return true;
    }

    /** Called before the end of MAS execution */

    @Override

    public void stop() {
        super.stop();
    }
    
    public void delay(long milis) {
        try {
              Thread.sleep(milis); 
        } catch (Exception e) {
            e.printStackTrace();  
        }
    }

}


