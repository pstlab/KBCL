package it.istc.pst.gecko.ontology.kb.owl;

import it.istc.pst.gecko.ontology.kb.exception.PropertyNotFoundException;
import it.istc.pst.gecko.ontology.model.owl.OWLChannel;
import it.istc.pst.gecko.ontology.model.owl.OWLElement;
import it.istc.pst.gecko.ontology.model.owl.OWLElementType;
import it.istc.pst.gecko.ontology.model.owl.OWLFunctionality;
import it.istc.pst.gecko.ontology.model.owl.OWLFunctionalityType;
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
public class OWLFunctionalityDAOTest {

	private OWLFunctionalityDAO dao;
	private OWLElementDAO edao;
	private OWLModelFactory factory;
	
	/**
	 * 
	 */
	@Before
	public void init() {
		// create factory
		this.dao = new OWLFunctionalityDAO();
		this.edao = new OWLElementDAO();
		this.factory = OWLModelFactory.getSingletonInstance();
		
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
	
	/**
	 * 
	 */
	@Test
	public void setAndRetrieveChannelInputPortTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- setAndRetrieveChannelInputPortTest()");
		System.out.println();
		
		// get types
		List<OWLFunctionalityType> types = this.dao.retrieveAllFunctionalityTypes();
		
		// create functionality
		OWLChannel channel = null;
		for (OWLFunctionalityType type : types) {
			// check type
			if (type.getLabel().equals("Channel")) {
				// create element
				OWLFunctionality f = this.dao.createFunctionality("channelFB", type);
				// create channel
				channel = this.factory.createChannel(f.getId(), f.getLabel());
				Assert.assertNotNull(channel);
				Assert.assertTrue(channel.getType().equals(type));
				System.out.println("New Individual -> " + channel);
			}
		}
		
		// create element
		OWLPort port = null;
		for (OWLElementType type : this.edao.retrieveAllElementTypes()) {
			// check type
			if (type.getLabel().equals("Port")) {
				// create element
				OWLElement e = this.edao.createElement("portF", type);
				// create port
				port = this.factory.createPort(e.getId(), e.getLabel());
				Assert.assertNotNull(port);
				Assert.assertTrue(type.equals(port.getType()));
				System.out.println("New Individual -> " + port);
			}
		}
		
		// check 
		if (channel != null && port != null) {
			try 
			{
				// set input port
				this.dao.setInputPortToChannel(port, channel);
				// get input port
				OWLPort e = this.dao.retrieveChannelInputPort(channel);
				Assert.assertNotNull(e);
				Assert.assertTrue(e.equals(port));
				System.out.println("Channel updated -> " + channel + "\n\tintput-port -> " + port);
			}
			catch (PropertyNotFoundException ex) {
				System.err.println(ex.getMessage());
				Assert.assertTrue(false);
			}
		}
		else {
			System.out.println("Error while creating resources");
			Assert.assertTrue(false);
		}
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void setAndRetrieveChannelOutputPortTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- setAndRetrieveChannelOutputPortTest()");
		System.out.println();
		
		// get types
		List<OWLFunctionalityType> types = this.dao.retrieveAllFunctionalityTypes();
		
		// create functionality
		OWLChannel channel = null;
		for (OWLFunctionalityType type : types) {
			// check type
			if (type.getLabel().equals("Channel")) {
				// create element
				OWLFunctionality f = this.dao.createFunctionality("channelFB", type);
				// create channel
				channel = this.factory.createChannel(f.getId(), f.getLabel());
				Assert.assertNotNull(channel);
				Assert.assertTrue(channel.getType().equals(type));
				System.out.println("New Individual -> " + channel);
			}
		}
		
		// create element
		OWLPort port = null;
		for (OWLElementType type : this.edao.retrieveAllElementTypes()) {
			// check type
			if (type.getLabel().equals("Port")) {
				// create element
				OWLElement e = this.edao.createElement("portF", type);
				// create port
				port = this.factory.createPort(e.getId(), e.getLabel());
				Assert.assertNotNull(port);
				Assert.assertTrue(type.equals(port.getType()));
				System.out.println("New Individual -> " + port);
			}
		}
		
		// check 
		if (channel != null && port != null) {
			try 
			{
				// set input port
				this.dao.setOutputPortToChannel(port, channel);
				// get input port
				OWLPort e = this.dao.retrieveChannelOutputPort(channel);
				Assert.assertNotNull(e);
				Assert.assertTrue(e.equals(port));
				System.out.println("Channel updated -> " + channel + "\n\tintput-port -> " + port);
			}
			catch (PropertyNotFoundException ex) {
				System.err.println(ex.getMessage());
				Assert.assertTrue(false);
			}
		}
		else {
			System.out.println("Error while creating resources");
			Assert.assertTrue(false);
		}
		System.out.println("-----------------------------------------------------------------");
	}
}
