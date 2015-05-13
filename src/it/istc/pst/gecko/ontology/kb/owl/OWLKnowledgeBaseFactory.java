package it.istc.pst.gecko.ontology.kb.owl;


/**
 * 
 * @author alessandroumbrico
 *
 */
public class OWLKnowledgeBaseFactory 
{
	/**
	 * 
	 */
	public OWLAgentDAO createAgentDAO() {
		return new OWLAgentDAO();
	}

	/**
	 * 
	 */
	public OWLFunctionalityDAO createFunctionalityDAO() {
		return new OWLFunctionalityDAO();
	}

	/**
	 * 
	 */
	public OWLElementDAO createElementDAO() {
		return new OWLElementDAO();
	}
}
