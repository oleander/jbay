import jade.core.AID;
import jade.lang.acl.ACLMessage;

/*
    Used by: Buyer
*/
public class ListenToWinnerOfAuctionBehaviour extends CB {
	private static final long serialVersionUID = -4486539774038431412L;

	@Override
    public void action() {
        this.listen(Mediator.WINNEROFAUCTION, new Message(){
            public void execute(ACLMessage message, Object object, AID sender, String id){
                Auction auction = (Auction) object;
                if(message.getPerformative() == ACLMessage.INFORM){
                    say("Yay, I just won an auction: " + auction);
                } else {
                    // TODO: Respond to @sender with invalid request
                    say("Invalid request");
                }
            }
        });
    }
}
