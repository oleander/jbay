
import jade.core.AID;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.util.List;

public class SearchForItemBehaviour extends B {
    private static final long serialVersionUID = 1L;
    private String id = Helper.getUUID();
    private String item;
    private int maxPrice;
    private int step;
    
    public SearchForItemBehaviour(String item, int maxPrice){
        this.item = item;
        this.maxPrice = maxPrice;
    }

    @Override
    public void action() {
        switch(step){
        case 0:
             AID searcher = Catalog.getAgent("searching", myAgent);
            try {
                this.sendMessageTo(searcher, id , Mediator.SEARCHFORAUCTION, ACLMessage.REQUEST, this.item);
            } catch (IOException e) {
                e.printStackTrace();
            }
            say("Sending search query to Searcher");

            step = 1;
            break;
        case 1:
            this.listen(Mediator.SEARCHFORAUCTION, new Message(){
                public void execute(ACLMessage message, Object object, AID seller, String id){
                    List<Auction> auctions = (List<Auction>) object;
                    say("We received " + auctions.size() + " auctions");
                    step = 2;
                }
            });
            
            break;
        }

        block(1000);
    }

    @Override
    public boolean done() {
        return step == 2;
    }
}
