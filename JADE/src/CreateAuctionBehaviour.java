import java.io.IOException;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.core.AID;
/*
  Used by: Mediator
*/
public class CreateAuctionBehaviour extends CB {
    private static final long serialVersionUID = 1L;
    @Override
    public void action() {
        this.listen(Mediator.CREATEAUCTION, new Message(){
            public void execute(ACLMessage message, Object object, AID seller, String id){
                String name = null;
                if(seller != null){
                    name = seller.toString();
                }

                say("Received message on CREATENEWAUCTION from: " + name);
                Auction auction = (Auction) object;
                if(auction.isValid()){
                    say("Auction is valid, storing ...");
                    ((Mediator) myAgent).getAuctions().store(auction);
                    try {
                        sendMessageTo(seller, id, Mediator.CREATEAUCTION, ACLMessage.ACCEPT_PROPOSAL, auction);
                    } catch (IOException e) {
                        say("Something went wrong: " + e.getMessage());
                    }
                 } else {
                     say("Auction is not valid");
                     try {
                        sendMessageTo(seller, id, Mediator.CREATEAUCTION, ACLMessage.FAILURE, auction);
                    } catch (IOException e) {
                        say("Something went wrong: " + e.getMessage());
                    }
                 }
            }
        });

        block(1000);
    }
}