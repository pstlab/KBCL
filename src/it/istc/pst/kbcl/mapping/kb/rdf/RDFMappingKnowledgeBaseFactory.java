package it.istc.pst.kbcl.mapping.kb.rdf;


/**
 * 
 * @author alessandroumbrico
 *
 */
public class RDFMappingKnowledgeBaseFactory 
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
