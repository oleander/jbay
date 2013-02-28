
import jade.core.Agent;

public class Seller extends Agent {
    static final long serialVersionUID = 4;

    @Override
    protected void setup(){
    	
//    	String description =  (String) getArguments()[0];
//    	int minPrice = (int) getArguments()[1];
//    	String type 
    	
        // Create new auction and listen for confirmation
        this.addBehaviour(new RequestCreateAuctionBehaviour());

        // When new bids arrives on started auctions
        this.addBehaviour(new ListenToNewBidsBehaviour());
    }

    @Override
    protected void takeDown(){
        System.out.println("Mediator " + getAID().getName() + " terminating.");
    }
}
