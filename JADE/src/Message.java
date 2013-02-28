import jade.core.AID;

public interface Message{
    void execute(Object object, AID sender);
}