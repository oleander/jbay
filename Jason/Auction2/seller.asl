// Agent seller in project Auction2.mas2j



/* Initial beliefs and rules */



/* Initial goals */



!requestAuction(volvo, car, 100, 100).

/* Plans */

// Skapar en G hos mediator
+!requestAuction(Descr, Type, MinPrice, EndTime) 
	<- .print("Sending request to ", mediator, " ", Descr, Type, MinPrice, EndTime);
		.send(mediator, achieve, createAuction(Descr, Type, MinPrice, EndTime)).
		
