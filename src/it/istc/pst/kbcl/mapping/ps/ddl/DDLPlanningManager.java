package it.istc.pst.kbcl.mapping.ps.ddl;

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
import it.istc.pst.kbcl.exception.KbclRequestProcessingFailureException;
import it.istc.pst.kbcl.inference.model.owl.OWLAgent;
import it.istc.pst.kbcl.inference.model.owl.OWLFunctionality;
import it.istc.pst.kbcl.mapping.kb.rdf.exception.RDFPropertyNotFoundException;
import it.istc.pst.kbcl.mapping.kb.rdf.exception.RDFResourceNotFoundException;
import it.istc.pst.kbcl.mapping.model.rdf.RDFFunctionality;
import it.istc.pst.kbcl.mapping.model.rdf.RDFMappingKnowledgeBaseFacade;
import it.istc.pst.kbcl.mapping.ps.PlanningManager;
import it.istc.pst.kbcl.mapping.ps.ddl.exception.DDLPlanningModelInitializationFailureException;
import it.istc.pst.kbcl.model.Event;
import it.istc.pst.kbcl.model.EventObserver;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * @author alessandroumbrico
 *
 */
public class DDLPlanningManager extends PlanningManager implements EventObserver
{
	private static final long HORIZON = 1000;
	private static final String PS_FOLDER = "ps";
	
	// mapping knowledge base
	private RDFMappingKnowledgeBaseFacade kbMapping;
	// the observed agent
	private OWLAgent agent;
	
	private DDLKnowledgeBaseProcessor processor;
	private Map<DDLComponentType, List<? extends DDLComponent>> components;
	private List<DDLSynchronization> synchronizations;
	
	// planner 
	private static final Class<APSIEmbeddedPlanner> EPSL_APP_CLASS = APSIEmbeddedPlanner.class;
	private EPSLEmbeddedPlanner planner;
	private EPSLLanguageFactory factory;
	private EPSLLanguageQueryFactory queryFactory;
	
	/**
	 * 
	 * @param agent
	 * @throws DDLPlanningModelInitializationFailureException
	 */
	public DDLPlanningManager(OWLAgent agent)
			throws DDLPlanningModelInitializationFailureException 
	{
		super(HORIZON);
		try 
		{
			// set observed agent
			this.agent = agent;
			
			// set knowledge base
			this.kbMapping = new RDFMappingKnowledgeBaseFacade(agent);
			// subscribe to mapping knowledge updates
			this.kbMapping.subscribe(this);
			
			
			// create processor
			this.processor = new DDLKnowledgeBaseProcessor(this.kbMapping.getAgent(), this.horizon);

			// lazy approach
			this.synchronizations = null;
			// initialize components
			this.components = new HashMap<DDLComponentType, List<? extends DDLComponent>>();
			
			// create planning domain
			String ddl = this.generateDDLFile();
			// create planning problem
			String pdl = this.generatePDLFile();
			
			// initialize planner
			this.planner = EPSLApplicationBuilder.build(EPSL_APP_CLASS, ddl, pdl);
			// create language factory
			this.factory = EPSLLanguageFactory.getSingletonInstance(this.horizon);
			// create query factory
			this.queryFactory = EPSLLanguageQueryFactory.getSingletonInstance();
			
		}
		catch (RDFResourceNotFoundException | RDFPropertyNotFoundException ex) {
			// throws exception
			throw new DDLPlanningModelInitializationFailureException(ex.getMessage());
		}
		catch (Exception ex) {
			// throws exception
			throw new DDLPlanningModelInitializationFailureException(ex.getMessage());
		}
	}
	
	/**
	 * 
	 */
	@Override
	public String getModelName() {
		return this.agent.getLabel();
	}
	
	/**
	 * 
	 */
	@Override
	public void update(Event event) {
		// check event
		switch (event) {
			// agent update
			case MAPPING_KNOWLEDGE_UPDATE_EVENT : 
			{
				try 
				{
					// create processor
					this.processor = new DDLKnowledgeBaseProcessor(this.kbMapping.getAgent(), this.horizon);

					// lazy approach
					this.synchronizations = null;
					// initialize components
					this.components = new HashMap<DDLComponentType, List<? extends DDLComponent>>();
					
					// create planning domain
					String ddl = this.generateDDLFile();
					// create planning problem
					String pdl = this.generatePDLFile();
					
					// initialize planner
					this.planner = EPSLApplicationBuilder.build(EPSL_APP_CLASS, ddl, pdl);
					// create language factory
					this.factory = EPSLLanguageFactory.getSingletonInstance(this.horizon);
					// create query factory
					this.queryFactory = EPSLLanguageQueryFactory.getSingletonInstance();
				}
				catch (Exception ex) {
					// throws exception
					System.err.println(ex.getLocalizedMessage());
				}
			}
			break;
			
			default : {
				// ignore
			}
			break;
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
	 * @throws RDFResourceNotFoundException
	 */
	public void plan(OWLFunctionality func) 
			throws KbclRequestProcessingFailureException, RDFResourceNotFoundException
	{
		// get functionality from model
		RDFFunctionality f = this.kbMapping.getFunctionality(func);
		// get the related DDLValue
		DDLValue value = this.getDDLValue(f);

		// create time-line descriptor
		EPSLTimelineDescriptor tl = this.factory.createTimelineDescriptor(value.getComponent().getName(), value.getComponent().getTimeline(), false);
		// create token request
		EPSLTokenDescriptor goal = this.factory.createTokenDescriptor(
				tl, 
				value.getValue(),
				true,
				new long[] {1, this.horizon}, 
				new long[] {1, this.horizon}, 
				new long[] {value.getDmin(), value.getDmax()},
				new String[] {}, 
				new EPSLParameterTypes[] {}, 
				new long[][] {}, new String[][] {});
		
		try {
			// plan goal 
			this.runPlanner(goal);
		}
		catch (EPSLUnsolvableGoalException | EPSLPlannerInitializationException ex) {
			throw new KbclRequestProcessingFailureException(ex.getMessage());
		}
	}
	
	/**
	 * 
	 * @param query
	 */
	public void query(EPSLQuery query) 
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
	 * @param func
	 * @return
	 */
	public DDLValue getDDLValue(RDFFunctionality func) {
		return this.processor.getDDLValue(func);
	}
	
	/**
	 * 
	 */
	@Override
	public String getPlanningDomainDescription() {
		// initialize DDL domain description
		String ddl = "DOMAIN " + this.getModelName() + "_Domain {\n\n"
				+ "\tTEMPORAL_MODULE temporal_module = [0, " + this.horizon + "], 500;\n"
				+ "\n";
		
		// component types and instances
		ddl += this.getDDLComponentDescription();
		// synchronizations
		ddl += this.getDDLSynchronizationDescription();
		
		// close domain description
		ddl += "}\n";
		return ddl;
	}
	
	/**
	 * 
	 * @return
	 */
	@Override
	public String getPlanningProblemDescription() {
		String pdl = "PROBLEM " + this.getModelName() + "_Problem (DOMAIN " + this.getModelName() + "_Domain)\n{\n";
		pdl += this.getDDLComponentInitialFacts();
		pdl += "}\n";
		return pdl;
	}
	
	/**
	 * 
	 */
	public void close() {
		if (this.processor != null) {
			this.processor.close();
		}
		this.components = null;
		this.synchronizations = null;
	}
	
	/**
	 * 
	 * @return
	 */
	protected List<DDLComponent> getFunctionalComponents() {
		// check components
		if (!this.components.containsKey(DDLComponentType.FUNCTIONAL_COMPONENT)) {
			// initialize
			this.components.put(DDLComponentType.FUNCTIONAL_COMPONENT, 
					this.processor.extractFunctionalComponents());
		}
		// get functional components
		return new ArrayList<DDLComponent>(this.components.get(
				DDLComponentType.FUNCTIONAL_COMPONENT));
	}

	/**
	 * 
	 * @return
	 */
	protected List<DDLComponent> getInternalComponents() {
		// check components
		if (!this.components.containsKey(DDLComponentType.INTERNAL_COMPONENT)) {
			// initialize
			this.components.put(DDLComponentType.INTERNAL_COMPONENT, 
					this.processor.extractInternalComponents());
		}
		// get internal components
		return new ArrayList<DDLComponent>(this.components.get(
				DDLComponentType.INTERNAL_COMPONENT));
	}
	
	/**
	 * 
	 * @return
	 */
	protected List<DDLComponent> getExternalComponents() { 
		// check components
		if (!this.components.containsKey(DDLComponentType.EXTERNAL_COMPONENT)) {
			// initialize
			this.components.put(DDLComponentType.EXTERNAL_COMPONENT, 
					this.processor.extractExternalComponents());
		}
		// get external components
		return new ArrayList<DDLComponent>(this.components.get(
				DDLComponentType.EXTERNAL_COMPONENT));
	}
	
	/**
	 * 
	 * @return
	 */
	protected List<DDLSynchronization> getSynchronizations() {
		// check data
		if (this.synchronizations == null) {
			// extract data
			this.synchronizations = this.processor.extractSynchronizations();
		}
		// get synchronizations
		return new ArrayList<DDLSynchronization>(this.synchronizations);
	}
	
	/**
	 * 
	 * @param out
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	private String generateDDLFile()//String path) 
			throws FileNotFoundException, UnsupportedEncodingException 
	{
		// DDL file path
		String ddlpath = PS_FOLDER + "/" + this.getModelName() + ".ddl";
		// create writer
		PrintWriter writer = new PrintWriter(ddlpath, "UTF-8");
		// writer header
		writer.println(this.getPlanningDomainDescription());
		// close writer
		writer.flush();
		writer.close();
		// get DDL file path
		return ddlpath;
	}
	
	/**
	 * 
	 * @param path
	 * @return
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	private String generatePDLFile()//String path) 
			throws FileNotFoundException, UnsupportedEncodingException
	{
		// DDL file path
		String pdlPath = PS_FOLDER + "/" + this.getModelName() + ".pdl";
		// create writer
		PrintWriter writer = new PrintWriter(pdlPath, "UTF-8");
		// writer header
		writer.println(this.getPlanningProblemDescription());
		// close writer
		writer.flush();
		writer.close();
		// get DDL file path
		return pdlPath;
	}
	
	/**
	 * 
	 * @return
	 */
	private List<DDLComponent> getAllComponents() {
		List<DDLComponent> list = new ArrayList<DDLComponent>();
		list.addAll(this.getFunctionalComponents());
		list.addAll(this.getInternalComponents());
		list.addAll(this.getExternalComponents());
		return list;
	}

	
	/**
	 * 
	 * @return
	 */
	private String getDDLComponentDescription() {
		String ddl = "";
		// get all components
		List<DDLComponent> comps = this.getAllComponents();
		for (DDLComponent comp : comps) {
			// print component type 
			ddl += "\tCOMP_TYPE SingletonStateVariable " + comp.getName() + "Type (";
			// add values
			for (int i=0; i < comp.getValues().size(); i++) {
				DDLValue val = comp.getValues().get(i);
				ddl += val.getValue() + "()";
				if (i < comp.getValues().size() - 1) {
					ddl += ", ";
				}
			}
			ddl += ") {\n\n";
			
			// print value details
			for (DDLValue val : comp.getValues()) {
				ddl += "\t\tVALUE " + val.getValue() + "() ["+ val.getDmin() +", " + val.getDmax() + "]\n";
				ddl += "\t\tMEETS {\n";
				// get value successors
				for (DDLValue succ : comp.getSuccessors(val)) {
					ddl += "\t\t\t" + succ.getValue() + "();\n";
				}
				ddl += "\t\t}\n\n";
			}
			
			// close component type
			ddl += "\t}\n\n";
		}
		
		// print component instances
		for (DDLComponent comp : comps) {
			ddl += "\tCOMPONENT " + comp.getName() + " { FLEXIBLE " + comp.getTimeline() + "(trex_internal_dispatch_asap) } : " + comp.getName() + "Type;\n";
		}
		ddl += "\n";
		return ddl;
	}
	
	/**
	 * 
	 * @return
	 */
	private String getDDLSynchronizationDescription() {
		String ddl = "";
		// index synchronization by reference's component
		Map<DDLComponent, List<DDLSynchronization>> comp2sync = new HashMap<DDLComponent, List<DDLSynchronization>>();
		for (DDLSynchronization sync : this.getSynchronizations()) {
			// get reference value
			DDLValue reference = sync.getReference();
			DDLComponent refComp = reference.getComponent();
			
			// check index
			if (!comp2sync.containsKey(refComp)) {
				comp2sync.put(refComp, new ArrayList<DDLSynchronization>());
			}
			// update index
			comp2sync.get(refComp).add(sync);
		}
		
		// print synchronizations
		for (DDLComponent ddlcomp : comp2sync.keySet()) {
			// print synchronizations for current component
			ddl += "\tSYNCHRONIZE " + ddlcomp.getName() + "." + ddlcomp.getTimeline() + " {\n\n";
			// print synchronization information
			for (DDLSynchronization sync : comp2sync.get(ddlcomp)) {
				// get reference value
				DDLValue reference = sync.getReference();
				// print reference value
				ddl += "\t\tVALUE " + reference.getValue() + "() {\n\n";
				// constraint counter
				int counter = 0;
				// print constraints
				for (DDLConstraint ddlconstraint : sync.getConstraints()) 
				{
					// check constraint source
					if (reference.equals(ddlconstraint.getReference())) {
						
						// get target constraint
						DDLValue target = ddlconstraint.getTarget();
						ddl += "\t\t\tcd" + counter + " ";
						// check if external component
						if (target.getComponent().isExternal()) {
							// add control knowledge
							ddl += " <?> ";
						}
						
						ddl	+= target.getComponent().getName() + "."
							+ "" + target.getComponent().getTimeline() + "."
							+ "" + target.getValue() + "();\n";
						
						// print constraint
						ddl += "\t\t\t" + ddlconstraint.getConstraint() + " cd" + counter + ";\n\n";
					}
					else 
					{
						// restriction constraint
						DDLValue resReference = ddlconstraint.getReference();
						int refCdId = counter;
						ddl += "\t\t\tcd" + refCdId + " ";
						// check if external component
						if (resReference.getComponent().isExternal()) {
							// add control knowledge
							ddl += " <?> ";
						}
						
						ddl	+= resReference.getComponent().getName() + "."
							+ "" + resReference.getComponent().getTimeline() + "."
							+ "" + resReference.getValue() + "();\n";
						
						// update counter 
						counter++;
						int targetCdId = counter;
						
						// get target constraint
						DDLValue target = ddlconstraint.getTarget();
						ddl += "\t\t\tcd" + targetCdId + " ";
						// check if external component
						if (target.getComponent().isExternal()) {
							// add control knowledge
							ddl += " <?> ";
						}
						
						ddl	+= target.getComponent().getName() + "."
							+ "" + target.getComponent().getTimeline() + "."
							+ "" + target.getValue() + "();\n";
						
						// print constraint
						ddl += "\t\t\tcd" + refCdId +  " " + ddlconstraint.getConstraint() + " cd" + targetCdId + ";\n\n";
					}
					
					// update counter
					counter++;
				}
				// close synchronization non value
				ddl += "\t\t}\n\n";
			}
			// close synchronization on component
			ddl += "\t}\n\n";
		}
		return ddl;
	}
	
	/**
	 * 
	 * @return
	 */
	private String getDDLComponentInitialFacts() {
		String pdl = "";
		int factCounter = 0;
		// get all components
		List<DDLComponent> comps = this.getAllComponents();
		for (DDLComponent comp : comps) {
			// print component's initial fact
			pdl += "\tf" + factCounter + " <fact> ";
			pdl+= comp.getName() + "." + comp.getTimeline() + "." + comp.getInitialValue().getValue() + "() "
					+ "AT [0, 0] "
					+ (comp.isExternal() ? "[" + this.getHorizon() +"," + this.getHorizon() + "] " : "[1, +INF] ")  + ""
					+ (comp.isExternal() ? "[" + this.getHorizon() + "," + this.getHorizon() + "];" : "[1, +INF];") + "\n";
			factCounter++;
		}
		pdl += "\n";
		return pdl;
	}
	
	/**
	 * 
	 * @param goal
	 * @return
	 * @throws EPSLUnsolvableGoalException
	 * @throws EPSLPlannerInitializationException
	 */
	private void runPlanner(EPSLTokenDescriptor goal) 
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
}
