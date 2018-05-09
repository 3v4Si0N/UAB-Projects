package model.core;

public class Message {

	private String message;
	private String from;
	private String clientType;
	public static final String REFRESH = "refresh";
	public static final String NOTIFICATION = "notification";
	
	
	/**
	 * @param message
	 * @param from
	 */
	public Message(String message, String from, String clientType) {
		super();
		this.message = message;
		this.from = from;
		this.clientType = clientType;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}
	
	/**
	 * @return the clientType
	 */
	public String getClientType() {
		return clientType;
	}

	/**
	 * @param clientType the clientType to set
	 */
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public String toString() {
		return this.getFrom()+":>"+this.getMessage();
	}
}
