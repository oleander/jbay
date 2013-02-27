import java.io.IOException;
import java.io.Serializable;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;


public class ResponseMakeBidBehaviour extends CyclicBehaviour {
    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            try {
                Bid newBid = (Bid) msg.getContentObject();
                Auction auction = Auctions.getInstance().findByid(newBid.getAuctionID());
                if(auction.makeBid(newBid)){
                	
                } else {
                    ACLMessage reply = msg.createReply();
                }
            } catch (UnreadableException e) {
                e.printStackTrace();
            }
        } else {
            block();
        }
    }
    
    
    public void notifySellerAboutNewBid(Seller seller, Auction auction, Bid bid){
    	ACLMessage notification = new ACLMessage(ACLMessage.INFORM);
    	notification.addReceiver(new AID(seller.getName(), AID.ISLOCALNAME));
//    	notification.setContent("Dear " + seller.getName() + "! A new bid has been made to" +
//    			" your auction " + auction.getDescription() + ". The highest bid is now " + 
//    			auction.getHigestBid());
    	try {
			notification.setContentObject((Serializable) new AuctionNotification(auction, Mediator.SELLERNEWBID));
			myAgent.send(notification);
		} catch (IOException e) {
			e.printStackTrace();
		}

    }
    
    public void notifyNotHighestBidder(Buyer bidder, Auction auction, Bid bid){
    	ACLMessage notification = new ACLMessage(ACLMessage.INFORM);
    	notification.addReceiver(new AID(bidder.getName(), AID.ISLOCALNAME));
    	try {
    		notification.setContentObject((Serializable) new AuctionNotification(auction, Mediator.NOTHIGHESTBIDDER));
			myAgent.send(notification);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
