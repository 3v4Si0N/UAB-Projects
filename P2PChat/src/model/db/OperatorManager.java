package model.db;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Singleton;

import model.core.Operator;

@Singleton
public class OperatorManager {

	@EJB OperatorDB operatorDB;
	
	public OperatorManager() {}
	
	public boolean isRegisteredOperator(String username, String password) {
		boolean isRegistered = false;
		Hashtable<Long, Operator> operatorht = operatorDB.getAuthorizedOperators();
		
		for (Operator operator : operatorht.values()) {
			if(operator.getUsername().equals(username) &&
					operator.getPassword().equals(password)) {
				isRegistered = true;
				break;
			}
		}
		return isRegistered;
	}
	
	public Operator getOperatorByUsername(String username) {
		Operator optr = null;
		Hashtable<Long, Operator> operatorht = operatorDB.getAuthorizedOperators();
		
		for (Operator operator : operatorht.values()) {
			if(operator.getUsername().equals(username)) {
				optr = operator;
				break;
			}
		}
		return optr;
	}
	
	public Operator getOperatorById(long id) {
		Operator optr = null;
		Hashtable<Long, Operator> operatorht = operatorDB.getAuthorizedOperators();
		
		for (Operator operator : operatorht.values()) {
			if(operator.getId() == id) {
				optr = operator;
				break;
			}
		}
		return optr;
	}
	
	public Operator getAvailableOperator() {
		Operator optr = null;
		int activeChats = 9999999; // Impossible number of active chats
		List<Operator> onlineOperators = (ArrayList<Operator>)getOnlineOperators();
		
		for (int i = 0; i < onlineOperators.size(); i++) {
			if (onlineOperators.get(i).getActiveChats().size() < activeChats) {
				activeChats = onlineOperators.get(i).getActiveChats().size();
				optr = onlineOperators.get(i);
			}
		}
				
		return optr;
	}
	
	private List<Operator> getOnlineOperators() {
		List<Operator> operatorList = new ArrayList<>();
		Hashtable<Long, Operator> operatorht = operatorDB.getAuthorizedOperators();
		
		for (Operator operator : operatorht.values()) {
			if(operator.isOnline()) {
				operatorList.add(operator);
			}
		}
		return operatorList;
	}
}
