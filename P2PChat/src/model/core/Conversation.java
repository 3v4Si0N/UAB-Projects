package model.core;

import java.util.ArrayList;
import java.util.List;

public class Conversation {

	private long conversationID;
	private long operatorID;
	private String userName;
	private List<Message> messages;
	private int notifications;
	
	/**
	 * @param conversationID
	 * @param operatorID
	 * @param userName
	 */
	public Conversation(long conversationID, long operatorID, String userName) {
		super();
		this.conversationID = conversationID;
		this.operatorID = operatorID;
		this.userName = userName;
		this.messages = new ArrayList<>();
	}
	/**
	 * @return the conversationID
	 */
	public long getConversationID() {
		return conversationID;
	}
	/**
	 * @return the operatorID
	 */
	public long getOperatorID() {
		return operatorID;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	
	public void addMessage(Message message) {
		this.messages.add(message);
	}
	
	/**
	 * @return the messages
	 */
	public List<Message> getMessages() {
		return messages;
	}
	
	/**
	 * @return the notifications
	 */
	public int getNotifications() {
		return notifications;
	}
	
	/**
	 * @param notification the notification to set
	 */
	public void setNotifications(int notifications) {
		this.notifications = notifications;
	}
	
	public String toString() {
		return "";
	}
}
