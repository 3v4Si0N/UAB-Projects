package model.endPoint;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import javax.ejb.EJB;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import model.core.Conversation;
import model.core.ConversationManager;
import model.core.Message;
import model.core.Operator;
import model.db.OperatorManager;

@ServerEndpoint(value = "/chat/{channel}")
public class ChatServerEndpoint {

	private static ConcurrentHashMap<String,Session> channelSessionMapping = new ConcurrentHashMap<>();
	@EJB private ConversationManager conversationManager;
	@EJB private OperatorManager operatorManager;
	
	
	public ChatServerEndpoint() {}
	
	/**
	* This method is executed when a client send a message.
	* @param session the client session
	* @param message string to send
	 * @throws IOException 
	 * @throws EncodeException 
	*/
	@OnMessage
	public void handleMessage(String message, Session session) throws IOException, EncodeException {
		System.out.println("ONMESSAGE JAVA");
		String channel = session.getPathParameters().get("channel");
		Conversation conversation = null;
		Operator operator = null;
		boolean hasToBeSent = false;
		String username = "";
		String toChannel = "";
		Message messageToSend = null;
		
		
		if(this.doesChannelBelongToUser(channel)) {
			conversation = conversationManager.getConversationById(Long.valueOf(channel));
			if (conversation != null) {
				long operatorID = conversation.getOperatorID();
				username = conversation.getUserName();
				operator = operatorManager.getOperatorById(operatorID);
				System.out.println("MESSAGE SENT BY USER: " + username);
				
				if (operator.getCurrentConversation().getConversationID() == Long.valueOf(channel)) {
					hasToBeSent = true;
					toChannel = String.valueOf(operatorID);
				}
				
				messageToSend = new Message(message, username, "user");
				
			} else { System.out.println("NULL CONVERSATION"); }
		} else {
			operator = operatorManager.getOperatorById(Long.valueOf(channel));
			conversation = operator.getCurrentConversation();
			username = operator.getUsername();
			System.out.println("MESSAGE SENT BY OPERATOR: " + username);
			hasToBeSent = true;
			toChannel = String.valueOf(conversation.getConversationID());
			messageToSend = new Message(message, username, "operator");
		}
		
		
		if (messageToSend.getMessage().length() > 0) {
			conversation.addMessage(messageToSend);
			
			if (hasToBeSent) {
				Session sess = channelSessionMapping.get(toChannel);
				sess.getBasicRemote().sendText(
						String.valueOf(this.doesChannelBelongToUser(toChannel)) +
						":>" + 
						messageToSend.toString());
			} else {
				this.sendNotificationMessage(channel);
			}
		}
	}
	
	/**
	* This method is executed when a client opens a websocket session.
	* @param session the session opened by a client
	* @param channel the first path parameter of the websockets connection
	 * @throws EncodeException 
	 * @throws IOException 
	*/
	@OnOpen
	public void onOpen(Session session, @PathParam("channel") String channel) throws IOException, EncodeException {		// TODO creo que faltan cosas
		System.out.println("ONOPEN JAVA");
		channelSessionMapping.put(channel, session); // creator session add the session to the mapping
		
		if(doesChannelBelongToUser(channel)) {
			System.out.println("USER " + conversationManager.getConversationById(Long.valueOf(channel)).getUserName() + " HAS BEEN CONNECTED!!");
			sendRefreshMsgIfUserOpenedASession(channel);
		} else {
			Operator newOperator = operatorManager.getOperatorById(Long.valueOf(channel));
			newOperator.setOnline(true);
			System.out.println("OPERATOR " + newOperator.getUsername() + " HAS BEEN CONNECTED!!");
		}
	}
	
	@OnClose
	public void onClose(Session session, @PathParam("channel") String channel) {
		
	}
	
	/**
	* This method checks if a channel belongs to a user or an operator.
	* @param channel the client channel
	* @return belongToUser the final value
	*/
	private boolean doesChannelBelongToUser(String channel) {
		boolean belongToUser = false;
		
		if (conversationManager.containsConversationId(Long.valueOf(channel))) {
			belongToUser = true;
		}

		return belongToUser;
	}
	
	/**
	* This method is executed when a user opens a websocket session.
	* Refresh the operator active conversations.
	* @param fromChannel the first path parameter of the websockets connection
	 * @throws IOException 
	 * @throws EncodeException 
	*/
	private void sendRefreshMsgIfUserOpenedASession(String fromChannel) throws IOException, EncodeException {
		if (conversationManager.containsConversationId(Long.valueOf(fromChannel))) {
			Conversation conversation = conversationManager.getConversationById(Long.valueOf(fromChannel));
			long operatorID = conversation.getOperatorID();
			
			Session session = channelSessionMapping.get(Long.toString(operatorID));
			session.getBasicRemote().sendObject(new Message(Message.REFRESH, "", ""));
			System.out.println("REFRESH MESSAGE HAS BEEN SENT TO --> " + operatorManager.getOperatorById(operatorID).getUsername());
		}
	}
	
	private void sendNotificationMessage(String fromChannel) throws IOException, EncodeException {
		if (conversationManager.containsConversationId(Long.valueOf(fromChannel))) {
			Conversation conversation = conversationManager.getConversationById(Long.valueOf(fromChannel));
			long operatorID = conversation.getOperatorID();
			
			conversation.setNotifications(conversation.getNotifications()+1);
			
			Session session = channelSessionMapping.get(Long.toString(operatorID));
			session.getBasicRemote().sendObject(new Message(Message.NOTIFICATION, "", ""));
			System.out.println("NOTIFICATION MESSAGE HAS BEEN SENT TO --> " + operatorManager.getOperatorById(operatorID).getUsername());
		}
	}
}
