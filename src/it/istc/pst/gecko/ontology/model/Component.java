package it.istc.pst.gecko.ontology.model;

import it.istc.pst.gecko.ontology.kb.ComponentDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author alessandroumbrico
 *
 */
public abstract class Component 
{
	protected String id;
	protected String label;
	protected List<State> states;
	protected List<Restriction> restrictions;
	
	protected ComponentDAO dao;
	
	/**
	 * 
	 * @param id
	 * @param label
	 */
	protected Component(String id, String label) {
		this.id = id;
		this.label = label;
		
		// lazy load approach
		this.states = null;
		this.restrictions = null;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getLabel() {
		return label;
	}
	

	/**
	 * 
	 * @return
	 */
	public List<State> getStates() {
		// check states
		if (this.states == null) {
			// load states
			this.states = this.dao.retrieveComponentStates(this);
		}
		return new ArrayList<State>(this.states);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Restriction> getRestrictions() {
		// check restrictions
		if (this.restrictions == null) {
			// load restrictions
			this. restrictions = this.dao.retrieveComponentRestrictions(this);
		}
		return new ArrayList<Restriction>(this.restrictions);
	}
	
	/**
	 * 
	 */
	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
	
	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Component)) {
			throw new RuntimeException("Uncomparable objects");
		}
		Component other = (Component) obj;
		return this.id.equals(other.getId());
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[Component id=" + this.id +"\n"
				+ "\tlabel= " + this.label + "\n"
				+ "\tstates= " + this.states + "\n"
				+ "\trestrictions= " + this.restrictions + "]\n";
	}
}
