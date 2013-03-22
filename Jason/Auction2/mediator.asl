// Agent mediator in project Auction2.mas2j

/* Initial beliefs and rules */

/* Initial goals */

/* Plans */

+!createAuction(Descr, Type, MinPrice, EndTime)[source(S)] 
  <- .print("Received request from ", S,  ": ", Descr, Type, MinPrice, EndTime);
    addAuction(Descr, Type, MinPrice, EndTime, S).
