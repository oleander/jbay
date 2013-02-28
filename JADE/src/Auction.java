import java.util.ArrayList;
import redis.clients.johm.*;
import jade.lang.acl.ACLMessage;
import java.io.Serializable;
import jade.lang.acl.ACLMessage;


/*
Struct class for auctions
*/
@Model
public class Auction implements Serializable {
    private String description;
	private int minPrice;
	private String type;
    private int endTime;
    private ArrayList<Bid> bids = new ArrayList<Bid>();

	public Auction(String description) {
        this.description = description;
        // TODO: Fix this. Should not be static
        this.minPrice = 1000;
        this.type = "This is a type";
        this.endTime = 1000;
    }
  
    public Auction(String description, int minPrice, String type, int endTime) {
        this.description = description;
        this.minPrice = minPrice;
        this.type = type;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Auction [description=" + description + ", minPrice=" + minPrice + ", type=" + type + "]";
    }

    public boolean isValid() {
        return true;
    }

    public String getDescription() {
        return this.description;
    }

    public String getType() {
        return this.type;
    }

    /*
     * @return Is the given bid the highest one?
     */
    public synchronized boolean makeBid(Bid bid) {
        Bid higestBid = this.getHigestBid();
        if(higestBid != null){
            if(higestBid.getAmount() > bid.getAmount()){
                // Bid is not valid, amount too low
                return false;
            }
        }

        return this.bids.add(bid);
    }

    /*
     * @return Higest bid for auction
     */
    public Bid getHigestBid(){
        Bid higestBid = null;
        for (Bid bid : this.bids) {
            if(higestBid != null && bid.getAmount() > higestBid.getAmount()){
                higestBid = bid;
            }
        }

        return higestBid;
    }

    /*
     * @return All bids for auction
     */
    public ArrayList<Bid> getBids(){
        return this.bids;
    }

    public int getEndTime(){
        return this.endTime;
    }

	public Seller getSeller() {
		// TODO Auto-generated method stub
		return null;
	}
}
