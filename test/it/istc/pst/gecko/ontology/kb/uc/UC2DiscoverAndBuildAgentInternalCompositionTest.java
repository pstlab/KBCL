package it.istc.pst.gecko.ontology.kb.uc;

import it.istc.pst.gecko.ontology.model.Agent;
import it.istc.pst.gecko.ontology.model.Component;
import it.istc.pst.gecko.ontology.model.KnowledgeBaseFacade;
import it.istc.pst.gecko.ontology.model.Restriction;
import it.istc.pst.gecko.ontology.model.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class UC2DiscoverAndBuildAgentInternalCompositionTest 
{
	private KnowledgeBaseFacade facade;
	
	/**
	 * 
	 */
	@Before
	public void init() {
		this.facade = KnowledgeBaseFacade.getSingletonInstance();
	
		System.out.println("************************************************************");
		System.out.println("*********************** Use Case 2 Test ********************");
		System.out.println("****** Discover and Build Agent Internal Components ********");
		System.out.println("************************************************************");
	}
	
	/**
	 * 
	 */
	@Test
	public void discoverAgentInternalCompositionTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- discoverAgentInternalCompositionTest()");
		System.out.println();
		
		// get agents in the knowledge base
		List<Agent> agents = this.facade.getAgents();
		for (Agent agent : agents) {
			// print agent information
			System.out.println(agent);
			
			System.out.println("........... Agent's Internal Components ......");
			System.out.println();
			// get agent internal components
			List<Component> comps = agent.getComponents();
			for (Component comp : comps) {
				System.out.println(comp);
			}
		}
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void buildAgentInternalStateVariablesTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- buildAgentInternalStateVariablesTest()");
		System.out.println();
		
		// get agents
		List<Agent> agents = this.facade.getAgents();
		for (Agent agent : agents) {
			// print agent's information
			System.out.println(agent);
			
			// get agent internal components 
			List<Component> comps = agent.getComponents();
			// put values into the state variables
			for (Component comp : comps) {
				// initialize internal state variable
				Map<String, List<String>> sv = new HashMap<String, List<String>>();
				
				// put values into the state variable
				for (State state : comp.getStates()) {
					// add value
					String value = state.getLabel().replace(" ", "_");
					sv.put(value, new ArrayList<String>());
				}
				
				// add constraints among values
				for (String val1 : sv.keySet()) {
					for (String val2 : sv.keySet()) {
						if (!val1.equals(val2)) {
							sv.get(val1).add(val2);
						}
						
					}
				}
				
				System.out.println("Internal State Variable: " + comp.getLabel().replace(" ", "_"));
				// print internal state variable
				for (String key : sv.keySet()) {
					System.out.print("\t- value: " + key + "()\n");
					for (String val : sv.get(key)) {
						System.out.println("\t\t-> " + val + "()");
					}
				}
				System.out.println();
			}
		}
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void buildAgentInternalStateVariablesWithRestrictionsTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- buildAgentInternalStateVariablesWithRestrictionsTest()");
		System.out.println();
		
		// get agents
		List<Agent> agents = this.facade.getAgents();
		for (Agent agent : agents) {
			// print agent's information
			System.out.println(agent);
			
			// get agent internal components 
			List<Component> comps = agent.getComponents();
			// put values into the state variables
			for (Component comp : comps) {
				// initialize internal state variable
				Map<String, List<String>> sv = new HashMap<String, List<String>>();
				
				// put values into the state variable
				for (State state : comp.getStates()) {
					// add value
					String value = state.getLabel().replace(" ", "_");
					sv.put(value, new ArrayList<String>());
				}
				
				// add constraints among values
				for (String val1 : sv.keySet()) {
					for (String val2 : sv.keySet()) {
						if (!val1.equals(val2)) {
							sv.get(val1).add(val2);
						}
						
					}
				}
				
				// consider restrictions if any
				List<Restriction> restrictions = comp.getRestrictions();
				for (Restriction res : restrictions) {
					// get states
					List<State> states = res.getStates();
					for (int i = 0; i < states.size() - 1; i++) {
						State si = states.get(i);
						String vi = si.getLabel().replace(" ", "_");
						
						for (int j = i+1; j < states.size(); j++) {
							State sj = states.get(j);
							String vj = sj.getLabel().replace(" ", "_");
							
							// remove constraints on state variables
							sv.get(vi).remove(vj);
							sv.get(vj).remove(vi);
						}
					}
				}
				
				System.out.println("Internal State Variable: " + comp.getLabel().replace(" ", "_"));
				// print internal state variable
				for (String key : sv.keySet()) {
					System.out.print("\t- value: " + key + "()\n");
					for (String val : sv.get(key)) {
						System.out.println("\t\t-> " + val + "()");
					}
				}
				System.out.println();
			}
		}
		System.out.println("-----------------------------------------------------------------");
	}
}
