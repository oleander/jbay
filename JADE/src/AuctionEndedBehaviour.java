import jade.core.Agent;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;

public class AuctionEndedBehaviour extends WB {
    private Auction auction;

    public AuctionEndedBehaviour(Agent agent, Auction auction) {
        super(agent, auction.getEndTime());
        this.auction = auction;
    }
    
    private static final long serialVersionUID = 1L;

    public void handleElapsedTimeout(){
        ArrayList<String> losers = new ArrayList<String>();
        Seller seller = this.auction.getSeller();
        Bid higestBid = this.auction.getHigestBid();
        Buyer winner = higestBid.getBidder();
        
        if(higestBid != null){
            this.notifyWinnerOfAuction(winner);
        }

        for(Bid bid : this.auction.getBids()){
            Buyer loser = bid.getBidder();
            // We do not want to alert:
            // - A person that has already got the message
            // - The winner
            if(!losers.contains(loser.getName()) && ! loser.getName().equals(winner.getName())){
                this.notifyLoserOfAuction(loser);
            }

            losers.add(loser.getName());
        }

        // Notify seller about ended auction
        this.notifyAboutEndedAuction(seller);
    }

    private void packageAndSendTo(Agent agent, String status) {
        this.sendMessageTo(agent, null, status, ACLMessage.INFORM, this.auction);
    }

    private void notifyWinnerOfAuction(Buyer winner){
        say("Sending notifyWinnerOfAuction to " + winner.getName());
        this.packageAndSendTo(winner, Mediator.WINNEROFAUCTION);
    }

    private void notifyLoserOfAuction(Buyer loser){
        say("Sending notifyLoserOfAuction to " + loser.getName());
        this.packageAndSendTo(loser, Mediator.LOSEROFAUCTION);
    }

    private void notifyAboutEndedAuction(Seller seller){
        say("Sending notifyAboutEndedAuction to " + seller.getName());
        this.packageAndSendTo(seller, Mediator.AUCTIONHASENDED);
    }
}
