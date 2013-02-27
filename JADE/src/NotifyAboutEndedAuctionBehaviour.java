import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;


/*
Used by: Seller
*/
public class NotifyAboutEndedAuctionBehaviour extends CB {
    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            try {
                AuctionNotification an = (AuctionNotification) msg.getContentObject();
                if(an.getStatus().equals(Mediator.AUCTIONHASENDED)){
                    say("Auction has ended, the winner is " + an.getAuction().);
                }
            } catch (UnreadableException e) {
                say("Could not receive message");
            }
        } else {
            block();
        }
    }
}
