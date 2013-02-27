import java.util.ArrayList;

import redis.clients.johm.*;
import jade.lang.acl.ACLMessage;


/*
Struct class for auctions
*/
@Model
public class Auction {
    @Id
    private Integer id;
    @Attribute
    private String type;
    @Attribute
    private int minPrice;
    @Attribute
    private String description;
  
    public Auction(String description) {
        this.description = description;
        // TODO: Fix this. Should not be static
        this.minPrice = 1000;
        this.type = "This is a type";
    }
  
    public Auction(String description, int minPrice, String type) {
        this.description = description;
        this.minPrice = minPrice;
        this.type = type;
    }

    public boolean isValid() {
        return true;
    }

	public String getDescription() {
		return this.description;
	}

	public String getType() {
		return this.type;
	}
}