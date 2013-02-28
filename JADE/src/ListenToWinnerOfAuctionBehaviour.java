import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

/*
    Used by: Buyer
*/
public class ListenToWinnerOfAuctionBehaviour extends CB {

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
