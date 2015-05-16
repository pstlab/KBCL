package it.istc.pst.kbcl;

import it.istc.pst.epsl.app.EPSLApplicationBuilder;
import it.istc.pst.epsl.app.EPSLEmbeddedPlanner;
import it.istc.pst.epsl.app.apsi.APSIEmbeddedPlanner;
import it.istc.pst.epsl.app.exception.EPSLPlannerInitializationException;
import it.istc.pst.epsl.app.exception.EPSLUnsolvableGoalException;
import it.istc.pst.epsl.microkernel.lang.EPSLLanguageFactory;
import it.istc.pst.epsl.microkernel.lang.EPSLParameterTypes;
import it.istc.pst.epsl.microkernel.lang.EPSLTimelineDescriptor;
import it.istc.pst.epsl.microkernel.lang.EPSLTokenDescriptor;
import it.istc.pst.epsl.microkernel.lang.query.EPSLLanguageQueryFactory;
import it.istc.pst.epsl.microkernel.lang.query.EPSLQuery;
import it.istc.pst.epsl.microkernel.lang.query.EPSLQueryTypes;
import it.istc.pst.epsl.microkernel.lang.query.get.EPSLGetFlexibleTimelinesQuery;
import it.istc.pst.kbcl.exception.KbclInitializationException;
import it.istc.pst.kbcl.exception.KbclNoAgentSelectedException;
import it.istc.pst.kbcl.exception.KbclRequestProcessingFailureException;
import it.istc.pst.kbcl.mapping.kb.rdf.exception.RDFPropertyNotFoundException;
import it.istc.pst.kbcl.mapping.kb.rdf.exception.RDFResourceNotFoundException;
import it.istc.pst.kbcl.mapping.ps.ddl.DDLPlanningModel;
import it.istc.pst.kbcl.mapping.ps.ddl.DDLValue;
import it.istc.pst.kbcl.mapping.ps.ddl.exception.DDLPlanningModelInitializationFailureException;
import it.istc.pst.kbcl.model.Event;
import it.istc.pst.kbcl.model.EventObserver;
import it.istc.pst.kbcl.model.Functionality;
import it.istc.pst.kbcl.ontology.model.owl.OWLAgent;
import it.istc.pst.kbcl.ontology.model.owl.OWLAgentType;
import it.istc.pst.kbcl.ontology.model.owl.OWLFunctionalityType;
import it.istc.pst.kbcl.ontology.model.owl.OWLKnowledgeBaseFacade;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class KbclManager implements EventObserver
{
	private String label;
	private OWLKnowledgeBaseFacade facade;
	private OWLAgent focus;
	
	// planning domain
	private static final long HORIZON = 1000;
	private static final String PS_FOLDER = "ps";
	private DDLPlanningModel model;
	
	// planner 
	private static final Class<APSIEmbeddedPlanner> EPSL_APP_CLASS = APSIEmbeddedPlanner.class;
	private EPSLEmbeddedPlanner planner;
	private EPSLLanguageFactory factory;
	private EPSLLanguageQueryFactory queryFactory;
	
	/**
	 * 
	 */
	public KbclManager() {
		// set label
		this.label = this.getClass().getSimpleName();
		// get OWL KB facade
		this.facade = OWLKnowledgeBaseFacade.getSingletonInstance();
	}
	
	/**
	 * 
	 * @param agent
	 * @throws DDLPlanningModelInitializationFailureException
	 * @throws KbclInitializationException
	 * @throws RDFPropertyNotFoundException
	 * @throws RDFResourceNotFoundException
	 */
	public void initialize(OWLAgent agent) 
			throws DDLPlanningModelInitializationFailureException, KbclInitializationException, RDFPropertyNotFoundException, RDFResourceNotFoundException
	{
		// setup manager
		this.setup(agent);
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
		this.focus = this.facade.getAgents().get(index);
		this.focus.subscribe(this);
		return this.focus;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<OWLAgent> getAgents() {
		return this.facade.getAgents();
	}
	
	/**
	 * 
	 * @return
	 */
	public List<OWLAgentType> getAgentTypes() {
		return this.facade.getAgentTypes();
	}
	
	/**
	 * 
	 * @return
	 */
	public List<OWLFunctionalityType> getFunctionalityTypes() {
		return this.facade.getFunctionalityTypes();
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
		String desc = "";
		try {
			// create query
			EPSLGetFlexibleTimelinesQuery query = this.queryFactory.
					createQuery(EPSLQueryTypes.GET_FLEXIBLE_TIMELINES);
			
			// send query
			this.planner.query(query);
			// get response
			for (EPSLTimelineDescriptor tl : query.getTimelines()) {
				desc += "Flexible Timeline " + tl + "\n";
				for (EPSLTokenDescriptor token : tl.getTokens()) {
					desc += "\t" + token + "\n";
				}
				desc += "\n";
 			}
			desc += "\n";
		} catch (InterruptedException ex) {
			System.err.println(ex.getMessage());
		}
		return desc;
	}
	
	/**
	 * 
	 * @param func
	 * @throws KbclRequestProcessingFailureException
	 */
	public void planRequest(Functionality func) 
			throws KbclRequestProcessingFailureException 
	{
		// get the related DDLValue
		DDLValue value = this.model.getDDLValue(func);

		// create timeline descriptor
		EPSLTimelineDescriptor tl = this.factory.createTimelineDescriptor(value.getComponent().getName(), value.getComponent().getTimeline(), false);
		// create token request
		EPSLTokenDescriptor goal = this.factory.createTokenDescriptor(
				tl, 
				value.getValue(),
				true,
				new long[] {1, this.model.getHorizon()}, 
				new long[] {1, this.model.getHorizon()}, 
				new long[] {value.getDmin(), value.getDmax()},
				new String[] {}, 
				new EPSLParameterTypes[] {}, 
				new long[][] {}, new String[][] {});
		
		try {
			// plan goal 
			this.plan(goal);
		}
		catch (EPSLUnsolvableGoalException | EPSLPlannerInitializationException ex) {
			throw new KbclRequestProcessingFailureException(ex.getMessage());
		}
	}
	
	/**
	 * 
	 * @param goal
	 * @return
	 * @throws EPSLUnsolvableGoalException
	 * @throws EPSLPlannerInitializationException
	 */
	protected void plan(EPSLTokenDescriptor goal) 
			throws EPSLUnsolvableGoalException, EPSLPlannerInitializationException 
	{
		// check planner
		if (this.planner == null) {
			throw new EPSLPlannerInitializationException("Planner Not Initialized yet!");
		}
		
		try {
			// run planner
			this.planner.plan(goal);
		}
		catch (InterruptedException ex) {
			// interrupted
			System.err.println(ex.getMessage());
		}
	}
	
	/**
	 * 
	 * @param query
	 */
	protected void query(EPSLQuery query) 
			throws EPSLPlannerInitializationException
	{
		// check planner
		if (this.planner == null) {
			throw new EPSLPlannerInitializationException("Planner Not Initialized yet!");
		}
		try {
			// process query
			this.planner.query(query);
		}
		catch (InterruptedException ex) {
			// interrupted
			System.err.println(ex.getMessage());
		}
	}

	/**
	 * 
	 */
	@Override
	public void update(Event event) {
		// check event
		switch (event) {
			case AGENT_ELEMENT_UDPATE_EVENT : {
				try {
					// close mapping model
					this.model.close();
					// update focusing agent information
					this.setup(this.focus);
				}
				catch (Exception ex) {
					System.err.println(ex.getMessage());
					ex.printStackTrace();
				}
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
	 * @param agent
	 * @throws RDFResourceNotFoundException
	 * @throws RDFPropertyNotFoundException
	 * @throws DDLPlanningModelInitializationFailureException
	 */
	private void setup(OWLAgent agent) 
			throws RDFResourceNotFoundException, RDFPropertyNotFoundException, DDLPlanningModelInitializationFailureException 
	{
		try {
			// create domain model
			this.model = new DDLPlanningModel(agent, HORIZON);
			// create planning domain
			String ddl = this.model.generateDDLFile(PS_FOLDER);
			// create planning problem
			String pdl = this.model.generatePDLFile(PS_FOLDER);
			// initialize planner
			this.planner = EPSLApplicationBuilder.build(EPSL_APP_CLASS, ddl, pdl);
			// create language factory
			this.factory = EPSLLanguageFactory.getSingletonInstance(this.model.getHorizon());
			// create query factory
			this.queryFactory = EPSLLanguageQueryFactory.getSingletonInstance();
		}
		catch (FileNotFoundException | UnsupportedEncodingException ex) {
			System.err.println(ex.getMessage());
		}
	}
}
