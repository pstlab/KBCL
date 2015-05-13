package it.istc.pst.gecko.ontology.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author alessandroumbrico
 *
 */
public abstract class FunctionalityImplementation 
{
	private Functionality funcionality;
	private List<TemporalConstraint> constraints;
	private Map<State, List<TemporalConstraint>> restrictions;
	
	/**
	 * 
	 * @param func
	 */
	protected FunctionalityImplementation(Functionality func) {
		this.funcionality = func;
		this.constraints = new ArrayList<TemporalConstraint>();
		this.restrictions = new HashMap<State, List<TemporalConstraint>>();
	}
	
	/**
	 * 
	 * @return
	 */
	public Functionality getFuncionality() {
		return funcionality;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<TemporalConstraint> getConstraints() {
		return new ArrayList<TemporalConstraint>(constraints);
	}
	
	/**
	 * 
	 * @param c
	 */
	public void addConstraint(TemporalConstraint c) {
		this.constraints.add(c);
	}
	
	/**
	 * 
	 * @return
	 */
	public Map<State, List<TemporalConstraint>> getRestrictions() {
		return new HashMap<State, List<TemporalConstraint>>(restrictions);
	}
	
	/**
	 * 
	 * @param c
	 */
	public void addRestriction(State s, TemporalConstraint c) {
		// check map
		if (!this.restrictions.containsKey(s)) {
			// initialize
			this.restrictions.put(s, new ArrayList<TemporalConstraint>());
		}
		// add restriction constraint
		this.restrictions.get(s).add(c);
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[FunctionalityImplementation\n"
				+ "\t- constraints= " + this.constraints + "\n"
				+ "\t- restrictions= " + this.restrictions + "]\n";
	}
}
