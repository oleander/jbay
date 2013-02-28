import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class ListenToLoserOfAuctionBehaviour extends CB {
	private static final long serialVersionUID = 1L;

	@Override
    public void action() {
        this.listen(Mediator.LOSEROFAUCTION, new Message(){
            public void execute(ACLMessage message, Object object, AID sender, String id){
                Auction auction = (Auction) object;
                say("Yay, I just won an auction: " + auction);
            }
        });
    }
}
