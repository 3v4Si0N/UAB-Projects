package model.core;

import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;

import model.db.OperatorManager;

@Singleton
public class ConversationManager {

	private static long lastConversationId = System.currentTimeMillis();
	private Hashtable <Long, Conversation> conversations;
	@EJB private OperatorManager operatorManager;
	
	public ConversationManager() {}
	
	@PostConstruct
	private void init() {
		this.conversations = new Hashtable<>();
	}
	
	/**
	* Creates a conversation.
	* @param username The user name specified in the user Login.
	* @return The id associated to the conversation.
	*/
	public long createConversation(String username) {
		long conversationId = generateConversationId();
		Operator opAvailable = operatorManager.getAvailableOperator();
		System.out.println("AVAILABLE OPERATOR --> " + opAvailable.getUsername());
		Conversation newConversation = new Conversation(conversationId, opAvailable.getId(), username);
		
		opAvailable.addConversation(newConversation);
		
		if(opAvailable.getActiveChats().size() == 1) {
			opAvailable.setCurrentConversation(newConversation);
		}
		
		this.conversations.put(conversationId, newConversation);
		System.out.println("CONVERSATION CREATED!!! with conversationID --> " + conversationId);
		return conversationId;
	}
	
	public Conversation getConversationById(long conversationId) {
		return conversations.get(conversationId);
	}
	
	public boolean containsConversationId(long id) {
		return this.conversations.containsKey(id);
	}
	
	private static synchronized long generateConversationId() {
		return lastConversationId++;
	}
}
