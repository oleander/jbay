import jade.core.AID;

/*
Used by: Seller
*/
public class NotifySellerBehaviour extends CB {
	private static final long serialVersionUID = 1L;

	@Override
    public void action() {
        this.addListeners(Mediator.AUCTIONHASENDED, new Message(){
            public void execute(Object object, AID sender){
                Auction auction = (Auction) object;
                notifyAboutEndedAuction(auction);
            }
        });

        this.listen();
    }

    /*
     * Is triggered when auction has ended
     */
    public void notifyAboutEndedAuction(Auction auction){
        String name = "none";
        Bid bid = auction.getHigestBid();
        if(bid != null){
            name = bid.getBidder().getName();
        }
        say("Auction has ended, the winner is " + name);
    }
}
