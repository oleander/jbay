import jade.core.AID;

public class ListenToLoserOfAuctionBehaviour extends CB {
	private static final long serialVersionUID = 1L;

	@Override
    public void action() {
        this.addListeners(Mediator.LOSEROFAUCTION, new Message(){
            public void execute(Object object, AID sender){
                Auction auction = (Auction) object;
                say("Yay, I just won an auction: " + auction);
            }
        });
        
        this.listen();
    }
}
