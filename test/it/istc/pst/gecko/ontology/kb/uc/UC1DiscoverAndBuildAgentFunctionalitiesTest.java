package it.istc.pst.gecko.ontology.kb.uc;

import it.istc.pst.gecko.ontology.model.Agent;
import it.istc.pst.gecko.ontology.model.Component;
import it.istc.pst.gecko.ontology.model.Functionality;
import it.istc.pst.gecko.ontology.model.FunctionalityImplementation;
import it.istc.pst.gecko.ontology.model.FunctionalityType;
import it.istc.pst.gecko.ontology.model.KnowledgeBaseFacade;
import it.istc.pst.gecko.ontology.model.State;
import it.istc.pst.gecko.ontology.model.TemporalConstraint;

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
	private KnowledgeBaseFacade facade;
	
	/**
	 * 
	 */
	@Before
	public void init() {
		this.facade = KnowledgeBaseFacade.getSingletonInstance();
	
		System.out.println("************************************************************");
		System.out.println("*********************** Use Case 1 Test ********************");
		System.out.println("******** Discover and Build Agent Functionalities **********");
		System.out.println("************************************************************");
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
		List<Agent> agents = this.facade.getAgents();
		Assert.assertNotNull(agents);
		Assert.assertTrue(agents.size() > 0);
		for (Agent agent : agents) 
		{
			Assert.assertNotNull(agent);
			// print agent information
			System.out.println(agent);
			System.out.println("........... Agent's Functionalities ......");
			System.out.println();

			// get agent functionalities
			Map<FunctionalityType, List<Functionality>> functionalities = agent.getFunctionalities();
			Assert.assertNotNull(functionalities);
			Assert.assertTrue(functionalities.size() > 0);
			for (FunctionalityType type : functionalities.keySet()) {
				// get functionalities
				List<Functionality> funcs = agent.getFunctionalitiesByType(type);
				Assert.assertNotNull(funcs);
				System.out.println(type);
				System.out.println(funcs);
			}
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
		List<Agent> agents = this.facade.getAgents();
		Assert.assertNotNull(agents);
		Assert.assertTrue(agents.size() > 0);
		for (Agent agent : agents) 
		{
			// get agent functionalities
			Map<FunctionalityType, List<Functionality>> type2funcs = agent.getFunctionalities();
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
				List<Functionality> funcs = agent.getFunctionalitiesByType(type);
				for (Functionality func : funcs) 
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
		List<Agent> agents = this.facade.getAgents();
		Assert.assertNotNull(agents);
		Assert.assertTrue(agents.size() > 0);
		for (Agent agent : agents) 
		{
			Assert.assertNotNull(agent);
			System.out.println(agent);
			System.out.println("..... Agent's Synchronizations");
			
			// get agent's functionalities
			Map<FunctionalityType, List<Functionality>> type2funcs = agent.getFunctionalities();
			for (FunctionalityType type : type2funcs.keySet()) 
			{
				// get functionalities by type
				List<Functionality> funcs = agent.getFunctionalitiesByType(type);
				Assert.assertNotNull(funcs);
				for (Functionality func : funcs) 
				{
					// get Functionality implementation
					List<FunctionalityImplementation> implementations = func.getImplementations();
					Assert.assertNotNull(implementations);
					Assert.assertTrue(implementations.size() >= 0);
					for (FunctionalityImplementation implementation : implementations)
					{
						// build synchronization
						System.out.println("Synchronization for fucntionality [" + func.getLabel().replace(" ", "_") + "()]\n\t- Duration: [" + func.getMinDuration() + ", " + func.getMaxDuration() + "]");
		
						// build constraints
						for (TemporalConstraint con : implementation.getConstraints()) 
						{
							// temporal constraint label
							String label = con.getLabel().replace(" ", "_");
							// required state
							State state = con.getTarget();
							String stateLabel = state.getLabel().replace(" ", "_");
							// on component
							Component comp = state.getComponent();
							String compLabel = comp.getLabel().replace(" ", "_");
							
							// print synchronization
							System.out.println("\t- Temporal constraint: " + label);
							System.out.println("\t\trequire state: " + stateLabel + "()");
							System.out.println("\t\ton component: " + compLabel);
						}
						
						System.out.println("\nTemporal restrictions:");
						// build restriction constraints
						for (State s : implementation.getRestrictions().keySet()) {
							// get "from" value
							String fromLabel = s.getLabel().replace(" ", "_");
							// on component
							String fromCompLabel = s.getComponent().getLabel().replace(" ", "_");
							for (TemporalConstraint con : implementation.getRestrictions().get(s)) {
								// temporal constraint label
								String label = con.getLabel().replace(" ", "_");
								
								// get "to" value
								State to = con.getTarget();
								String toLabel = to.getLabel().replace(" ", "_");
								// on component
								Component toComp = to.getComponent();
								String toCompLabel = toComp.getLabel().replace(" ", "_");
								
								// print synchronization
								System.out.println("\t- Temporal constraint: " + label);
								System.out.println("\t\tfrom state: " + fromLabel + "() on component " + fromCompLabel);
								System.out.println("\t\to state: " + toLabel + "() on component " + toCompLabel);
							}
						}
						
						/*
						 * TODO: BUILD RESTRICTIONS
						 */
						
						System.out.println();
					}
				}
			}
		}
		System.out.println("-----------------------------------------------------------------");
	}
}
