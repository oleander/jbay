import java.io.Serializable;

public class AuctionNotification implements Serializable {
    private Auction auction;
	private String status;

	public AuctionNotification(Auction auction, String status) {
        this.auction = auction;
        this.status = status;
    }

    public Auction getAuction(){
        return this.auction;
    }

    public String getStatus(){
        return this.status;
    }
}