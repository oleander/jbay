import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.event.ChangeListener; 
import javax.swing.event.ChangeEvent; 
/*
Struct class for auctions
*/
public class Auction implements Serializable, Runnable {
    private static final long serialVersionUID = 1L;
    private int id = -1;
    private String description;
    private int minPrice;
    private String type;
    private int endTime;
    private Bid higestBid;
    private boolean locked = false;
    private String seller;
    private ArrayList<Bid> bids = new ArrayList<Bid>();
  private ChangeListener changeListener;
  private Thread thread;
  
    public Auction(String description, int minPrice, String type, int endTime, String seller, ChangeListener changeListener) {
        this.description = description;
        this.minPrice = minPrice;
        this.type = type;
        this.endTime = endTime;
        this.seller = seller;
    this.changeListener = changeListener;
    thread = new Thread(this);
    thread.start();
    }

    public Auction(String description, int minPrice, int endTime, String seller, ChangeListener changeListener) {
        this(description, minPrice, "", endTime, seller, changeListener);
    }
  
  @Override
  public void run() {
    try {
      thread.sleep(endTime);
      changeListener.stateChanged(new ChangeEvent(this));
    } catch (InterruptedException ie) {
      ie.printStackTrace();
    }
    
  }

    @Override
    public String toString() {
    return new ESB("auction").insert(description, type,
      minPrice, endTime, id, getMinimumBidValue());
    
    //return toString(description, type, minPrice, endTime, id, getMinimumBidValue());
    }
  /*
  private String toString(Object... variables) {
    StringBuilder sb = new StringBuilder("auction(");
    for(Object variable : variables){
      sb.append(variable).append(",");
    }
    sb.setCharAt(sb.length() - 1, ')');
    return sb.toString();
  }*/

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

    public int getMinimumBidValue(){
        int value = this.getHigestBidPrice();
        if(value == 0){
            return this.getMinPrice();
        } else {
            return value;
        }
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
  
  public ArrayList<String> getLosersOfAuction() {
      ArrayList<String> losers = new ArrayList<String>();     
      for (Bid bid : bids) {
        String bidder = bid.getBidder();
        if( !(bidder.equals(getHigestBid().getBidder()) || losers.contains(bidder)) ) {
          losers.add(bidder);                   
        }       
      }
    return losers; 
  }
  
  public ArrayList<String> getBidders() {
    ArrayList<String> bidders = new ArrayList<String>();
    for (Bid bid : bids) {
      bidders.add(bid.getBidder());
    }
    return bidders;
  }

    public int getEndTime(){
        return this.endTime;
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

    public String getSeller(){
        return this.seller;
    }
}