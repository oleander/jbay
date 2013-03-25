/* Plans */

+confirmCreatedBid(AuctionId, Price) <-
  .print("My bid on ", AuctionId, " was a success").

/*
 Notifyed about not highest bidder. Inc by 1
*/
+notifyNotHighestBidder(AuctionId, CurrentHighestPrice) : searchFor(Item, MaxPrice) & CurrentHighestPrice + 1 < MaxPrice <-
  .print("Trying to make a new bid on ", AuctionId, " with ", CurrentHighestPrice + 1);
  .send(mediator, achieve, makeBid(AuctionId, CurrentHighestPrice + 1)).

+!start : true <- .print("hello world.").

+!searchFor(Item, Max_price) <-  
  .random(N);
  .wait(N * 2000);
  .print("Sending search request to ", searcher, ": ", Item, ", ", Max_price); 
  .send(searcher, achieve, searchAuctions(Item, Max_price)).

+auctionWon(auction(Description, Type, MinPrice, EndTime, ID, MinimumBid))
	<- .print("Yay, I just won an auction: ", Description).
  
+auctionLost(auction(Description, Type, MinPrice, EndTime, ID, MinimumBid)) 
	 <- .print("Darn, just lost an auction: ", Description).
    
+auction(Item, Type, Price, EndTime, Id, MinPrice): searchFor(Item, MaxPrice) & MinPrice + 5 < MaxPrice <- 
  .print("Found auction", Id);
  .send(mediator, achieve, makeBid(Id, MinPrice + 5)).

/*
 We're not longer the highest bidder on AuctionId
 Trying to make a new bid, given that current price (CurrentHighestPrice) isn't to high
*/
+!tryMakeBid(AuctionId, CurrentHighestPrice) : searchFor(Item, MaxPrice) & CurrentHighestPrice + 5 < MaxPrice <-
  .print("Trying to make a new bid on ", AuctionId, " width ", CurrentHighestPrice + 5);
  .send(mediator, achieve, makeBid(AuctionId, CurrentHighestPrice + 5)).