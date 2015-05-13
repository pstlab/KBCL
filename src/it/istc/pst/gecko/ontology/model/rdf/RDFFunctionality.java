package it.istc.pst.gecko.ontology.model.rdf;

import it.istc.pst.gecko.ontology.kb.KnowledgeBaseFactory;
import it.istc.pst.gecko.ontology.kb.rdf.RDFKnowledgeBaseFactory;
import it.istc.pst.gecko.ontology.model.Functionality;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class RDFFunctionality extends Functionality 
{
	/**
	 * 
	 * @param id
	 * @param label
	 * @param dmin
	 * @param dmax
	 * @param type
	 */
	protected RDFFunctionality(String id, String label, String dmin, String dmax, RDFFunctionalityType type) {
		super(id, label, dmin, dmax, type);
		// create DAO
		KnowledgeBaseFactory factory = new RDFKnowledgeBaseFactory();
		this.dao = factory.createFunctionalityDAO();
	}
}
