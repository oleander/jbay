import java.util.Queue;

import jade.core.Agent;
import java.util.LinkedList;

public class Mediator extends Agent {
  public final static String VALIDAUCTION = "1";
  public final static String INVALIDAUCTION = "1";

  static final long serialVersionUID = 1;
  private Queue<Auction> auctionQueue;
  
  protected void setup() {
    this.auctionQueue = new LinkedList<Auction>();  
    addBehaviour(new CreateAuctionBehaviour());
  }
  
  @Override
  protected void takeDown(){
    System.out.println("Mediator " + getAID().getName() + " terminating.");
  }
  
  public Queue<Auction> getAuctionQueue() {
    return this.auctionQueue;
  }
}