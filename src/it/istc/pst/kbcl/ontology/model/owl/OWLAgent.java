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
	private List<OWLElement> crossTransfers;
	private List<OWLElement> conveyors;
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
		this.crossTransfers = null;
		this.conveyors = null;
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
	public List<OWLElement> getComponents() {
		List<OWLElement> list = new ArrayList<>();
		list.addAll(this.getConveyors());
		list.addAll(this.getCrossTransfers());
		list.addAll(this.getPorts());
		return list;
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
	
	/**
	 * 
	 * @return
	 */
	public List<OWLElement> getConveyors() {
		if (this.conveyors == null) {
			try {
				// load data from data-set
				this.conveyors = new ArrayList<>(this.loadAgentConveyors());
			}
			catch (OWLIndividualNotFoundException | OWLPropertyNotFoundException ex) {
				this.conveyors = null;
				System.err.println(ex.getMessage());
			}
		}
		// get engines
		return new ArrayList<>(this.conveyors);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<OWLElement> getCrossTransfers() {
		if (this.crossTransfers == null) {
			try {
				// load data from data-set
				this.crossTransfers = new ArrayList<>(this.loadAgentCrossTransfers());
			}
			catch (OWLIndividualNotFoundException | OWLPropertyNotFoundException ex) {
				this.crossTransfers = null;
				System.err.println(ex.getMessage());
			}
		}
		// get engines
		return new ArrayList<>(this.crossTransfers);
	}
	
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
	public OWLElement removeComponent(int index) {
		// get components
		List<OWLElement> comps = this.getComponents();
		// get component to remove
		OWLElement element = comps.get(index);
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
			
			case AGENT_ELEMENT_UDPATE_EVENT : {
				try {
					// update conveyors
					this.conveyors = this.loadAgentConveyors();
					// update cross transfers
					this.crossTransfers = this.loadAgentCrossTransfers();
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
	private List<OWLElement> loadAgentCrossTransfers() 
			throws OWLIndividualNotFoundException, OWLPropertyNotFoundException
	{
		List<OWLElement> list = new ArrayList<>();
		List<OWLInstance> acts = this.dataset
				.retrieveAllInstancesRelatedByProperty(this.label, 
						OWLDatasetManager.PROPERTY_LABEL_HAS_ELEMENT);
		
		// create actuator list
		for (OWLInstance act : acts) {
			OWLElement el = new OWLElement(act.getUrl(), act.getLabel(), 
					new OWLElementType(act.getType().getUrl(), act.getType().getLabel()));
			// check element type
			if (el.getType().getLabel().equals(OWLDatasetManager.CONSTANT_CROSS_TRANSFER_TYPE)) {
				// add element
				list.add(el);
			}
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
	private List<OWLElement> loadAgentConveyors() 
			throws OWLIndividualNotFoundException, OWLPropertyNotFoundException
	{
		List<OWLElement> list = new ArrayList<>();
		List<OWLInstance> acts = this.dataset
				.retrieveAllInstancesRelatedByProperty(this.label, 
						OWLDatasetManager.PROPERTY_LABEL_HAS_ELEMENT);
		
		// create actuator list
		for (OWLInstance act : acts) {
			OWLElement el = new OWLElement(act.getUrl(), act.getLabel(), 
					new OWLElementType(act.getType().getUrl(), act.getType().getLabel()));
			// check element type
			if (el.getType().getLabel().equals(OWLDatasetManager.CONSTANT_CONVEYOR_TYPE)) {
				// add element
				list.add(el);
			}
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
