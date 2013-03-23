// Agent searcher in project Auction2.mas2j

/* Initial beliefs and rules */

auction(0, volvo, car, 110).

/* Initial goals */

!start.

/* Plans */

+!start : true <- .print("hello world.").

+searchResult(auction(Item, Type, Price, EndTime, Id, MinPrice), OriginalRequester) 
<- 	.print("found auction", auction(Item, Type, Price, EndTime, MinPrice));
	.send(OriginalRequester, tell, auction(Item, Type, Price, EndTime, Id, MinPrice)).
	

+!searchAuctions(Item, Max_price)[source(S)] 
<-	.print("Received request from ", S, Item, Max_price);//;
	 searchAuctions(Item, Max_price, S).

//+?auction(ID, Item, Type, Price)[maxPrice(M)] 
//  <-  .print("dealing with search request").//;
     //searchAuctions(Item, 120).
    //.send(bidder, tell, auction(volvo, Item, Type, 50));


