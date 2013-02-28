import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

/*
    Used by: Buyer
*/
public class ListenToWinnerOfAuction extends CB {

    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            try {
                AuctionNotification an = (AuctionNotification) msg.getContentObject();
                if(an.getStatus().equals(Mediator.WINNEROFAUCTION)){
                    say("Yay, I just won an auction: " + an.getAuction());
                }
            } catch (UnreadableException e) {
                say("Could not receive message");
            }
        } else {
            block();
        }
    }
}
