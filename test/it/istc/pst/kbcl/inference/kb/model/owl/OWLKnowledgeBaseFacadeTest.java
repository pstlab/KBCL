package it.istc.pst.kbcl.inference.kb.model.owl;

import it.istc.pst.kbcl.inference.model.owl.OWLKnowledgeBaseFacade;
import it.istc.pst.kbcl.model.Agent;
import it.istc.pst.kbcl.model.AgentType;
import it.istc.pst.kbcl.model.ElementType;
import it.istc.pst.kbcl.model.FunctionalityType;

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
public class OWLKnowledgeBaseFacadeTest 
{
	private OWLKnowledgeBaseFacade facade;
	
	/**
	 * 
	 */
	@Before
	public void init() {
		System.out.println("************************************************************");
		System.out.println("************* OWL Knowledge Base Facade Test ***************");
		System.out.println("************************************************************");
		
		this.facade = null;
	}
	
	/**
	 * 
	 */
	@After
	public void cleanup() {
		if (this.facade != null) {
			// close data-set manager
			this.facade.close();
		}
	}
	
	/**
	 * 
	 */
	@Test
	public void initializationTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- initializationTest()");
		System.out.println();
		// create factory
		this.facade = OWLKnowledgeBaseFacade.getSingletonInstance();
		Assert.assertNotNull(this.facade);
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void getAgentTypesTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- getAgentTypesTest()");
		System.out.println();
		// create factory
		this.facade = OWLKnowledgeBaseFacade.getSingletonInstance();
		// get agent types
		List<AgentType> types = this.facade.getAgentTypes();
		Assert.assertNotNull(types);
		Assert.assertTrue(!types.isEmpty());
		for (AgentType type : types) {
			Assert.assertNotNull(type);
			System.out.println(type);
		}
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void getFunctionalityTypes() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- getFunctionalityTypes()");
		System.out.println();
		// create factory
		this.facade = OWLKnowledgeBaseFacade.getSingletonInstance();
		// get agent types
		List<FunctionalityType> types = this.facade.getFunctionalityTypes();
		Assert.assertNotNull(types);
		Assert.assertTrue(!types.isEmpty());
		for (FunctionalityType type : types) {
			Assert.assertNotNull(type);
			System.out.println(type);
		}
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void getElementTypes() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- getElementTypes()");
		System.out.println();
		// create factory
		this.facade = OWLKnowledgeBaseFacade.getSingletonInstance();
		// get agent types
		List<ElementType> types = this.facade.getElementTypes();
		Assert.assertNotNull(types);
		Assert.assertTrue(!types.isEmpty());
		for (ElementType type : types) {
			Assert.assertNotNull(type);
			System.out.println(type);
		}
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void getAgents() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- getAgents()");
		System.out.println();
		// create factory
		this.facade = OWLKnowledgeBaseFacade.getSingletonInstance();
		// get agent types
		List<Agent> agents = this.facade.getAgents();
		Assert.assertNotNull(agents);
		Assert.assertTrue(!agents.isEmpty());
		Assert.assertTrue(agents.size() == 1);
		for (Agent agent : agents) {
			Assert.assertNotNull(agent);
			System.out.println(agent);
		}
		System.out.println("-----------------------------------------------------------------");
	}

}
