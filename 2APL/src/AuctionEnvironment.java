import apapl.Environment;
import apapl.ExternalActionFailedException;
import apapl.data.APLFunction;
import apapl.data.APLIdent;
import apapl.data.APLNum;
import apapl.data.Term;


public class AuctionEnvironment extends Environment {
    private final boolean log = true;
    
    public static void main(String [] args) {
        this.log("AuctionEnvironment has been loaded");
    }
    
    /**
     * This method is automatically called whenever an agent enters the MAS.
     * @param agName the name of the agent that just registered
     */
    protected void addAgent(String agName) {
        log("env> agent " + agName + " has registered to the environment.");
        
        /* If we want to send information to a 2APL agent, we need to code this into special
         * objects. We can then send these objects to the agent so that he can parse them correctly.
         * All the objects extend the basic class "Term".
         *
         * We distinguish between the following objects:
        
         * APLNum     This is equal to int and is for example instantiated by: new APLNum(0)
         * APLIdent     Equal to String, instantiated by: new APLIdent("string")
         * APLList      Can be seen as a LinkedList and will be parsed as a Prolog list in 2APL
         *          See the constructor comments of this class for information on how to use it
         * APLFunction    Represents a function, where the arguments of the function again need to be
         *          Term objects. For example, the function: func(0) should be instantiated as
         *          new APLFunction("func", new APLNum(0))
         */
        APLIdent aplagName = new APLIdent(agName);
        APLFunction event = new APLFunction("name", aplagName);
        
        // If we throw an event, we always need to throw an APLFunction.
        throwEvent(event, agName);
        
        // note: we can also throw an event to all agents by letting out the last parameter: 
        // throwEvent(event);
    }
    
    /**
     * External actions of agents can be caught by defining methods that have a Term as return value.
     * This method can be called by a 2APL agents as follows: \@env(square(5), X).
     * X will now contain the return value, in this case 25.
     * @param agName The name of the agent that does the external action
     * @param num The num to calculate the square of, coded in an APLNum
     * @return The square of the input, coded in an APLNum
     */
    public Term square(String agName, APLNum aplNum) throws ExternalActionFailedException {
        int num = aplNum.toInt();
        
        log("env> agent " + agName + " requests the square of " + num + ".");
            
        try {
            return new APLNum(num*num);
        } catch (Exception e) {
            //exception handling
            System.err.println("env> external action square() of " + agName + " failed: " +e.getMessage());
            return null;
        }
    }  

    private void log(String str) {
        if (log) System.out.println(str);
    }
}