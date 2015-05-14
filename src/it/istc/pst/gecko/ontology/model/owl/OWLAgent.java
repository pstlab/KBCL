package it.istc.pst.gecko.ontology.model.owl;

import it.istc.pst.gecko.ontology.kb.owl.OWLAgentDAO;
import it.istc.pst.gecko.ontology.kb.owl.OWLKnowledgeBaseFactory;
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
	
	private OWLAgentDAO dao;
	
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
		
		// get DAOs
		OWLKnowledgeBaseFactory factory = new OWLKnowledgeBaseFactory();
		this.dao = factory.createAgentDAO();
	}
	
	/**
	 * 
	 * @return
	 */
	public List<OWLFunctionality> getChannels() {
		if (this.functionalities == null) {
			// load data
			this.functionalities = this.dao.retrieveAgentFunctionalities(this);
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
			// load data
			this.actuators = this.dao.retrieveAgentActuators(this);
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
			// load data
			this.ports = this.dao.retrieveAgentPorts(this);
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
			// load data
			this.neighbors = this.dao.retrieveAgentNeighbors(this);
		}
		// get neighbors
		return new ArrayList<>(this.neighbors);
	}
}
