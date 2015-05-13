package it.istc.pst.gecko.ontology.model.rdf;

import it.istc.pst.gecko.ontology.kb.KnowledgeBaseFactory;
import it.istc.pst.gecko.ontology.kb.rdf.RDFKnowledgeBaseFactory;
import it.istc.pst.gecko.ontology.model.Agent;
import it.istc.pst.gecko.ontology.model.AgentType;

/**
 * 
 * @author alessandroumbrico
 *
 */
public final class RDFAgent extends Agent 
{
	/**
	 * 
	 * @param id
	 * @param label
	 * @param type
	 */
	protected RDFAgent(String id, String label, AgentType type) {
		super(id, label, type);
		// get factory
		KnowledgeBaseFactory factory = new RDFKnowledgeBaseFactory();
		// get DAO
		this.dao = factory.createAgentDAO();
	}
}
