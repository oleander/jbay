// Agent mediator in project Auction2.mas2j

/* Initial beliefs and rules */

/* Initial goals */

/* Plans */
+confirmCreatedAuction(Id, Seller) <-
  .send(Seller, tell, confirmCreatedAuction(Id)).
  
+auctionEnded(auction(description, type, minPrice, endTime), Seller) 
	<- .print("Auction ", Descr, " has ended.").

+!createAuction(Descr, Type, MinPrice, EndTime)[source(S)] <- 
    .print("Received request from ", S,  ": ", Descr, Type, MinPrice, EndTime);
    addAuction(Descr, Type, MinPrice, EndTime, S);
    .print("Hej hej").
