// Agent bidder in project Auction2.mas2j



/* Initial beliefs and rules */


search_for(volvo, 120).
/* Initial goals */



!start.
!search_for(volvo, 120).


/* Plans */



+!start : true <- .print("hello world.").

+!search_for(Item, Max_price)
	<- .print("Sending search request to ", searcher, ": ", Item, ", ", Max_price); 
		.send(searcher, askOne, auction(ID, Item, Type, Max_price)).
		
+auction(ID, Item, Type, Price) : search_for(Item, MaxPrice) & Price < MaxPrice  <- .print(Price,MaxPrice).


