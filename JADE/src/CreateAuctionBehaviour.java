import jade.core.AID;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
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

                    say("Add auction to queue, wait " + auction.getEndTime() + " ms");
                    myAgent.addBehaviour(new AuctionEndedBehaviour(myAgent, auction));
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