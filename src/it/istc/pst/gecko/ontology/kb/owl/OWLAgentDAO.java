package it.istc.pst.gecko.ontology.kb.owl;

import it.istc.pst.gecko.ontology.kb.AgentDAO;
import it.istc.pst.gecko.ontology.kb.owl.exception.OWLResourceNotFoundException;
import it.istc.pst.gecko.ontology.model.Agent;
import it.istc.pst.gecko.ontology.model.AgentType;
import it.istc.pst.gecko.ontology.model.Component;
import it.istc.pst.gecko.ontology.model.ExternalComponent;
import it.istc.pst.gecko.ontology.model.Functionality;
import it.istc.pst.gecko.ontology.model.FunctionalityType;
import it.istc.pst.gecko.ontology.model.owl.OWLModelFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntResource;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class OWLAgentDAO implements AgentDAO 
{
	private OWLDatasetManager dataset;
	private OWLModelFactory factory;
	
	/**
	 * 
	 */
	protected OWLAgentDAO() {
		// get data set 
		this.dataset = OWLDatasetManager.getSingletonInstance();
		this.factory = OWLModelFactory.getSingletonInstance();
	}
	
	/**
	 * 
	 * @param name
	 * @param type
	 * @return
	 */
	@Override
	public Agent createAgent(String name, AgentType type) {
		// create individual of desired type
		OntClass agentClass = this.dataset.model.getOntClass(type.getId());
		// create individual
		Individual agent = agentClass.createIndividual(OWLDatasetManager.NS + name);
		// get instance
		return this.factory.createAgent(agent.getURI(), agent.getLocalName(), 
				this.factory.createAgentType(type.getId(), type.getLabel()));
	}
	
	/**
	 * 
	 * @return
	 */
	@Override
	public List<AgentType> retrieveAllAgentTypes() {
		List<AgentType> list = new ArrayList<>();
		
		// get all agent sub classes
		OntClass agent = this.dataset.model.getOntClass(OWLDatasetManager.NS + "Agent");
		Iterator<? extends OntResource> it = agent.listSubClasses();
		while (it.hasNext()) {
			// get subclass
			OntClass subclass = it.next().asClass();
			list.add(this.factory.createAgentType(subclass.getURI(), subclass.getLocalName()));
		}
		
		// get list of agent types
		return list;
	}
	
	
	/**
	 * 
	 * @return
	 */
	@Override
	public List<Agent> retrieveAllAgents() {
		List<Agent> list = new ArrayList<>();
		
		// get types
		for (AgentType type : this.retrieveAllAgentTypes()) {
			// get individuals for current type
			OntClass at = this.dataset.model.getOntClass(type.getId());
			Iterator<? extends OntResource> it = at.listInstances();
			while (it.hasNext()) {
				// get individual
				Individual a = it.next().asIndividual();
				// add agent
				list.add(this.factory.createAgent(a.getURI(), a.getLocalName(), 
						this.factory.createAgentType(type.getId(), type.getLabel())));
			}
		}

		// get list of agents
		return list;
	}
	
	/**
	 * 
	 */
	@Override
	public List<Agent> retrieveAgentsByType(AgentType type) {
		List<Agent> list = new ArrayList<>();
		
		// get class
		OntClass module = this.dataset.model.getOntClass(type.getId());
		Iterator<? extends OntResource> it = module.listInstances();
		while (it.hasNext()) {
			Individual ind = it.next().asIndividual();
			list.add(this.factory.createAgent(ind.getURI(), ind.getLocalName(), 
					this.factory.createAgentType(type.getId(), type.getLabel())));
		}
		
		// get module agents
		return list;
	}

	/**
	 * 
	 */
	@Override
	public Agent retrieveAgentById(String id)
			throws OWLResourceNotFoundException 
	{
		// get individual
		Individual ind = this.dataset.model.getIndividual(id);
		// check result 
		if (ind == null) {
			throw new OWLResourceNotFoundException("OWL Individual \"" + id + "\" not found... ");
		}
		return null;
	}

	/**
	 * 
	 */
	@Override
	public Map<FunctionalityType, List<Functionality>> retrieveAgentFunctionalities(Agent agent) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Component> retrieveAgentInternalComponents(Agent agent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExternalComponent> retrieveAgentExternalComponents(Agent agent) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
