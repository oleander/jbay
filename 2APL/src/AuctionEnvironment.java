import java.util.LinkedList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import apapl.Environment;
import apapl.ExternalActionFailedException;
import apapl.data.APLFunction;
import apapl.data.APLIdent;
import apapl.data.APLList;
import apapl.data.APLNum;
import apapl.data.APLVar;
import apapl.data.Term;


public class AuctionEnvironment extends Environment implements ChangeListener {
    private final boolean log = true;
	private Auctions auctions = Auctions.getInstance();
    
    public static void main(String [] args) {
        System.out.println("AuctionEnvironment has been loaded");
    }
    
    /**
     * This method is automatically called whenever an agent enters the MAS.
     * @param agName the name of the agent that just registered
     */
    protected void addAgent(String agName) {
        log("env> agent " + agName + " has registered to the environment.");
        
        /* If we want to send information to a 2APL agent, we need to code this into special
         * objects. We can then send these objects to the agent so that he can parse them correctly.
         * All the objects extend the basic class "Term".
         *
         * We distinguish between the following objects:
        
         * APLNum     This is equal to int and is for example instantiated by: new APLNum(0)
         * APLIdent     Equal to String, instantiated by: new APLIdent("string")
         * APLList      Can be seen as a LinkedList and will be parsed as a Prolog list in 2APL
         *          See the constructor comments of this class for information on how to use it
         * APLFunction    Represents a function, where the arguments of the function again need to be
         *          Term objects. For example, the function: func(0) should be instantiated as
         *          new APLFunction("func", new APLNum(0))
         */
        APLIdent aplagName = new APLIdent(agName);
        APLFunction event = new APLFunction("name", aplagName);
        
        // If we throw an event, we always need to throw an APLFunction.
        throwEvent(event, agName);
        
        // note: we can also throw an event to all agents by letting out the last parameter: 
        // throwEvent(event);
    }

    /*
        Creates an auction
        Called by: Mediator
        Returns: An auction id
    */
    public Term createAuction(String mediator, APLIdent seller, APLIdent description, APLNum minPrice, APLNum endTime) throws ExternalActionFailedException {
        Auction auction = new Auction(description.getName(), minPrice.toInt(), endTime.toInt(), seller.toString(), this);
        APLNum id = new APLNum(this.auctions.store(auction));
        System.out.println("createAuction was called: " + id.toInt());
        return id;
    }

    /*
        @terms
            @query String Search query
            @maxPrice Integer
    */
    // public void searchAuctionHandler(List<Term> terms){
    //     if(terms.size() != 3){
    //         throw new IllegalArgumentException("Auction#searchAuctions takes 3 arguments");
    //     }

    //     String query = terms.get(0).toString();
    //     int maxPrice = Integer.parseInt(terms.get(1).toString());
    //     String agent = terms.get(2).toString();

    //     ArrayList<Auction> returnedAuctions = new ArrayList<Auction>();
    //     ArrayList<Auction> foundAuctions = this.auctions.search(query);

    //     for(Auction auction : foundAuctions) {
    //         if(auction.getHigestBidPrice() < maxPrice){
    //             returnedAuctions.add(auction);
    //         }
    //     }
        
    //     Auction auction = returnedAuctions.get(0);
        
    //     logger.info(("searchResult(" + auction + "," + agent +")"));
    //     addPercept("searcher", Literal.parseLiteral("searchResult(" + auction + "," + agent +")"));
    // }

    // public void makeBid(List<Term> terms) {
    //     int id = Integer.parseInt(terms.get(0).toString());
    //     int newPrice = Integer.parseInt(terms.get(1).toString());
    //     String bidder = terms.get(2).toString();
        
    //     Auction auction = auctions.findById(id);
    //     Bid bid = new Bid(id, newPrice, bidder);
    //     try {
    //         auction.makeBid(bid);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    @Override
    public void stateChanged(ChangeEvent e){
        Auction auction = (Auction) e.getSource();
        // TODO: Fix this
        System.out.println("Auction has ended");
        // logger.info("Highest : " + auction.getHigestBid());
        // /*String bidders = new ESB("[", "]").insert(auction.getBidders());
        // String perc = new ESB("auctionEnded").insert(auction, auction.getSeller(), bidders);
        // logger.info("state changed" + perc);        
        // addPercept("mediator", Literal.parseLiteral(perc));*/
        
        // addPercept("mediator",Literal.parseLiteral(new ESB("auctionEnded").
        //     insert(auction, auction.getSeller(), auction.getHigestBid().getBidder())));

        
        // for (String loser : auction.getLosersOfAuction()) {
        //     addPercept("mediator", Literal.parseLiteral(new ESB("auctionLost").insert(auction, loser)));            
        // }
    }
    private void log(String str) {
        if (log) System.out.println(str);
    }
}