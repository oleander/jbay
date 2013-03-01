import java.io.IOException;

import jade.core.AID;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;


/*
    Used by: Seller
    Use for requesting a new auction
*/
public class RequestCreateAuctionBehaviour extends B {
    private static final long serialVersionUID = 6112036204823563993L;
    private int step = 0;
    private String id = Helper.getUUID();
    private Auction auction;

    public RequestCreateAuctionBehaviour(Auction auction){
        super();
        this.auction = auction;
    }
    
    @Override
    public void action() {
        switch(this.step){
        case 0:
            say("Sending CREATEAUCTION to mediator");
            
            AID mediator = Catalog.getAgent("mediating", myAgent);
            
            
            // Create auction

            try {
				this.sendMessageTo(mediator, id, Mediator.CREATEAUCTION, ACLMessage.REQUEST, auction);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

            this.step = 1;
            break;
        case 1:
            this.listen(Mediator.CREATEAUCTION, new Message(){
                public void execute(ACLMessage message, Object object, AID sender, String id) {
                    if(message.getPerformative() == ACLMessage.ACCEPT_PROPOSAL){
                        Auction auction = (Auction) object;
                        ((Seller) myAgent).addAuction(auction);
                        try {
                            say("Auction was created in ListenToConfirmCreatedAuctionBehaviour: " + auction.getId());
                        } catch (Exception e) {
                            say(e.getMessage());
                        }
                    } else if(message.getPerformative() == ACLMessage.REFUSE) {
                        say("Invalid auction created in RequestCreateAuctionBehaviour");
                    } else {
                        say("Strange status code from Mediator");
                    }

                    step = 2;
                }
            });
            break;
        }

        block(1000);
    }

    public boolean done(){
        return this.step == 2;
    }
}
