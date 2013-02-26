
import jade.core.Agent;

public class Seller extends Agent {
	static final long serialVersionUID = 4;
	
	@Override
	protected void setup(){
		addBehaviour(new RequestCreateAuctionBehaviour());
	}
	
	@Override
	protected void takeDown(){
		    System.out.println("Mediator " + getAID().getName() + " terminating.");
	}
}
