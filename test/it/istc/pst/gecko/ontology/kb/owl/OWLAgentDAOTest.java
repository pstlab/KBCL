package it.istc.pst.gecko.ontology.kb.owl;

import it.istc.pst.gecko.ontology.kb.AgentDAO;
import it.istc.pst.gecko.ontology.model.Agent;
import it.istc.pst.gecko.ontology.model.AgentType;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class OWLAgentDAOTest {

	private AgentDAO dao;
	
	/**
	 * 
	 */
	@Before
	public void init() {
		// create factory
		this.dao = new OWLAgentDAO();
		
		System.out.println("************************************************************");
		System.out.println("******************* OWL Agent DAO Test *********************");
		System.out.println("************************************************************");
	}
	
	/**
	 * 
	 */
	@After
	public void cleanup() {
		// close data-set manager
		OWLDatasetManager.getSingletonInstance().close();
	}
	
	/**
	 * 
	 */
	@Test
	public void retrieveAllAgentTypesTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- retrieveAllAgentTypesTest()");
		System.out.println();
		
		// call DAO method
		List<AgentType> types = this.dao.retrieveAllAgentTypes();
		Assert.assertNotNull(types);
		Assert.assertFalse(types.isEmpty());
		// print agent types
		System.out.println(types);
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void createAgentTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- createAgentTest()");
		System.out.println();
		
		// get types
		List<AgentType> types = this.dao.retrieveAllAgentTypes();
		// create a module instance
		for (AgentType type : types) {
			// check type
			if (type.getLabel().equals("Module")) {
				// create agent
				Agent agent = this.dao.createAgent("t1", type);
				Assert.assertNotNull(agent);
				Assert.assertTrue(agent.getType().equals(type));
				System.out.println(agent);
			}
		}
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void retrieveAllAgentsTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- retrieveAllAgentsTest()");
		System.out.println();
		
		// get types
		List<AgentType> types = this.dao.retrieveAllAgentTypes();
		// create a module instance
		Agent module = null;
		for (AgentType type : types) {
			// check type
			if (type.getLabel().equals("Module")) {
				// create agent
				module = this.dao.createAgent("t1", type);
			}
		}
		
		Assert.assertNotNull(module);
		
		// get agents
		List<Agent> list = this.dao.retrieveAllAgents();
		Assert.assertNotNull(list);
		Assert.assertFalse(list.isEmpty());
		Assert.assertTrue(list.contains(module));
		System.out.println("Ok!");
		System.out.println("-----------------------------------------------------------------");
	}
}
