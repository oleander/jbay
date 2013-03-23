// Agent bidder in project Auction2.mas2j



/* Initial beliefs and rules */


searchFor(volvo, 120).
searchFor(cod, 12).
/* Initial goals */



!start.
!searchFor(volvo, 120).
!searchFor(cod, 8).


/* Plans */



+!start : true <- .print("hello world.").

//achieve
+!searchFor(Item, Max_price)
	<-  .wait(2000);
		.print("Sending search request to ", searcher, ": ", Item, ", ", Max_price); 
		.send(searcher, achieve, searchAuctions(Item, Max_price)).

		
+auction(Item, Type, Price2, EndTime): searchFor(Item, MaxPrice) & Price2 < MaxPrice <- .print("received auction!!",Item, Type, Price2, EndTime).




