package it.istc.pst.gecko.ontology.ps.ddl;

import it.istc.pst.gecko.ontology.model.Agent;
import it.istc.pst.gecko.ontology.model.rdf.RDFKnowledgeBaseFacade;
import it.istc.pst.gecko.ontology.ps.ddl.exception.DDLPlanningModelInitializationFailureException;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class DDLPlanningModelTest 
{
	private DDLPlanningModel model;
	
	/**
	 * 
	 */
	@Before
	public void init() 
	{
		try
		{
			// get facade
			RDFKnowledgeBaseFacade facade = RDFKnowledgeBaseFacade.getSingletonInstance();
			Agent agent = facade.getAgents().get(0);
			
			this.model = new DDLPlanningModel(agent.getLabel(), agent.getId(), 1000);
			
			System.out.println("************************************************************");
			System.out.println("***************** DDL Planning Model Test ******************");
			System.out.println("************************************************************");
		}
		catch (DDLPlanningModelInitializationFailureException ex) {
			System.err.println(ex.getMessage());
		}
		
	}
	
	/**
	 * 
	 */
	@Test
	public void getFunctionalComponentTest() { 
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- getFunctionalComponentTest()");
		System.out.println();
		
		// get functional components
		List<DDLComponent> components = this.model.getFunctionalComponents();
		Assert.assertNotNull(components);
		Assert.assertTrue(components.size() > 0);
		
		// print components
		for (DDLComponent comp : components) {
			Assert.assertNotNull(comp);
			// functional component
			System.out.println(comp);
			System.out.println();
		}
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void getInternalComponentTest() { 
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- getInternalComponentTest()");
		System.out.println();
		
		// get internal components
		List<DDLComponent> components = this.model.getInternalComponents();
		Assert.assertNotNull(components);
		Assert.assertTrue(components.size() > 0);
		
		// print components
		for (DDLComponent comp : components) {
			Assert.assertNotNull(comp);
			// functional component
			System.out.println(comp);
			System.out.println();
		}
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void getExternalComponentTest() { 
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- getExternalComponentTest()");
		System.out.println();
		
		// get external components
		List<DDLComponent> components = this.model.getExternalComponents();
		Assert.assertNotNull(components);
		Assert.assertTrue(components.size() > 0);
		
		// print components
		for (DDLComponent comp : components) {
			Assert.assertNotNull(comp);
			// functional component
			System.out.println(comp);
			System.out.println();
		}
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void getSynchronizationTest() { 
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- getSynchronizationTest()");
		System.out.println();
		
		// get synchronizations
		List<DDLSynchronization> syncs = this.model.getSynchronizations();
		Assert.assertNotNull(syncs);
		Assert.assertTrue(syncs.size() > 0);
		
		// print components
		for (DDLSynchronization sync : syncs) {
			Assert.assertNotNull(sync);
			// functional component
			System.out.println(sync);
			System.out.println();
		}
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void printPlanningDomainTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- printPlanningDomainTest()");
		System.out.println();
		String ddl = this.model.getPlanningDomainDescription();
		Assert.assertNotNull(ddl);
		System.out.println(ddl);
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void generateDDLFileTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- printPlanningDomainTest()");
		System.out.println();
		try {
			// generate DDL file
			this.model.generateDDLFile("/Users/alessandroumbrico/Desktop");
			Assert.assertTrue(true);
			System.out.println("OK!");
		}
		catch (Exception ex) {
			Assert.assertFalse(true);
		}
		System.out.println("-----------------------------------------------------------------");
	}
}
