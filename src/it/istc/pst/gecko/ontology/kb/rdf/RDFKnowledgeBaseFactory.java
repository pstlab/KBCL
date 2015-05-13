package it.istc.pst.gecko.ontology.kb.rdf;


/**
 * 
 * @author alessandroumbrico
 *
 */
public class RDFKnowledgeBaseFactory 
{
	/**
	 * 
	 * @return
	 */
	public RDFAgentDAO createAgentDAO() {
		// create DAO
		return new RDFAgentDAO();
	}
	
	/**
	 * 
	 * @return
	 */
	public RDFFunctionalityDAO createFunctionalityDAO() {
		// create DAO
		return new RDFFunctionalityDAO();
	}
	
	/**
	 * 
	 * @return
	 */
	public RDFComponentDAO createComponentDAO() {
		// create DAO
		return new RDFComponentDAO();
	}
}
