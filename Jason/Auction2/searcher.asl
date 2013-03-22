// Agent searcher in project Auction2.mas2j

/* Initial beliefs and rules */

//auction(0, volvo, car, 110).

/* Initial goals */

!start.

/* Plans */

+!start : true <- .print("hello world.").

+auction(ID, Item, Type, Price) <- .print("found auction", Price).

+?auction(ID, Item, Type, Price)[maxPrice(M)] 
  <-  .print("dealing with search request").//;
     //searchAuctions(Item, 120).
    //.send(bidder, tell, auction(volvo, Item, Type, 50));


