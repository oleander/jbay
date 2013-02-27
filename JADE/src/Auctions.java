import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.johm.JOhm;


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
    public int store(Auction auction){
        synchronized(this){
            this.auctions.add(auction);
            return this.auctions.size() - 1;
        }
    }
  
    /*
    * Find auction by it's unique id
    */
    public Auction findByid(int id){
        return this.auctions.get(id);
    }


    /*
    * Delete user by id
    */
    public boolean deleteByid(int id){
        return this.auctions.remove(id) == null;
    }

    /*
    * Search by string
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
