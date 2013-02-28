import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

/*
    Used by: Buyer
*/
public class ListenToWinnerOfAuction extends CB {

    @Override
    public void action() {
        this.addListeners(Mediator.WINNEROFAUCTION, new Message(){
            public void execute(Object object, ACLMessage sender){
                AuctionNotification an = (AuctionNotification) object;
                say("Yay, I just won an auction: " + an.getAuction());
            }
        });
        
        this.listen();
    }
}
