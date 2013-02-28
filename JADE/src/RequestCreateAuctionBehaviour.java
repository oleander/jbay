import jade.core.AID;
import jade.lang.acl.ACLMessage;


/*
    Used by: Seller
    Use for requesting a new auction
*/
public class RequestCreateAuctionBehaviour extends B {
    private static final long serialVersionUID = 6112036204823563993L;
    private int step = 0;
    private String id = Helper.getUUID();
    @Override
    public void action() {
        say("I'm in: " + this.step);
        switch(this.step){
        case 0:
            say("Sending CREATEAUCTION to mediator");
            Auction auction = new Auction("Fisk");
            
            // Create auction
            this.sendMessageTo("mediator", "random", Mediator.CREATEAUCTION, ACLMessage.REQUEST, auction);
            this.step = 1;
            break;
        case 1:
            this.listen(id, "RANDOM", new Message(){
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
    }

    public boolean done(){
        return this.step == 2;
    }
}
