package it.istc.pst.kbcl.inference.kb.uc;

import it.istc.pst.kbcl.mapping.model.rdf.RDFAgent;
import it.istc.pst.kbcl.mapping.model.rdf.RDFComponent;
import it.istc.pst.kbcl.mapping.model.rdf.RDFFunctionality;
import it.istc.pst.kbcl.mapping.model.rdf.RDFFunctionalityImplementation;
import it.istc.pst.kbcl.mapping.model.rdf.RDFFunctionalityType;
import it.istc.pst.kbcl.mapping.model.rdf.RDFMappingKnowledgeBaseFacade;
import it.istc.pst.kbcl.mapping.model.rdf.RDFState;
import it.istc.pst.kbcl.mapping.model.rdf.RDFTemporalConstraint;
import it.istc.pst.kbcl.model.FunctionalityType;

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
public class UC1DiscoverAndBuildAgentFunctionalitiesTest 
{
	private RDFMappingKnowledgeBaseFacade facade;
	
	/**
	 * 
	 */
	@Before
	public void init() {
		System.out.println("************************************************************");
		System.out.println("*********************** Use Case 1 Test ********************");
		System.out.println("******** Discover and Build Agent Functionalities **********");
		System.out.println("************************************************************");
		try {
			this.facade = new RDFMappingKnowledgeBaseFacade(null);
		}
		catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	/**
	 * 
	 */
	@Test
	public void discoverAgentFunctionalitiesTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- discoverAgentFunctionalitiesTest()");
		System.out.println();
		
		// get agents in the knowledge base
		RDFAgent agent = this.facade.getAgent();
		System.out.println(agent);
		System.out.println("........... Agent's Functionalities ......");
		System.out.println();

		// get agent functionalities
		Map<RDFFunctionalityType, List<RDFFunctionality>> functionalities = agent.getFunctionalities();
		Assert.assertNotNull(functionalities);
		Assert.assertTrue(functionalities.size() > 0);
		for (FunctionalityType type : functionalities.keySet()) {
			// get functionalities
			List<RDFFunctionality> funcs = agent.getFunctionalitiesByType(type);
			Assert.assertNotNull(funcs);
			System.out.println(type);
			System.out.println(funcs);
		}
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void buildAgentFunctionalStateVariablesTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- buildAgentFunctionalStateVariablesTest()");
		System.out.println();
		
		// get agents
		RDFAgent agent = this.facade.getAgent();
		// get agent functionalities
		Map<RDFFunctionalityType, List<RDFFunctionality>> type2funcs = agent.getFunctionalities();
		Assert.assertNotNull(type2funcs);
		Assert.assertTrue(type2funcs.size() > 0);
		for (FunctionalityType type : type2funcs.keySet()) 
		{
			// initialize agent's state variable
			Map<String, List<String>> sv = new HashMap<String, List<String>>();
			// create default value
			String defval = "Idle";
			// add default value with empty list of successor states
			sv.put(defval, new ArrayList<String>());
			
			// get functionalities by type
			List<RDFFunctionality> funcs = agent.getFunctionalitiesByType(type);
			for (RDFFunctionality func : funcs) 
			{
				// get functionality label
				String value = func.getLabel().replace(" ", "_");
				// update state variable
				if (!sv.containsKey(value)) {
					sv.put(value, new ArrayList<String>());
				}
				
				// add value and transition constraints
				sv.get(defval).add(value);
				Assert.assertTrue(sv.get(defval).size() >= 1);
				
				sv.get(value).add(defval);
				Assert.assertTrue(sv.get(value).size() == 1);
			}
			
			// print agent's state variable
			System.out.println("Functional State Variables [" + type.getLabel() + "]");
			for (String key : sv.keySet()) {
				System.out.print("\tValue " + key + "():\n");
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
	public void buildSynchronizationsTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- buildSynchronizationsTest()");
		System.out.println();
		
		// get agents
		RDFAgent agent = this.facade.getAgent();
		Assert.assertNotNull(agent);
		System.out.println(agent);
		System.out.println("..... Agent's Synchronizations");
		
		// get agent's functionalities
		Map<RDFFunctionalityType, List<RDFFunctionality>> type2funcs = agent.getFunctionalities();
		for (RDFFunctionalityType type : type2funcs.keySet()) 
		{
			// get functionalities by type
			List<RDFFunctionality> funcs = agent.getFunctionalitiesByType(type);
			Assert.assertNotNull(funcs);
			for (RDFFunctionality func : funcs) 
			{
				// get Functionality implementation
				List<RDFFunctionalityImplementation> implementations = func.getImplementations();
				Assert.assertNotNull(implementations);
				Assert.assertTrue(implementations.size() >= 0);
				for (RDFFunctionalityImplementation implementation : implementations)
				{
					// build synchronization
					System.out.println("Synchronization for fucntionality [" + func.getLabel().replace(" ", "_") + "()]\n\t- Duration: [" + func.getMinDuration() + ", " + func.getMaxDuration() + "]");
	
					// build constraints
					for (RDFTemporalConstraint con : implementation.getConstraints()) 
					{
						// temporal constraint label
						String label = con.getLabel().replace(" ", "_");
						// required state
						RDFState state = con.getTarget();
						String stateLabel = state.getLabel().replace(" ", "_");
						// on component
						RDFComponent comp = state.getComponent();
						String compLabel = comp.getLabel().replace(" ", "_");
						
						// print synchronization
						System.out.println("\t- Temporal constraint: " + label);
						System.out.println("\t\trequire state: " + stateLabel + "()");
						System.out.println("\t\ton component: " + compLabel);
					}
					
					System.out.println("\nTemporal restrictions:");
					// build restriction constraints
					for (RDFState s : implementation.getRestrictions().keySet()) {
						// get "from" value
						String fromLabel = s.getLabel().replace(" ", "_");
						// on component
						String fromCompLabel = s.getComponent().getLabel().replace(" ", "_");
						for (RDFTemporalConstraint con : implementation.getRestrictions().get(s)) {
							// temporal constraint label
							String label = con.getLabel().replace(" ", "_");
							
							// get "to" value
							RDFState to = con.getTarget();
							String toLabel = to.getLabel().replace(" ", "_");
							// on component
							RDFComponent toComp = to.getComponent();
							String toCompLabel = toComp.getLabel().replace(" ", "_");
							
							// print synchronization
							System.out.println("\t- Temporal constraint: " + label);
							System.out.println("\t\tfrom state: " + fromLabel + "() on component " + fromCompLabel);
							System.out.println("\t\to state: " + toLabel + "() on component " + toCompLabel);
						}
					}
					System.out.println();
				}
			}
		}
		System.out.println("-----------------------------------------------------------------");
	}
}
