import java.io.Serializable;
import java.util.UUID;

/*
 * Encapsulates a message send over channel 
 */
public class Notification implements Serializable {
    private Object item;
	private String status;
    private String id;

	public Notification(Object item, String status) {
        this.item = item;
        this.status = status;
        this.id = UUID.randomUUID().toString();
    }

    public Object getObject(){
        return this.item;
    }

    public String getStatus(){
        return this.status;
    }

    public String getUniqueId(){
        return this.id;
    }

    public void setUniqueId(String id){
        this.id = id;
    }
}