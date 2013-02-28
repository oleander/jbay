import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class AuctionEndedBehaviour extends WB {
    private Auction auction;

    public AuctionEndedBehaviour(Agent agent, Integer time, Auction auction) {
        super(agent, time);
        this.auction = auction;
    }
    
    private static final long serialVersionUID = 1L;

    public void handleElapsedTimeout(){
        ArrayList<Buyer> loosers = new ArrayList<Buyer>();
        Seller seller = this.auction.getSeller();
        Bid higestBid = this.auction.getHigestBid();
        Buyer winner = null;
        
        if(higestBid != null){
            this.notifyWinnerOfAuction(higestBid.getBidder());
        }

        for(Bid bid : this.auction.getBids()){
            Buyer looser = bid.getBidder();
            // We do not want to alert:
            // - A person that has already got the message
            // - The winner
            if(loosers.contains(looser) && ! looser.equals(winner)){
                this.notifyLoserOfAuction(looser);
                loosers.add(looser);
            }
        }

        // Notify seller about ended auction
        this.notifyAboutEndedAuction(seller);
    }

    private void packageAndSendTo(Agent agent, String status) {
        this.sendMessageTo(agent, new AuctionNotification(this.auction, status));
    }

    private void notifyWinnerOfAuction(Buyer winner){
        say("Sending notifyWinnerOfAuction to " + winner.getName());
        this.packageAndSendTo(winner, Mediator.WINNEROFAUCTION);
    }

    private void notifyLoserOfAuction(Buyer looser){
        say("Sending notifyLoserOfAuction to " + looser.getName());
        this.packageAndSendTo(looser, Mediator.LOOSEROFAUCTION);
    }

    private void notifyAboutEndedAuction(Seller seller){
        say("Sending notifyAboutEndedAuction to " + seller.getName());
        this.packageAndSendTo(seller, Mediator.AUCTIONHASENDED);
    }
}
