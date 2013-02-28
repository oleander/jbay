import jade.lang.acl.ACLMessage;

import java.util.List;

public class SearchForItemBehaviour extends B {
    private static final long serialVersionUID = 1L;

    @Override
    public void action() {
        Notification notifiction = new Notification("fisk", Mediator.SEARCHFORAUCTION);
        this.sendMessageTo(this.myAgent, notifiction);
        this.addListeners(Mediator.SEARCHFORAUCTION, notifiction, new Message(){
            public void execute(Object object, ACLMessage sender){
                List<Auction> auctions = (List<Auction>) object;
                say("We received " + auctions.size() + " auctions");
            }
        });
        this.listen();
    }

    @Override
    public boolean done() {
        return true;
    }
}
