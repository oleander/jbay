// Agent bidder in project Auction2.mas2j



/* Initial beliefs and rules */


searchFor(volvo, 120).
searchFor(cod, 12).
/* Initial goals */



!start.
!searchFor(volvo, 120).
!searchFor(cod, 8).


/* Plans */

+confirmCreatedBid(AuctionId) <-
  .print("My bid on ", AuctionId, " was a success").

/*
 Notifyed about not higest bidder.
*/
+notifyNotHighestBidder(AuctionId, CurrentHighestPrice) <-
  .print("Darn, someone overbid me on auction", AuctionId, " width ", CurrentHighestPrice).

+!start : true <- .print("hello world.").

//achieve
+!searchFor(Item, Max_price)
	<-  .wait(2000);
		.print("Sending search request to ", searcher, ": ", Item, ", ", Max_price); 
		.send(searcher, achieve, searchAuctions(Item, Max_price)).

		
+auction(Item, Type, Price, EndTime, Id): searchFor(Item, MaxPrice) & Price + 5 < MaxPrice <- 
  .print("Found auction", Id);
  .send(mediator, achieve, makeBid(Id, Price + 5)).




