import jade.core.AID;
import jade.lang.acl.ACLMessage;

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
                        
                        if(formerHighestBid != null){
                            // Notify previous higest bidder about not having the higest bid
                            notifyNotHighestBidder(formerHighestBid.getBidder(), auction);
                        }
                        
                    } else {
                        // TODO: FIX THIS
                        // sender.setContent(Mediator.INVALIDBID);
                        // myAgent.send(sender);
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
}
