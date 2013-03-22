import java.io.Serializable;
import java.util.ArrayList;

/*
Struct class for auctions
*/
public class Auction implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id = -1;
    private String description;
    private int minPrice;
    private String type;
    private int endTime;
    private Seller seller;
    private Bid higestBid;
    private boolean locked = false;
    private ArrayList<Bid> bids = new ArrayList<Bid>();
  
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
    public synchronized boolean makeBid(Bid bid) throws Exception {
        if(this.locked){
            throw new Exception("Auction is locked");
        }

        Bid higestBid = this.getHigestBid();
        if(higestBid != null){
            if(higestBid.getAmount() >= bid.getAmount()){
                // Bid is not valid, amount too low
                return false;
            }
        } 
        
        if(bid.getAmount() < this.minPrice){
            return false;
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

    public int getHigestBidPrice(){
        if(this.getHigestBid() != null){
            return this.getHigestBid().getAmount();
        }

        return 0;
    }

    public int getMinPrice(){
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

    public String getStringId() throws Exception {
        return this.getId() + "";
    }

    public void lock(){
        this.locked = true;
    }
}