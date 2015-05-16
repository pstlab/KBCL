package it.istc.pst.kbcl.ontology.model.owl;

import it.istc.pst.kbcl.model.Agent;
import it.istc.pst.kbcl.model.Event;
import it.istc.pst.kbcl.model.EventObserver;
import it.istc.pst.kbcl.model.EventPublisher;
import it.istc.pst.kbcl.ontology.kb.owl.OWLDatasetManager;
import it.istc.pst.kbcl.ontology.kb.owl.OWLInstance;
import it.istc.pst.kbcl.ontology.kb.owl.exception.OWLIndividualNotFoundException;
import it.istc.pst.kbcl.ontology.kb.owl.exception.OWLPropertyNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class OWLAgent extends Agent implements EventObserver, EventPublisher
{
	private List<OWLFunctionality> functionalities;
//	private List<OWLElement> internalElements;
	private List<OWLPort> ports;
	private List<OWLElement> neighbors;
	
	private List<EventObserver> observers;
	private OWLDatasetManager dataset;
	
	/**
	 * 
	 * @param id
	 * @param label
	 * @param type
	 */
	protected OWLAgent(String id, String label, OWLAgentType type) {
		super(id, label, type);
		
		// lazy approach
		this.functionalities = null;
//		this.internalElements = null;
		this.ports = null;
		this.neighbors = null;
		
		// get OWL data-set manager
		this.dataset = OWLDatasetManager.getSingletonInstance();

		// initialize observer list
		this.observers = new ArrayList<>();
	}
	
	/**
	 * 
	 * @return
	 */
	public List<OWLFunctionality> getFunctionalities() {
		if (this.functionalities == null) {
			try {
				// load data from data-set
				this.functionalities = this.loadAgentFunctionalities();
			}
			catch (OWLIndividualNotFoundException | OWLPropertyNotFoundException ex) {
				this.functionalities = null;
				System.err.println(ex.getMessage());
			}
		}
		// get channels
		return new ArrayList<>(this.functionalities);
	}
	
//	/**
//	 * 
//	 * @return
//	 */
//	public List<OWLElement> getInternalElements() {
//		if (this.internalElements == null) {
//			try {
//				// load data from data-set
//				this.internalElements = new ArrayList<>(this.loadAgentInternalElements());
//			}
//			catch (OWLIndividualNotFoundException | OWLPropertyNotFoundException ex) {
//				this.internalElements = null;
//				System.err.println(ex.getMessage());
//			}
//		}
//		// get engines
//		return new ArrayList<>(this.internalElements);
//	}
	
	/**
	 * 
	 * @return
	 */
	public List<OWLPort> getPorts() {
		if (this.ports == null) {
			try {
				// load data from data-set
				this.ports = this.loadAgentPorts();
			}
			catch (OWLIndividualNotFoundException | OWLPropertyNotFoundException ex) {
				this.ports = null;
				System.err.println(ex.getMessage());
			}
		}
		// get ports
		return new ArrayList<>(this.ports);
	}
	
	/**
	 * 
	 * @param elementName
	 */
	public void addElement(OWLElement element) {
		try {
			// assert property
			this.dataset.assertStatement(this.getLabel(), 
					OWLDatasetManager.PROPERTY_LABEL_HAS_ELEMENT, element.getLabel());
			// update agent
			this.update(Event.AGENT_ELEMENT_UDPATE_EVENT);
		}
		catch (OWLPropertyNotFoundException | OWLIndividualNotFoundException ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	/**
	 * 
	 * @param elementName
	 */
	public void removeElement(OWLElement element) {
		try {
			// remove property
			this.dataset.removeStatement(this.getLabel(), 
					OWLDatasetManager.PROPERTY_LABEL_HAS_ELEMENT, element.getLabel());
			// update agent
			this.update(Event.AGENT_ELEMENT_UDPATE_EVENT);
		}
		catch (OWLPropertyNotFoundException | OWLIndividualNotFoundException ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	/**
	 * 
	 * @param index
	 */
	public OWLElement removeElement(int index) {
		// get element to remove
		OWLElement element = this.ports.get(index);
		try {
			// remove property
			this.dataset.removeStatement(this.getLabel(), 
					OWLDatasetManager.PROPERTY_LABEL_HAS_ELEMENT, element.getLabel());
			// update agent
			this.update(Event.AGENT_ELEMENT_UDPATE_EVENT);
		}
		catch (OWLPropertyNotFoundException | OWLIndividualNotFoundException ex) {
			System.err.println(ex.getMessage());
		}
		// get removed element
		return element;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<OWLElement> getNeighbors() {
		if (this.neighbors == null) {
			try {
				// load data from data-set
				this.neighbors = this.loadAgentNeighbors();
			}
			catch (OWLIndividualNotFoundException | OWLPropertyNotFoundException ex) {
				this.neighbors = null;
				System.err.println(ex.getMessage());
			}
		}
		// get neighbors
		return new ArrayList<>(this.neighbors);
	}
	
	/**
	 * 
	 * @param observer
	 */
	@Override
	public void subscribe(EventObserver observer) {
		// add observer
		this.observers.add(observer);
	}
	
	/**
	 * 
	 */
	@Override
	public void unSubscribe(EventObserver observer) {
		if (this.observers.contains(observer)) {
			// remove 
			this.observers.remove(observer);
		}
	}
	
	/**
	 * 
	 */
	@Override
	public void update(Event event) {
		switch (event) {
			case AGENT_ELEMENT_UDPATE_EVENT : 
			case PORT_CONNECTION_UPDATE_EVENT : {
				try {
					// update agent ports
					this.ports = this.loadAgentPorts();
					// update agent's neighbors
					this.neighbors = this.loadAgentNeighbors();
					// update agent's functionalities
					this.functionalities = this.loadAgentFunctionalities();
				}
				catch (OWLIndividualNotFoundException | OWLPropertyNotFoundException ex) {
					this.ports = null;
					this.neighbors = null;
					this.functionalities = null;
					System.err.println(ex.getMessage());
				}
				finally {
					// propagate update to agent's observers
					for (EventObserver o : this.observers) {
						// notify observers
						o.update(Event.AGENT_ELEMENT_UDPATE_EVENT);
					}			
				}
			}
			break;
		}
	}
	
	/**
	 * 
	 * @return
	 * @throws OWLIndividualNotFoundException
	 * @throws OWLPropertyNotFoundException
	 */
	private List<OWLPort> loadAgentPorts() 
			throws OWLIndividualNotFoundException, OWLPropertyNotFoundException 
	{
		List<OWLPort> list = new ArrayList<>();
		List<OWLInstance> ps = this.dataset
				.retrieveAllInstancesRelatedByProperty(this.label, 
						OWLDatasetManager.PROPERTY_LABEL_HAS_PORT);
		
		// create port list
		for (OWLInstance p : ps) {
			// add port
			OWLPort i = new OWLPort(p.getUrl(), p.getLabel(), 
					new OWLElementType(p.getType().getUrl(), p.getType().getLabel()));
			// subscribe
			i.subscribe(this);
			// add 
			list.add(i);
		}
		// get list
		return list;
	}

	/**
	 * 
	 * @return
	 * @throws OWLIndividualNotFoundException
	 * @throws OWLPropertyNotFoundException
	 */
	private List<OWLElement> loadAgentNeighbors() 
			throws OWLIndividualNotFoundException, OWLPropertyNotFoundException 
	{
		List<OWLElement> list = new ArrayList<>();
		List<OWLInstance> ns = this.dataset
				.retrieveAllInstancesRelatedByProperty(this.label, 
						OWLDatasetManager.PROPERTY_LABEL_HAS_NEIGHBOR);
		
		// create neighbor list
		for (OWLInstance n : ns) {
			// add neighbor
			list.add(new OWLElement(n.getUrl(), n.getLabel(), 
					new OWLElementType(n.getType().getUrl(), n.getType().getLabel())));
		}
		// get list
		return list;
	}
	
	/**
	 * 
	 * @return
	 * @throws OWLIndividualNotFoundException
	 * @throws OWLPropertyNotFoundException
	 */
	private List<OWLElement> loadAgentInternalElements() 
			throws OWLIndividualNotFoundException, OWLPropertyNotFoundException
	{
		List<OWLElement> list = new ArrayList<>();
		List<OWLInstance> acts = this.dataset
				.retrieveAllInstancesRelatedByProperty(this.label, 
						OWLDatasetManager.PROPERTY_LABEL_HAS_INTERNAL_ELEMENT);
		
		// create actuator list
		for (OWLInstance act : acts) {
			list.add(new OWLElement(act.getUrl(), act.getLabel(), 
					new OWLElementType(act.getType().getUrl(), act.getType().getLabel())));
		}
		
		// get list
		return list;
	}

	/**
	 * 
	 * @return
	 * @throws OWLIndividualNotFoundException
	 * @throws OWLPropertyNotFoundException
	 */
	private List<OWLFunctionality> loadAgentFunctionalities() 
			throws OWLIndividualNotFoundException, OWLPropertyNotFoundException
	{
		List<OWLFunctionality> list = new ArrayList<>();
		List<OWLInstance> funcs =  this.dataset
				.retrieveAllInstancesRelatedByProperty(this.label, 
						OWLDatasetManager.PROPERTY_LABEL_HAS_CHANNEL);

		// create channel list
		for (OWLInstance f : funcs) {
			// add functionality
			list.add(new OWLFunctionality(f.getUrl(), f.getLabel(), 
					new OWLFunctionalityType(f.getType().getUrl(), f.getType().getLabel())));
		}
		
		// get list
		return list;
	}
}
