package it.istc.pst.gecko.ontology.kb.model.owl;

import it.istc.pst.kbcl.ontology.model.owl.OWLAgent;
import it.istc.pst.kbcl.ontology.model.owl.OWLElement;
import it.istc.pst.kbcl.ontology.model.owl.OWLFunctionality;
import it.istc.pst.kbcl.ontology.model.owl.OWLKnowledgeBaseFacade;
import it.istc.pst.kbcl.ontology.model.owl.OWLPort;

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
public class OWLAgentTest {


	private OWLKnowledgeBaseFacade facade;
	
	/**
	 * 
	 */
	@Before
	public void init() {
		System.out.println("************************************************************");
		System.out.println("************* OWL Knowledge Base Facade Test ***************");
		System.out.println("************************************************************");
		
		this.facade = OWLKnowledgeBaseFacade.getSingletonInstance();
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
	public void getNeighborsTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- getNeighborsTest()");
		System.out.println();
		// get agents
		List<OWLAgent> agents = this.facade.getAgents();
		Assert.assertNotNull(agents);
		Assert.assertFalse(!agents.isEmpty());
		OWLAgent t1 = agents.get(0);
		System.out.println(t1);
		
		// get functionalities
		List<OWLElement> neighbors = t1.getNeighbors();
		Assert.assertNotNull(neighbors);
		Assert.assertFalse(neighbors.isEmpty());
		for (OWLElement neighbor : neighbors) {
			Assert.assertNotNull(neighbor);
			System.out.println(neighbor);
		}
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void getPortsTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- getPortsTest()");
		System.out.println();
		// get agents
		List<OWLAgent> agents = this.facade.getAgents();
		Assert.assertNotNull(agents);
		Assert.assertFalse(!agents.isEmpty());
		OWLAgent t1 = agents.get(0);
		System.out.println(t1);
		
		// get functionalities
		List<OWLPort> ports = t1.getPorts();
		Assert.assertNotNull(ports);
		Assert.assertFalse(ports.isEmpty());
		for (OWLPort port : ports) {
			Assert.assertNotNull(port);
			System.out.println(port);
		}
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void checkUpdateOnPortDisconnection() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- checkUpdateOnPortDisconnection()");
		System.out.println();
		// get agents
		List<OWLAgent> agents = this.facade.getAgents();
		Assert.assertNotNull(agents);
		Assert.assertFalse(!agents.isEmpty());
		OWLAgent t1 = agents.get(0);
		System.out.println(t1);
		
		// get functionalities
		List<OWLPort> ports1 = t1.getPorts();
		Assert.assertNotNull(ports1);
		Assert.assertFalse(ports1.isEmpty());
		System.out.println("... Initial ports");
		System.out.println("The module has " + ports1.size() + " ports");
		
		// get a port
		OWLPort p = ports1.get(0);
		// disconnect
		p.disconnect();
		
		// get new ports
		List<OWLPort> ports2 = t1.getPorts();
		Assert.assertNotNull(ports2);
		Assert.assertTrue(ports1.size() != ports2.size());
		Assert.assertTrue(ports1.size() > ports2.size());
		Assert.assertFalse(ports2.contains(p));
		System.out.println("... Ports after disconnection");
		System.out.println("The module has " + ports2.size() + " ports");
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void checkUpdateOnRemoveOfElement() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- checkUpdateOnRemoveOfElement()");
		System.out.println();
		// get agents
		List<OWLAgent> agents = this.facade.getAgents();
		Assert.assertNotNull(agents);
		Assert.assertFalse(!agents.isEmpty());
		OWLAgent t1 = agents.get(0);
		System.out.println(t1);
		
		// get functionalities
		List<OWLPort> ports1 = t1.getPorts();
		Assert.assertNotNull(ports1);
		Assert.assertFalse(ports1.isEmpty());
		System.out.println("... Initial ports");
		System.out.println("The module has " + ports1.size() + " ports");
		
		// get a port
		OWLPort p = ports1.get(0);
		System.out.println("... Removing port " + p);
		t1.removeElement(p);
		
		// get new ports
		List<OWLPort> ports2 = t1.getPorts();
		Assert.assertNotNull(ports2);
		Assert.assertTrue(ports1.size() != ports2.size());
		Assert.assertTrue(ports1.size() > ports2.size());
		Assert.assertFalse(ports2.contains(p));
		System.out.println("... Ports after removal");
		System.out.println("The module has " + ports2.size() + " ports");
		System.out.println(ports2);
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void checkNeighborUpdateOnRemoveOfAgentElements() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- checkNeighborUpdateOnRemoveOfAgentElements()");
		System.out.println();
		// get agents
		List<OWLAgent> agents = this.facade.getAgents();
		Assert.assertNotNull(agents);
		Assert.assertFalse(!agents.isEmpty());
		OWLAgent t1 = agents.get(0);
		System.out.println(t1);
		
		// get functionalities
		List<OWLElement> neighbors1 = t1.getNeighbors();
		Assert.assertNotNull(neighbors1);
		Assert.assertFalse(neighbors1.isEmpty());
		System.out.println("... Initial neighbors");
		System.out.println("The module has " + neighbors1.size() + " neighbors");
		
		// get a port
		OWLPort p = t1.getPorts().get(0);
		System.out.println("... Removing port " + p);
		// remove port
		t1.removeElement(p);
		
		// get new ports
		List<OWLElement> neighbors2 = t1.getNeighbors();
		Assert.assertNotNull(neighbors2);
		Assert.assertTrue(neighbors1.size() != neighbors2.size());
		Assert.assertTrue(neighbors1.size() > neighbors2.size());
		System.out.println("... Neighbors after port removal");
		System.out.println("The module has " + neighbors2.size() + " neighbors");
		System.out.println(neighbors2);
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void checkNeighborUpdateOnPortDisconnection() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- checkNeighborUpdateOnPortDisconnection()");
		System.out.println();
		// get agents
		List<OWLAgent> agents = this.facade.getAgents();
		Assert.assertNotNull(agents);
		Assert.assertFalse(!agents.isEmpty());
		OWLAgent t1 = agents.get(0);
		System.out.println(t1);
		
		// get functionalities
		List<OWLElement> neighbors1 = t1.getNeighbors();
		Assert.assertNotNull(neighbors1);
		Assert.assertFalse(neighbors1.isEmpty());
		System.out.println("... Initial neighbors");
		System.out.println("The module has " + neighbors1.size() + " neighbors");
		
		// get a port
		OWLPort p = t1.getPorts().get(0);
		System.out.println("... Disconnecting port " + p);
		p.disconnect();
		
		// get new ports
		List<OWLElement> neighbors2 = t1.getNeighbors();
		Assert.assertNotNull(neighbors2);
		Assert.assertTrue(neighbors1.size() != neighbors2.size());
		Assert.assertTrue(neighbors1.size() > neighbors2.size());
		System.out.println("... Neighbors after port disconnection");
		System.out.println("The module has " + neighbors2.size() + " neighbors");
		System.out.println(neighbors2);
		System.out.println("-----------------------------------------------------------------");
	}
	
	
	
	/**
	 * 
	 */
	@Test
	public void getFunctionalitiesTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- getFunctionalitiesTest()");
		System.out.println();
		// get agents
		List<OWLAgent> agents = this.facade.getAgents();
		Assert.assertNotNull(agents);
		Assert.assertFalse(!agents.isEmpty());
		OWLAgent t1 = agents.get(0);
		System.out.println(t1);
		
		// get functionalities
		List<OWLFunctionality> funcs = t1.getFunctionalities();
		Assert.assertNotNull(funcs);
		Assert.assertFalse(funcs.isEmpty());
		System.out.println("The module has " + funcs.size() + " functionalities");
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void checkFunctionalityUpdateOnPortDisconnections() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- checkFunctionalityUpdateOnPortDisconnections()");
		System.out.println();
		// get agents
		List<OWLAgent> agents = this.facade.getAgents();
		Assert.assertNotNull(agents);
		Assert.assertFalse(!agents.isEmpty());
		OWLAgent t1 = agents.get(0);
		System.out.println(t1);

		// get functionalities
		List<OWLFunctionality> funcs1 = t1.getFunctionalities();
		Assert.assertNotNull(funcs1);
		Assert.assertFalse(funcs1.isEmpty());
		System.out.println("... Initial set of functions");
		System.out.println("The module has " + funcs1.size() + " functions");
		
		// get ports
		List<OWLPort> ports = t1.getPorts();
		Assert.assertNotNull(ports);
		Assert.assertFalse(ports.isEmpty());
		// disconnect a port
		ports.get(0).disconnect();
		
		// check new list of functionalities
		List<OWLFunctionality> funcs2 = t1.getFunctionalities();
		Assert.assertNotNull(funcs2);
		Assert.assertFalse(funcs2.isEmpty());
		System.out.println("... Set of functions after port disconnection");
		System.out.println("The module has " + funcs2.size() + " functions after port disconnection");
		// compare lists
		Assert.assertTrue(funcs1.size() != funcs2.size());
		Assert.assertTrue(funcs1.size() > funcs2.size());
		
		// disconnect another port
		ports = t1.getPorts();
		Assert.assertNotNull(ports);
		Assert.assertFalse(ports.isEmpty());
		// disconnect a port
		ports.get(0).disconnect();
		
		// check new list of functionalities
		List<OWLFunctionality> funcs3 = t1.getFunctionalities();
		Assert.assertNotNull(funcs3);
		Assert.assertFalse(funcs3.isEmpty());
		System.out.println("... Set of functions after (another) port disconnection");
		System.out.println("The module has " + funcs3.size() + " functions after (another) port disconnection");
		// compare lists
		Assert.assertTrue(funcs1.size() != funcs2.size() && funcs2.size() != funcs3.size());
		Assert.assertTrue(funcs1.size() > funcs2.size() && funcs2.size() > funcs3.size());
		System.out.println("-----------------------------------------------------------------");
	}
	
	
	/**
	 * 
	 */
	@Test
	public void checkFunctionalityUpdateOnRemoveOfAgentElements() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- checkFunctionalityUpdateOnRemoveOfAgentElements()");
		System.out.println();
		// get agents
		List<OWLAgent> agents = this.facade.getAgents();
		Assert.assertNotNull(agents);
		Assert.assertFalse(!agents.isEmpty());
		OWLAgent t1 = agents.get(0);
		System.out.println(t1);

		// get functionalities
		List<OWLFunctionality> funcs1 = t1.getFunctionalities();
		Assert.assertNotNull(funcs1);
		Assert.assertFalse(funcs1.isEmpty());
		System.out.println("... Initial set of functions");
		System.out.println("The module has " + funcs1.size() + " functions");
		
		// get ports
		List<OWLPort> ports = t1.getPorts();
		Assert.assertNotNull(ports);
		Assert.assertFalse(ports.isEmpty());
		// get port to remove
		OWLPort p1 = ports.get(0);
		System.out.println("... Removing port " + p1);
		// remove port
		t1.removeElement(p1);
		
		// check new list of functionalities
		List<OWLFunctionality> funcs2 = t1.getFunctionalities();
		Assert.assertNotNull(funcs2);
		Assert.assertFalse(funcs2.isEmpty());
		System.out.println("... Set of functions after port removal");
		System.out.println("The module has " + funcs2.size() + " functions after port removal");
		// compare lists
		Assert.assertTrue(funcs1.size() != funcs2.size());
		Assert.assertTrue(funcs1.size() > funcs2.size());
		
		// disconnect another port
		ports = t1.getPorts();
		Assert.assertNotNull(ports);
		Assert.assertFalse(ports.isEmpty());
		// get port to remove
		OWLPort p2 = ports.get(0);
		System.out.println("... Removing port " + p2);
		// remove port
		t1.removeElement(p2);
		
		// check new list of functionalities
		List<OWLFunctionality> funcs3 = t1.getFunctionalities();
		Assert.assertNotNull(funcs3);
		Assert.assertFalse(funcs3.isEmpty());
		System.out.println("... Set of functions after (another) port removal");
		System.out.println("The module has " + funcs3.size() + " functions after (another) port removal");
		// compare lists
		Assert.assertTrue(funcs1.size() != funcs2.size() && funcs2.size() != funcs3.size());
		Assert.assertTrue(funcs1.size() > funcs2.size() && funcs2.size() > funcs3.size());
		System.out.println("-----------------------------------------------------------------");
	}
}
