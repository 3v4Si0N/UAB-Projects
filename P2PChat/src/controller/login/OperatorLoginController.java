package controller.login;


import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import model.db.OperatorManager;

@ManagedBean(name="operatorLoginC")
public class OperatorLoginController {

	@ManagedProperty(value="#{operatorLogin}")
	private OperatorLoginBean operatorLoginBean;
	@EJB private OperatorManager operatorManager;
	private static final String OPERATOR_NOT_EXIST = "Operator not exist or incorrect password";
	
	
	public OperatorLoginController() {}


	/**
	 * @param operatorLoginBean the operatorLoginBean to set
	 */
	public void setOperatorLoginBean(OperatorLoginBean operatorLoginBean) {
		this.operatorLoginBean = operatorLoginBean;
	}
	
	public String verifyOperator() {
		String filename = "";
		
		if(this.operatorManager.isRegisteredOperator(
				this.operatorLoginBean.getUsername(),
				this.operatorLoginBean.getPassword())) {
			
			this.operatorLoginBean.setLogged(true);
			this.operatorLoginBean.setOperatorId(
					this.operatorManager.getOperatorByUsername(
							this.operatorLoginBean.getUsername()).getId());

			filename = "operatorChat.xhtml";
		} else {
			this.getOperatorNotExistMsg();
		}
		
		return filename;
	}
	
	public void logout() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		this.operatorLoginBean.setLogged(false);
		FacesContext.getCurrentInstance().getExternalContext().redirect("operatorChat.xhtml");
	}
	
	public String getOperatorNotExistMsg() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						OPERATOR_NOT_EXIST));;
		return "";
	}
}
