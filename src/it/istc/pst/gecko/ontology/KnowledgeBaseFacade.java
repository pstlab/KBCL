package it.istc.pst.gecko.ontology;

import it.istc.pst.gecko.ontology.kb.exception.ResourceNotFoundException;
import it.istc.pst.gecko.ontology.kb.rdf.RDFAgentDAO;
import it.istc.pst.gecko.ontology.kb.rdf.RDFFunctionalityDAO;
import it.istc.pst.gecko.ontology.kb.rdf.RDFKnowledgeBaseFactory;
import it.istc.pst.gecko.ontology.kb.rdf.exception.RDFResourceNotFoundException;
import it.istc.pst.gecko.ontology.model.Agent;
import it.istc.pst.gecko.ontology.model.rdf.RDFAgent;
import it.istc.pst.gecko.ontology.model.rdf.RDFAgentType;
import it.istc.pst.gecko.ontology.model.rdf.RDFFunctionalityType;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class KnowledgeBaseFacade 
{
	private List<RDFAgent> agents;
	private RDFKnowledgeBaseFactory factory;
	
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
	public List<RDFAgent> getAgents() {
		// check list
		if (this.agents == null) {
			// load agents
			RDFAgentDAO dao = this.factory.createAgentDAO();
			this.agents = dao.retrieveAllAgents();
		}
		return new ArrayList<>(this.agents);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<RDFAgentType> getAgentTypes() {
		// create DAO
		RDFAgentDAO dao = this.factory.createAgentDAO();
		return dao.retrieveAllAgentTypes();
	}
	
	/**
	 * 
	 * @return
	 */
	public List<RDFFunctionalityType> getFunctionalityTypes() {
		// create DAO
		RDFFunctionalityDAO dao = this.factory.createFunctionalityDAO();
		return dao.retrieveAllFunctionalityTypes();
	}

	/**
	 * 
	 * @param agentId
	 * @return
	 * @throws RDFResourceNotFoundException 
	 */
	public RDFAgent getAgentById(String agentId) 
			throws ResourceNotFoundException 
	{
		// create DAO
		RDFAgentDAO dao = this.factory.createAgentDAO();
		return dao.retrieveAgentById(agentId);
	}
}
