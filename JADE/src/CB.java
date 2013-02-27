import jade.core.behaviours.CyclicBehaviour;

public abstract class CB extends CyclicBehaviour {
    public void say(String message) {
        System.out.println(this.myAgent.getAID().getName() + ": " + message);
    }
}