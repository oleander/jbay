import jade.lang.acl.ACLMessage;

/*
 * Used by: Seller
 * Notifications for new bids on auctions
 */
public class ListenToNewBidsBehaviour extends CB {
	private static final long serialVersionUID = 1553826817194716181L;

	@Override
    public void action() {
        this.addListeners(Mediator.SELLERNEWBID, new Message(){
            public void execute(Object object, ACLMessage sender){
                Auction auction = (Auction) object;
                say("Someone made a new bid on " + auction.toString());
            }
        });
        
        this.listen();
    }
}
