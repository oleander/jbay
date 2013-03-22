class AuctionObject {
    private int id;
    private String description;
    private String type;
    private int price;
    
    public AuctionObject(int id, String description, String type, int price){
        this.id = id;
        this.description = description;
        this.type = type;
        this.price = price;
    }
        
    
    public int getId(){
        return id;
    }
    
    public String getDescription(){
        return description;
    }
    
    public String getType(){
        return type;
    }
    
    public int getPrice(){
        return price    ;
    }
    
    @Override
    public String toString() {
        return "{id: " + id + ", description: " + description + ", type: " + type + ", price: " + price + "}";
    }
    
}