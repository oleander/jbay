import jade.lang.acl.ACLMessage;

public interface Message{
    void execute(Object object, ACLMessage aclMessage);
}