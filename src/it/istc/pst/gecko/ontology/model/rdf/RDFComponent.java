package it.istc.pst.gecko.ontology.model.rdf;

import it.istc.pst.gecko.ontology.kb.rdf.RDFComponentDAO;
import it.istc.pst.gecko.ontology.kb.rdf.RDFKnowledgeBaseFactory;
import it.istc.pst.gecko.ontology.model.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class RDFComponent extends Component 
{
	protected List<RDFState> states;
	protected List<RDFRestriction> restrictions;
	
	protected RDFComponentDAO dao;
	
	
	/**
	 * 
	 * @param id
	 * @param label
	 */
	protected RDFComponent(String id, String label) {
		super(id, label);
		// lazy load approach
		this.states = null;
		this.restrictions = null;
		
		// get factory
		RDFKnowledgeBaseFactory factory = new RDFKnowledgeBaseFactory();
		// create DAO
		this.dao = factory.createComponentDAO();
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
	public String toString() {
		return "[Component id=" + this.id +"\n"
				+ "\tlabel= " + this.label + "\n"
				+ "\tstates= " + this.states + "\n"
				+ "\trestrictions= " + this.restrictions + "]\n";
	}
}
