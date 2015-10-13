package it.istc.pst.kbcl.inference.model.owl;

import it.istc.pst.kbcl.inference.kb.owl.OWLClass;
import it.istc.pst.kbcl.inference.kb.owl.OWLDatasetManager;
import it.istc.pst.kbcl.inference.kb.owl.OWLInstance;
import it.istc.pst.kbcl.inference.kb.owl.exception.OWLClassNotFoundException;
import it.istc.pst.kbcl.model.Agent;
import it.istc.pst.kbcl.model.AgentType;
import it.istc.pst.kbcl.model.ElementType;
import it.istc.pst.kbcl.model.FunctionalityType;

import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.ontology.OntModelSpec;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class OWLKnowledgeBaseFacade
{
	private List<AgentType> agentTypes;
	private List<FunctionalityType> functionTypes;
	private List<ElementType> elementTypes;
	private List<Agent> agents;
	
	private OWLDatasetManager dataset;
	
	private static OWLKnowledgeBaseFacade INSTANCE = null;
	
	/**
	 * 
	 * @return
	 */
	public static final OWLKnowledgeBaseFacade getSingletonInstance() {
		if (INSTANCE == null) {
			INSTANCE = new OWLKnowledgeBaseFacade();
		}
		return INSTANCE;
	}
	
	/**
	 * 
	 * @param model
	 * @param rulesPath
	 * @param aboxPath
	 * @param aboxURL
	 * @param tboxPath
	 * @param tboxURL
	 * @return
	 */
	public static final OWLKnowledgeBaseFacade getSingletonInstance(OntModelSpec model, String rulesPath, String aboxPath, String aboxURL, String tboxPath, String tboxURL) {
		if (INSTANCE == null) {
			INSTANCE = new OWLKnowledgeBaseFacade(model, rulesPath, aboxPath, aboxURL, tboxPath, tboxURL);
		}
		return INSTANCE;
	}
	
	/**
	 * 
	 */
	protected OWLKnowledgeBaseFacade() {
		// lazy approach
		this.agentTypes = null;
		this.functionTypes = null;
		this.elementTypes = null;
		this.agents = null;
		// get data set
		this.dataset = OWLDatasetManager.getSingletonInstance();
	}
	
	/**
	 * 
	 * @param model
	 * @param rulesPath
	 * @param aboxPath
	 * @param aboxURL
	 * @param tboxPath
	 * @param tboxURL
	 */
	protected OWLKnowledgeBaseFacade(OntModelSpec model, String rulesPath, String aboxPath, String aboxURL, String tboxPath, String tboxURL) {
		// lazy approach
		this.agentTypes = null;
		this.functionTypes = null;
		this.elementTypes = null;
		this.agents = null;
		// get data set
		this.dataset = OWLDatasetManager.getSingletonInstance(model, rulesPath, aboxPath, aboxURL, tboxPath, tboxURL);
	}
	
	/**
	 * 
	 * @return
	 */
	public long getTotalInferenceTime() {
		return this.dataset.getTotalInferenceTime();
	}
	
	/**
	 * 
	 * @return
	 */
	public long getLastInferenceTime() {
		return this.dataset.getLastInferenceTime();
	}

	/**
	 * 
	 * @return
	 */
	public long getMaxInferenceTime() {
		return this.dataset.getMaxInferenceTime();
	}
	
	/**
	 * 
	 * @return
	 */
	public List<AgentType> getAgentTypes() {
		if (this.agentTypes == null) {
			try {
				// load data from data-set
				this.agentTypes = this.loadAgentTypes();
			}
			catch (OWLClassNotFoundException ex) {
				this.agentTypes = null;
				System.err.println(ex.getMessage());
			}
		}
		// get types
		return new ArrayList<>(this.agentTypes);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<FunctionalityType> getFunctionalityTypes() {
		if (this.functionTypes == null) {
			try {
				// load data from data-set
				this.functionTypes = this.loadFunctionalityTypes();
			}
			catch (OWLClassNotFoundException ex) {
				this.functionTypes = null;
				System.err.println(ex.getMessage());
			}
		}
		// get types
		return new ArrayList<>(this.functionTypes);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<ElementType> getElementTypes() {
		if (this.elementTypes == null) {
			try {
				// load data from data-set
				this.elementTypes = this.loadElementTypes();
			}
			catch (OWLClassNotFoundException ex) {
				this.elementTypes = null;
				System.err.println(ex.getMessage());
			}
		}
		// get types
		return new ArrayList<>(this.elementTypes);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Agent> getAgents() {
		if (this.agents == null) {
			try {
				// load data from data-set
				this.agents = this.loadAgents();
			}
			catch (OWLClassNotFoundException ex) {
				this.agents = null;
				System.err.println(ex.getMessage());
			}
		}
		// get agents
		return new ArrayList<>(this.agents);
	}
	
	/**
	 * 
	 * @return
	 * @throws OWLClassNotFoundException
	 */
	private List<AgentType> loadAgentTypes() 
			throws OWLClassNotFoundException
	{
		List<AgentType> list = new ArrayList<>();
		// load types
		List<OWLClass> types = this.dataset
				.retrieveAllSubclasses(OWLDatasetManager.CONSTANT_AGENT_TYPE);
		for (OWLClass type : types) {
			list.add(new OWLAgentType(type.getUrl(), type.getLabel()));
		}
		// get list
		return list;
	}
	
	/**
	 * 
	 * @return
	 * @throws OWLClassNotFoundException
	 */
	private List<FunctionalityType> loadFunctionalityTypes() 
			throws OWLClassNotFoundException 
	{
		List<FunctionalityType> list = new ArrayList<>();
		// load types
		List<OWLClass> types = this.dataset
				.retrieveAllSubclasses(OWLDatasetManager.CONSTANT_FUNCTIONALITY_TYPE);
		for (OWLClass type : types) {
			list.add(new OWLFunctionalityType(type.getUrl(), type.getLabel()));
		}
		// get list
		return list;
	}
	
	/**
	 * 
	 * @return
	 * @throws OWLClassNotFoundException
	 */
	private List<ElementType> loadElementTypes() 
			throws OWLClassNotFoundException 
	{
		List<ElementType> list = new ArrayList<>();
		// load types
		List<OWLClass> types = this.dataset
				.retrieveAllSubclasses(OWLDatasetManager.CONSTANT_ELEMENT_TYPE);
		for (OWLClass type : types) {
			list.add(new OWLElementType(type.getUrl(), type.getLabel()));
		}
		// get list
		return list;
	}
	
	/**
	 * 
	 * @return
	 * @throws OWLClassNotFoundException
	 */
	private List<Agent> loadAgents() throws OWLClassNotFoundException 
	{
		List<Agent> list = new ArrayList<>();
		// load agents
		List<OWLInstance> as = this.dataset
				.retrieveAllInstancesOfClass(OWLDatasetManager.CONSTANT_AGENT_TYPE);
		for (OWLInstance a : as) {
			list.add(new OWLAgent(a.getUrl(), a.getLabel(),
					new OWLAgentType(a.getType().getUrl(), a.getType().getLabel())));
		}
		// get list
		return list;
	}

	/**
	 * 
	 */
	public void close() {
		// close data-set manager
		this.dataset.close();
		this.agents = null;
		this.agentTypes = null;
		this.elementTypes = null;
		this.functionTypes = null;
		INSTANCE = null;
		System.gc();
	}
	
}
