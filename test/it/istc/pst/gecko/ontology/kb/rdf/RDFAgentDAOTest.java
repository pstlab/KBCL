package it.istc.pst.gecko.ontology.kb.rdf;

import it.istc.pst.kbcl.mapping.kb.rdf.RDFAgentDAO;
import it.istc.pst.kbcl.mapping.kb.rdf.RDFDatasetManager;
import it.istc.pst.kbcl.mapping.kb.rdf.RDFMappingKnowledgeBaseFactory;
import it.istc.pst.kbcl.mapping.model.rdf.RDFAgent;
import it.istc.pst.kbcl.mapping.model.rdf.RDFAgentType;
import it.istc.pst.kbcl.mapping.model.rdf.RDFComponent;
import it.istc.pst.kbcl.mapping.model.rdf.RDFExternalComponent;
import it.istc.pst.kbcl.mapping.model.rdf.RDFFunctionality;
import it.istc.pst.kbcl.mapping.model.rdf.RDFFunctionalityType;
import it.istc.pst.kbcl.model.Agent;
import it.istc.pst.kbcl.model.AgentType;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class RDFAgentDAOTest 
{
	protected static final String DEFAULT_DATASET = "ijcai/test.xml";
	private RDFMappingKnowledgeBaseFactory factory;
	
	/**
	 * 
	 */
	@Before
	public void init() {
		// create factory
		this.factory = new RDFMappingKnowledgeBaseFactory();
		
		System.out.println("************************************************************");
		System.out.println("******************* RDF Agent DAO Test *********************");
		System.out.println("************************************************************");
	}
	
	/**
	 * 
	 */
	@After
	public void cleanup() {
		// close data-set manager
		RDFDatasetManager.getSingletonInstance().close();
	}
	
	/**
	 * 
	 */
	@Test
	public void retrieveAllAgentTypesTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- retrieveAllAgentTypesTest()");
		System.out.println();
		
		// create DAO
		RDFAgentDAO dao = this.factory.createAgentDAO();
		
		// call DAO method
		List<RDFAgentType> types = dao.retrieveAllAgentTypes();
		Assert.assertNotNull(types);
		Assert.assertFalse(types.isEmpty());
		Assert.assertTrue(types.size() == 2);
		// print agent types
		System.out.println(types);
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
		
		// create DAO
		RDFAgentDAO dao = this.factory.createAgentDAO();
		
		// call DAO method
		List<RDFAgent> agents = dao.retrieveAllAgents();
		Assert.assertNotNull(agents);
		Assert.assertFalse(agents.isEmpty());
		Assert.assertTrue(agents.size() == 1);
		// print agent types
		System.out.println(agents);
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void retrieveAgentsByTypeTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- retrieveAgentsByTypeTest()");
		System.out.println();
		
		// create DAO
		RDFAgentDAO dao = this.factory.createAgentDAO();
		
		// get all agent types
		List<RDFAgentType> types = dao.retrieveAllAgentTypes();
		Assert.assertNotNull(types);
		Assert.assertFalse(types.isEmpty());
		Assert.assertTrue(types.size() == 2);
		
		// for each types find out agents
		for (AgentType type : types) {
			// print type
			System.out.println(type);
			// get agents by type
			List<RDFAgent> agents = dao.retrieveAgentsByType(type);
			
			// check result
			Assert.assertNotNull(agents);
			Assert.assertTrue(agents.size() == 0 || agents.size() == 1);
			
			// print agents
			System.out.println(agents);
			System.out.println();
		}
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void retrieveAgentByIdTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- retrieveAgentByIdTest()");
		System.out.println();
		
		// create DAO
		RDFAgentDAO dao = this.factory.createAgentDAO();
		
		// get agents
		Agent a1 = dao.retrieveAllAgents().get(0);
		Assert.assertNotNull(a1);
		System.out.println(a1);
		
		try {
			// success expected
			Agent a2 = dao.retrieveAgentById(a1.getId());
			System.out.println(a2);
			Assert.assertNotNull(a2);
			Assert.assertTrue(a1.equals(a2));
			Assert.assertEquals(a1.getId(), a2.getId());
			Assert.assertEquals(a1.getLabel(), a2.getLabel());
		}
		catch (Exception ex) {
			Assert.assertTrue(false);
		}
		
		try {
			// not success expected
			Agent a3 = dao.retrieveAgentById("pippo");
			System.out.println(a3);
			Assert.assertTrue(false);
		}
		catch (Exception ex) {
			Assert.assertNotNull(ex);
			System.out.println("OK!");
		}
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void retrieveAgentFunctionalitiesTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- retrieveAgentFunctionalitiesTest()");
		System.out.println();
		
		// create DAO
		RDFAgentDAO dao = this.factory.createAgentDAO();
		
		// get an agent
		Agent agent = dao.retrieveAllAgents().get(0);
		Assert.assertNotNull(agent);
		System.out.println(agent);
		
		// get agent's functionalities
		Map<RDFFunctionalityType, List<RDFFunctionality>> funcs = dao.retrieveAgentFunctionalities(agent);
		Assert.assertNotNull(funcs);
		Assert.assertTrue(funcs.size() > 0);
		// print functionalities
		System.out.println(funcs);
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void retrieveAgentComponentsTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- retrieveAgentComponentsTest()");
		System.out.println();
		
		// create DAO
		RDFAgentDAO dao = this.factory.createAgentDAO();
		
		// get an agent
		RDFAgent agent = dao.retrieveAllAgents().get(0);
		Assert.assertNotNull(agent);
		System.out.println(agent);
		
		// get agent's components
		List<RDFComponent> comps = dao.retrieveAgentInternalComponents(agent);
		Assert.assertNotNull(comps);
		Assert.assertTrue(comps.size() > 0);
		// print functionalities
		System.out.println(comps);
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void retrieveAgentNeighborsTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- retrieveAgentNeighborsTest()");
		System.out.println();
		
		// create DAO
		RDFAgentDAO dao = this.factory.createAgentDAO();
		
		// get an agent
		RDFAgent agent = dao.retrieveAllAgents().get(0);
		Assert.assertNotNull(agent);
		System.out.println(agent);
		
		// get agent's components
		List<RDFExternalComponent> list = dao.retrieveAgentExternalComponents(agent);
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size() > 0);
		// print functionalities
		System.out.println(list);
		System.out.println("-----------------------------------------------------------------");
	}
}
