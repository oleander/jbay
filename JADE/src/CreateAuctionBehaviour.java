import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.core.AID;
/*
  Used by: Mediator
*/
public class CreateAuctionBehaviour extends B {
    private static final long serialVersionUID = 1L;

    @Override
    public void action() {
        this.addListeners(Mediator.CREATENEWAUCTION, new Message(){
            public void execute(Object object, AID seller){
                String name = null;
                if(seller != null){
                    name = seller.toString();
                }
                Notification notifiction = null;
                say("Received message on CREATENEWAUCTION from: " + name);
                Auction auction = (Auction) object;
                if(auction.isValid()){
                    say("Auction is valid, storing ...");
                    notifiction = new Notification(auction, Mediator.VALIDAUCTION);
                    ((Mediator) myAgent).getAuctions().store(auction);
                    sendMessageTo(seller, notifiction);
                 } else {
                     say("Auction is not valid");
                     notifiction = new Notification(auction, Mediator.INVALIDAUCTION);
                    sendMessageTo(seller, notifiction); 
                 }
            }
        });

        this.listen();
    }

    @Override
    public boolean done() {
        return false;
    }
}