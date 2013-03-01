import jade.core.AID;
import jade.lang.acl.ACLMessage;

import java.io.IOException;

/*
 * Used by: Mediator
 */
public class ResponseMakeBidBehaviour extends CB {
    private static final long serialVersionUID = -6999339207150014475L;

    @Override
    public void action() {
        this.listen(Mediator.MAKEBID, new Message(){
            public void execute(ACLMessage message, Object object, AID sender, String id){
                say("Just got a message from " + sender.getName());

                if(message.getPerformative() != ACLMessage.PROPOSE){
                    say("Invalid request"); return;
                }

                // Fetch new bid
                Bid newBid = (Bid) object;

                // What auction are we bidding on?
                Auction auction = Auctions.getInstance().findByid(newBid.getAuctionID());

                // We don't want anyone else to interact with our auction
                // The interaction should be atomic
                synchronized (auction) {
                    Bid formerHighestBid = auction.getHigestBid();
                    if(auction.makeBid(newBid)){
                        // Notify seller about new bid
                        notifySellerAboutNewBid(auction.getSeller(), auction);
                        say("new bid was added: " + newBid.getAmount());
                        if(formerHighestBid != null){
                            // Notify previous higest bidder about not having the higest bid
                            notifyNotHighestBidder(formerHighestBid.getBidder(), auction);
                        }

                        notifyBidderAboutRequest(sender, auction);
                        
                    } else {
                        notifyBidderAboutInvalidRequest(sender, auction);
                    }
                }
            }
        });

        block(1000);
    }
    
    public void notifySellerAboutNewBid(Seller seller, Auction auction){
        this.sendMessageTo(seller, null, Mediator.NEWBID, ACLMessage.ACCEPT_PROPOSAL, auction);
    }
    
    public void notifyNotHighestBidder(Buyer bidder, Auction auction){
        this.sendMessageTo(bidder, null, Mediator.NEWBID, ACLMessage.FAILURE, auction);
    }

    public void notifyBidderAboutInvalidRequest(AID buyer, Auction auction){
        try {
            this.sendMessageTo(buyer, null, Mediator.MAKEBID, ACLMessage.FAILURE, auction);
        } catch (IOException e) {
            say("Something went wrong in: " + e.getMessage());
        }
    }

    public void notifyBidderAboutRequest(AID buyer, Auction auction){
        try {
            this.sendMessageTo(buyer, null, Mediator.MAKEBID, ACLMessage.ACCEPT_PROPOSAL, auction);
        } catch (IOException e) {
            say("Something went wrong in: " + e.getMessage());
        }
    }
}
