/* Plans */
+confirmCreatedAuction(Id): true <- .print("Auction id: ", Id).

/*
 Notify seller about new bid
*/
+notifySellerAboutNewBid(AuctionId, CurrentHighestPrice, Bidder) <-
  .print(Bidder, " is highest bidder on ", AuctionId, " width ", CurrentHighestPrice).

// Skapar en G hos mediator
// Goal
+!requestAuction(Descr, Type, MinPrice, EndTime) <-
  .print("Sending request to ", mediator, " ", Descr, Type, MinPrice, EndTime);
  .send(mediator, achieve, createAuction(Descr, Type, MinPrice, EndTime)).
