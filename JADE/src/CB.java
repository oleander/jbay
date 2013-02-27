import jade.core.behaviours.CyclicBehaviour;

public abstract class CB extends CyclicBehaviour {
    public void say(String message) {
        System.out.println(this.myAgent.getAID().getName() + ": " + message);
    }

    
    protected void sendMessageTo(Agent agent, Object item) {
        ACLMessage senderMessage = new ACLMessage(ACLMessage.REQUEST); 
        senderMessage.addReceiver(new AID(agent.getName(), AID.ISLOCALNAME));
        try {
            senderMessage.setContentObject((Serializable) item);
            myAgent.send(senderMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}