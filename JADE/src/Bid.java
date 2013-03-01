import java.io.Serializable;

public class Bid implements Serializable {
	private static final long serialVersionUID = 1L;
	private final int auctionID;
    private final int amount;
    private final Buyer bidder;
    
    public Bid(int auctionID, int amount, Buyer bidder) {
        this.auctionID = auctionID; 
        this.amount = amount;
        this.bidder = bidder;
    }

    public int getAuctionID() {
        return auctionID;
    }
    
    public int getAmount() {
        return amount;
    }

    public Buyer getBidder() {
        return bidder;
    }
}
