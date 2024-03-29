package it.istc.pst.kbcl.inference.kb.rdf;

import it.istc.pst.kbcl.mapping.kb.rdf.RDFDatasetManager;
import it.istc.pst.kbcl.mapping.kb.rdf.RDFFunctionalityDAO;
import it.istc.pst.kbcl.mapping.kb.rdf.RDFMappingKnowledgeBaseFactory;
import it.istc.pst.kbcl.mapping.kb.rdf.exception.RDFResourceNotFoundException;
import it.istc.pst.kbcl.mapping.model.rdf.RDFFunctionality;
import it.istc.pst.kbcl.mapping.model.rdf.RDFFunctionalityImplementation;
import it.istc.pst.kbcl.mapping.model.rdf.RDFFunctionalityType;
import it.istc.pst.kbcl.model.Functionality;

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
public class RDFFunctionalityDAOTest 
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
		System.out.println("*************** RDF Functionality DAO Test *****************");
		System.out.println("************************************************************");
	}
	
	/**
	 * 
	 */
	@After
	public void cleanUp() {
		// close data-set manager
		RDFDatasetManager.getSingletonInstance().close();
	}
	
	/**
	 * 
	 */
	@Test
	public void retrieveAllFunctionalityTypesTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- retrieveAllFunctionalityTypesTest()");
		System.out.println();
		
		// create DAO
		RDFFunctionalityDAO dao = this.factory.createFunctionalityDAO();
		
		// call DAO method
		List<RDFFunctionalityType> types = dao.retrieveAllFunctionalityTypes();
		Assert.assertNotNull(types);
		Assert.assertFalse(types.isEmpty());
		Assert.assertTrue(types.size() == 3);
		// print agent types
		System.out.println(types);
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void retrieveFunctionalitiesByTypeTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- retrieveFunctionalitiesByTypeTest()");
		System.out.println();
		
		// create DAO
		RDFFunctionalityDAO dao = this.factory.createFunctionalityDAO();
		
		// get all functionality types
		List<RDFFunctionalityType> types = dao.retrieveAllFunctionalityTypes();
		for (RDFFunctionalityType type : types) {
			// call DAO method
			List<RDFFunctionality> funcs = dao.retrieveFunctionalitiesByType(type);
			Assert.assertNotNull(funcs);
			Assert.assertTrue(funcs.size() >= 0);
			// print agent types
			System.out.println(funcs);
		}
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void retrieveAllFunctionalitiesTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- retrieveAllFunctionalitiesTest()");
		System.out.println();
		
		// create DAO
		RDFFunctionalityDAO dao = this.factory.createFunctionalityDAO();
		
		// call DAO method
		List<RDFFunctionality> funcs = dao.retrieveAllFunctionalities();
		Assert.assertNotNull(funcs);
		Assert.assertTrue(funcs.size() >= 0);
		// print agent types
		System.out.println(funcs);
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void retrieveFunctionalityByIdTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- retrieveFunctionalityByIdTest()");
		System.out.println();
		
		// create DAO
		RDFFunctionalityDAO dao = this.factory.createFunctionalityDAO();
		
		// call DAO method
		RDFFunctionality f1 = dao.retrieveAllFunctionalities().get(0);
		Assert.assertNotNull(f1);
		System.out.println(f1);
		try {
			// success expected
			RDFFunctionality f2 = dao.retrieveFunctionalityById(f1.getId());
			Assert.assertNotNull(f2);
			Assert.assertTrue(f1.equals(f2));
			Assert.assertEquals(f1.getLabel(), f2.getLabel());
			Assert.assertEquals(f1.getMinDuration(), f2.getMinDuration());
			Assert.assertEquals(f1.getMaxDuration(), f2.getMaxDuration());
			System.out.println(f2);
		}
		catch (RDFResourceNotFoundException ex) {
			Assert.assertTrue(false);
		}
		
		try {
			// failure expected
			Functionality f3 = dao.retrieveFunctionalityById("pippo");
			System.out.println(f3);
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
	public void retrieveFunctionalityImplementationTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- retrieveFunctionalityImplementationTest()");
		System.out.println();
		
		// create DAO
		RDFFunctionalityDAO dao = this.factory.createFunctionalityDAO();
		
		// call DAO method
		List<RDFFunctionality> funcs = dao.retrieveAllFunctionalities();
		Assert.assertNotNull(funcs);
		Assert.assertTrue(funcs.size() >= 0);
		// get a functionality
		RDFFunctionality func = funcs.get(0);
		System.out.println(func);
		// get implementation
		List<RDFFunctionalityImplementation> implementations = dao.retrieveFunctionalityImplementations(func);
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
		System.out.println("-----------------------------------------------------------------");
	}
}
