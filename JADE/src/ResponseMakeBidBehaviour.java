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
                if(message.getPerformative() != ACLMessage.REQUEST){
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

                        // Notify previous higest bidder about not having the higest bid
                        notifyNotHighestBidder(formerHighestBid.getBidder(), auction);
                        
                    } else {
                        // TODO: FIX THIS
                        // sender.setContent(Mediator.INVALIDBID);
                        // myAgent.send(sender);
                    }
                }
            }
        });
    }
    
    public void notifySellerAboutNewBid(Seller seller, Auction auction){
        this.sendMessageTo(seller, null, Mediator.MAKEBID, ACLMessage.ACCEPT_PROPOSAL, auction);
    }
    
    public void notifyNotHighestBidder(Buyer bidder, Auction auction){
        this.sendMessageTo(bidder, null, Mediator.MAKEBID, ACLMessage.FAILURE, auction);
    }
}
