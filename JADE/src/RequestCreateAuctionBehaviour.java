import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;


/*
    Used by: Seller
    Use for requesting a new auction
*/
public class RequestCreateAuctionBehaviour extends Behaviour {
  @Override
  public void action() {
    // Create auction
    Auction auction = new Auction("fisk");
    Auctions auctions = Auctions.getInstance();

    // Request auction
    this.createAuction(auction);

    while(true){
        // Wait for status on created auction
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            if(msg.getContent().equals(Mediator.VALIDAUCTION)){
                System.out.println("Auction was created in RequestCreateAuctionBehaviour");
                auctions.store(auction);
            } else if(msg.getContent().equals(Mediator.INVALIDAUCTION)) {
                System.out.println("Auction could not be created in RequestCreateAuctionBehaviour");
            } else {
                System.out.println("Something strange has been passed in RequestCreateAuctionBehaviour :" + msg.getContent());
            }
            break;
        } else { 
            block(); 
        }
    }
  }

  private void createAuction(Auction auction){
    ACLMessage senderMessage = new ACLMessage(ACLMessage.REQUEST); 
    // Mediator is our receiver
    senderMessage.addReceiver(new AID("mediator", AID.ISLOCALNAME));
    senderMessage.setContent(auction.toString());
    myAgent.send(senderMessage);
  }

  @Override
  public boolean done() {
    return true;
  }
}
