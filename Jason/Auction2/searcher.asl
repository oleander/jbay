// Agent searcher in project Auction2.mas2j



/* Initial beliefs and rules */


auction(0, volvo, car, 110).


/* Initial goals */



!start.



/* Plans */



+!start : true <- .print("hello world.").

+?auction(ID, Item, Type, Price) 
	<-	search_auctions(Item, Price);
		.send(bidder, tell, auction(volvo, Item, Type, 50));
		.print(Price).



