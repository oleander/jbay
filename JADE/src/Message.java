import jade.core.AID;
import jade.lang.acl.ACLMessage;

public interface Message{
    void execute(ACLMessage message, Object object, AID sender, String id);
}