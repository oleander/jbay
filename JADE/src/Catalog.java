import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;


public class Catalog {
	
	public static void register(String type, Agent agent){
		DFAgentDescription dfd = new DFAgentDescription();
	       dfd.setName(agent.getAID());
	       ServiceDescription sd = new ServiceDescription();
	       sd.setType("searching");
	       sd.setName("Search for auctions");
	       dfd.addServices(sd);
	       try {
	         DFService.register(agent, dfd);
	       } catch(FIPAException fe) {
	         fe.printStackTrace();
	       } 
	}
	
	public static AID getAgent(String type, Agent agent){
		DFAgentDescription template = new DFAgentDescription();
   	 ServiceDescription sd = new ServiceDescription();
   	 sd.setType("searching");
   	 template.addServices(sd);
   	 DFAgentDescription searcher = null;
   	
   	 try {
   	 searcher = DFService.search(agent, template)[0];
   	 } catch (FIPAException e1) {
   	 e1.printStackTrace();
   	 }
//   	
//   	 if(searcher == null){
//   	 say("No searcher found");
//   	 return;
//   	 }
	return searcher.getName();
	}

}
