/*
 * Encapsulates a message send over channel 
 */
public class Notification {
    private Object item;
	private String status;

	public Notification(Object item, String status) {
        this.item = item;
        this.status = status;
    }

    public Object getObject(){
        return this.item;
    }

    public String getStatus(){
        return this.status;
    }
}