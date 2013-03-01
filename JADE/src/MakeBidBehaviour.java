import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;


public class MakeBidBehaviour extends B {
    private static final long serialVersionUID = -1288633840082856281L;

    private Auction auction;
    private int step = 0;
    private int interval;
    private int maxPrice;

    public MakeBidBehaviour(Auction auction, int maxPrice, int interval) {
        super();
        this.auction = auction;
        this.maxPrice = maxPrice;
        this.interval = interval;
    }
  
    @Override
    public void action() {
        switch(this.step){
        // Make a bid
        case 0:
            if(!this.shouldBid()){
                say("Auction price is too high");
                this.step = 5;
                break;
            }

            Bid bid = null;
            try {
                bid = new Bid(this.auction.getId(), this.getNextBidValue(), (Buyer) myAgent);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                say("Something went wrong: " + e.getMessage());
            }
            
            if(bid == null){
                say("Abort!");
                this.step = 2;
                break;
            }
            
            this.sendMessageTo("mediator", null , Mediator.MAKEBID, ACLMessage.PROPOSE, bid);
            this.step = 1;
            break;
        // Was the bid okay?
        case 1:
            this.listen(Mediator.MAKEBID, new Message(){
                public void execute(ACLMessage message, Object object, AID sender, String id){
                    Auction a = (Auction) object;
                    // This is not the correct auction
                    try {
                        if(a.getId() != auction.getId()){
                            return;
                        }
                    } catch (Exception e) {
                        say("Someting went wrong: " + e.getMessage());
                    }
                    
                    if(message.getPerformative() == ACLMessage.ACCEPT_PROPOSAL){
                         myAgent.addBehaviour(new ListenToNewBidsBuyerBehaviour(auction, maxPrice, interval));
                        say("Our bid was valid");
                    } else if(message.getPerformative() == ACLMessage.FAILURE){
                        say("Our bid was to low");
                        step = 2;
                    } else {
                        say("Invalid data from " + sender.getName());
                    }
                }
            });
            break;
        }

        block(1000);
    }
  
    @Override
    public boolean done() {
      return this.step == 2;
    }

    private int getNextBidValue(){
        return Math.max(this.auction.getHigestBidPrice() + this.interval, this.auction.getMinPrice());
    }

    private boolean shouldBid(){
        return this.getNextBidValue() < this.maxPrice;
    }

}
