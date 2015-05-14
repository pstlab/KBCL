package it.istc.pst.gecko.ontology.kb.model.rdf;

import it.istc.pst.gecko.ontology.RDFKnowledgeBaseFacade;
import it.istc.pst.gecko.ontology.model.rdf.RDFAgent;
import it.istc.pst.gecko.ontology.model.rdf.RDFComponent;
import it.istc.pst.gecko.ontology.model.rdf.RDFExternalComponent;
import it.istc.pst.gecko.ontology.model.rdf.RDFFunctionality;
import it.istc.pst.gecko.ontology.model.rdf.RDFFunctionalityType;

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
	private RDFKnowledgeBaseFacade facade;
	
	/**
	 * 
	 */
	@Before
	public void init() {
		// get facade
		this.facade = RDFKnowledgeBaseFacade.getSingletonInstance();
		
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
		RDFAgent agent = this.facade.getAgents().get(0);
		System.out.println(agent);
		
		// get functionalities
		Map<RDFFunctionalityType, List<RDFFunctionality>> funcs = agent.getFunctionalities();
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
		RDFAgent agent = this.facade.getAgents().get(0);
		System.out.println(agent);
		
		// get functionalities
		Map<RDFFunctionalityType, List<RDFFunctionality>> funcs = agent.getFunctionalities();
		Assert.assertNotNull(funcs);
		Assert.assertTrue(funcs.size() > 0);
		
		// check types
		for (RDFFunctionalityType type : funcs.keySet()) {
			// get list of functionalities
			List<RDFFunctionality> list = agent.getFunctionalitiesByType(type);
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
		RDFAgent agent = this.facade.getAgents().get(0);
		System.out.println(agent);
		
		// get functionalities
		List<RDFComponent> comps = agent.getComponents();
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
		RDFAgent agent = this.facade.getAgents().get(0);
		System.out.println(agent);
		
		// get functionalities
		List<RDFExternalComponent> list = agent.getNeighbors();
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size() > 0);
		System.out.println(agent);
		System.out.println("-----------------------------------------------------------------");
	}
}
