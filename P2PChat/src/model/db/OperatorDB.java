package model.db;

import java.util.Hashtable;

import javax.ejb.Singleton;

import model.core.Operator;

@Singleton
public class OperatorDB {

	private Hashtable<Long,Operator> authorizedOperators;
	
	public OperatorDB() {
		this.init();
	}
	
	private void init() {
		this.authorizedOperators = new Hashtable<Long, Operator>();

		this.authorizedOperators.put(new Long(1L), new Operator("op1", "op1", 1L));
		this.authorizedOperators.put(new Long(2L), new Operator("op2", "op2", 2L));
		this.authorizedOperators.put(new Long(3L), new Operator("op3", "op3", 3L));
		this.authorizedOperators.put(new Long(4L), new Operator("op4", "op4", 4L));
	}

	/**
	 * @return the authorizedUSers
	 */
	public Hashtable<Long, Operator> getAuthorizedOperators() {
		return authorizedOperators;
	}
	
	
}
