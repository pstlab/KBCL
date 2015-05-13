package it.istc.pst.gecko.ontology;

import it.istc.pst.gecko.ontology.kb.AgentDAO;
import it.istc.pst.gecko.ontology.kb.FunctionalityDAO;
import it.istc.pst.gecko.ontology.kb.KnowledgeBaseFactory;
import it.istc.pst.gecko.ontology.kb.exception.ResourceNotFoundException;
import it.istc.pst.gecko.ontology.kb.rdf.RDFKnowledgeBaseFactory;
import it.istc.pst.gecko.ontology.kb.rdf.exception.RDFResourceNotFoundException;
import it.istc.pst.gecko.ontology.model.Agent;
import it.istc.pst.gecko.ontology.model.AgentType;
import it.istc.pst.gecko.ontology.model.FunctionalityType;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class KnowledgeBaseFacade 
{
	private List<Agent> agents;
	private KnowledgeBaseFactory factory;
	
	private static KnowledgeBaseFacade INSTANCE = null;

	/**
	 * 
	 */
	private KnowledgeBaseFacade() {
		// create factory
		this.factory = new RDFKnowledgeBaseFactory();
		
		// lazy approach
		this.agents = null;
	}
	
	/**
	 * 
	 * @return
	 */
	public static KnowledgeBaseFacade getSingletonInstance() {
		if (INSTANCE == null) {
			INSTANCE = new KnowledgeBaseFacade();
		}
		return INSTANCE;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Agent> getAgents() {
		// check list
		if (this.agents == null) {
			// load agents
			AgentDAO dao = this.factory.createAgentDAO();
			this.agents = dao.retrieveAllAgents();
		}
		return new ArrayList<Agent>(this.agents);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<AgentType> getAgentTypes() {
		// create DAO
		AgentDAO dao = this.factory.createAgentDAO();
		return dao.retrieveAllAgentTypes();
	}
	
	/**
	 * 
	 * @return
	 */
	public List<FunctionalityType> getFunctionalityTypes() {
		// create DAO
		FunctionalityDAO dao = this.factory.createFunctionalityDAO();
		return dao.retrieveAllFunctionalityTypes();
	}

	/**
	 * 
	 * @param agentId
	 * @return
	 * @throws RDFResourceNotFoundException 
	 */
	public Agent getAgentById(String agentId) 
			throws ResourceNotFoundException 
	{
		// create DAO
		AgentDAO dao = this.factory.createAgentDAO();
		return dao.retrieveAgentById(agentId);
	}
}
