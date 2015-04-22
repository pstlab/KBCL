package it.istc.pst.gecko.ontology.kb.model;

import it.istc.pst.gecko.ontology.model.Agent;
import it.istc.pst.gecko.ontology.model.Component;
import it.istc.pst.gecko.ontology.model.ExternalComponent;
import it.istc.pst.gecko.ontology.model.Functionality;
import it.istc.pst.gecko.ontology.model.FunctionalityType;
import it.istc.pst.gecko.ontology.model.KnowledgeBaseFacade;

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
public class AgentTest 
{
	private KnowledgeBaseFacade facade;
	
	/**
	 * 
	 */
	@Before
	public void init() {
		// get facade
		this.facade = KnowledgeBaseFacade.getSingletonInstance();
		
		System.out.println("************************************************************");
		System.out.println("*********************** Agent Test *************************");
		System.out.println("************************************************************");
	}
	
	/**
	 * 
	 */
	@Test
	public void getAgentFunctionalitiesTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- getAgentFunctionalitiesTest()");
		System.out.println();
		
		// get agents
		Agent agent = this.facade.getAgents().get(0);
		System.out.println(agent);
		
		// get functionalities
		Map<FunctionalityType, List<Functionality>> funcs = agent.getFunctionalities();
		Assert.assertNotNull(funcs);
		Assert.assertTrue(funcs.size() > 0);
		System.out.println(funcs);
		System.out.println(agent);
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void getAgentFunctionalitiesByTypeTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- getAgentFunctionalitiesByTypeTest()");
		System.out.println();
		
		// get agents
		Agent agent = this.facade.getAgents().get(0);
		System.out.println(agent);
		
		// get functionalities
		Map<FunctionalityType, List<Functionality>> funcs = agent.getFunctionalities();
		Assert.assertNotNull(funcs);
		Assert.assertTrue(funcs.size() > 0);
		
		// check types
		for (FunctionalityType type : funcs.keySet()) {
			// get list of functionalities
			List<Functionality> list = agent.getFunctionalitiesByType(type);
			Assert.assertNotNull(list);
			Assert.assertTrue(list.size() > 0);
			Assert.assertArrayEquals(funcs.get(type).toArray(), list.toArray());
			
			System.out.println(list);
			System.out.println();
		}
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void getAgentComponentsTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- getAgentComponentsTest()");
		System.out.println();
		
		// get agents
		Agent agent = this.facade.getAgents().get(0);
		System.out.println(agent);
		
		// get functionalities
		List<Component> comps = agent.getComponents();
		Assert.assertNotNull(comps);
		Assert.assertTrue(comps.size() > 0);
		System.out.println(agent);
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void getAgentNeighborsTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- getAgentNeighborsTest()");
		System.out.println();
		
		// get agents
		Agent agent = this.facade.getAgents().get(0);
		System.out.println(agent);
		
		// get functionalities
		List<ExternalComponent> list = agent.getNeighbors();
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size() > 0);
		System.out.println(agent);
		System.out.println("-----------------------------------------------------------------");
	}
}
