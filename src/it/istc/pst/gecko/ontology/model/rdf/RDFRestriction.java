package it.istc.pst.gecko.ontology.model.rdf;

import it.istc.pst.gecko.ontology.kb.rdf.RDFComponentDAO;
import it.istc.pst.gecko.ontology.kb.rdf.RDFKnowledgeBaseFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class RDFRestriction 
{
	protected String id;
	protected RDFComponent component;
	protected List<RDFState> states;
	
	protected RDFComponentDAO dao;
	
	/**
	 * 
	 * @param id
	 * @param comp
	 */
	protected RDFRestriction(String id, RDFComponent comp) {
		this.id = id;
		this.component = comp;
		
		// lazy approach 
		this.states = null;
		
		// get DAO
		RDFKnowledgeBaseFactory factory = new RDFKnowledgeBaseFactory();
		this.dao = factory.createComponentDAO();
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
	public RDFComponent getComponent() {
		return component;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<RDFState> getStates() {
		// check states
		if (this.states == null) {
			// load states
			this.states = this.dao.retrieveRestrictionStates(this);
		}
		return new ArrayList<RDFState>(this.states);
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
		return (obj instanceof RDFRestriction) && ((RDFRestriction) obj).id.equals(this.getId()) && ((RDFRestriction) obj).component.equals(this.component);
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
