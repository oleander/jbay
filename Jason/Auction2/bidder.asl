// Agent bidder in project Auction2.mas2j



/* Initial beliefs and rules */


searchFor(volvo, 120).
/* Initial goals */



!start.
!searchFor(volvo, 120).


/* Plans */



+!start : true <- .print("hello world.").

+!searchFor(Item, Max_price)
	<- .print("Sending search request to ", searcher, ": ", Item, ", ", Max_price); 
		.send(searcher, askOne, auction(ID, Item, Type, Price)[maxPrice(Max_price)]).
		
+auction(ID, Item, Type, Price2) : searchFor(Item, MaxPrice) & Price2 < MaxPrice  <- .print("received auction!!", Item, Price2, MaxPrice).


