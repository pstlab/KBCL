package it.istc.pst.gecko.ontology.model.owl;

import it.istc.pst.gecko.ontology.kb.owl.OWLDatasetManager;
import it.istc.pst.gecko.ontology.kb.owl.OWLInstance;
import it.istc.pst.gecko.ontology.kb.owl.exception.OWLIndividualNotFoundException;
import it.istc.pst.gecko.ontology.kb.owl.exception.OWLPropertyNotFoundException;
import it.istc.pst.gecko.ontology.model.Agent;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class OWLAgent extends Agent 
{
	private List<OWLFunctionality> functionalities;
	private List<OWLElement> actuators;
	private List<OWLPort> ports;
	private List<OWLElement> neighbors;
	
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
		this.actuators = null;
		this.ports = null;
		this.neighbors = null;
		
		// get OWL data-set manager
		this.dataset = OWLDatasetManager.getSingletonInstance();
	}
	
	/**
	 * 
	 * @return
	 */
	public List<OWLFunctionality> getFunctionalities() {
		if (this.functionalities == null) {
			try {
				// load data from data-set
				this.functionalities = new ArrayList<>();
				List<OWLInstance> funcs =  this.dataset
						.retrieveAllInstancesRelatedByProperty(this.label, 
								OWLDatasetManager.PROPERTY_LABEL_HAS_CHANNEL);
	
				// create channel list
				for (OWLInstance f : funcs) {
					// TODO : create and add
				}
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
	public List<OWLElement> getActuators() {
		if (this.actuators == null) {
			try {
				// load data from data-set
				this.actuators = new ArrayList<>();
				List<OWLInstance> acts = this.dataset
						.retrieveAllInstancesRelatedByProperty(this.label, 
								OWLDatasetManager.PROPERTY_LABEL_HAS_ACTUATOR);
				
				// create actuator list
				for (OWLInstance act : acts) {
					// TODO: create and add
				}
			}
			catch (OWLIndividualNotFoundException | OWLPropertyNotFoundException ex) {
				this.actuators = null;
				System.err.println(ex.getMessage());
			}
		}
		// get engines
		return new ArrayList<>(this.actuators);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<OWLPort> getPorts() {
		if (this.ports == null) {
			try {
				// load data from data-set
				this.ports = new ArrayList<>();
				List<OWLInstance> ps = this.dataset
						.retrieveAllInstancesRelatedByProperty(this.label, 
								OWLDatasetManager.PROPERTY_LABEL_HAS_PORT);
				
				// create port list
				for (OWLInstance p : ps) {
					// TODO : create and add
				}
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
	 * @return
	 */
	public List<OWLElement> getNeighbors() {
		if (this.neighbors == null) {
			try {
				// load data from data-set
				this.neighbors = new ArrayList<>();
				List<OWLInstance> ns = this.dataset
						.retrieveAllInstancesRelatedByProperty(this.label, 
								OWLDatasetManager.PROPERTY_LABEL_HAS_NEIGHBOR);
				
				// create neighbor list
				for (OWLInstance n : ns) {
					// TODO : create and add
				}
			}
			catch (OWLIndividualNotFoundException | OWLPropertyNotFoundException ex) {
				this.neighbors = null;
				System.err.println(ex.getMessage());
			}
		}
		// get neighbors
		return new ArrayList<>(this.neighbors);
	}
}
