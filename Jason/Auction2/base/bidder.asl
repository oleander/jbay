/* Plans */

+confirmCreatedBid(AuctionId) <-
  .print("My bid on ", AuctionId, " was a success").

/*
 Notifyed about not highest bidder.
*/
+notifyNotHighestBidder(AuctionId, CurrentHighestPrice) <-
  .print("Darn, someone overbid me on auction", AuctionId, " width ", CurrentHighestPrice);
  !tryMakeBid(AuctionId, CurrentHighestPrice).

+!start : true <- .print("hello world.").

+!searchFor(Item, Max_price) <-  
  .random(N);
  .wait(N * 2000);
  .print("Sending search request to ", searcher, ": ", Item, ", ", Max_price); 
  .send(searcher, achieve, searchAuctions(Item, Max_price)).

    
+auction(Item, Type, Price, EndTime, Id): searchFor(Item, MaxPrice) & Price + 5 < MaxPrice <- 
  .print("Found auction", Id);
  .send(mediator, achieve, makeBid(Id, Price + 5)).

/*
 We're not longer the highest bidder on AuctionId
 Trying to make a new bid, given that current price (CurrentHighestPrice) isn't to high
*/
+!tryMakeBid(AuctionId, CurrentHighestPrice) : searchFor(Item, MaxPrice) & CurrentHighestPrice + 5 < MaxPrice <-
  .print("Trying to make a new bid on ", AuctionId, " width ", CurrentHighestPrice + 5);
  .send(mediator, achieve, makeBid(AuctionId, CurrentHighestPrice + 5)).