import jade.lang.acl.ACLMessage;


public class Auction {
	private String message;
	public Auction(String msg) {
		this.message = msg;
	}
	public boolean isValid() {
		return true;
	}
}
