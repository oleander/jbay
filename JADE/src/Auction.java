import jade.lang.acl.ACLMessage;


public class Auction {
  private String description;
  private int minPrice;
  private String type;
  
  public Auction(String description) {
    this.description = description;
  }
  
  
  
  public Auction(String description, int minPrice, String type) {
    this.description = description;
    this.minPrice = minPrice;
    this.type = type;
  }



  public boolean isValid() {
    return true;
  }



  @Override
  public String toString() {
    return "Auction [description=" + description + ", minPrice=" + minPrice
        + ", type=" + type + "]";
  }
  
  
}
