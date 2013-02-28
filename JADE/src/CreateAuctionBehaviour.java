import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

/*
  Used by: Mediator
*/
public class CreateAuctionBehaviour extends Behaviour {
	private static final long serialVersionUID = 1L;

@Override
  public void action() {
    this.doStuff();

    // VÄnta på svar ...
  }

  @Override
  public boolean done() {
    return false;
  }

  private void doStuff(){
      // Fetch message
    ACLMessage msg = myAgent.receive();

    if (msg != null) {
        // Channel for contact seller
        ACLMessage sellerChannel = msg.createReply();

        // Create auction
        Auction auction;
        try {
            auction = (Auction) msg.getContentObject();
             // Is auction valid?
             if(auction.isValid()){
                sellerChannel.setContent(Mediator.VALIDAUCTION);
                Mediator mediator = ((Mediator) myAgent);
                // Store auction for later use
                ((Mediator) myAgent).getAuctions().store(auction);

                mediator.addBehaviour(new AuctionEndedBehaviour(mediator, auction));
                
              } else {
                sellerChannel.setContent(Mediator.INVALIDAUCTION);
              }
        } catch (UnreadableException e) {
            sellerChannel.setContent(Mediator.INVALIDAUCTION);
            e.printStackTrace();
        }
        // Answer seller
        myAgent.send(sellerChannel);
    } else { 
        block(); 
    }
  }
}