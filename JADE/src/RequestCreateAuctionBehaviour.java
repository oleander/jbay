import java.io.IOException;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;


/*
    Used by: Seller
    Use for requesting a new auction
*/
public class RequestCreateAuctionBehaviour extends B {
    @Override
    public void action() {
    	Auction auction = new Auction("Car");;
        this.sendMessageTo("mediator", new Notification(auction, Mediator.CREATENEWAUCTION));
    }

    @Override
    public boolean done() {
        return true;
    }
}
