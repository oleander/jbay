// Agent mediator in project Auction2.mas2j



/* Initial beliefs and rules */



/* Initial goals */





/* Plans */



// Kšr sina goals
+!create_auction(Descr, Type, Price)[source(S)] 
	<- .print("Received request from ", S,  ": ", Descr, Type, Price);
		add_auction(Descr, Type, Price).
	

