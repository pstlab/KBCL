package it.istc.pst.gecko.ontology.model.owl;

import it.istc.pst.gecko.ontology.kb.owl.OWLClass;
import it.istc.pst.gecko.ontology.kb.owl.OWLDatasetManager;
import it.istc.pst.gecko.ontology.kb.owl.OWLInstance;
import it.istc.pst.gecko.ontology.kb.owl.exception.OWLClassNotFoundException;
import it.istc.pst.gecko.ontology.model.Event;
import it.istc.pst.gecko.ontology.model.EventObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class OWLKnowledgeBaseFacade implements EventObserver
{
	private List<OWLAgentType> agentTypes;
	private List<OWLFunctionalityType> functionTypes;
	private List<OWLElementType> elementTypes;
	private List<OWLAgent> agents;
	private OWLAgent focus;
	
	private OWLDatasetManager dataset;
	
	private static OWLKnowledgeBaseFacade INSTANCE = null;
	public static final OWLKnowledgeBaseFacade getSingletonInstance() {
		if (INSTANCE == null) {
			INSTANCE = new OWLKnowledgeBaseFacade();
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
		this.focus = null;
		// get data set
		this.dataset = OWLDatasetManager.getSingletonInstance();
	}
	
	/**
	 * 
	 * @return
	 */
	public List<OWLAgentType> getAgentTypes() {
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
	public List<OWLFunctionalityType> getFunctionalityTypes() {
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
	public List<OWLElementType> getElementTypes() {
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
	public List<OWLAgent> getAgents() {
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
	 * @param agent
	 * @return
	 */
	public OWLAgent setFocus(OWLAgent agent) {
		if (this.focus != null) {
			// delete subscription
			this.focus.unSubscribe(this);
		}
		
		// set focus and subscribe
		this.focus = agent;
		this.focus.subscribe(this);
		return this.focus;
	}
	
	/**
	 * 
	 * @param index
	 * @return
	 */
	public OWLAgent setFocus(int index) {
		if (this.focus != null) {
			// delete subscription
			this.focus.unSubscribe(this);
		}
		// set focus and subscribe
		this.focus = this.agents.get(index);
		this.focus.subscribe(this);
		return this.focus;
	}
	
	/**
	 * 
	 */
	@Override
	public void update(Event event) {
		// check event
		switch (event) {
			case AGENT_ELEMENT_UDPATE_EVENT : {
				// update focusing agent information
			}
			break;
			
			default : {
				// ignore other events
			}
			break;
		}
	}
	
	/**
	 * 
	 * @return
	 * @throws OWLClassNotFoundException
	 */
	private List<OWLAgentType> loadAgentTypes() 
			throws OWLClassNotFoundException
	{
		List<OWLAgentType> list = new ArrayList<>();
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
	private List<OWLFunctionalityType> loadFunctionalityTypes() 
			throws OWLClassNotFoundException 
	{
		List<OWLFunctionalityType> list = new ArrayList<>();
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
	private List<OWLElementType> loadElementTypes() 
			throws OWLClassNotFoundException 
	{
		List<OWLElementType> list = new ArrayList<>();
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
	private List<OWLAgent> loadAgents() throws OWLClassNotFoundException 
	{
		List<OWLAgent> list = new ArrayList<>();
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
		this.focus = null;
		this.agentTypes = null;
		this.elementTypes = null;
		this.functionTypes = null;
		INSTANCE = null;
	}

}
