package it.istc.pst.kbcl.inference.kb.rdf;

import it.istc.pst.kbcl.mapping.kb.rdf.RDFAgentDAO;
import it.istc.pst.kbcl.mapping.kb.rdf.RDFComponentDAO;
import it.istc.pst.kbcl.mapping.kb.rdf.RDFDatasetManager;
import it.istc.pst.kbcl.mapping.kb.rdf.RDFMappingKnowledgeBaseFactory;
import it.istc.pst.kbcl.mapping.kb.rdf.exception.RDFResourceNotFoundException;
import it.istc.pst.kbcl.mapping.model.rdf.RDFAgent;
import it.istc.pst.kbcl.mapping.model.rdf.RDFComponent;
import it.istc.pst.kbcl.mapping.model.rdf.RDFExternalComponent;
import it.istc.pst.kbcl.mapping.model.rdf.RDFRestriction;
import it.istc.pst.kbcl.mapping.model.rdf.RDFState;

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
public class RDFComponentDAOTest 
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
		System.out.println("***************** RDF Component DAO Test *******************");
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
	public void retrieveAllComponentsTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- retrieveComponentStatesTest()");
		System.out.println();
		
		// create DAO
		RDFComponentDAO dao = this.factory.createComponentDAO();
		
		// get an agent
		List<RDFComponent> comps = dao.retrieveAllInternalComponents();
		Assert.assertNotNull(comps);
		Assert.assertTrue(comps.size() > 0);
		System.out.println(comps);
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void retrieveInternalComponentByIdTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- retrieveInternalComponentStatesTest()");
		System.out.println();
		
		// create DAO
		RDFComponentDAO dao = this.factory.createComponentDAO();
		
		// get an agent
		RDFComponent c1 = dao.retrieveAllInternalComponents().get(0);
		try {
			// success expected
			RDFComponent c2 = dao.retrieveInternalComponentById(c1.getId());
			Assert.assertNotNull(c2);
			Assert.assertEquals(c1.getId(), c2.getId());
			Assert.assertEquals(c1.getLabel(), c2.getLabel());
			Assert.assertEquals(c1, c2);
			System.out.println(c2);
		}
		catch (RDFResourceNotFoundException ex) {
			Assert.assertTrue(false);
			
		}
		
		try {
			// failure expected
			RDFComponent c3 = dao.retrieveInternalComponentById("pippo");
			System.out.println(c3);
			Assert.assertTrue(false);
		}
		catch (RDFResourceNotFoundException ex) {
			Assert.assertNotNull(ex);
			System.out.println("OK!");
		}
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void retrieveComponentStatesTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- retrieveComponentStatesTest()");
		System.out.println();
		
		// create DAO
		RDFComponentDAO dao = this.factory.createComponentDAO();
		
		// get an agent
		List<RDFComponent> comps = dao.retrieveAllInternalComponents();
		// get a component
		RDFComponent c = comps.get(0);
		System.out.println("\n" + c + "\n");
		// get agent's functionalities
		Assert.assertNotNull(c);
		// get states
		List<RDFState> states = dao.retrieveComponentStates(c);
		Assert.assertNotNull(states);
		Assert.assertTrue(states.size() > 0);
		// print functionalities
		System.out.println(states);
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void retrieveNeighborStatesTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- retrieveNeighborStatesTest()");
		System.out.println();
		
		// create DAO
		RDFComponentDAO dao = this.factory.createComponentDAO();
		// get Agent DAO
		RDFAgentDAO adao = this.factory.createAgentDAO();
		
		RDFAgent agent = adao.retrieveAllAgents().get(0);
		RDFExternalComponent neighbor = adao.retrieveAgentExternalComponents(agent).get(0);
		System.out.println(neighbor);
		// call DAO method
		List<RDFState> states = dao.retrieveComponentStates(neighbor);
		Assert.assertNotNull(states);
		Assert.assertTrue(states.size() > 0);
		// print agent types
		System.out.println(states);
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void retrieveConnectedByTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- retrieveConnectedByTest()");
		System.out.println();
		
		// create DAO
		RDFComponentDAO dao = this.factory.createComponentDAO();
		// get Agent DAO
		RDFAgentDAO adao = this.factory.createAgentDAO();
		
		RDFAgent agent = adao.retrieveAllAgents().get(0);
		RDFExternalComponent neighbor = adao.retrieveAgentExternalComponents(agent).get(0);
		System.out.println(neighbor);
		// call DAO method
		RDFComponent c = dao.retrieveConnectedComponent(neighbor);
		// call DAO method
		Assert.assertNotNull(c);
		// print agent types
		System.out.println(c);
		System.out.println("-----------------------------------------------------------------");
	}

	/**
	 * 
	 */
	@Test
	public void retrieveComponentRestrictionsTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- retrieveComponentRestrictionsTest()");
		System.out.println();
		
		// create DAO
		RDFComponentDAO dao = this.factory.createComponentDAO();
		// get all internal components
		List<RDFComponent> comps = dao.retrieveAllInternalComponents();
		Assert.assertNotNull(comps);
		for (RDFComponent comp : comps) {
			// retrieve restrictions if any
			List<RDFRestriction> restrictions = dao.retrieveComponentRestrictions(comp);
			Assert.assertNotNull(restrictions);
			// check if any restriction is specified
			if (!restrictions.isEmpty()) {
				// print component information
				System.out.println(comp);
				System.out.println(restrictions);
			}
		}
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void retrieveRestrictionStatesTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- retrieveRestrictionStatesTest()");
		System.out.println();
		
		// create DAO
		RDFComponentDAO dao = this.factory.createComponentDAO();
		// get all internal components
		List<RDFComponent> comps = dao.retrieveAllInternalComponents();
		Assert.assertNotNull(comps);
		for (RDFComponent comp : comps) {
			// retrieve restrictions if any
			List<RDFRestriction> restrictions = dao.retrieveComponentRestrictions(comp);
			Assert.assertNotNull(restrictions);
			// check if any restriction is specified
			if (!restrictions.isEmpty()) {
				// get a restriction
				RDFRestriction res = restrictions.get(0);
				// retrieve restriciton's states
				List<RDFState> states = dao.retrieveRestrictionStates(res);
				Assert.assertNotNull(states);
				Assert.assertTrue(states.size() > 0);
				
				// print restriction and states
				System.out.println(res);
				System.out.println(states);
			}
		}
		System.out.println("-----------------------------------------------------------------");
	}
}
