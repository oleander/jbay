// Agent bidder in project Auction2.mas2j



/* Initial beliefs and rules */


search_for(volvo, 120).
/* Initial goals */



!start.
!search_for(volvo, 120).


/* Plans */



+!start : true <- .print("hello world.").

+!search_for(Item, Max_price)
	<- .wait(5000);
		.print("Sending search request to ", searcher, ": ", Item, ", ", Max_price); 
		.send(searcher, achieve, auction(ID, Item, Type, Price)[maxPrice(Max_price)]).
		
+auction(ID, Item, Type, Price2)[source(S)]   <- .print("received auction!!",Item, Price2, MaxPrice).


