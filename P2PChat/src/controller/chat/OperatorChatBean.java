package controller.chat;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.core.Message;

@ManagedBean(name="operatorChat")
@SessionScoped
public class OperatorChatBean {

	private List<Message> history;
	private ConversationDetails currentConversation;
	private List<ConversationDetails> conversations;
	
	
	public OperatorChatBean() {
		this.conversations = new ArrayList<>();
	}
	
	/**
	 * @return the history
	 */
	public List<Message> getHistory() {
		return history;
	}

	/**
	 * @param history the history to set
	 */
	public void setHistory(List<Message> history) {
		this.history = history;
	}

	/**
	 * @return the currentConversation
	 */
	public ConversationDetails getCurrentConversation() {
		return currentConversation;
	}

	/**
	 * @param currentConversation the currentConversation to set
	 */
	public void setCurrentConversation(ConversationDetails currentConversation) {
		this.currentConversation = currentConversation;
	}

	/**
	 * @return the conversations
	 */
	public List<ConversationDetails> getConversations() {
		return conversations;
	}

	/**
	 * @param conversations the conversations to set
	 */
	public void setConversations(List<ConversationDetails> conversations) {
		this.conversations = conversations;
	}


	
	public static class ConversationDetails {
		
		private long conversationId;
		private String user;
		private String conversationLinkStyle;
		private int notifications;
		private static final String UNSELECTED_CHAT_STYLE = "background-color: white;";
		private static final String SELECTED_CHAT_STYLE = "font-weight: bold;";
	
		
		/**
		 * @param conversationId
		 * @param user
		 * @param conversationLinkStyle
		 */
		public ConversationDetails(long conversationId, String user, String conversationLinkStyle) {
			super();
			this.conversationId = conversationId;
			this.user = user;
			this.conversationLinkStyle = conversationLinkStyle;
		}

		/**
		* Builds a ConversationDetails object setting the css style to
		* unselected_chat_style.
		* @param conversationId The id of the conversation
		* @param user The user who created the conversation
		* @return a ConversationDetails object.
		*/
		public static ConversationDetails getUnselectedConversationDetailsInstance(long conversationId, String user) {
			return new ConversationDetails(conversationId, user, UNSELECTED_CHAT_STYLE);
		}
		
		/**
		* Builds a ConversationDetails object setting the css style to *
		* selected_chat_style
		* @param conversationId The id of the conversation
		* @param user The user who created the conversation
		* @return a ConversationDetails object.
		*/
		public static ConversationDetails getSelectedConversationDetailsInstance(long conversationId, String user) {
			return new ConversationDetails(conversationId, user, SELECTED_CHAT_STYLE);
		}

		/**
		 * @return the conversationId
		 */
		public long getConversationId() {
			return conversationId;
		}

		/**
		 * @return the user
		 */
		public String getUser() {
			return user;
		}

		/**
		 * @return the conversationLinkStyle
		 */
		public String getConversationLinkStyle() {
			return conversationLinkStyle;
		}
		
		public void switchLinkStyle() {
			if (this.conversationLinkStyle.equals(SELECTED_CHAT_STYLE)) {
				this.conversationLinkStyle = UNSELECTED_CHAT_STYLE;
			} else {
				this.conversationLinkStyle = SELECTED_CHAT_STYLE;
			}
		}
		
		/**
		 * @return the notifications
		 */
		public int getNotifications() {
			return notifications;
		}

		/**
		 * @param notifications the notifications to set
		 */
		public void setNotifications(int notifications) {
			this.notifications = notifications;
		}
	}
}