package it.istc.pst.gecko.ontology.kb;

import it.istc.pst.gecko.ontology.kb.rdf.exception.RDFResourceNotFoundException;
import it.istc.pst.gecko.ontology.model.Agent;
import it.istc.pst.gecko.ontology.model.AgentType;
import it.istc.pst.gecko.ontology.model.Component;
import it.istc.pst.gecko.ontology.model.ExternalComponent;
import it.istc.pst.gecko.ontology.model.Functionality;
import it.istc.pst.gecko.ontology.model.FunctionalityType;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author alessandroumbrico
 *
 */
public interface AgentDAO 
{
	/**
	 * 
	 * @return
	 */
	public List<AgentType> retrieveAllAgentTypes();
	
	/**
	 * 
	 * @return
	 */
	public List<Agent> retrieveAllAgents();
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	public List<Agent> retrieveAgentsByType(AgentType type);
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws RDFResourceNotFoundException
	 */
	public Agent retrieveAgentById(String id) throws RDFResourceNotFoundException;
	
	/**
	 * 
	 * @param agent
	 * @return
	 */
	public Map<FunctionalityType, List<Functionality>> retrieveAgentFunctionalities(Agent agent);
	
	/**
	 * 
	 * @param agent
	 * @return
	 */
	public List<Component> retrieveAgentInternalComponents(Agent agent);
	
	/**
	 * 
	 * @param agent
	 * @return
	 */
	public List<ExternalComponent> retrieveAgentExternalComponents(Agent agent);
}
