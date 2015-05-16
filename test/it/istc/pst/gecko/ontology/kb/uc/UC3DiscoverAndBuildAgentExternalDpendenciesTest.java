package it.istc.pst.gecko.ontology.kb.uc;

import it.istc.pst.kbcl.mapping.model.rdf.RDFAgent;
import it.istc.pst.kbcl.mapping.model.rdf.RDFComponent;
import it.istc.pst.kbcl.mapping.model.rdf.RDFExternalComponent;
import it.istc.pst.kbcl.mapping.model.rdf.RDFMappingKnowledgeBaseFacade;
import it.istc.pst.kbcl.mapping.model.rdf.RDFState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class UC3DiscoverAndBuildAgentExternalDpendenciesTest 
{
	private RDFMappingKnowledgeBaseFacade facade;
	
	/**
	 * 
	 */
	@Before
	public void init() {
		System.out.println("************************************************************");
		System.out.println("*********************** Use Case 3 Test ********************");
		System.out.println("****** Discover and Build Agent External Components ********");
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
	public void discoverAgentExternalDependenciesTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- discoverAgentExternalDependenciesTest()");
		System.out.println();
		
		// get agents in the knowledge base
		RDFAgent agent = this.facade.getAgent();
		// print agent information
		System.out.println(agent);
		
		System.out.println("........... Agent's External Dependencies ......");
		System.out.println();
		// get agent external components
		List<RDFExternalComponent> comps = agent.getNeighbors();
		for (RDFExternalComponent comp : comps) {
			Assert.assertNotNull(comp);
			// get connection information
			RDFComponent connected = comp.getConnectedBy();
			Assert.assertNotNull(connected);
			// print data
			System.out.println(comp);
		}
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void buildAgentExternalStateVariablesTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- buildAgentExternalStateVariablesTest()");
		System.out.println();
		
		// get agents
		RDFAgent agent = this.facade.getAgent();
		// print agent's information
		System.out.println(agent);
		
		// get agent external components 
		List<RDFExternalComponent> comps = agent.getNeighbors();
		// put values into the state variables
		for (RDFExternalComponent comp : comps) {
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
			
			System.out.println("External State Variable: " + comp.getLabel().replace(" ", "_"));
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
