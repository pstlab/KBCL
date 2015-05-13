package it.istc.pst.gecko.ontology.model.rdf;

import it.istc.pst.gecko.ontology.kb.KnowledgeBaseFactory;
import it.istc.pst.gecko.ontology.kb.rdf.RDFKnowledgeBaseFactory;
import it.istc.pst.gecko.ontology.model.Component;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class RDFComponent extends Component 
{
	/**
	 * 
	 * @param id
	 * @param label
	 */
	protected RDFComponent(String id, String label) {
		super(id, label);
		// get factory
		KnowledgeBaseFactory factory = new RDFKnowledgeBaseFactory();
		// create DAO
		this.dao = factory.createComponentDAO();
	}
}
