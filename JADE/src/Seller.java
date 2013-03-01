

import java.util.Date;
import java.util.ArrayList;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;

public class Seller extends Agent {
    static final long serialVersionUID = 4;
    
    private ArrayList<Auction> auctions = new ArrayList<Auction>();

    @Override
    protected void setup(){
    	
    	execute();
    	
    }
    
    public void execute(){    	
    	Object[] args = getArguments();
    	
    	for (int i = 0; i < args.length; i+=4){    		
    		String description =  (String) args[i];
        	int minPrice = Integer.parseInt((String) args[i + 1]);
        	String type = (String) args[i + 2];
        	int endTime = Integer.parseInt((String) args[i + 3]);
    		auctions.add(new Auction(description, minPrice, type, endTime, this));
    	}
    	
//    	String description = "Volvo V70";
//    	int minPrice = 100;
//    	String type = "Car";
//    	int endtime = 10000;
    	
    	
    	
    	for (Auction auction : auctions) {
    		this.addBehaviour(new RequestCreateAuctionBehaviour(auction));
    	}
    	
        // Create new auction and listen for confirmation
        

        // When new bids arrives on started auctions
        this.addBehaviour(new ListenToNewBidsBehaviour());

        // Auctions ends
        this.addBehaviour(new CB(){
            public void action() {
                this.listen(Mediator.AUCTIONHASENDED, new Message(){
                    public void execute(ACLMessage message, Object object, AID sender, String id){
                        Auction auction = (Auction) object;
                        Bid bid = auction.getHigestBid();
                        if(bid != null){
                            say(auction + " just ended with higest bidder " + bid.getBidder().getName() + " and price " + bid.getAmount());
                        } else {
                            say(auction + " just ended with no bidders");
                        }
                    }
                });
                block(1000);
            }
        });
    }
    
    @Override
    protected void takeDown(){
        System.out.println("Mediator " + getAID().getName() + " terminating.");
    }

    protected void addAuction(Auction auction){
        this.auctions.add(auction);
    }
}
