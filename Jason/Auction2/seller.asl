// Agent seller in project Auction2.mas2j



/* Initial beliefs and rules */


/* Initial goals */


!requestAuction(volvo, car, 100, 100).
!requestAuction(cod, fish, 10, 123).

/* Plans */
+confirmCreatedAuction(Id): true <- .print("Auction id: ", Id).

/*
 Notify seller about new bid
*/
+newBid(AuctionId, CurrentHighestPrice, Bidder) <-
  .print(Bidder, " is highest bidder on ", AuctionId, " width ", CurrentHighestPrice).

// Skapar en G hos mediator
// Goal
+!requestAuction(Descr, Type, MinPrice, EndTime) <-
  .print("Sending request to ", mediator, " ", Descr, Type, MinPrice, EndTime);
  .send(mediator, achieve, createAuction(Descr, Type, MinPrice, EndTime)).
