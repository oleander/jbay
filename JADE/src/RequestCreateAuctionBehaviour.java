import jade.lang.acl.ACLMessage;



/*
    Used by: Seller
    Use for requesting a new auction
*/
public class RequestCreateAuctionBehaviour extends B {
	private static final long serialVersionUID = 6112036204823563993L;

	@Override
    public void action() {
    	Auction auction = new Auction("Car");;
        Notification notifiction = new Notification(auction, Mediator.CREATENEWAUCTION);
        this.sendMessageTo("mediator", notifiction);

        this.addListeners(Mediator.VALIDAUCTION, notifiction, new Message(){
            public void execute(Object object, ACLMessage sender){
                Auction auction = (Auction) object;
                say("Auction was created in ListenToConfirmCreatedAuctionBehaviour: " + auction);
            }
        });

        // Listen for invalid auction
        this.addListeners(Mediator.INVALIDAUCTION, notifiction, new Message(){
            public void execute(Object object, ACLMessage sender){
                Auction auction = (Auction) object;
                say("Auction could not be created in ListenToConfirmCreatedAuctionBehaviour: " + auction);
            }
        });

        this.listen();
    }

    @Override
    public boolean done() {
        return true;
    }
}
