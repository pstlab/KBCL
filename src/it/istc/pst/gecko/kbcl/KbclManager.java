package it.istc.pst.gecko.kbcl;

import it.istc.pst.epsl.app.EPSLApplicationBuilder;
import it.istc.pst.epsl.app.EPSLEmbeddedPlanner;
import it.istc.pst.epsl.app.apsi.APSIEmbeddedPlanner;
import it.istc.pst.epsl.app.exception.EPSLPlannerInitializationException;
import it.istc.pst.epsl.app.exception.EPSLUnsolvableGoalException;
import it.istc.pst.epsl.microkernel.lang.EPSLLanguageFactory;
import it.istc.pst.epsl.microkernel.lang.EPSLParameterTypes;
import it.istc.pst.epsl.microkernel.lang.EPSLTimelineDescriptor;
import it.istc.pst.epsl.microkernel.lang.EPSLTokenDescriptor;
import it.istc.pst.epsl.microkernel.lang.query.EPSLQuery;
import it.istc.pst.epsl.microkernel.lang.query.EPSLLanguageQueryFactory;
import it.istc.pst.epsl.microkernel.lang.query.EPSLQueryTypes;
import it.istc.pst.epsl.microkernel.lang.query.get.EPSLGetFlexibleTimelinesQuery;
import it.istc.pst.gecko.kbcl.exception.KbclInitializationException;
import it.istc.pst.gecko.kbcl.exception.KbclRequestProcessingFailureException;
import it.istc.pst.gecko.ontology.model.Agent;
import it.istc.pst.gecko.ontology.model.Functionality;
import it.istc.pst.gecko.ontology.ps.ddl.DDLPlanningModel;
import it.istc.pst.gecko.ontology.ps.ddl.DDLValue;
import it.istc.pst.gecko.ontology.ps.ddl.exception.DDLPlanningModelInitializationFailureException;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class KbclManager
{
	private static final String DDL_FOLDER = "ijcai/ddl";
	private static final String PDL_PATH = "ijcai/pdl/T1.pdl";
	private DDLPlanningModel model;
	private static final Class<APSIEmbeddedPlanner> EPSL_APP_CLASS = APSIEmbeddedPlanner.class;
	private EPSLEmbeddedPlanner planner;
	private EPSLLanguageFactory factory;
	private EPSLLanguageQueryFactory queryFactory;
	
	/**
	 * 
	 * @param agent
	 * @param horizon
	 * @throws DDLPlanningModelInitializationFailureException
	 */
	public KbclManager(Agent agent, long horizon) 
			throws DDLPlanningModelInitializationFailureException, KbclInitializationException {
		try {
			// create domain model
			this.model = new DDLPlanningModel(agent.getLabel(), agent.getId(), horizon);
			// create planning domain
			String ddl = this.model.generateDDLFile(DDL_FOLDER);
			// initialize planner
			this.planner = EPSLApplicationBuilder.build(EPSL_APP_CLASS, ddl, PDL_PATH);
			// create language factory
			this.factory = EPSLLanguageFactory.getSingletonInstance(this.model.getHorizon());
			// create query factory
			this.queryFactory = EPSLLanguageQueryFactory.getSingletonInstance();
		}
		catch (FileNotFoundException | UnsupportedEncodingException ex) {
			throw new KbclInitializationException(ex.getMessage());
		}
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

		// create timeline descrptor
		EPSLTimelineDescriptor tl = this.factory.createTimelineDescriptor(value.getComponent().getName(), value.getComponent().getTimeline(), false);
		// create token request
		EPSLTokenDescriptor goal = this.factory.createTokenDescriptor(
				tl, 
				value.getValue(), 
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
}
