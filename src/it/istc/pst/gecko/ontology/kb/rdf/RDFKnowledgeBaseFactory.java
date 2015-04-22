package it.istc.pst.gecko.ontology.kb.rdf;

import it.istc.pst.gecko.ontology.kb.AgentDAO;
import it.istc.pst.gecko.ontology.kb.ComponentDAO;
import it.istc.pst.gecko.ontology.kb.FunctionalityDAO;
import it.istc.pst.gecko.ontology.kb.KnowledgeBaseFactory;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class RDFKnowledgeBaseFactory implements KnowledgeBaseFactory 
{
	/**
	 * 
	 */
	@Override
	public AgentDAO createAgentDAO() {
		// create DAO
		return new RDFAgentDAO();
	}
	
	/**
	 * 
	 */
	@Override
	public FunctionalityDAO createFunctionalityDAO() {
		// create DAO
		return new RDFFunctionalityDAO();
	}
	
	/**
	 * 
	 */
	@Override
	public ComponentDAO createComponentDAO() {
		// create DAO
		return new RDFComponentDAO();
	}
}
