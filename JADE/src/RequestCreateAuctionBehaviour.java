

/*
    Used by: Seller
    Use for requesting a new auction
*/
public class RequestCreateAuctionBehaviour extends B {
	private static final long serialVersionUID = 6112036204823563993L;

	@Override
    public void action() {
    	Auction auction = new Auction("Car");;
        this.sendMessageTo("mediator", new Notification(auction, Mediator.CREATENEWAUCTION));
    }

    @Override
    public boolean done() {
        return true;
    }
}
