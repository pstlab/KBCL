package it.istc.pst.gecko.ontology.model.owl;

import it.istc.pst.gecko.ontology.kb.owl.OWLFunctionalityDAO;
import it.istc.pst.gecko.ontology.kb.owl.OWLKnowledgeBaseFactory;
import it.istc.pst.gecko.ontology.model.Functionality;
import it.istc.pst.gecko.ontology.model.FunctionalityType;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class OWLFunctionality extends Functionality 
{
	protected OWLFunctionalityDAO dao;
	
	/**
	 * 
	 * @param id
	 * @param label
	 * @param type
	 */
	protected OWLFunctionality(String id, String label, FunctionalityType type) {
		super(id, label, type);
		// get DAO
		OWLKnowledgeBaseFactory factory = new OWLKnowledgeBaseFactory();
		this.dao = factory.createFunctionalityDAO();
	}
}
