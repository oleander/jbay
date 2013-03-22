import java.io.Serializable;

public class Bid implements Serializable {
	private static final long serialVersionUID = 1L;
	private final int auctionID;
    private final int amount;
    private final String bidder;
    
    public Bid(int auctionID, int amount, String bidder) {
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

    public String getBidder() {
        return bidder;
    }
}
