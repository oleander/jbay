import jade.core.behaviours.Behaviour;


public class NotifySellerAboutNewBidBehaviour extends Behaviour {

  private Mediator mediator;
  
  public NotifySellerAboutNewBidBehaviour(Mediator mediator) {
    this.mediator = mediator;
  }

  @Override
  public void action() {
    // Find seller 
    
  }

  @Override
  public boolean done() {
    // TODO Auto-generated method stub
    return false;
  }

}
