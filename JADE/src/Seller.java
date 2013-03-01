import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;

public class Seller extends Agent {
    static final long serialVersionUID = 4;
    
    private ArrayList<Auction> auctions = new ArrayList<Auction>();

    @Override
    protected void setup(){
        System.out.println("Seller started");
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
        
        for (Auction auction : auctions) {
            this.addBehaviour(new RequestCreateAuctionBehaviour(auction));
        }
        
        // Create new auction and listen for confirmation
        
        // When new bids arrives on started auctions
        this.addBehaviour(new ListenToNewBidsBehaviour());

        // Auctions ends
        this.addBehaviour(new CB(){
			private static final long serialVersionUID = 1L;
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

                        // No one else can bid on the auction
                        auction.lock();
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
