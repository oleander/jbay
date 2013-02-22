import jade.core.Agent;

public class Mediator extends Agent {
	static final long serialVersionUID = 1;
	protected void setup() { 
		System.out.println("Hello! Buyer-agent " + getAID().getName() + " is ready.");
	}
}