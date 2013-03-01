import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class ListenToLoserOfAuctionBehaviour extends CB {
    private static final long serialVersionUID = 1L;

    @Override
    public void action() {
        this.listen(Mediator.LOSEROFAUCTION, new Message(){
            public void execute(ACLMessage message, Object object, AID sender, String id){
                Auction auction = (Auction) object;
                if(message.getPerformative() == ACLMessage.INFORM){
                    say("Yay, I just lost an auction: " + auction + " to " + auction.getHigestBid().getBidder().getName());
                } else {
                    // TODO: Respond with ACLMessage.INVALID_REQUEST
                    say("Invalid request");
                }
            }
        });

        block(1000);
    }
}
