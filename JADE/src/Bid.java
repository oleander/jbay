public class Bid {
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
