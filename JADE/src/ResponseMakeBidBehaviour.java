import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;


public class ResponseMakeBidBehaviour extends CyclicBehaviour {

	@Override
	public void action() {
		ACLMessage msg = myAgent.receive();
		
		if (msg != null) {
			
			
		} else {
			block();
		}
		

	}



}
