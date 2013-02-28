import jade.lang.acl.ACLMessage;

public class ListenToLoserOfAuction extends CB {
    @Override
    public void action() {
        this.addListeners(Mediator.LOSEROFAUCTION, new Message(){
            public void execute(Object object, ACLMessage sender){
                AuctionNotification an = (AuctionNotification) object;
                say("Yay, I just won an auction: " + an.getAuction());
            }
        });
        
        this.listen();
    }
}
