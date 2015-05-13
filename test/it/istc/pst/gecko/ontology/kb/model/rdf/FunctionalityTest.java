package it.istc.pst.gecko.ontology.kb.model.rdf;

import it.istc.pst.gecko.ontology.KnowledgeBaseFacade;
import it.istc.pst.gecko.ontology.model.rdf.RDFAgent;
import it.istc.pst.gecko.ontology.model.rdf.RDFFunctionality;
import it.istc.pst.gecko.ontology.model.rdf.RDFFunctionalityImplementation;
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
public class FunctionalityTest 
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
		System.out.println("****************** Functionality Test **********************");
		System.out.println("************************************************************");
	}
	
	/**
	 * 
	 */
	@Test
	public void getFunctionalityImplementationTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- getFunctionalityImplementationTest()");
		System.out.println();
		
		// get agents
		RDFAgent agent = this.facade.getAgents().get(0);
		Map<RDFFunctionalityType, List<RDFFunctionality>> funcs = agent.getFunctionalities();
		Assert.assertNotNull(funcs);
		Assert.assertTrue(funcs.size() > 0);
		for (RDFFunctionalityType type : funcs.keySet()) {
			// get a functionality
			RDFFunctionality func = agent.getFunctionalitiesByType(type).get(0);
			Assert.assertNotNull(func);
			System.out.println(func);
			// get functionality implementation
			List<RDFFunctionalityImplementation> implementations = func.getImplementations();
			Assert.assertNotNull(implementations);
			Assert.assertTrue(implementations.size() > 0);
			for (RDFFunctionalityImplementation implementation : implementations) {
				Assert.assertNotNull(implementation);
				Assert.assertNotNull(implementation.getConstraints());
				Assert.assertTrue(implementation.getConstraints().size() > 0);
				Assert.assertNotNull(implementation.getRestrictions());
				
				System.out.println("Functionality Implementation:");
				System.out.println(implementation);
			}
		}
		System.out.println("-----------------------------------------------------------------");
	}
}
