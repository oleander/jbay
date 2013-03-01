import java.io.Serializable;
import java.util.ArrayList;

import redis.clients.johm.Model;


/*
Struct class for auctions
*/
@Model
public class Auction implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id = -1;
    private String description;
    private int minPrice;
    private String type;
    private int endTime;
    private Seller seller;
    private Bid higestBid;
    private ArrayList<Bid> bids = new ArrayList<Bid>();

    public Auction(String description) {
        this.description = description;
        // TODO: Fix this. Should not be static
        this.minPrice = 1000;
        this.type = "This is a type";
        this.endTime = 1000;
    }
  
    public Auction(String description, int minPrice, String type, int endTime, Seller seller) {
        this.description = description;
        this.minPrice = minPrice;
        this.type = type;
        this.endTime = endTime;
        this.seller = seller;
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

        this.bids.add(bid);
        this.higestBid = bid;
        return true;
    }

    /*
     * @return Higest bid for auction
     */
    public Bid getHigestBid(){
        return this.higestBid;
    }

    public int getPrice(){
        Bid bid = this.getHigestBid();
        if(bid != null){
            return bid.getAmount();
        }

        return this.minPrice;
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
        return this.seller;
    }

    public void setId(int id) throws IllegalArgumentException {
        if(this.id != -1){
            throw new IllegalArgumentException("Id for auction has already been set: " + this.id);
        }

        this.id = id;
    }

    public int getId() throws Exception {
        if(this.id == -1){
            throw new Exception("Id has not been set yet, has it been stored?");
        }
        
        return this.id;
    }
}