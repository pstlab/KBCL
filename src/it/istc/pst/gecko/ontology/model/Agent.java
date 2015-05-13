package it.istc.pst.gecko.ontology.model;

import it.istc.pst.gecko.ontology.kb.AgentDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author alessandroumbrico
 *
 */
public abstract class Agent 
{
	private String id;
	private String label;
	private AgentType type;
	private Map<FunctionalityType, List<Functionality>> functionalities;
	private List<Component> components;
	private List<ExternalComponent> neighbors;

	protected AgentDAO dao;
	
	/**
	 * 
	 * @param id
	 * @param label
	 * @param type
	 */
	protected Agent(String id, String label, AgentType type) {
		this.id = id;
		this.label = label;
		this.type = type;
		// lazy approach
		this.functionalities = null;
		this.components = null;
		this.neighbors = null;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * 
	 * @return
	 */
	public AgentType getType() {
		return type;
	}
	
	/**
	 * 
	 * @return
	 */
	public Map<FunctionalityType, List<Functionality>> getFunctionalities() {
		// check functionalities
		if (this.functionalities == null) {
			// load functionalities
			this.functionalities = this.dao.retrieveAgentFunctionalities(this);
		}
		// get all functionalities
		return new HashMap<FunctionalityType, List<Functionality>>(this.functionalities);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Functionality> getAllFunctionalities() {
		List<Functionality> list = new ArrayList<Functionality>();
		for (FunctionalityType type : this.getFunctionalities().keySet()) {
			for (Functionality func : this.getFunctionalities().get(type)) {
				list.add(func);
			}
		}
		return list;
	}
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	public List<Functionality> getFunctionalitiesByType(FunctionalityType type) {
		// check functionalities
		if (this.functionalities == null){
			// load functionalities
			this.functionalities = this.dao.retrieveAgentFunctionalities(this);
		}
		// get functionality by type
		return new ArrayList<Functionality>(this.functionalities.get(type));
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Component> getComponents() {
		// check components
		if (this.components == null) {
			// load components
			this.components = this.dao.retrieveAgentInternalComponents(this); 
		}
		return new ArrayList<Component>(this.components);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<ExternalComponent> getNeighbors() {
		// check neighbors
		if (this.neighbors == null) {
			// load data
			this.neighbors = this.dao.retrieveAgentExternalComponents(this);
		}
		return new ArrayList<ExternalComponent>(this.neighbors);
	}
	
	/**
	 * 
	 */
	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
	
	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Agent)) {
			throw new RuntimeException("Uncomparable objects");
		}
		Agent other = (Agent) obj;
		return this.id.equals(other.getId());
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[Agent id=" + this.id + "\n"
				+ "\tlabel= " + this.label +"\n"
				+ "\ttype= " + this.type + "\n"
				+ "\tfunctionalities= " + this.functionalities + "\n"
				+ "\tcomponents= " + this.components + "\n"
				+ "\tneighbors= " + this.neighbors + "]\n";
	}
}
