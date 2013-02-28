import jade.core.AID;
import jade.lang.acl.ACLMessage;

/*
 * Used by: Seller
 * Notifications for new bids on auctions
 */
public class ListenToNewBidsBehaviour extends CB {
	private static final long serialVersionUID = 1553826817194716181L;

	@Override
    public void action() {
        this.listen(Mediator.NEWBID, new Message(){
            public void execute(ACLMessage message, Object object, AID sender, String id){
                Auction auction = (Auction) object;
                say("Someone made a new bid on " + auction.toString() + ", yay!");
            }
        });

        block(1000);
    }
}
