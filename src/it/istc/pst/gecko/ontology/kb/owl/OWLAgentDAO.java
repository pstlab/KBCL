package it.istc.pst.gecko.ontology.kb.owl;

import it.istc.pst.gecko.ontology.kb.owl.exception.OWLResourceNotFoundException;
import it.istc.pst.gecko.ontology.model.AgentType;
import it.istc.pst.gecko.ontology.model.owl.OWLAgent;
import it.istc.pst.gecko.ontology.model.owl.OWLAgentType;
import it.istc.pst.gecko.ontology.model.owl.OWLModelFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class OWLAgentDAO 
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
	public OWLAgent createAgent(String name, AgentType type) {
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
	public List<OWLAgentType> retrieveAllAgentTypes() {
		List<OWLAgentType> list = new ArrayList<>();
		
		// get all agent sub classes
		OntClass agent = this.dataset.model.getOntClass(OWLDatasetManager.NS + "Agent");
		// add root class
		list.add(this.factory.createAgentType(agent.getURI(), agent.getLocalName()));
		Iterator<? extends OntResource> it = agent.listSubClasses(false);
		while (it.hasNext()) {
			// get subclass
			OntClass subclass = it.next().asClass();
			if (!subclass.isAnon()) {
				list.add(this.factory.createAgentType(subclass.getURI(), subclass.getLocalName()));
			}
		}
		
		// get list of agent types
		return list;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public List<OWLAgent> retrieveAllAgents() {
		List<OWLAgent> list = new ArrayList<>();
		
		// get agent types
		for (OWLAgentType type : this.retrieveAllAgentTypes()) {
			// get individuals
			List<OWLAgent> agents = this.retrieveAgentsByType(type);
			list.addAll(agents);
		}
		
		// get list of agents
		return list;
	}
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	public List<OWLAgent> retrieveAgentsByType(AgentType type) {
		List<OWLAgent> list = new ArrayList<>();
		
		// get class
		OntClass module = this.dataset.model.getOntClass(type.getId());
		Iterator<? extends OntResource> it = module.listInstances(false);
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
	 * @param id
	 * @return
	 * @throws OWLResourceNotFoundException
	 */
	public OWLAgent retrieveAgentById(String id)
			throws OWLResourceNotFoundException 
	{
		// get individual
		Individual a = this.dataset.model.getIndividual(id);
		// check result 
		if (a == null) {
			throw new OWLResourceNotFoundException("OWL Individual \"" + id + "\" not found... ");
		}
		
		// get type
		Resource type = a.getRDFType();
		// get agent
		return this.factory.createAgent(a.getURI(), a.getLocalName(), 
				this.factory.createAgentType(type.getURI(), type.getLocalName()));
	}

}
