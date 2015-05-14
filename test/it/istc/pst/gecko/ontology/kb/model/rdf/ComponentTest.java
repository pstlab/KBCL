package it.istc.pst.gecko.ontology.kb.model.rdf;

import it.istc.pst.gecko.ontology.RDFKnowledgeBaseFacade;
import it.istc.pst.gecko.ontology.model.rdf.RDFAgent;
import it.istc.pst.gecko.ontology.model.rdf.RDFComponent;
import it.istc.pst.gecko.ontology.model.rdf.RDFExternalComponent;
import it.istc.pst.gecko.ontology.model.rdf.RDFRestriction;
import it.istc.pst.gecko.ontology.model.rdf.RDFState;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class ComponentTest 
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
		System.out.println("*********************** Component Test *************************");
		System.out.println("************************************************************");
	}
	
	/**
	 * 
	 */
	@Test
	public void getComponentStatesTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- getComponentStatesTest()");
		System.out.println();
		
		// get agents
		RDFAgent agent = this.facade.getAgents().get(0);
		List<RDFComponent> components = agent.getComponents();
		Assert.assertNotNull(components);
		Assert.assertTrue(components.size() > 0);
		System.out.println(agent);
		
		// get a component and check states
		RDFComponent c1 = components.get(0);
		Assert.assertNotNull(c1);
		System.out.println(c1);
		// check states
		List<RDFState> states = c1.getStates();
		Assert.assertNotNull(states);
		Assert.assertTrue(states.size() > 0);
		System.out.println(c1);
		System.out.println("-----------------------------------------------------------------");
	}
	

	/**
	 * 
	 */
	@Test
	public void getNeighborStatesTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- getNeighborStatesTest()");
		System.out.println();
		
		// get agents
		RDFAgent agent = this.facade.getAgents().get(0);
		System.out.println(agent);
		// get a neighbor
		RDFExternalComponent neighbor = agent.getNeighbors().get(0);
		System.out.println(neighbor);
		
		// get neighbor's states
		List<RDFState> states = neighbor.getStates();
		Assert.assertNotNull(states);
		Assert.assertTrue(states.size() > 0);
		System.out.println(agent);
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void getConnectedByTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- getConnectedByTest()");
		System.out.println();
		
		// get agents
		RDFAgent agent = this.facade.getAgents().get(0);
		System.out.println(agent);
		// get a neighbor
		RDFExternalComponent neighbor = agent.getNeighbors().get(0);
		System.out.println(neighbor);
		neighbor.getStates();
		
		// get neighbor's states
		RDFComponent comp = neighbor.getConnectedBy();
		Assert.assertNotNull(comp);
		List<RDFState> states = comp.getStates();
		Assert.assertNotNull(states);
		Assert.assertTrue(states.size() > 0);
		System.out.println(agent);
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void getRestrictionsTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- getRestrictionsTest()");
		System.out.println();
		
		// get agents
		RDFAgent agent = this.facade.getAgents().get(0);
		System.out.println(agent);
		// get components
		List<RDFComponent> comps = agent.getComponents();
		Assert.assertNotNull(comps);
		for (RDFComponent comp : comps) {
			// check restrictions if any
			List<RDFRestriction> restrictions = comp.getRestrictions();
			Assert.assertNotNull(restrictions);
			if (!restrictions.isEmpty()) {
				// print component information
				System.out.println(comp);
			}
		}
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void getRestrictionStatesTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- getRestrictionsTest()");
		System.out.println();
		
		// get agents
		RDFAgent agent = this.facade.getAgents().get(0);
		System.out.println(agent);
		// get components
		List<RDFComponent> comps = agent.getComponents();
		Assert.assertNotNull(comps);
		for (RDFComponent comp : comps) {
			// check restrictions if any
			List<RDFRestriction> restrictions = comp.getRestrictions();
			Assert.assertNotNull(restrictions);
			if (!restrictions.isEmpty()) {
				// get a restriction
				RDFRestriction res = restrictions.get(0);
				// get restriction states
				List<RDFState> states = res.getStates();
				Assert.assertNotNull(states);
				Assert.assertTrue(states.size() > 0);
				
				// print restriction information
				System.out.println(res);
				// print component information
				System.out.println(comp);
			}
		}
		System.out.println("-----------------------------------------------------------------");
	}
}
