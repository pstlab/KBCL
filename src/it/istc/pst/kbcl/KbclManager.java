package it.istc.pst.kbcl;

import it.istc.pst.epsl.app.exception.EPSLPlannerInitializationException;
import it.istc.pst.epsl.microkernel.lang.query.EPSLQuery;
import it.istc.pst.kbcl.exception.KbclInitializationException;
import it.istc.pst.kbcl.exception.KbclNoAgentSelectedException;
import it.istc.pst.kbcl.exception.KbclRequestProcessingFailureException;
import it.istc.pst.kbcl.inference.model.owl.OWLKnowledgeBaseFacade;
import it.istc.pst.kbcl.mapping.kb.rdf.exception.RDFPropertyNotFoundException;
import it.istc.pst.kbcl.mapping.kb.rdf.exception.RDFResourceNotFoundException;
import it.istc.pst.kbcl.mapping.ps.ddl.DDLPlanningManager;
import it.istc.pst.kbcl.mapping.ps.ddl.exception.DDLPlanningModelInitializationFailureException;
import it.istc.pst.kbcl.model.Agent;
import it.istc.pst.kbcl.model.AgentType;
import it.istc.pst.kbcl.model.Functionality;
import it.istc.pst.kbcl.model.FunctionalityType;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class KbclManager
{
	private String label;
	
	// inference knowledge base
	private OWLKnowledgeBaseFacade kbInference;
	private Agent focus;
	
	// planning domain generator
	private DDLPlanningManager planningManager;
	
	// statistics
	private long planningTime;
	private long totalPlanningTime;
	private long maxPlanningTime;
	
	/**
	 * 
	 */
	public KbclManager() {
		// set label
		this.label = this.getClass().getSimpleName();
		// get OWL KB facade
		this.kbInference = OWLKnowledgeBaseFacade.getSingletonInstance();
	}
	
	/**
	 * 
	 * @param agent
	 * @throws DDLPlanningModelInitializationFailureException
	 * @throws KbclInitializationException
	 * @throws RDFPropertyNotFoundException
	 * @throws RDFResourceNotFoundException
	 */
	public void setup(Agent agent) 
			throws DDLPlanningModelInitializationFailureException, KbclInitializationException, RDFPropertyNotFoundException, RDFResourceNotFoundException
	{
		try {
			// set focus and subscribe
			this.focus = agent;
			// create planning manager
			this.planningManager = new DDLPlanningManager(this.focus);
		}
		catch (DDLPlanningModelInitializationFailureException ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public long getMappingTime() {
		return (this.planningManager != null) ?  this.planningManager.getMappingTime() : 0;
	}
	
	/**
	 * 
	 * @return
	 */
	public long getMaxMappingTime() {
		return (this.planningManager != null) ? this.planningManager.getMaxMappingTime() : 0;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getNumberOfSynchronizations() {
		return (this.planningManager != null) ? this.planningManager.getNumberOfSynchronizations() : 0;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getNumberOfFunctionalities() {
		return (this.planningManager != null) ? this.planningManager.getNumberOfFunctionalities() : 0;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getNumberOfInternalComponents() {
		return (this.planningManager != null) ? this.planningManager.getNumberOfInternalComponents() : 0;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getNumberOfExternalComponents() {
		return (this.planningManager != null) ? this.planningManager.getNumberOfExternalComponents() : 0;
	}
	
	/**
	 * 
	 * @return
	 */
	public long getLastInferenceTime() {
		return this.kbInference.getLastInferenceTime();
	}
	
	/**
	 * 
	 * @return
	 */
	public long getTotalInferenceTime() {
		return this.kbInference.getTotalInferenceTime();
	}
	
	/**
	 * 
	 * @return
	 */
	public long getMaxInferenceTime() {
		return this.kbInference.getMaxInferenceTime();
	}
	
	/**
	 * 
	 * @return
	 */
	public long getTotalPlanningTime() {
		return this.totalPlanningTime;
	}
	
	/**
	 * 
	 * @return
	 */
	public long getLastPlanningTime() {
		return this.planningTime;
	}

	/**
	 * 
	 * @return
	 */
	public long getMaximumPlanningTime() {
		return this.maxPlanningTime;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Agent> getAgents() {
		return new ArrayList<>(this.kbInference.getAgents());
	}
	
	/**
	 * 
	 * @param label
	 * @return
	 */
	public Agent getAgent(String label) {
		Agent agent = null;
		for (Agent a : this.kbInference.getAgents()) {
			if (a.getLabel().equals(label)) {
				agent = a;
			}
		}
		return agent;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<AgentType> getAgentTypes() {
		return this.kbInference.getAgentTypes();
	}
	
	/**
	 * 
	 * @return
	 */
	public List<FunctionalityType> getFunctionalityTypes() {
		return this.kbInference.getFunctionalityTypes();
	}
	
	/**
	 * 
	 * @return
	 * @throws KbclNoAgentSelectedException
	 */
	public Agent getFocusedAgent() 
			throws KbclNoAgentSelectedException
	{
		// check if agent has been selected
		if (this.focus == null) {
			throw new KbclNoAgentSelectedException("[" + this.label + "]: No agent has been selected!");
		}
		// get agent functionalities
		return this.focus;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getPDBDescription() {
		return this.planningManager.getPDBDescription();
	}
	
	/**
	 * 
	 * @param func
	 * @throws KbclRequestProcessingFailureException
	 * @throws RDFResourceNotFoundException
	 */
	public void plan(Functionality func) 
			throws KbclRequestProcessingFailureException, RDFResourceNotFoundException
	{
		long start = System.currentTimeMillis();
		try {
			// propagate planning request
			this.planningManager.plan(func);
		}
		finally {
			// update planning time
			this.planningTime = System.currentTimeMillis() - start;
			this.totalPlanningTime += this.planningTime;
			this.maxPlanningTime = Math.max(this.maxPlanningTime, this.planningTime);
		}
	}
	
	/**
	 * 
	 * @param query
	 */
	public void query(EPSLQuery query) 
			throws EPSLPlannerInitializationException {
		// propagate query 
		this.planningManager.query(query);
	}
}
