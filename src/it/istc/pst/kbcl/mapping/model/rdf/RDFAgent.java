package it.istc.pst.kbcl.mapping.model.rdf;

import it.istc.pst.kbcl.mapping.kb.rdf.RDFAgentDAO;
import it.istc.pst.kbcl.mapping.kb.rdf.RDFMappingKnowledgeBaseFactory;
import it.istc.pst.kbcl.mapping.kb.rdf.exception.RDFPropertyNotFoundException;
import it.istc.pst.kbcl.mapping.kb.rdf.exception.RDFResourceNotFoundException;
import it.istc.pst.kbcl.model.Agent;
import it.istc.pst.kbcl.model.AgentType;
import it.istc.pst.kbcl.model.Element;
import it.istc.pst.kbcl.model.Functionality;
import it.istc.pst.kbcl.model.FunctionalityType;

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
		RDFMappingKnowledgeBaseFactory factory = new RDFMappingKnowledgeBaseFactory();
		// get DAO
		this.dao = factory.createAgentDAO();
	}
	
	/**
	 * 
	 * @return
	 */
	public Map<RDFFunctionalityType, List<RDFFunctionality>> getFunctionalityIndex() {
		// check functionalities
		if (this.functionalities == null) {
			// load functionalities
			this.functionalities = this.dao.retrieveAgentFunctionalities(this);
		}
		// get all functionalities
		return new HashMap<RDFFunctionalityType, List<RDFFunctionality>>(this.functionalities);
	}
	
	@Override
	public Functionality getFunctionality(String label) {
		System.exit(1);
		return null;
	}

	@Override
	public boolean removeComponent(String label) {
		System.exit(1);
		return false;
	}
	
	@Override
	public boolean disconnectNeighbor(String label) {
		System.exit(1);
		return false;
	}
	
	@Override
	public List<Functionality> getFunctionalities() {
		System.exit(1);
		return null;
	}
	
	@Override
	public List<Element> getComponents() {
		System.exit(1);
		return null;
	}

	@Override
	public List<Element> getNeighbors() {
		System.exit(1);
		return null;
	}
	
	/**
	 * 
	 * @param funcName
	 * @param funcType
	 * @throws RDFPropertyNotFoundException
	 * @throws RDFResourceNotFoundException
	 */
	public void addFunctionality(String funcName) 
			throws RDFPropertyNotFoundException, RDFResourceNotFoundException 
	{
		// assert statement
		this.dao.addFunctionality(this.label, funcName);
		// update functionalities
		this.functionalities = this.dao.retrieveAgentFunctionalities(this);
	}
	
	/**
	 * 
	 */
	@Override
	public void addComponent(String compName) 
	{
		try {
			// assert statement
			this.dao.addComponent(this.label, compName);
			// update components
			this.components = this.dao.retrieveAgentInternalComponents(this);
		}
		catch (RDFPropertyNotFoundException | RDFResourceNotFoundException ex) {
			System.err.println(ex.getMessage());
		}
		
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
	public List<RDFComponent> getRDFComponents() {
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
	public List<RDFExternalComponent> getRDFNeighbors() {
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
