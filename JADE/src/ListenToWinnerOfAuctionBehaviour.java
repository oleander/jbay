import jade.lang.acl.ACLMessage;

/*
    Used by: Buyer
*/
public class ListenToWinnerOfAuctionBehaviour extends CB {
	private static final long serialVersionUID = -4486539774038431412L;

	@Override
    public void action() {
        this.addListeners(Mediator.WINNEROFAUCTION, new Message(){
            public void execute(Object object, ACLMessage sender){
                Auction auction = (Auction) object;
                say("Yay, I just won an auction: " + auction);
            }
        });
        
        this.listen();
    }
}
