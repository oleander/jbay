import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.core.AID;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
* Used by: Searcher
* Search for items in database
*/
public class ResponseSearchForItemBehaviour extends CB {
	private static final long serialVersionUID = 844913931022530879L;

	@Override
    public void action() {
        this.listen(Mediator.SEARCHFORAUCTION, new Message(){
            public void execute(ACLMessage message, Object object, AID seller, String id){
                if(message.getPerformative() == ACLMessage.REQUEST){
                    String term = (String) object;
                    say(seller.getName() + " is searching for " + term);
                    ArrayList<Auction> results = Auctions.getInstance().search(term);
                    say("We found " + results.size() + " in database");
                } else {
                    // TODO: Respond with ACLMessage.INVALID_REQUEST
                    say("Invalid request");
                }

            }
        });

        block(1000);
    }
}
