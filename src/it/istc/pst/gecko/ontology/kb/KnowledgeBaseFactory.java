package it.istc.pst.gecko.ontology.kb;

/**
 * 
 * @author alessandroumbrico
 *
 */
public interface KnowledgeBaseFactory 
{
	/**
	 * 
	 * @return
	 */
	public AgentDAO createAgentDAO();
	
	/**
	 * 
	 * @return
	 */
	public FunctionalityDAO createFunctionalityDAO();
	
	/**
	 * 
	 * @return
	 */
	public ComponentDAO createComponentDAO();
}
