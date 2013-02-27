import java.util.ArrayList;
import redis.clients.johm.*;
import jade.lang.acl.ACLMessage;
import java.io.Serializable;
import jade.lang.acl.ACLMessage;


/*
Struct class for auctions
*/
@Model
public class Auction implements Serializable {
    private String description;
	private int minPrice;
	private String type;

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

    @Override
    public String toString() {
        return "Auction [description=" + description + ", minPrice=" + minPrice + ", type=" + type + "]";
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
