
import jade.core.Agent;

public class Seller extends Agent {
    static final long serialVersionUID = 4;

    @Override
    protected void setup(){
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
