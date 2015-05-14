package it.istc.pst.gecko.ontology.ps.ddl;

import it.istc.pst.gecko.ontology.RDFKnowledgeBaseFacade;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class DDLKnowledgeProcessorTest 
{
	private DDLKnowledgeBaseProcessor processor;
	
	/**
	 * 
	 */
	@Before
	public void init() {
		// get an agent
		RDFKnowledgeBaseFacade facade = RDFKnowledgeBaseFacade.getSingletonInstance();
		this.processor = new DDLKnowledgeBaseProcessor(facade.getAgents().get(0), 1000);
		
		System.out.println("************************************************************");
		System.out.println("*************** DDL Knowledge Processor Test ***************");
		System.out.println("************************************************************");
	}
	
	/**
	 * 
	 */
	@Test
	public void buildDDLFunctionalComponentsTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- buildDDLFunctionalComponentsTest()");
		System.out.println();
		
		// get functional components
		List<DDLFunctionalComponent> components = this.processor.extractFunctionalComponents();
		Assert.assertNotNull(components);
		Assert.assertTrue(components.size() > 0);
		
		// print components
		for (DDLComponent comp : components) {
			Assert.assertNotNull(comp);
			// functional component
			System.out.println("Functional Component: " +  comp.getName());
			// print values
			for (DDLValue value : comp.getValues()) {
				Assert.assertNotNull(value);
				// print value
				System.out.println("\t- " + value.getValue() + " [" + value.getDmin() + ", " + value.getDmax() + "]");
				// print successors
				for (DDLValue successor : comp.getSuccessors(value)) {
					System.out.println("\t\t-> " + successor.getValue() + " [" + successor.getDmin() + ", " + successor.getDmax() + "]");
				}
			}
			System.out.println();
		}
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void buildDDLInternalComponentsTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- buildDDLInternalComponentsTest()");
		System.out.println();
		
		// get internal components
		List<DDLInternalComponent> components = this.processor.extractInternalComponents();
		Assert.assertNotNull(components);
		Assert.assertTrue(components.size() > 0);
		
		// print components
		for (DDLComponent comp : components) {
			Assert.assertNotNull(comp);
			// internal component
			System.out.println("Internal Component: " +  comp.getName());
			// print values
			for (DDLValue value : comp.getValues()) {
				Assert.assertNotNull(value);
				// print value
				System.out.println("\t- " + value.getValue() + " [" + value.getDmin() + ", " + value.getDmax() + "]");
				// print successors
				for (DDLValue successor : comp.getSuccessors(value)) {
					System.out.println("\t\t-> " + successor.getValue() + " [" + successor.getDmin() + ", " + successor.getDmax() + "]");
				}
			}
			System.out.println();
		}
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void buildDDLExternalComponentsTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- buildDDLExternalComponentsTest()");
		System.out.println();
		
		// get external components
		List<DDLExternalComponent> components = this.processor.extractExternalComponents();
		Assert.assertNotNull(components);
		Assert.assertTrue(components.size() > 0);
		
		// print components
		for (DDLComponent comp : components) {
			Assert.assertNotNull(comp);
			// external component
			System.out.println("External Component: " +  comp.getName());
			// print values
			for (DDLValue value : comp.getValues()) {
				Assert.assertNotNull(value);
				// print value
				System.out.println("\t- " + value.getValue() + " [" + value.getDmin() + ", " + value.getDmax() + "]");
				// print successors
				for (DDLValue successor : comp.getSuccessors(value)) {
					System.out.println("\t\t-> " + successor.getValue() + " [" + successor.getDmin() + ", " + successor.getDmax() + "]");
				}
			}
			System.out.println();
		}
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void buildDDLSynchronizationsTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- buildDDLSynchronizationsTest()");
		System.out.println();
		
		// get synchronizations
		List<DDLSynchronization> syncs = this.processor.extractSynchronizations();
		Assert.assertNotNull(syncs);
		Assert.assertTrue(syncs.size() > 0);
		for (DDLSynchronization sync : syncs) {
			// current synchronization
			Assert.assertNotNull(sync);
			// synchronization reference
			DDLValue reference = sync.getReference();
			Assert.assertNotNull(reference);
			Assert.assertNotNull(reference.getComponent());
			System.out.println("Synchronization for functionality " + reference.getValue() + " [" + reference.getDmin() + ", " + reference.getDmax() + "]");
			System.out.println("... require constraints:");
			// get required constraints
			List<DDLConstraint> constraints = sync.getConstraints();
			Assert.assertNotNull(constraints);
			for (DDLConstraint con : constraints) {
				// current constraint
				Assert.assertNotNull(con);
				Assert.assertEquals(reference, con.getReference());
				
				// get target
				DDLValue target = con.getTarget();
				Assert.assertNotNull(target);
				Assert.assertNotNull(target.getComponent());
				System.out.println("\t" + con.getConstraint().label + " " + target.getValue() + " [" + target.getDmin() + ", " + target.getDmax() + "]");
				// get related component
				DDLComponent comp = target.getComponent();
				System.out.println("\t\ton component:  " + comp.getName() + "." + comp.getTimeline());
			}
			System.out.println();
		}
		System.out.println("-----------------------------------------------------------------");
	}
}
