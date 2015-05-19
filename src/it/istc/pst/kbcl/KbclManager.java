package it.istc.pst.kbcl;

import it.istc.pst.epsl.app.exception.EPSLPlannerInitializationException;
import it.istc.pst.epsl.microkernel.lang.query.EPSLQuery;
import it.istc.pst.kbcl.exception.KbclInitializationException;
import it.istc.pst.kbcl.exception.KbclNoAgentSelectedException;
import it.istc.pst.kbcl.exception.KbclRequestProcessingFailureException;
import it.istc.pst.kbcl.inference.model.owl.OWLAgent;
import it.istc.pst.kbcl.inference.model.owl.OWLAgentType;
import it.istc.pst.kbcl.inference.model.owl.OWLFunctionality;
import it.istc.pst.kbcl.inference.model.owl.OWLFunctionalityType;
import it.istc.pst.kbcl.inference.model.owl.OWLKnowledgeBaseFacade;
import it.istc.pst.kbcl.mapping.kb.rdf.exception.RDFPropertyNotFoundException;
import it.istc.pst.kbcl.mapping.kb.rdf.exception.RDFResourceNotFoundException;
import it.istc.pst.kbcl.mapping.ps.ddl.DDLPlanningManager;
import it.istc.pst.kbcl.mapping.ps.ddl.exception.DDLPlanningModelInitializationFailureException;

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
	private OWLAgent focus;
	
	// planning domain generator
	private DDLPlanningManager planningManager;
	
	// statistics
	private long time;
	private long maxTime;
	private long planningTime;
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
	public void setup(OWLAgent agent) 
			throws DDLPlanningModelInitializationFailureException, KbclInitializationException, RDFPropertyNotFoundException, RDFResourceNotFoundException
	{
		// get start time
		long start = System.currentTimeMillis();
		try 
		{
			// set focus and subscribe
			this.focus = agent;
			// create planning manager
			this.planningManager = new DDLPlanningManager(this.focus);
		}
		catch (DDLPlanningModelInitializationFailureException ex) {
			System.err.println(ex.getMessage());
		}
		finally {
			// set mapping time
			this.time = System.currentTimeMillis() - start;
			// update max
			this.maxTime = Math.max(this.maxTime, this.time);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public long getMappingTime() {
		return this.time;
	}
	
	/**
	 * 
	 * @return
	 */
	public long getMaxMappingTime() {
		return this.maxTime;
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
	public List<OWLAgent> getAgents() {
		return this.kbInference.getAgents();
	}
	
	/**
	 * 
	 * @return
	 */
	public List<OWLAgentType> getAgentTypes() {
		return this.kbInference.getAgentTypes();
	}
	
	/**
	 * 
	 * @return
	 */
	public List<OWLFunctionalityType> getFunctionalityTypes() {
		return this.kbInference.getFunctionalityTypes();
	}
	
	/**
	 * 
	 * @return
	 */
	public long getTotalPlanningTime() {
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
	 * @throws KbclNoAgentSelectedException
	 */
	public OWLAgent getFocusedAgent() 
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
	public void plan(OWLFunctionality func) 
			throws KbclRequestProcessingFailureException, RDFResourceNotFoundException
	{
		long start = System.currentTimeMillis();
		try {
			// propagate planning request
			this.planningManager.plan(func);
		}
		finally {
			// update planning time
			long time = System.currentTimeMillis() - start;
			this.planningTime += time;
			this.maxPlanningTime = Math.max(this.maxPlanningTime, time);
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

//	/**
//	 * 
//	 */
//	@Override
//	public void update(Event event) {
//		// check event
//		switch (event) {
//			case AGENT_ELEMENT_UDPATE_EVENT : {
//				try {
//					// close mapping model
////					this.psModelGenerator.close();
//					
//					// update 
//					
//					// FIXME update mapping KB
////					this.psModelGenerator = new RDFMappingKnowledgeBaseFacade(this.focus);
//					
//					
//					// update focusing agent information
////					this.setup(this.kbMapping.getAgent());
//				}
//				catch (Exception ex) {
//					System.err.println(ex.getMessage());
//					ex.printStackTrace();
//				}
//			}
//			break;
//			
//			default : {
//				// ignore other events
//			}
//			break;
//		}
//	}
	
}
