// Environment code for project Auction2.mas2j

import jason.asSyntax.*;
import jason.environment.*;
import java.util.Map.Entry;
import java.util.logging.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.*;
import java.util.LinkedList;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent; 

public class Auction2 extends Environment implements ChangeListener {
    private Logger logger = Logger.getLogger("Auction2.mas2j." + Auction2.class.getName());
    private HashMap<Integer, AuctionObject> map = new HashMap<Integer, AuctionObject>();
    private Auctions auctions = null;

    /* 
        Called before the MAS execution with the args informed in .mas2j
    */
    @Override
    public void init(String[] args) {
        this.auctions = Auctions.getInstance();
    }

    /*
        @terms
            @description String
            @minPrice Integer
            @type String
            @endTime Integer
            @seller String
        @return Integer Unique id for auction
    */
    public void addAuctionHander(List<Term> terms){
        if(terms.size() != 5){
            throw new IllegalArgumentException("Auction#addAuction takes 5 arguments");
        }

        String description = terms.get(0).toString();
        String type        = terms.get(1).toString();
        int minPrice       = Integer.parseInt(terms.get(2).toString());
        int endTime        = Integer.parseInt(terms.get(3).toString());
        String seller      = terms.get(4).toString();

        Auction auction = new Auction(description, minPrice, type, endTime, seller, this);
        int id = this.auctions.store(auction);
        addPercept("mediator", Literal.parseLiteral(new ESB("confirmCreatedAuction").insert(id, seller)));
    }
	
	@Override
	public void stateChanged(ChangeEvent e){
		Auction auction = (Auction) e.getSource();
		logger.info("Highest : " + auction.getHigestBid());
		/*String bidders = new ESB("[", "]").insert(auction.getBidders());
		String perc = new ESB("auctionEnded").insert(auction, auction.getSeller(), bidders);
		logger.info("state changed" + perc);		
		addPercept("mediator", Literal.parseLiteral(perc));*/
		
		addPercept("mediator",Literal.parseLiteral(new ESB("auctionEnded").
			insert(auction, auction.getSeller(), auction.getHigestBid().getBidder())));

		
		for (String loser : auction.getLosersOfAuction()) {
			addPercept("mediator", Literal.parseLiteral(new ESB("auctionLost").insert(auction, loser)));			
		}
	}
	
	
    
    /*
        @terms
            @query String Search query
            @maxPrice Integer
    */
    public void searchAuctionHandler(List<Term> terms){
        if(terms.size() != 3){
            throw new IllegalArgumentException("Auction#searchAuctions takes 3 arguments");
        }

        String query = terms.get(0).toString();
        int maxPrice = Integer.parseInt(terms.get(1).toString());
		String agent = terms.get(2).toString();

        ArrayList<Auction> returnedAuctions = new ArrayList<Auction>();
        ArrayList<Auction> foundAuctions = this.auctions.search(query);

        for(Auction auction : foundAuctions) {
            if(auction.getHigestBidPrice() < maxPrice){
                returnedAuctions.add(auction);
            }
        }
		
		Auction auction = returnedAuctions.get(0);
		
		logger.info(("searchResult(" + auction + "," + agent +")"));
        addPercept("searcher", Literal.parseLiteral("searchResult(" + auction + "," + agent +")"));
    }
	
	public void makeBid(List<Term> terms) {
		int id = Integer.parseInt(terms.get(0).toString());
		int newPrice = Integer.parseInt(terms.get(1).toString());
		String bidder = terms.get(2).toString();
		
		Auction auction = auctions.findById(id);
		Bid bid = new Bid(id, newPrice, bidder);
		try {
			auction.makeBid(bid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    @Override

    public boolean executeAction(String agName, Structure action) {
        List<Term> terms = action.getTerms();
        switch(action.getFunctor()){
            case "addAuction":
                this.addAuctionHander(terms); break;
            case "searchAuctions":
                searchAuctionHandler(terms); 
                break;
			case "makeBid":
				makeBid(terms);
				break;
            default:
                logger.info("executing: "+action+", but not implemented!"); break;
        }

        return true;
    }

    /** Called before the end of MAS execution */

    @Override

    public void stop() {
        super.stop();
    }
    
    public void delay(long milis) {
        try {
              Thread.sleep(milis); 
        } catch (Exception e) {
            e.printStackTrace();  
        }
    }

}


