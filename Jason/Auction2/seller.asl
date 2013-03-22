// Agent seller in project Auction2.mas2j



/* Initial beliefs and rules */



/* Initial goals */



!request_auction(volvo, car, 100).


/* Plans */





+new_item(Descr, Type, Price) <- !request_auction(Descr, Type, Price). 

// Skapar en G hos mediator
+!request_auction(Descr, Type, Price) 
	<- .print("Sending request to ", mediator, " ", Descr, Type,  Price);
		.send(mediator, achieve, create_auction(Descr, Type, Price)).
		
