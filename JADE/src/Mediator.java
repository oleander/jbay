import jade.core.Agent;

public class Mediator extends Agent {
  static final long serialVersionUID = 1;
  @Override
  protected void setup() { 
    System.out.println("Hello! Buyer-agent " + getAID().getName() + " is ready.");
  }
  @Override
  protected void takeDown(){
    System.out.println("Mediator " + getAID().getName() + " terminating.");
  }
}