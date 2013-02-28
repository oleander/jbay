import jade.core.AID;

/*
 * Used by: Mediator
 */
public class ResponseMakeBidBehaviour extends CB {
    private static final long serialVersionUID = -6999339207150014475L;

    @Override
    public void action() {
        this.addListeners(Mediator.CREATENEWAUCTION, new Message(){
            public void execute(Object object, AID sender){
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
                        // sender.setContent(Mediator.INVALIDBID);
                        // myAgent.send(sender);
                    }
                }
            }
        });
        
        this.listen();
    }
    
    public void notifySellerAboutNewBid(Seller seller, Auction auction){
        this.sendMessageTo(seller, new Notification(auction, Mediator.SELLERNEWBID));
    }
    
    public void notifyNotHighestBidder(Buyer bidder, Auction auction){
        this.sendMessageTo(bidder, new Notification(auction, Mediator.NOTHIGHESTBIDDER));
    }
}
