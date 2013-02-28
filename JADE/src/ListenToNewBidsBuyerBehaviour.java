import java.util.Date;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;


public class ListenToNewBidsBuyerBehaviour extends CB {
	
	private int bidInc;

	@Override
	public void action() {
		this.listen(Mediator.NEWBID, new Message(){
            public void execute(ACLMessage message, Object object, AID sender, String id){
                Auction auction = (Auction) object;
                say("Someone made a new bid on " + auction.toString());
                Buyer buyerAgent = (Buyer) myAgent;
                int highestBid = auction.getHigestBid().getAmount();
                
                if(highestBid < buyerAgent.getMaxBid() - bidInc){
                	int newBidAmount = highestBid + bidInc;
                	buyerAgent.addBehaviour(new MakeBidBehaviour(auction, new Bid(
                			0, newBidAmount, buyerAgent, new Date(System.currentTimeMillis()))));
                } else {
                	System.out.println(buyerAgent.getLocalName() + " admits defeat");
                }
            }
        });


	}


}
