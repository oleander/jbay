import jade.core.AID;
import jade.lang.acl.ACLMessage;

/*
Used by: Seller
*/
public class NotifySellerBehaviour extends CB {
	private static final long serialVersionUID = 1L;

	@Override
    public void action() {
        this.listen(Mediator.AUCTIONHASENDED, new Message(){
            public void execute(ACLMessage message, Object object, AID sender, String id) {
                if(message.getPerformative() == ACLMessage.INFORM){
                    Auction auction = (Auction) object;
                    notifyAboutEndedAuction(auction);
                } else {
                    say("Invalid request");
                }
            }
        });

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
