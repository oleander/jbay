import java.util.LinkedList;
import java.util.Queue;

import jade.core.Agent;

public class Mediator extends Agent {
  public final static String VALIDAUCTION     = "1";
  public final static String INVALIDAUCTION   = "2";
  public final static String WINNEROFAUCTION  = "3";
  public final static String AUCTIONHASENDED  = "4";
  public final static String LOSEROFAUCTION   = "5";
  public final static String SELLERNEWBID     = "6";
  public final static String NOTHIGHESTBIDDER = "7";
  public final static String INVALIDBID       = "8";
  public final static String CREATENEWAUCTION = "9";

  static final long serialVersionUID = 1;
  private Queue<Auction> auctionQueue;
  
  protected void setup() {
    this.auctionQueue = new LinkedList<Auction>();  

    // We can now create new auctions
    this.addBehaviour(new CreateAuctionBehaviour());

    // Handles new bids
    this.addBehaviour(new ResponseMakeBidBehaviour());
  }
  
  @Override
  protected void takeDown(){
    System.out.println("Mediator " + getAID().getName() + " terminating.");
  }
  
  public Auctions getAuctions() {
    return Auctions.getInstance();
  }
}