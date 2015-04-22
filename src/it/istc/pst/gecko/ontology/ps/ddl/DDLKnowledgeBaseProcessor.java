package it.istc.pst.gecko.ontology.ps.ddl;

import it.istc.pst.gecko.ontology.model.Agent;
import it.istc.pst.gecko.ontology.model.Component;
import it.istc.pst.gecko.ontology.model.ExternalComponent;
import it.istc.pst.gecko.ontology.model.Functionality;
import it.istc.pst.gecko.ontology.model.FunctionalityImplementation;
import it.istc.pst.gecko.ontology.model.FunctionalityType;
import it.istc.pst.gecko.ontology.model.Restriction;
import it.istc.pst.gecko.ontology.model.State;
import it.istc.pst.gecko.ontology.model.TemporalConstraint;
import it.istc.pst.gecko.ontology.ps.KnowledgeProcessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class DDLKnowledgeBaseProcessor implements KnowledgeProcessor<DDLFunctionalComponent, DDLInternalComponent, DDLExternalComponent, DDLSynchronization> 
{
	private Agent agent;
	private long horizon;
	private Map<Functionality, DDLValue> findex;		// functionality index
	private Map<State, DDLValue> sindex;				// state index
	private boolean functionalBuilt;					// this flag checks if functional components have been built
	private boolean internalBuilt;						// this flag checks if internal components have been built
	private boolean externalBuilt;						// this flag checks if external components have been built
	
	/**
	 * 
	 * @param agent
	 * @param horizon
	 */
	protected DDLKnowledgeBaseProcessor(Agent agent, long horizon) {
		this.agent = agent;
		this.horizon = horizon;
		this.findex = new HashMap<Functionality, DDLValue>();
		this.sindex = new HashMap<State, DDLValue>();
		
		// set flags
		this.functionalBuilt = false;
		this.internalBuilt = false;
		this.externalBuilt = false;
	}
	
	/**
	 * 
	 * @return
	 */
	@Override
	public List<DDLFunctionalComponent> extractFunctionalComponents() {
		// prepare data
		List<DDLFunctionalComponent> list = new ArrayList<DDLFunctionalComponent>();
		
		// get agent's functionalities
		Map<FunctionalityType, List<Functionality>> functionalities  = this.agent.getFunctionalities();
		// for each type of functionality build a state variable
		for (FunctionalityType type : functionalities.keySet())
		{
			// create DDLComponent
			DDLFunctionalComponent component = new DDLFunctionalComponent(
					this.agent.getLabel().replace(" ", "_") + "_" + type.getLabel().replace(" ", "_"),
					this.horizon);
			
			// get functionalities by type
			for (Functionality func : this.agent.getFunctionalitiesByType(type)) 
			{
				// functionality value
				String label = func.getLabel().replace(" ", "_");
				DDLValue value = new DDLValue(label, 
						Long.parseLong(func.getMinDuration()), 
						(func.getMaxDuration().equals("H")) ? this.horizon : Long.parseLong(func.getMaxDuration()), 
						component);
				
				// add value to component
				component.addValue(value);
				// update index
				this.findex.put(func, value);
			}
			// add component to list
			list.add(component);
		}
		// functional components have been built
		this.functionalBuilt = true;
		// get list of functional components
		return list;
	}
	
	/**
	 * 
	 * @return
	 */
	@Override
	public List<DDLInternalComponent> extractInternalComponents() {
		// prepare data
		List<DDLInternalComponent> list = new ArrayList<DDLInternalComponent>();

		// get agent's internal components
		List<Component> components = this.agent.getComponents();
		// create a state variable for each internal component
		for (Component component : components) {
			// create a supporting data structure
			Map<State, DDLValue> cache = new HashMap<State, DDLValue>();
			
			// create DDLComponent
			DDLInternalComponent ddlcomp = new DDLInternalComponent(
					this.agent.getLabel().replace(" ", "_") + "_" + component.getLabel().replace(" ", "_"));
			
			// get values
			for (State state : component.getStates()) {
				// create value to component's state variable
				DDLValue value = new DDLValue(
						state.getLabel().replace(" ", "_").trim(),
						Long.parseLong(state.getMinDuration()),
						(state.getMaxDuration().equals("H")) ? this.horizon : Long.parseLong(state.getMaxDuration()),
						ddlcomp);
				
				// add value
				ddlcomp.addValue(value);
				
				// update cache data
				cache.put(state, value);
				// update state index
				this.sindex.put(state, value);
			}
			
			// get restrictions
			List<Restriction> restrictions = component.getRestrictions(); 
			for (Restriction res : restrictions) {
				// get state restrictions
				List<State> states = res.getStates();
				for (int i = 0; i < states.size() - 1; i++) {
					// get related value
					DDLValue vi = cache.get(states.get(i));
					for (int j = i+1; j < states.size(); j++) {
						// get related value
						DDLValue vj = cache.get(states.get(j));
						
						// remove constraint between values
						ddlcomp.removeConstraintBetweenValues(vi, vj);
					}
				}
			}
			
			// add component to list
			list.add(ddlcomp);
		}
		// internal components have been built
		this.internalBuilt = true;
		// get list of internal components
		return list;
	}
	
	/**
	 * 
	 * @return
	 */
	@Override
	public List<DDLExternalComponent> extractExternalComponents() {
		// prepare data
		List<DDLExternalComponent> list = new ArrayList<DDLExternalComponent>();

		// get agent's neighbors
		List<ExternalComponent> components = this.agent.getNeighbors();
		// create a state variable for each external component
		for (ExternalComponent component : components) {
			// create DDLComponent
			DDLExternalComponent ddlcomp = new DDLExternalComponent(
					this.agent.getLabel().replace(" ", "_") + "_" + component.getLabel().replace(" ", "_"));
			
			// get values
			for (State state : component.getStates()) {
				// create value to component's state variable
				DDLValue value = new DDLValue(
						state.getLabel().replace(" ", "_").trim(),
						Long.parseLong(state.getMinDuration()),
						(state.getMaxDuration().toUpperCase().equals("H")) ? this.horizon : Long.parseLong(state.getMaxDuration()),
						ddlcomp);
				
				// add value
				ddlcomp.addValue(value);
				// update state index
				this.sindex.put(state, value);
			}
			// add component to list
			list.add(ddlcomp);
		}
		// external components have been built
		this.externalBuilt = true;
		// get list of external components
		return list;
	}
	
	/**
	 * 
	 */
	@Override
	public List<DDLSynchronization> extractSynchronizations() 
	{
		// check if functional components have been built
		if (!this.functionalBuilt) {
			// build functional components
			this.extractFunctionalComponents();
		}
		// check if internal components have been built
		if (!this.internalBuilt) {
			// build internal components
			this.extractInternalComponents();
		}
		// check if external components have been built
		if (!this.externalBuilt) {
			// build external components
			this.extractExternalComponents();
		}
		
		// prepare data
		List<DDLSynchronization> list = new ArrayList<DDLSynchronization>();
		
		// get agent's functionalities
		Map<FunctionalityType, List<Functionality>> funcs = this.agent.getFunctionalities();
		for (FunctionalityType type : funcs.keySet()) {
			// get functionalities by type
			for (Functionality func : funcs.get(type)) 
			{
				// get functionality implementations
				List<FunctionalityImplementation> implementations = func.getImplementations();
				for (FunctionalityImplementation impl : implementations) {
					// create a synchronization
					// get the DDLValue related to the functionality
					DDLValue reference = this.findex.get(func);
					// create a synchronization for the current functionality
					DDLSynchronization ddlsync = new DDLSynchronization(reference);
					// add constraints to synchronization
					for (TemporalConstraint tc : impl.getConstraints()) {
						// get required state
						State state = tc.getTarget();
						// get related DDLValue
						DDLValue target = this.sindex.get(state);
						// get constraint label
						String label = tc.getLabel().toUpperCase().trim();
						
						// create constraint
						DDLConstraint cons = new DDLConstraint(reference, target, DDLTemporalConstraintType.getType(label));
						// add constraint to synchronization
						ddlsync.addConstraint(cons);
					}
					
					// add restrictions
					for (State s : impl.getRestrictions().keySet()) {
						// create related value
						DDLValue from = this.sindex.get(s);
						for (TemporalConstraint c : impl.getRestrictions().get(s)) {
							// get required value
							DDLValue to = this.sindex.get(c.getTarget());
							// get constraint label
							String label = c.getLabel().toUpperCase().trim();
							// create constraint
							DDLConstraint cons = new DDLConstraint(from, to, DDLTemporalConstraintType.getType(label));
							// add constraint to synchronization
							ddlsync.addConstraint(cons);
						}
					}
					
					// add synchronization
					list.add(ddlsync);
				}
			}
		}

		// get list of synchronizations
		return list;
	}

	/**
	 * 
	 * @param func
	 * @return
	 */
	public DDLValue getDDLValue(Functionality func) {
		if (this.functionalBuilt) {
			// build functional components
			this.extractFunctionalComponents();
		}
		// get DDL value
		return this.findex.get(func);
	}
}
