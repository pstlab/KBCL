package it.istc.pst.gecko.ontology.kb.model.owl;

import it.istc.pst.gecko.ontology.model.owl.OWLAgent;
import it.istc.pst.gecko.ontology.model.owl.OWLAgentType;
import it.istc.pst.gecko.ontology.model.owl.OWLElementType;
import it.istc.pst.gecko.ontology.model.owl.OWLFunctionalityType;
import it.istc.pst.gecko.ontology.model.owl.OWLKnowledgeBaseFacade;

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
		List<OWLAgentType> types = this.facade.getAgentTypes();
		Assert.assertNotNull(types);
		Assert.assertTrue(!types.isEmpty());
		for (OWLAgentType type : types) {
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
		List<OWLFunctionalityType> types = this.facade.getFunctionalityTypes();
		Assert.assertNotNull(types);
		Assert.assertTrue(!types.isEmpty());
		for (OWLFunctionalityType type : types) {
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
		List<OWLElementType> types = this.facade.getElementTypes();
		Assert.assertNotNull(types);
		Assert.assertTrue(!types.isEmpty());
		for (OWLElementType type : types) {
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
		List<OWLAgent> agents = this.facade.getAgents();
		Assert.assertNotNull(agents);
		Assert.assertTrue(!agents.isEmpty());
		Assert.assertTrue(agents.size() == 1);
		for (OWLAgent agent : agents) {
			Assert.assertNotNull(agent);
			System.out.println(agent);
		}
		System.out.println("-----------------------------------------------------------------");
	}

}
