package controller.login;

import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.event.ValueChangeEvent;

import model.core.ConversationManager;
import model.db.CategoryDB;

@ManagedBean(name="userLoginC")
public class UserLoginController {

	@ManagedProperty(value="#{userLogin}")
	private UserLoginBean userLoginBean;
	@EJB private CategoryDB categoryDB;
	@EJB private ConversationManager convManager;
	
	
	public UserLoginController() {}


	/**
	 * @param userLoginBean the userLoginBean to set
	 */
	public void setUserLoginBean(UserLoginBean userLoginBean) {
		this.userLoginBean = userLoginBean;
	}
	
	public Set<String> getCategories() {
		return this.categoryDB.getCategories();
	}
	
	public List<String> getSubCategories() {
		return (this.userLoginBean.getSelectedCategory() == null) ? 
				this.categoryDB.getSubcategories(this.categoryDB.getFirstCategory()) :
					this.categoryDB.getSubcategories(this.userLoginBean.getSelectedCategory());
	}
	
	public void categoryChanged(ValueChangeEvent event) {
		this.userLoginBean.setSelectedCategory(event.getNewValue().toString());
	}
	
	public String createConversation() {
		long conversationID = this.convManager.createConversation(this.userLoginBean.getUsername());
		this.userLoginBean.setConversationId(conversationID);
		return "userChat.xhtml?faces-redirect=true";
	}
}
