package controller.login;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="operatorLogin")
@SessionScoped
public class OperatorLoginBean {

	private String username;
	private String password;
	private boolean logged;
	private long operatorId;


	public OperatorLoginBean() {}

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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return the logged
	 */
	public boolean isLogged() {
		return logged;
	}


	/**
	 * @param logged the logged to set
	 */
	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	/**
	 * @return the operatorId
	 */
	public long getOperatorId() {
		return operatorId;
	}

	/**
	 * @param operatorId the operatorId to set
	 */
	public void setOperatorId(long operatorId) {
		this.operatorId = operatorId;
	}
}
