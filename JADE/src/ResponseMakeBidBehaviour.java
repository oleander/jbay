import java.io.IOException;
import java.io.Serializable;
import java.util.List;

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
                synchronized (auction) {
                	Bid formerHighestBid = auction.getHigestBid();
                	if(auction.makeBid(newBid)){
                    	notifySellerAboutNewBid(auction.getSeller(), auction);
                    	notifyNotHighestBidder(formerHighestBid.getBidder(), auction);            	
                    	
                    } else {
                        ACLMessage reply = msg.createReply();
                        reply.setContent(Mediator.INVALIDBID);
                        myAgent.send(reply);
                    }
                	
				}
                
                
            } catch (UnreadableException e) {
                e.printStackTrace();
            }
        } else {
            block();
        }
    }
    
    
    public void notifySellerAboutNewBid(Seller seller, Auction auction){
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
    

    
    public void notifyNotHighestBidder(Buyer bidder, Auction auction){
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
