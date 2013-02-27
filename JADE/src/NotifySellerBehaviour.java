import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;


/*
Used by: Seller
*/
public class NotifySellerBehaviour extends CB {
    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            try {
                AuctionNotification an = (AuctionNotification) msg.getContentObject();
                if(an.getStatus().equals(Mediator.AUCTIONHASENDED)){
                    this.notifyAboutEndedAuction(an.getAuction());
                }
            } catch (UnreadableException e) {
                say("Could not receive message");
            }
        } else {
            block();
        }
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
