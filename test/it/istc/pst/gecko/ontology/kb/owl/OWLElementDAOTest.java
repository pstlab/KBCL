package it.istc.pst.gecko.ontology.kb.owl;

import it.istc.pst.gecko.ontology.model.Element;
import it.istc.pst.gecko.ontology.model.ElementType;
import it.istc.pst.gecko.ontology.model.owl.OWLElement;
import it.istc.pst.gecko.ontology.model.owl.OWLElementType;

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
public class OWLElementDAOTest {

	private OWLElementDAO dao;
	
	/**
	 * 
	 */
	@Before
	public void init() {
		// create factory
		this.dao = new OWLElementDAO();
		
		System.out.println("************************************************************");
		System.out.println("******************* OWL Element DAO Test *********************");
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
	public void retrieveAllElementTypesTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- retrieveAllElementTypesTest()");
		System.out.println();
		
		// call DAO method
		List<OWLElementType> types = this.dao.retrieveAllElementTypes();
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
	public void createElementTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- createElementTest()");
		System.out.println();
		
		// get types
		List<OWLElementType> types = this.dao.retrieveAllElementTypes();
		// create a module instance
		for (ElementType type : types) {
			// check type
			if (type.getLabel().equals("Port")) {
				// create element
				Element port = this.dao.createElement("portF", type);
				Assert.assertNotNull(port);
				Assert.assertTrue(port.getType().equals(type));
				System.out.println("New Individual -> " + port);
			}
		}
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void retrieveAllElementsTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- retrieveAllElementsTest()");
		System.out.println();
		
		// get types
		List<OWLElementType> types = this.dao.retrieveAllElementTypes();
		// create a module instance
		Element port = null;
		Element neighbor = null;
		Element conveyor = null;
		for (ElementType type : types) {
			// check type
			if (type.getLabel().equals("Port")) {
				// create port element
				port = this.dao.createElement("portB", type);
				System.out.println("New Individual -> " + port);
			}
			
			if (type.getLabel().equals("Neighbor")) {
				// create neighbor element
				neighbor = this.dao.createElement("neighborF", type);
				System.out.println("New Individual -> " + neighbor);
			}
			
			if (type.getLabel().equals("ConveyorEngine")) {
				// create main conveyor engine
				conveyor = this.dao.createElement("mainConveyor", type);
				System.out.println("New Individual -> " + conveyor);
			}
 		}
		
		Assert.assertNotNull(port);
		Assert.assertNotNull(neighbor);
		Assert.assertNotNull(conveyor);
		
		// get elements
		List<OWLElement> list = this.dao.retrieveAllElements();
		Assert.assertNotNull(list);
		Assert.assertFalse(list.isEmpty());
		Assert.assertTrue(list.contains(port));
		Assert.assertTrue(list.contains(neighbor));
		Assert.assertTrue(list.contains(conveyor));
		System.out.println("-----------------------------------------------------------------");
	}
}
