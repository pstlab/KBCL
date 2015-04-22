package it.istc.pst.gecko.ontology.kb.model;

import it.istc.pst.gecko.ontology.model.Agent;
import it.istc.pst.gecko.ontology.model.Component;
import it.istc.pst.gecko.ontology.model.ExternalComponent;
import it.istc.pst.gecko.ontology.model.KnowledgeBaseFacade;
import it.istc.pst.gecko.ontology.model.Restriction;
import it.istc.pst.gecko.ontology.model.State;

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
	private KnowledgeBaseFacade facade;
	
	/**
	 * 
	 */
	@Before
	public void init() {
		// get facade
		this.facade = KnowledgeBaseFacade.getSingletonInstance();
		
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
		Agent agent = this.facade.getAgents().get(0);
		List<Component> components = agent.getComponents();
		Assert.assertNotNull(components);
		Assert.assertTrue(components.size() > 0);
		System.out.println(agent);
		
		// get a component and check states
		Component c1 = components.get(0);
		Assert.assertNotNull(c1);
		System.out.println(c1);
		// check states
		List<State> states = c1.getStates();
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
		Agent agent = this.facade.getAgents().get(0);
		System.out.println(agent);
		// get a neighbor
		ExternalComponent neighbor = agent.getNeighbors().get(0);
		System.out.println(neighbor);
		
		// get neighbor's states
		List<State> states = neighbor.getStates();
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
		Agent agent = this.facade.getAgents().get(0);
		System.out.println(agent);
		// get a neighbor
		ExternalComponent neighbor = agent.getNeighbors().get(0);
		System.out.println(neighbor);
		neighbor.getStates();
		
		// get neighbor's states
		Component comp = neighbor.getConnectedBy();
		Assert.assertNotNull(comp);
		List<State> states = comp.getStates();
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
		Agent agent = this.facade.getAgents().get(0);
		System.out.println(agent);
		// get components
		List<Component> comps = agent.getComponents();
		Assert.assertNotNull(comps);
		for (Component comp : comps) {
			// check restrictions if any
			List<Restriction> restrictions = comp.getRestrictions();
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
		Agent agent = this.facade.getAgents().get(0);
		System.out.println(agent);
		// get components
		List<Component> comps = agent.getComponents();
		Assert.assertNotNull(comps);
		for (Component comp : comps) {
			// check restrictions if any
			List<Restriction> restrictions = comp.getRestrictions();
			Assert.assertNotNull(restrictions);
			if (!restrictions.isEmpty()) {
				// get a restriction
				Restriction res = restrictions.get(0);
				// get restriction states
				List<State> states = res.getStates();
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
