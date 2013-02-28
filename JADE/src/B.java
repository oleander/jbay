import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;


public abstract class B extends Behaviour {
    private static final long serialVersionUID = 1L;
    
    protected void sendMessageTo(Agent agent, String id, String type, int acl, Object content) {
        try {
            this.sendMessageTo(agent.getAID(), id, type, acl, content);
        } catch (IOException e) {
            say("Something went wrong: " + e.getMessage());
        }
    }
    
    protected void sendMessageTo(String name, String id, String type, int acl, Object content) {
        try {
            sendMessageTo(new AID(name, AID.ISLOCALNAME), id, type, acl, content);
        } catch (IOException e) {
            say("Something went wrong: " + e.getMessage());
        }
    }

    protected void sendMessageTo(AID aid, String id, String type, int acl, Object content) throws IOException {
        ACLMessage senderMessage = new ACLMessage(acl); 
        senderMessage.addReceiver(aid);
        senderMessage.setConversationId(type);
        senderMessage.setContentObject((Serializable) content);
        senderMessage.setReplyWith(id);
        myAgent.send(senderMessage);
    }

    protected void listen(String id, String type, int times, Message message) {
        for (int i = 0; i < times; i++) {
            this.listen(id, type, message);
        }
    }

    protected void listen(String type, Message message) {
        MessageTemplate mt = MessageTemplate.MatchConversationId(type);
        this.request(mt, message);
    }

    protected void listen(String id, String type, Message message) {
        MessageTemplate mt = MessageTemplate.and(
            MessageTemplate.MatchConversationId(type), 
            MessageTemplate.MatchInReplyTo(id)
        );

        this.request(mt, message);
    }

    private void request(MessageTemplate mt, Message message){
        ACLMessage reply;
        reply = myAgent.receive(mt);
        if (reply != null) { 
            try {
                message.execute(reply, reply.getContentObject(), reply.getSender(), reply.getConversationId());
            } catch (UnreadableException e) {
                say("Something went wrong: " + e.getMessage());
            }
        }
    }
    
    protected void say(String message) {
        System.out.println(this.myAgent.getAID().getName() + ": " + message);
    }
}
