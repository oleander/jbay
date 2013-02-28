import jade.lang.acl.ACLMessage;

/*
* Used by: Seller
*/
public class ListenToConfirmCreatedAuctionBehaviour extends B {
	private static final long serialVersionUID = 1L;

	@Override
    public void action() {
        // Listen for valid auction
        this.addListeners(Mediator.VALIDAUCTION, new Message(){
            public void execute(Object object, ACLMessage sender){
                Auction auction = (Auction) object;
                say("Auction was created in ListenToConfirmCreatedAuctionBehaviour: " + auction);
            }
        });

        // Listen for invalid auction
        this.addListeners(Mediator.INVALIDAUCTION, new Message(){
            public void execute(Object object, ACLMessage sender){
                Auction auction = (Auction) object;
                say("Auction could not be created in ListenToConfirmCreatedAuctionBehaviour: " + auction);
            }
        });

        this.listen();
    }

    @Override
    public boolean done() {
        return false;
    }
}
