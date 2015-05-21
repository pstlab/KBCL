package it.istc.pst.kbcl.inference.model.owl;

import it.istc.pst.kbcl.inference.kb.owl.OWLDatasetManager;
import it.istc.pst.kbcl.inference.kb.owl.OWLInstance;
import it.istc.pst.kbcl.inference.kb.owl.exception.OWLIndividualNotFoundException;
import it.istc.pst.kbcl.inference.kb.owl.exception.OWLPropertyNotFoundException;
import it.istc.pst.kbcl.model.Agent;
import it.istc.pst.kbcl.model.Element;
import it.istc.pst.kbcl.model.Event;
import it.istc.pst.kbcl.model.EventObserver;
import it.istc.pst.kbcl.model.Functionality;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class OWLAgent extends Agent implements EventObserver
{
	private Map<String, OWLFunctionality> functionalities;
	private Map<String, OWLElement> crossTransfers;
	private Map<String, OWLElement> conveyors;
	private Map<String, OWLPort> ports;
	private Map<String, OWLElement> neighbors;
	
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
	}
	
	/**
	 * 
	 * @return
	 */
	@Override
	public List<Element> getComponents() {
		List<Element> list = new ArrayList<>();
		list.addAll(this.getConveyors());
		list.addAll(this.getCrossTransfers());
		list.addAll(this.getPorts());
		return list;
	}
	
	/**
	 * 
	 * @return
	 */
	@Override
	public List<Functionality> getFunctionalities() {
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
		return new ArrayList<Functionality>(this.functionalities.values());
	}
	
	/**
	 * 
	 */
	@Override
	public Functionality getFunctionality(String label) {
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
		// get functionality
		return this.functionalities.get(label);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Element> getConveyors() {
		if (this.conveyors == null) {
			try {
				// load data from data-set
				this.conveyors = this.loadAgentConveyors();
			}
			catch (OWLIndividualNotFoundException | OWLPropertyNotFoundException ex) {
				this.conveyors = null;
				System.err.println(ex.getMessage());
			}
		}
		// get engines
		return new ArrayList<Element>(this.conveyors.values());
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Element> getCrossTransfers() {
		if (this.crossTransfers == null) {
			try {
				// load data from data-set
				this.crossTransfers = this.loadAgentCrossTransfers();
			}
			catch (OWLIndividualNotFoundException | OWLPropertyNotFoundException ex) {
				this.crossTransfers = null;
				System.err.println(ex.getMessage());
			}
		}
		// get engines
		return new ArrayList<Element>(this.crossTransfers.values());
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
		return new ArrayList<>(this.ports.values());
	}
	
	/**
	 * 
	 * @param elementName
	 */
	@Override
	public void addComponent(String label) {
		try {
			// assert property
			this.dataset.assertStatement(this.getLabel(), 
					OWLDatasetManager.PROPERTY_LABEL_HAS_ELEMENT, label);
			// update agent
			this.update(Event.AGENT_ELEMENT_UDPATE_EVENT);
		}
		catch (OWLPropertyNotFoundException | OWLIndividualNotFoundException ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	/**
	 * 
	 * @param label
	 */
	@Override
	public boolean removeComponent(String label) {
		boolean removed = true;
		try {
			// remove property
			this.dataset.removeStatement(this.getLabel(), 
					OWLDatasetManager.PROPERTY_LABEL_HAS_ELEMENT, 
					label);
			
			// update agent
			this.update(Event.AGENT_ELEMENT_UDPATE_EVENT);
		}
		catch (OWLPropertyNotFoundException | OWLIndividualNotFoundException ex) {
			removed = false;
			System.err.println(ex.getMessage());
		}
		return removed;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Element> getNeighbors() {
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
		return new ArrayList<Element>(this.neighbors.values());
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
			
			default : {
				// ignore
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
	private Map<String, OWLPort> loadAgentPorts() 
			throws OWLIndividualNotFoundException, OWLPropertyNotFoundException 
	{
		// initialize index
		Map<String, OWLPort> index = new HashMap<>();
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
			// add entry to index
			index.put(i.getLabel(), i);
		}
		// get index
		return index;
	}

	/**
	 * 
	 * @return
	 * @throws OWLIndividualNotFoundException
	 * @throws OWLPropertyNotFoundException
	 */
	private Map<String, OWLElement> loadAgentNeighbors() 
			throws OWLIndividualNotFoundException, OWLPropertyNotFoundException 
	{
		// initialize index
		Map<String, OWLElement> index = new HashMap<>();
		List<OWLInstance> ns = this.dataset
				.retrieveAllInstancesRelatedByProperty(this.label, 
						OWLDatasetManager.PROPERTY_LABEL_HAS_NEIGHBOR);
		
		// create neighbor list
		for (OWLInstance n : ns) {
			// add neighbor
			OWLElement el = new OWLElement(n.getUrl(), n.getLabel(), 
					new OWLElementType(n.getType().getUrl(), n.getType().getLabel()));
			// add entry to index
			index.put(el.getLabel(), el);
		}
		// get index
		return index;
	}
	
	/**
	 * 
	 * @return
	 * @throws OWLIndividualNotFoundException
	 * @throws OWLPropertyNotFoundException
	 */
	private Map<String, OWLElement> loadAgentCrossTransfers() 
			throws OWLIndividualNotFoundException, OWLPropertyNotFoundException
	{
		// initialize index
		Map<String, OWLElement> index = new HashMap<>();
		List<OWLInstance> acts = this.dataset
				.retrieveAllInstancesRelatedByProperty(this.label, 
						OWLDatasetManager.PROPERTY_LABEL_HAS_CROSS_TRANSFER);
		
		// create actuator list
		for (OWLInstance act : acts) {
			OWLElement el = new OWLElement(act.getUrl(), act.getLabel(), 
					new OWLElementType(act.getType().getUrl(), act.getType().getLabel()));
			// check element type
			if (el.getType().getLabel().equals(OWLDatasetManager.CONSTANT_CROSS_TRANSFER_TYPE)) {
				// add entry to index
				index.put(el.getLabel(), el);
			}
		}
		
		// get index
		return index;
	}
	
	/**
	 * 
	 * @return
	 * @throws OWLIndividualNotFoundException
	 * @throws OWLPropertyNotFoundException
	 */
	private Map<String, OWLElement> loadAgentConveyors() 
			throws OWLIndividualNotFoundException, OWLPropertyNotFoundException
	{
		// initialize index
		Map<String, OWLElement> index = new HashMap<>();
		List<OWLInstance> acts = this.dataset
				.retrieveAllInstancesRelatedByProperty(this.label, 
						OWLDatasetManager.PROPERTY_LABEL_HAS_CONVEYOR);
		
		// create actuator list
		for (OWLInstance act : acts) {
			OWLElement el = new OWLElement(act.getUrl(), act.getLabel(), 
					new OWLElementType(act.getType().getUrl(), act.getType().getLabel()));
			// check element type
			if (el.getType().getLabel().equals(OWLDatasetManager.CONSTANT_CONVEYOR_TYPE)) {
				// add entry to index
				index.put(el.getLabel(), el);
			}
		}
		
		// get index
		return index;
	}

	/**
	 * 
	 * @return
	 * @throws OWLIndividualNotFoundException
	 * @throws OWLPropertyNotFoundException
	 */
	private Map<String, OWLFunctionality> loadAgentFunctionalities() 
			throws OWLIndividualNotFoundException, OWLPropertyNotFoundException
	{
		// initialize index
		Map<String, OWLFunctionality> index = new HashMap<>();
		List<OWLInstance> funcs =  this.dataset
				.retrieveAllInstancesRelatedByProperty(this.label, 
						OWLDatasetManager.PROPERTY_LABEL_HAS_CHANNEL);

		// create channel list
		for (OWLInstance f : funcs) {
			// add functionality
			OWLFunctionality func = new OWLFunctionality(f.getUrl(), f.getLabel(), 
					new OWLFunctionalityType(f.getType().getUrl(), f.getType().getLabel()));
			// add entry to index
			index.put(func.getLabel(), func);
		}
		
		// get index
		return index;
	}
}
