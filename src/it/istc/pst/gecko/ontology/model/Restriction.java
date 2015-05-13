package it.istc.pst.gecko.ontology.model;

import it.istc.pst.gecko.ontology.kb.ComponentDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author alessandroumbrico
 *
 */
public abstract class Restriction 
{
	private String id;
	private Component component;
	private List<State> states;
	
	protected ComponentDAO dao;
	
	/**
	 * 
	 * @param id
	 * @param comp
	 */
	protected Restriction(String id, Component comp) {
		this.id = id;
		this.component = comp;
		
		// lazy approach 
		this.states = null;
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
	public Component getComponent() {
		return component;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<State> getStates() {
		// check states
		if (this.states == null) {
			// load states
			this.states = this.dao.retrieveRestrictionStates(this);
		}
		return new ArrayList<State>(this.states);
	}
	
	/**
	 * 
	 */
	@Override
	public int hashCode() {
		return this.id.hashCode() + this.component.hashCode();
	}
	
	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Restriction)) {
			throw new RuntimeException("Uncomparable objects");
		}
		Restriction other = (Restriction) obj;
		return this.id.equals(other.getId()) && this.component.equals(other.getComponent());
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[Restriction id= " + this.id + "\n"
				+ "\tstates= " + this.states + "]\n";
	}
}
