import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;


/*
Used by: Seller
*/
public class NotifySellerBehaviour extends CB {
    @Override
    public void action() {
        this.listenTo(Mediator.AUCTIONHASENDED, new Message(){
            public void execute(Object object){
                AuctionNotification an = (AuctionNotification) object;
                notifyAboutEndedAuction(an.getAuction());
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
