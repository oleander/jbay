import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;


public class AuctionEndedBehaviour extends WakerBehaviour {
    private Auction auction;

    public AuctionEndedBehaviour(Agent a, Integer i, Auction auction) {
        super(a, i);
        this.auction = auction;
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public void handleElapsedTimeout(){
        ArrayList<Buyer> loosers = new ArrayList<Buyer>();
        Seller seller = this.auction.getSeller();
        Bid higestBid = this.auction.getHigestBid();
        Buyer winner = null;
        
		if(higestBid != null){
            winner = higestBid.getBidder();
            this.packageAndSendTo(winner, Mediator.WINNEROFAUCTION);
        }

        for(Bid bid : this.auction.getBids()){
            Buyer looser = bid.getBidder();
            // We do not want to alert:
            // - A person that has already got the message
            // - The winner
            if(loosers.contains(looser) && ! looser.equals(winner)){
                this.packageAndSendTo(looser, Mediator.LOSEROFAUCTION);
                loosers.add(looser);
            }
        }

        // Notify seller about ended auction
        this.packageAndSendTo(seller, Mediator.AUCTIONHASENDED);
    }

    private void packageAndSendTo(Agent agent, String status) {
        this.sendMessageTo(agent, new AuctionNotification(this.auction, status));
    }

    private void sendMessageTo(Agent agent, Object item) {
        ACLMessage senderMessage = new ACLMessage(ACLMessage.REQUEST); 
        senderMessage.addReceiver(new AID(agent.getName(), AID.ISLOCALNAME));
        try {
            senderMessage.setContentObject((Serializable) item);
            myAgent.send(senderMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
