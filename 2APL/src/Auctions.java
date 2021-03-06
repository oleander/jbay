import java.util.ArrayList;

public class Auctions {
    private static Auctions instance;
    private ArrayList<Auction> auctions;

    public Auctions(){
        this.auctions = new ArrayList<Auction>();
    }

    public static Auctions getInstance(){
        if(instance == null){
          instance = new Auctions();
        }

        return instance;
    }
  
    /*
    * Store auction in database
    * @return Auction id
    */  
    public synchronized int store(Auction auction){
        this.auctions.add(auction);
        int id = this.auctions.size() - 1;
        auction.setId(id);
        return id;
    }
  
    /*
    * Find auction by it's unique id
    */
    public Auction findById(int id){
        return this.auctions.get(id);
    }


    /*
    * Delete user by id
    */
    public synchronized boolean deleteByid(int id){
        return this.auctions.remove(id) == null;
    }

    public ArrayList<Auction> getAll(){
        return this.auctions;
    }

    /*
    * Search auction by string
    */
    public ArrayList<Auction> search(String any) {
        ArrayList<Auction> foundAuctions = new ArrayList<Auction>();
        for(Auction auction : this.auctions){
            if(auction.getDescription().contains(any)){
                foundAuctions.add(auction);
            } else if (auction.getType().contains(any)){
                foundAuctions.add(auction);
            }
        }
        return foundAuctions;
    }
}