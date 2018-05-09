package controller.chat;

import model.db.*;
import model.core.*;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import controller.chat.OperatorChatBean.ConversationDetails;
import controller.login.*;

@ManagedBean(name="operatorChatC")
public class OperatorChatController {
	
	@EJB private OperatorManager operatorManager;
	@EJB private ConversationManager conversationsManager;
	@ManagedProperty(value="#{operatorLogin}")
	private OperatorLoginBean operatorLoginBean;
	@ManagedProperty(value="#{operatorChat}")
	private OperatorChatBean operatorChatBean;
	
	
	public OperatorChatController(){}

	/**
	 * @param operatorLoginBean the operatorLoginBean to set
	 */
	public void setOperatorLoginBean(OperatorLoginBean operatorLoginBean) {
		this.operatorLoginBean = operatorLoginBean;
	}

	/**
	 * @param operatorChatBean the operatorChatBean to set
	 */
	public void setOperatorChatBean(OperatorChatBean operatorChatBean) {
		this.operatorChatBean = operatorChatBean;
	}
	
	public void refreshConversations() {
		List<ConversationDetails> conversationDetailsList = new ArrayList<>();
		Operator operator = this.getOperator();
		ConversationDetails conversationDetails = null;
		
		if(this.operatorChatBean.getCurrentConversation() == null) {
			this.operatorChatBean.setCurrentConversation(
					OperatorChatBean.ConversationDetails.getSelectedConversationDetailsInstance(
					operator.getCurrentConversation().getConversationID(), 
					operator.getCurrentConversation().getUserName()));
		}
		
		for(Conversation conversation : operator.getActiveChats()) {
			if(this.operatorChatBean.getCurrentConversation().getConversationId() == conversation.getConversationID()) {
				conversationDetails = operatorChatBean.getCurrentConversation();
			} else {
				conversationDetails = OperatorChatBean.ConversationDetails.getUnselectedConversationDetailsInstance(
						conversation.getConversationID(), 
						conversation.getUserName());
			}
			conversationDetailsList.add(conversationDetails);
		}
		this.operatorChatBean.setConversations(conversationDetailsList);
	}
	
	/**
	* Method called from the view when the user has clicked over one
	* of the conversations in the list of conversations.
	* This method has to set the style of the new selected conversation
	* to selected and set the previous selected conversation to unselected.
	* @param newConversation The new conversation clicked over.
	*/
	public void changeToConversation(ConversationDetails newConversationDetails) {
		Operator operator = this.getOperator();
		ConversationDetails currentConversation = this.operatorChatBean.getCurrentConversation();
		
		currentConversation.switchLinkStyle();
		newConversationDetails.switchLinkStyle();
		newConversationDetails.setNotifications(0);
		
		this.operatorChatBean.setCurrentConversation(newConversationDetails);
		Conversation conversation = this.conversationsManager.getConversationById(newConversationDetails.getConversationId());
		conversation.setNotifications(0);
		
		operator.setCurrentConversation(conversation);
		this.operatorChatBean.setHistory(conversation.getMessages());
	}
	
	/**
	 * @return operator
	 */
	public Operator getOperator() {
		return this.operatorManager.getOperatorById(
				this.operatorLoginBean.getOperatorId());
	}
	
	public void addNotification() {
		long conversationID = 0;
		Conversation conversation = null;
		
		for(ConversationDetails conversationDetails : operatorChatBean.getConversations()) {
			conversationID = conversationDetails.getConversationId();
			conversation = conversationsManager.getConversationById(conversationID);
			
			if(conversation.getNotifications() > 0) {
				conversationDetails.setNotifications(conversation.getNotifications());
			}
		}
	}
}
