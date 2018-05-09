package controller.login;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="userLogin")
@SessionScoped
public class UserLoginBean {

	private String username;
	private long conversationId;
	private String selectedCategory;
	private String selectedSubCategory;


	public UserLoginBean() {}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}


	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the conversationId
	 */
	public long getConversationId() {
		return conversationId;
	}

	/**
	 * @param conversationId the conversationId to set
	 */
	public void setConversationId(long conversationId) {
		this.conversationId = conversationId;
	}

	/**
	 * @return the selectedCategory
	 */
	public String getSelectedCategory() {
		return selectedCategory;
	}

	/**
	 * @param selectedCategory the selectedCategory to set
	 */
	public void setSelectedCategory(String selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

	/**
	 * @return the selectedSubCategory
	 */
	public String getSelectedSubCategory() {
		return selectedSubCategory;
	}

	/**
	 * @param selectedSubCategory the selectedSubCategory to set
	 */
	public void setSelectedSubCategory(String selectedSubCategory) {
		this.selectedSubCategory = selectedSubCategory;
	}	
}
