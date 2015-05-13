package it.istc.pst.gecko.ontology.model.rdf;

import it.istc.pst.gecko.ontology.kb.KnowledgeBaseFactory;
import it.istc.pst.gecko.ontology.kb.rdf.RDFKnowledgeBaseFactory;
import it.istc.pst.gecko.ontology.model.Restriction;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class RDFRestriction extends Restriction 
{
	/**
	 * 
	 * @param id
	 * @param comp
	 */
	protected RDFRestriction(String id, RDFComponent comp) {
		super(id, comp);
		// get factory
		KnowledgeBaseFactory factory = new RDFKnowledgeBaseFactory();
		// create component DAO
		this.dao = factory.createComponentDAO();
	}
}
