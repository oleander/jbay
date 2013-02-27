import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.sun.tools.internal.jxc.gen.config.Config;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.johm.JOhm;


public class Auctions {
  public Auctions() {
    // Use redis server on localhost
    JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "localhost");
    JOhm.setPool(jedisPool);
  }
  
  /*
   * Store auction in database
   */  
  public void store(Auction auction){
    JOhm.save(auction);
  }
  
  /*
   * Find auction by it's unique id
   */
  public Auction findByid(int id){
    return JOhm.get(Auction.class, id);
  }


  /*
  * Delete user by id
  */
  public boolean deleteByid(int id){
    return JOhm.delete(Auction.class, id);
  }

  /*
   * Search by string
   */
  public ArrayList<Auction> search(String any) {
    ArrayList<Auction> auctions = new ArrayList<Auction>();
    Set<Auction> currentAuctions = JOhm.getAll(Auction.class);
    
    for(Auction auction : currentAuctions){
        if(auction.getDescription().contains(any)){
            auctions.add(auction);
        } else if (auction.getType().contains(any)){
            auctions.add(auction);
        }
    }
    return auctions;
  }
}
