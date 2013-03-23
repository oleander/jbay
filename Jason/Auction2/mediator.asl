// Agent mediator in project Auction2.mas2j

/* Initial beliefs and rules */

/* Initial goals */

/* Plans */
+confirmCreatedAuction(Id, Seller) <-
  .send(Seller, tell, confirmCreatedAuction(Id)).
  
/*
 Bid was successfully created on auction
 Sends message to:
   Bidder: Bid was a success
   Seller: New bid
   Other bidders: New higher bid arrived
*/
+confirmCreatedBid(AuctionId, Bidder) <-
  .print("confirmCreatedBid : ", AuctionId, " ", Bidder);
  .send(Bidder, tell, confirmCreatedBid(AuctionId));
  notifyEveryOneAboutNewBid(AuctionId, Bidder).

+!createAuction(Descr, Type, MinPrice, EndTime)[source(S)] <- 
    .print("Received request from ", S,  ": ", Descr, Type, MinPrice, EndTime);
    addAuction(Descr, Type, MinPrice, EndTime, S);
    .print("Hej hej").

+!makeBid(Id, NewPrice)[source(Bidder)] <-
  .print(Bidder, " just bid ", NewPrice, " on auction ", Id);
  makeBind(Id, NewPrice, Bidder).
