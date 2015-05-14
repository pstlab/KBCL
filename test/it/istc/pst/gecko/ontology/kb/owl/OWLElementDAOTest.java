package it.istc.pst.gecko.ontology.kb.owl;

import it.istc.pst.gecko.ontology.kb.exception.PropertyNotFoundException;
import it.istc.pst.gecko.ontology.model.Element;
import it.istc.pst.gecko.ontology.model.ElementType;
import it.istc.pst.gecko.ontology.model.owl.OWLElement;
import it.istc.pst.gecko.ontology.model.owl.OWLElementType;
import it.istc.pst.gecko.ontology.model.owl.OWLModelFactory;
import it.istc.pst.gecko.ontology.model.owl.OWLPort;

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
	private OWLModelFactory factory;
	
	/**
	 * 
	 */
	@Before
	public void init() {
		// create factory
		this.dao = new OWLElementDAO();
		this.factory = OWLModelFactory.getSingletonInstance();
		
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
				OWLElement port = this.dao.createElement("portF", type);
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
	
	/**
	 * 
	 */
	@Test
	public void setAndRetrievePortNeighborTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- setAndRetrievePortNeighborTest()");
		System.out.println();
		
		// call DAO method
		List<OWLElementType> types = this.dao.retrieveAllElementTypes();
		OWLPort portF = null;
		OWLElement neighborF = null;
		// create a module instance
		for (ElementType type : types) {
			// check type
			if (type.getLabel().equals("Port")) {
				// create element
				OWLElement e = this.dao.createElement("portF", type);
				portF = this.factory.createPort(e.getId(), e.getLabel());
				Assert.assertNotNull(portF);
				Assert.assertTrue(portF.getType().equals(type));
				Assert.assertNull(portF.getConnectedNeighbor());
				System.out.println("New Individual -> " + portF);
			}
			if (type.getLabel().equals("Neighbor")) {
				// create element
				OWLElement e = this.dao.createElement("neighborF", type);
				neighborF = this.factory.createNeighbor(e.getId(), e.getLabel());
				Assert.assertNotNull(neighborF);
				Assert.assertTrue(neighborF.getType().equals(type));
				System.out.println("New Individual -> " + neighborF);
			}
			
		}
		
		// check port and neighbor
		if (neighborF != null && portF != null) {
			try 
			{
				// set port neighbor
				this.dao.setPortNeighbor(neighborF, portF);
				// get connected port
				OWLElement ne = this.dao.retrievePortNeighbor(portF);
				Assert.assertNotNull(ne);
				Assert.assertTrue(ne.equals(neighborF));
				System.out.println("Port updated -> " + portF + "\n\tneighbor -> " + neighborF);
			}
			catch (PropertyNotFoundException ex) {
				System.out.println(ex.getMessage());
				Assert.assertTrue(false);
			}
		}
		else {
			System.out.println("Error while creating resources");
			Assert.assertTrue(false);
		}
		
		// print element types
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void multipleSetAndRetrievePortNeighborTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- setAndRetrievePortNeighborTest()");
		System.out.println();
		
		// call DAO method
		List<OWLElementType> types = this.dao.retrieveAllElementTypes();
		OWLPort portF = null;
		OWLElement neighborF = null;
		OWLElement neighborB = null;
		// create a module instance
		for (ElementType type : types) {
			// check type
			if (type.getLabel().equals("Port")) {
				// create element
				OWLElement e = this.dao.createElement("portF", type);
				portF = this.factory.createPort(e.getId(), e.getLabel());
				Assert.assertNotNull(portF);
				Assert.assertTrue(portF.getType().equals(type));
				Assert.assertNull(portF.getConnectedNeighbor());
				System.out.println("New Individual -> " + portF);
			}
			if (type.getLabel().equals("Neighbor")) {
				// create element
				OWLElement e = this.dao.createElement("neighborF", type);
				neighborF = this.factory.createNeighbor(e.getId(), e.getLabel());
				Assert.assertNotNull(neighborF);
				Assert.assertTrue(neighborF.getType().equals(type));
				System.out.println("New Individual -> " + neighborF);
				
				// create element
				e = this.dao.createElement("neighborB", type);
				neighborB = this.factory.createNeighbor(e.getId(), e.getLabel());
				Assert.assertNotNull(neighborB);
				Assert.assertTrue(neighborB.getType().equals(type));
				System.out.println("New Individual -> " + neighborB);
			}
			
		}
		
		// check port and neighbor
		if (neighborF != null && neighborB != null && portF != null) {
			try 
			{
				// set port neighbor
				this.dao.setPortNeighbor(neighborF, portF);
				// get connected element
				OWLElement ne = this.dao.retrievePortNeighbor(portF);
				Assert.assertNotNull(ne);
				Assert.assertTrue(ne.equals(neighborF));
				Assert.assertTrue(!ne.equals(neighborB));
				System.out.println("Port updated -> " + portF + "\n\tneighbor= " + neighborF);
				
				// update neighbor
				this.dao.setPortNeighbor(neighborB, portF);
				// get connected element
				ne = this.dao.retrievePortNeighbor(portF);
				Assert.assertNotNull(ne);
				Assert.assertTrue(ne.equals(neighborB));
				Assert.assertTrue(!ne.equals(neighborF));
				System.out.println("Port updated -> " + portF + "\n\tneighbor= " + neighborB);
			}
			catch (PropertyNotFoundException ex) {
				System.out.println(ex.getMessage());
				Assert.assertTrue(false);
			}
		}
		else {
			System.out.println("Error while creating resources");
			Assert.assertTrue(false);
		}
		
		// print element types
		System.out.println("-----------------------------------------------------------------");
	}
}
