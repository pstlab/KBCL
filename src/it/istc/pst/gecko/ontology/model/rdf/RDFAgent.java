package it.istc.pst.gecko.ontology.model.rdf;

import it.istc.pst.gecko.ontology.kb.rdf.RDFAgentDAO;
import it.istc.pst.gecko.ontology.kb.rdf.RDFKnowledgeBaseFactory;
import it.istc.pst.gecko.ontology.model.Agent;
import it.istc.pst.gecko.ontology.model.AgentType;
import it.istc.pst.gecko.ontology.model.FunctionalityType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author alessandroumbrico
 *
 */
public final class RDFAgent extends Agent 
{
	protected Map<RDFFunctionalityType, List<RDFFunctionality>> functionalities;
	protected List<RDFComponent> components;
	protected List<RDFExternalComponent> neighbors;

	protected RDFAgentDAO dao;
	
	/**
	 * 
	 * @param id
	 * @param label
	 * @param type
	 */
	protected RDFAgent(String id, String label, AgentType type) {
		super(id, label, type);
		// lazy approach
		this.functionalities = null;
		this.components = null;
		this.neighbors = null;
		
		// get factory
		RDFKnowledgeBaseFactory factory = new RDFKnowledgeBaseFactory();
		// get DAO
		this.dao = factory.createAgentDAO();
	}
	
	/**
	 * 
	 * @return
	 */
	public Map<RDFFunctionalityType, List<RDFFunctionality>> getFunctionalities() {
		// check functionalities
		if (this.functionalities == null) {
			// load functionalities
			this.functionalities = this.dao.retrieveAgentFunctionalities(this);
		}
		// get all functionalities
		return new HashMap<RDFFunctionalityType, List<RDFFunctionality>>(this.functionalities);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<RDFFunctionality> getAllFunctionalities() {
		List<RDFFunctionality> list = new ArrayList<>();
		for (RDFFunctionalityType type : this.getFunctionalities().keySet()) {
			for (RDFFunctionality func : this.getFunctionalities().get(type)) {
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
	public List<RDFFunctionality> getFunctionalitiesByType(FunctionalityType type) {
		// check functionalities
		if (this.functionalities == null){
			// load functionalities
			this.functionalities = this.dao.retrieveAgentFunctionalities(this);
		}
		// get functionality by type
		return new ArrayList<>(this.functionalities.get(type));
	}
	
	/**
	 * 
	 * @return
	 */
	public List<RDFComponent> getComponents() {
		// check components
		if (this.components == null) {
			// load components
			this.components = this.dao.retrieveAgentInternalComponents(this); 
		}
		return new ArrayList<RDFComponent>(this.components);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<RDFExternalComponent> getNeighbors() {
		// check neighbors
		if (this.neighbors == null) {
			// load data
			this.neighbors = this.dao.retrieveAgentExternalComponents(this);
		}
		return new ArrayList<>(this.neighbors);
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
