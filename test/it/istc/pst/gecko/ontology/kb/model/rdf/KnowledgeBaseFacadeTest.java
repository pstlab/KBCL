package it.istc.pst.gecko.ontology.kb.model.rdf;

import it.istc.pst.gecko.ontology.model.rdf.RDFAgent;
import it.istc.pst.gecko.ontology.model.rdf.RDFAgentType;
import it.istc.pst.gecko.ontology.model.rdf.RDFFunctionalityType;
import it.istc.pst.gecko.ontology.model.rdf.RDFKnowledgeBaseFacade;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class KnowledgeBaseFacadeTest 
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
		System.out.println("*************** Knowledge Base Facade Test *****************");
		System.out.println("************************************************************");
	}
	
	/**
	 * 
	 */
	@Test
	public void createFacadeTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- createFacadeTest()");
		System.out.println();
		
		Assert.assertNotNull(this.facade);
		System.out.println("OK!");
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void getAgentsTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- getAgentsTest()");
		System.out.println();
		
		// get agents
		List<RDFAgent> agents = this.facade.getAgents();
		Assert.assertNotNull(agents);
		Assert.assertTrue(agents.size() > 0);
		System.out.println(agents);
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
		
		// get agent types
		List<RDFAgentType> types = this.facade.getAgentTypes();
		Assert.assertNotNull(types);
		Assert.assertTrue(types.size() > 0);
		System.out.println(types);
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void getFunctionalityTypesTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- getFunctionalityTypesTest()");
		System.out.println();
		
		// get functionality types
		List<RDFFunctionalityType> types = this.facade.getFunctionalityTypes();
		Assert.assertNotNull(types);
		Assert.assertTrue(types.size() > 0);
		System.out.println(types);
		System.out.println("-----------------------------------------------------------------");
	}
}
