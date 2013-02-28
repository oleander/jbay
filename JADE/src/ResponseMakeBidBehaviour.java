import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

/*
 * Used by: Mediator
 */
public class ResponseMakeBidBehaviour extends CB {
    @Override
    public void action() {
        this.addListeners(Mediator.CREATENEWAUCTION, new Message(){
            public void execute(Object object, ACLMessage sender){
                // Fetch new bid
                Bid newBid = (Bid) object;

                // What auction are we bidding on?
                Auction auction = Auctions.getInstance().findByid(newBid.getAuctionID());

                // We don't want anyone else to interact with our auction
                // The interaction should be atomic
                synchronized (auction) {
                    Bid formerHighestBid = auction.getHigestBid();
                    if(auction.makeBid(newBid)){
                        // Notify seller about new bid
                        notifySellerAboutNewBid(auction.getSeller(), auction);

                        // Notify previous higest bidder about not having the higest bid
                        notifyNotHighestBidder(formerHighestBid.getBidder(), auction);
                        
                    } else {
                        sender.setContent(Mediator.INVALIDBID);
                        myAgent.send(sender);
                    }
                }
            }
        });
        
        this.listen();
    }
    
    public void notifySellerAboutNewBid(Seller seller, Auction auction){
        this.sendMessageTo(seller, new Notification(auction, Mediator.SELLERNEWBID));
    }
    
    public void notifyNotHighestBidder(Buyer bidder, Auction auction){
        this.sendMessageTo(bidder, new Notification(auction, Mediator.NOTHIGHESTBIDDER));
    }
}