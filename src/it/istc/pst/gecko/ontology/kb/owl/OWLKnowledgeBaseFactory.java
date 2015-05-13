package it.istc.pst.gecko.ontology.kb.owl;

import it.istc.pst.gecko.ontology.kb.AgentDAO;
import it.istc.pst.gecko.ontology.kb.ComponentDAO;
import it.istc.pst.gecko.ontology.kb.FunctionalityDAO;
import it.istc.pst.gecko.ontology.kb.KnowledgeBaseFactory;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class OWLKnowledgeBaseFactory implements KnowledgeBaseFactory 
{
	/**
	 * 
	 */
	@Override
	public AgentDAO createAgentDAO() {
		return new OWLAgentDAO();
	}

	@Override
	public FunctionalityDAO createFunctionalityDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComponentDAO createComponentDAO() {
		// TODO Auto-generated method stub
		return null;
	}

}
