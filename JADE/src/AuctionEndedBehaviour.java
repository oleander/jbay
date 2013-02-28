import jade.core.Agent;

import java.util.ArrayList;

public class AuctionEndedBehaviour extends WB {
    private Auction auction;

    public AuctionEndedBehaviour(Agent agent, Auction auction) {
        super(agent, auction.getEndTime());
        this.auction = auction;
    }
    
    private static final long serialVersionUID = 1L;

    public void handleElapsedTimeout(){
        ArrayList<Buyer> losers = new ArrayList<Buyer>();
        Seller seller = this.auction.getSeller();
        Bid higestBid = this.auction.getHigestBid();
        Buyer winner = null;
        
        if(higestBid != null){
            this.notifyWinnerOfAuction(higestBid.getBidder());
        }

        for(Bid bid : this.auction.getBids()){
            Buyer loser = bid.getBidder();
            // We do not want to alert:
            // - A person that has already got the message
            // - The winner
            if(losers.contains(loser) && ! loser.equals(winner)){
                this.notifyLoserOfAuction(loser);
                losers.add(loser);
            }
        }

        // Notify seller about ended auction
        this.notifyAboutEndedAuction(seller);
    }

    private void packageAndSendTo(Agent agent, String status) {
        this.sendMessageTo(agent, new Notification(this.auction, status));
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
