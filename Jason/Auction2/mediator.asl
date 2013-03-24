// Agent mediator in project Auction2.mas2j

/* Initial beliefs and rules */

/* Initial goals */

/* Plans */
+confirmCreatedAuction(Id, Seller) <-
  .send(Seller, tell, confirmCreatedAuction(Id));
  -confirmCreatedAuction(Id, Seller).
  
/*
 Bid was successfully created on auction
 Sends message to:
   Bidder: Bid was a success
   Seller: New bid
   Other bidders: New higher bid arrived
*/

+confirmCreatedBid(AuctionId, Bidder, Price) <-
  .print("confirmCreatedBid : ", AuctionId, " ", Bidder);
  .send(Bidder, tell, confirmCreatedBid(AuctionId, Price));
  notifyEveryOneAboutNewBid(AuctionId, Bidder).
  
+auctionEnded(auction(Description, Type, MinPrice, EndTime, ID, MinimumBid), Seller, Winner) 
  <- .print("Auction ", Description, " has ended.");
  		.send(Seller, tell, auctionEnded(auction(Description, Type, MinPrice, EndTime, ID, MinimumBid), Winner));
		.send(Winner, tell, auctionWon(auction(Description, Type, MinPrice, EndTime, ID, MinimumBid))).
		
+auctionLost(auction(Description, Type, MinPrice, EndTime, ID, MinimumBid), Loser)
	<- .print(Loser, " lost an auction");
		.send(Loser, tell, auctionLost(auction(Description, Type, MinPrice, EndTime, ID, MinimumBid))).
  

+!createAuction(Descr, Type, MinPrice, EndTime)[source(S)] <- 
    .print("Received request from ", S,  ": ", Descr, Type, MinPrice, EndTime);
    addAuction(Descr, Type, MinPrice, EndTime, S);
    .print("Hej hej").

+!makeBid(Id, NewPrice)[source(Bidder)] <-
  .print(Bidder, " just bid ", NewPrice, " on auction ", Id);
  makeBid(Id, NewPrice, Bidder).