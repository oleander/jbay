import jade.core.AID;
import jade.lang.acl.ACLMessage;


public class ListenToNewBidsBuyerBehaviour extends CB { 
	private static final long serialVersionUID = 1L;
	private Auction auction;
    private int maxPrice;
    private int interval;
    
    public ListenToNewBidsBuyerBehaviour(Auction auction, int maxPrice,
          int interval) {
          super();
          this.auction = auction;
          this.maxPrice = maxPrice;
          this.interval = interval;
    }

    @Override
    public void action() {
        this.listen(Mediator.NEWBID, new Message(){
            public void execute(ACLMessage message, Object object, AID sender, String id){
                Auction auction = (Auction) object;
                say("Someone made a new bid on " + auction.toString());
                Buyer buyerAgent = (Buyer) myAgent;
                int highestBid = auction.getHigestBid().getAmount();
                if(highestBid + interval < buyerAgent.getMaxPrice(auction.getType())){
                    try {
                      buyerAgent.addBehaviour(new MakeBidBehaviour(auction, maxPrice, interval));
                    } catch (Exception e) {
                        say("Something went wrong: " + e.getMessage());
                    }
                } else {
                    say(buyerAgent.getLocalName() + " admits defeat");
                }
            }
        });

        block(1000);
    }
}