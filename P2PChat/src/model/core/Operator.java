package model.core;

import java.util.ArrayList;
import java.util.List;

public class Operator {

	private boolean online;
	private String username;
	private String password;
	private long id;
	private Conversation currentConversation;
	private List<Conversation> activeChats;
	
	
	/**
	 * @param username
	 * @param password
	 * @param id
	 */
	public Operator(String username, String password, long id) {
		super();
		this.username = username;
		this.password = password;
		this.id = id;
		this.activeChats = new ArrayList<>();
	}

	/**
	 * @return the online
	 */
	public boolean isOnline() {
		return online;
	}

	/**
	 * @param online the online to set
	 */
	public void setOnline(boolean online) {
		this.online = online;
	}

	/**
	 * @return the currentConversation
	 */
	public Conversation getCurrentConversation() {
		return currentConversation;
	}

	/**
	 * @param currentConversation the currentConversation to set
	 */
	public void setCurrentConversation(Conversation currentConversation) {
		this.currentConversation = currentConversation;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the activeChats
	 */
	public List<Conversation> getActiveChats() {
		return activeChats;
	}
	
	public int getNumberOfActiveChats() {
		return this.activeChats.size();
	}
	
	public void addConversation(Conversation conversation) {
		this.activeChats.add(conversation);
	}
}
