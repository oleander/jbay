import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class ResponseMakeBidBehaviour extends CyclicBehaviour {
    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            try {
                Bid newBid = (Bid) msg.getContentObject();
                if(newBid.getAmount() > 10){
                    // TODO: Add logic
                } else {
                    ACLMessage reply = msg.createReply();
                }
            } catch (UnreadableException e) {
                e.printStackTrace();
            }
        } else {
            block();
        }
    }
}
