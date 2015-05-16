package it.istc.pst.kbcl.mapping.model.rdf;

import it.istc.pst.kbcl.mapping.kb.rdf.RDFComponentDAO;
import it.istc.pst.kbcl.mapping.kb.rdf.RDFMappingKnowledgeBaseFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class RDFComponent // extends Component 
{
	protected String id;
	protected String label;
	protected List<RDFState> states;
	protected List<RDFRestriction> restrictions;
	
	protected RDFComponentDAO dao;
	
	
	/**
	 * 
	 * @param id
	 * @param label
	 */
	protected RDFComponent(String id, String label) {
		this.id = id;
		this.label = label;
		// lazy load approach
		this.states = null;
		this.restrictions = null;
		
		// get factory
		RDFMappingKnowledgeBaseFactory factory = new RDFMappingKnowledgeBaseFactory();
		// create DAO
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
	public String getLabel() {
		return label;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<RDFState> getStates() {
		// check states
		if (this.states == null) {
			// load states
			this.states = this.dao.retrieveComponentStates(this);
		}
		return new ArrayList<RDFState>(this.states);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<RDFRestriction> getRestrictions() {
		// check restrictions
		if (this.restrictions == null) {
			// load restrictions
			this. restrictions = this.dao.retrieveComponentRestrictions(this);
		}
		return new ArrayList<RDFRestriction>(this.restrictions);
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
		return (obj instanceof RDFComponent) && ((RDFComponent) obj).id.equals(this.id);
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
