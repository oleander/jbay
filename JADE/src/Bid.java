import java.util.Date;


public class Bid {
	
	private final int auctionID;
	private final int amount;
	private final Buyer bidder;
	private final Date date;
	
	
	public Bid(int auctionID, int amount, Buyer bidder, Date date) {

		this.auctionID = auctionID;	
		this.amount = amount;
		this.bidder = bidder;
		this.date = date;
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
	public Date getDate() {
		return date;
	}
	
	
	

}
