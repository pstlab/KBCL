package it.istc.pst.gecko.ontology.model.owl;

import it.istc.pst.gecko.ontology.kb.owl.OWLElementDAO;
import it.istc.pst.gecko.ontology.kb.owl.OWLKnowledgeBaseFactory;
import it.istc.pst.gecko.ontology.model.Element;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class OWLElement extends Element 
{
	protected OWLElementDAO dao;
	
	/**
	 * 
	 * @param id
	 * @param label
	 * @param type
	 */
	protected OWLElement(String id, String label, OWLElementType type) {
		super(id, label, type);
		
		// get DAO
		OWLKnowledgeBaseFactory factory = new OWLKnowledgeBaseFactory();
		this.dao = factory.createElementDAO();
	}
}
