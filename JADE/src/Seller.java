
import java.util.Date;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public class Seller extends Agent {
    static final long serialVersionUID = 4;
    
    private Auction auction;

    @Override
    protected void setup(){
    	
//    	String description =  (String) getArguments()[0];
//    	int minPrice = (int) getArguments()[1];
//    	String type;
    	
    	String description = "Volvo V70";
    	int minPrice = 100;
    	String type = "Car";
    	int endtime = 10000;
    	
    	auction = new Auction(description, minPrice, type, endtime);
    	
    	
    	
        // Create new auction and listen for confirmation
        this.addBehaviour(new RequestCreateAuctionBehaviour(auction));

        // When new bids arrives on started auctions
        // this.addBehaviour(new ListenToNewBidsBehaviour());
    }

    @Override
    protected void takeDown(){
        System.out.println("Mediator " + getAID().getName() + " terminating.");
    }
}
