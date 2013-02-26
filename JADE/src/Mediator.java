import java.util.Queue;

import jade.core.Agent;
import jade.util.leap.LinkedList;

public class Mediator extends Agent {
  static final long serialVersionUID = 1;
  private Queue<Auction> auctionQueue;
  
  protected void setup() {
	  System.out.println("mediator started");
	this.auctionQueue = (Queue<Auction>) new LinkedList();	
	addBehaviour(new CreateAuctionBehaviour());
	
  }
  
  @Override
  protected void takeDown(){
    System.out.println("Mediator " + getAID().getName() + " terminating.");
  }
  

public Queue<Auction> getAuctionQueue() {
	// TODO Auto-generated method stub
	return null;
}
}