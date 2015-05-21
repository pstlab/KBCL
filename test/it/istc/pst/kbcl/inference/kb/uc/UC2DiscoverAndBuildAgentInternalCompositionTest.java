package it.istc.pst.kbcl.inference.kb.uc;

import it.istc.pst.kbcl.mapping.model.rdf.RDFAgent;
import it.istc.pst.kbcl.mapping.model.rdf.RDFComponent;
import it.istc.pst.kbcl.mapping.model.rdf.RDFMappingKnowledgeBaseFacade;
import it.istc.pst.kbcl.mapping.model.rdf.RDFRestriction;
import it.istc.pst.kbcl.mapping.model.rdf.RDFState;

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
	private RDFMappingKnowledgeBaseFacade facade;
	
	/**
	 * 
	 */
	@Before
	public void init() {
		System.out.println("************************************************************");
		System.out.println("*********************** Use Case 2 Test ********************");
		System.out.println("****** Discover and Build Agent Internal Components ********");
		System.out.println("************************************************************");
		try {
			this.facade = new RDFMappingKnowledgeBaseFacade(null);
		}
		catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
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
		RDFAgent agent = this.facade.getAgent();
		
		// print agent information
		System.out.println(agent);
		
		System.out.println("........... Agent's Internal Components ......");
		System.out.println();
		// get agent internal components
		List<RDFComponent> comps = agent.getRDFComponents();
		for (RDFComponent comp : comps) {
			System.out.println(comp);
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
		RDFAgent agent = this.facade.getAgent();
		// print agent's information
		System.out.println(agent);
		
		// get agent internal components 
		List<RDFComponent> comps = agent.getRDFComponents();
		// put values into the state variables
		for (RDFComponent comp : comps) {
			// initialize internal state variable
			Map<String, List<String>> sv = new HashMap<String, List<String>>();
			
			// put values into the state variable
			for (RDFState state : comp.getStates()) {
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
		RDFAgent agent = this.facade.getAgent();
		// print agent's information
		System.out.println(agent);
		
		// get agent internal components 
		List<RDFComponent> comps = agent.getRDFComponents();
		// put values into the state variables
		for (RDFComponent comp : comps) {
			// initialize internal state variable
			Map<String, List<String>> sv = new HashMap<String, List<String>>();
			
			// put values into the state variable
			for (RDFState state : comp.getStates()) {
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
			List<RDFRestriction> restrictions = comp.getRestrictions();
			for (RDFRestriction res : restrictions) {
				// get states
				List<RDFState> states = res.getStates();
				for (int i = 0; i < states.size() - 1; i++) {
					RDFState si = states.get(i);
					String vi = si.getLabel().replace(" ", "_");
					
					for (int j = i+1; j < states.size(); j++) {
						RDFState sj = states.get(j);
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
		System.out.println("-----------------------------------------------------------------");
	}
}
