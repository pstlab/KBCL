package it.istc.pst.gecko.ontology.kb.owl;

import it.istc.pst.gecko.ontology.model.owl.OWLFunctionality;
import it.istc.pst.gecko.ontology.model.owl.OWLFunctionalityType;

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
public class OWLFunctionalityDAOTest {

	private OWLFunctionalityDAO dao;
	
	/**
	 * 
	 */
	@Before
	public void init() {
		// create factory
		this.dao = new OWLFunctionalityDAO();
		
		System.out.println("************************************************************");
		System.out.println("*************** OWL Functionality DAO Test *****************");
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
	public void retrieveAllFunctionalityTypesTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- retrieveAllFunctionalityTypesTest()");
		System.out.println();
		
		// call DAO method
		List<OWLFunctionalityType> types = this.dao.retrieveAllFunctionalityTypes();
		Assert.assertNotNull(types);
		Assert.assertFalse(types.isEmpty());
		// print element types
		System.out.println(types);
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void createFunctionalityType() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- createFunctionalityType()");
		System.out.println();
		
		// get types
		List<OWLFunctionalityType> types = this.dao.retrieveAllFunctionalityTypes();
		// create a module instance
		for (OWLFunctionalityType type : types) {
			// check type
			if (type.getLabel().equals("Channel")) {
				// create element
				OWLFunctionality f = this.dao.createFunctionality("channelFB", type);
				Assert.assertNotNull(f);
				Assert.assertTrue(f.getType().equals(type));
				System.out.println("New Individual -> " + f);
			}
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
		
		// get types
		List<OWLFunctionalityType> types = this.dao.retrieveAllFunctionalityTypes();
		// create a module instance
		OWLFunctionality cfb = null;
		OWLFunctionality cbf = null;
		for (OWLFunctionalityType type : types) {
			// check type
			if (type.getLabel().equals("Channel")) {
				// create port element
				cfb = this.dao.createFunctionality("ChannelFB", type);
				System.out.println("New Individual -> " + cfb);
				cbf = this.dao.createFunctionality("ChannelBF", type);
				System.out.println("New Individual -> " + cbf);
			}
 		}
		
		Assert.assertNotNull(cfb);
		Assert.assertNotNull(cbf);
		
		// get elements
		List<OWLFunctionality> list = this.dao.retrieveAllFunctionalities();
		Assert.assertNotNull(list);
		Assert.assertFalse(list.isEmpty());
		Assert.assertTrue(list.contains(cfb));
		Assert.assertTrue(list.contains(cbf));
		System.out.println("-----------------------------------------------------------------");
	}
}
