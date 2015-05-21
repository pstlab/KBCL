package it.istc.pst.kbcl.mapping.model.rdf;

import it.istc.pst.kbcl.mapping.kb.rdf.RDFAgentDAO;
import it.istc.pst.kbcl.mapping.kb.rdf.RDFDatasetManager;
import it.istc.pst.kbcl.mapping.kb.rdf.RDFFunctionalityDAO;
import it.istc.pst.kbcl.mapping.kb.rdf.RDFMappingKnowledgeBaseFactory;
import it.istc.pst.kbcl.mapping.kb.rdf.exception.RDFPropertyNotFoundException;
import it.istc.pst.kbcl.mapping.kb.rdf.exception.RDFResourceNotFoundException;
import it.istc.pst.kbcl.model.Agent;
import it.istc.pst.kbcl.model.Element;
import it.istc.pst.kbcl.model.Event;
import it.istc.pst.kbcl.model.EventObserver;
import it.istc.pst.kbcl.model.EventPublisher;
import it.istc.pst.kbcl.model.Functionality;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class RDFMappingKnowledgeBaseFacade implements EventObserver, EventPublisher
{
	private RDFDatasetManager dataset;
	private RDFMappingKnowledgeBaseFactory factory;
	private Agent agent;
	private RDFAgent rdfAgent;
	
	private List<EventObserver> observers;
	
	/**
	 * 
	 * @param agent
	 * @throws RDFResourceNotFoundException
	 * @throws RDFPropertyNotFoundException
	 */
	public RDFMappingKnowledgeBaseFacade(Agent agent) 
			throws RDFResourceNotFoundException, RDFPropertyNotFoundException
	{
		// get observed agent
		this.agent = agent;
		this.agent.subscribe(this);
		this.observers = new ArrayList<>();
		
		// get data set
		this.dataset = RDFDatasetManager.getSingletonInstance();
		// create factory
		this.factory = new RDFMappingKnowledgeBaseFactory();
		RDFAgentDAO dao = this.factory.createAgentDAO();
		// create agent elements
		this.rdfAgent = dao.createAgent(this.agent.getLabel());
		
		// insert agent's functionalities
		for (Functionality f : this.agent.getFunctionalities()) {	//getFunctionalityIndex()) {
			dao.addFunctionality(this.agent.getLabel(), f.getLabel());
		}
		
		for (Element e : this.agent.getComponents()) {
			dao.addComponent(this.agent.getLabel(), e.getLabel());
		}
		
		// insert agent's neighbors
		for (Element n : this.agent.getNeighbors()) {
			dao.addNeighbor(this.agent.getLabel(), n.getLabel());
		}
	}
	
	/**
	 * 
	 */
	@Override
	public void subscribe(EventObserver observer) {
		this.observers.add(observer);
	}
	
	/**
	 * 
	 */
	@Override
	public void unSubscribe(EventObserver observer) {
		this.observers.remove(observer);
	}
	
	/**
	 * 
	 * @return
	 */
	public RDFAgent getAgent() {
		return this.rdfAgent;
	}
	
	/**
	 * 
	 */
	@Override
	public void update(Event event) {
		// check event
		switch (event) {
			case AGENT_ELEMENT_UDPATE_EVENT : {
				try {
					// update knowledge base
					this.dataset.close();
					// get data set
					this.dataset = RDFDatasetManager.getSingletonInstance();
					// create factory
					this.factory = new RDFMappingKnowledgeBaseFactory();
					RDFAgentDAO dao = this.factory.createAgentDAO();
					// create agent elements
					this.rdfAgent = dao.createAgent(this.agent.getLabel());
					
					// insert agent's functionalities
					for (Functionality f : this.agent.getFunctionalities()) {	//.getFunctionalityIndex()) {
						dao.addFunctionality(this.agent.getLabel(), f.getLabel());
					}
					
					for (Element e : this.agent.getComponents()) {
						dao.addComponent(this.agent.getLabel(), e.getLabel());
					}
					
					// insert agent's neighbors
					for (Element n : this.agent.getNeighbors()) {
						dao.addNeighbor(this.agent.getLabel(), n.getLabel());
					}
				}
				catch (RDFPropertyNotFoundException | RDFResourceNotFoundException ex) {
					System.err.println(ex.getLocalizedMessage());
				}
				finally {
					// update observers
					for (EventObserver o : this.observers) {
						o.update(Event.MAPPING_KNOWLEDGE_UPDATE_EVENT);
					}
				}
			}
			break;
			
			default : {
				// ignore
			}
			break;
		}
	}
	
	/**
	 * 
	 * @param func
	 * @return
	 * @throws RDFResourceNotFoundException
	 */
	public RDFFunctionality getFunctionality(Functionality func) 
			throws RDFResourceNotFoundException 
	{
		// get DAO
		RDFFunctionalityDAO dao = this.factory.createFunctionalityDAO();
		return dao.retrieveFunctionalityByName(func.getLabel());
	}

	/**
	 * 
	 */
	public void close() {
		if (this.dataset != null) {
			this.dataset.close();
		}
		this.factory = null;
	}
}
