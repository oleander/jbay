import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

/*
  Used by: Mediator
*/
public class CreateAuctionBehaviour extends Behaviour {
  @Override
  public void action() {
    // Fetch message
    ACLMessage msg = myAgent.receive();
    Auctions auctions = Auctions.getInstance();

    if (msg != null) {
      // Channel for contact seller
      ACLMessage sellerChannel = msg.createReply();
    
      // Create auction
      Auction auction = new Auction(msg.getContent());
      
      // Is auction valid?
      if(auction.isValid()){
        sellerChannel.setContent(Mediator.VALIDAUCTION);
      } else {
        sellerChannel.setContent(Mediator.INVALIDAUCTION);
      }

      myAgent.send(sellerChannel);
      
      ((Mediator) myAgent).getAuctions().store(auction);
    } else { 
      block(); 
    } 
  }

  @Override
  public boolean done() {
    return false;
  }
}