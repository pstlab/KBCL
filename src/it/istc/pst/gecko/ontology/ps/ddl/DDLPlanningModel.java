package it.istc.pst.gecko.ontology.ps.ddl;

import it.istc.pst.gecko.ontology.model.Functionality;
import it.istc.pst.gecko.ontology.model.rdf.RDFAgent;
import it.istc.pst.gecko.ontology.ps.PlanningModel;
import it.istc.pst.gecko.ontology.ps.ddl.exception.DDLPlanningModelInitializationFailureException;

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
public class DDLPlanningModel extends PlanningModel
{
	private DDLKnowledgeBaseProcessor kbProcessor;
	private Map<DDLComponentType, List<? extends DDLComponent>> components;
	private List<DDLSynchronization> synchronizations;
	
	/**
	 * 
	 * @param domainName
	 * @param agentId
	 * @param horizon
	 * @throws DDLPlanningModelInitializationFailureException
	 */
	public DDLPlanningModel(String domainName, String agentId, long horizon)
			throws DDLPlanningModelInitializationFailureException 
	{
		super(domainName, horizon);
		try 
		{
			// get related agent
			RDFAgent agent = this.facade.getAgentById(agentId);
			// create processor
			this.kbProcessor = new DDLKnowledgeBaseProcessor(agent, this.horizon);
			
			// lazy approach
			this.synchronizations = null;
			this.components = new HashMap<DDLComponentType, List<? extends DDLComponent>>();
		}
		catch (Exception ex) {
			// throws exception
			throw new DDLPlanningModelInitializationFailureException(ex.getMessage());
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public List<DDLComponent> getFunctionalComponents() {
		// check components
		if (!this.components.containsKey(DDLComponentType.FUNCTIONAL_COMPONENT)) {
			// initialize
			this.components.put(DDLComponentType.FUNCTIONAL_COMPONENT, 
					this.kbProcessor.extractFunctionalComponents());
		}
		// get functional components
		return new ArrayList<DDLComponent>(this.components.get(
				DDLComponentType.FUNCTIONAL_COMPONENT));
	}
	
	/**
	 * 
	 * @param func
	 * @return
	 */
	public DDLValue getDDLValue(Functionality func) {
		return this.kbProcessor.getDDLValue(func);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<DDLComponent> getInternalComponents() {
		// check components
		if (!this.components.containsKey(DDLComponentType.INTERNAL_COMPONENT)) {
			// initialize
			this.components.put(DDLComponentType.INTERNAL_COMPONENT, 
					this.kbProcessor.extractInternalComponents());
		}
		// get internal components
		return new ArrayList<DDLComponent>(this.components.get(
				DDLComponentType.INTERNAL_COMPONENT));
	}
	
	/**
	 * 
	 * @return
	 */
	public List<DDLComponent> getExternalComponents() { 
		// check components
		if (!this.components.containsKey(DDLComponentType.EXTERNAL_COMPONENT)) {
			// initialize
			this.components.put(DDLComponentType.EXTERNAL_COMPONENT, 
					this.kbProcessor.extractExternalComponents());
		}
		// get external components
		return new ArrayList<DDLComponent>(this.components.get(
				DDLComponentType.EXTERNAL_COMPONENT));
	}
	
	/**
	 * 
	 * @return
	 */
	public List<DDLSynchronization> getSynchronizations() {
		// check data
		if (this.synchronizations == null) {
			// extract data
			this.synchronizations = this.kbProcessor.extractSynchronizations();
		}
		// get synchronizations
		return new ArrayList<DDLSynchronization>(this.synchronizations);
	}
	
	
	/**
	 * 
	 */
	@Override
	public String getPlanningDomainDescription() {
		// initialize DDL domain description
		String ddl = "DOMAIN " + this.name + "_Domain {\n\n"
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
	 * @param out
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	public String generateDDLFile(String path) 
			throws FileNotFoundException, UnsupportedEncodingException 
	{
		// DDL file path
		String ddlpath = path + "/" + this.name + ".ddl";
		// create writer
		PrintWriter writer = new PrintWriter(ddlpath, "UTF-8");
		// writer header
		writer.println("DOMAIN " + this.name + "_Domain {\n\n"
				+ "\tTEMPORAL_MODULE temporal_module = [0, " + this.horizon + "], 500;\n"
				+ "\n");
		writer.println(this.getDDLComponentDescription());
		writer.println(this.getDDLSynchronizationDescription());
		writer.println("}\n");
		// close writer
		writer.flush();
		writer.close();
		// get DDL file path
		return ddlpath;
	}
	
	/**
	 * 
	 */
	@Override
	public String getPlanningProblemDescription() {
		// TODO Auto-generated method stub
		return null;
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
}
