package it.istc.pst.kbcl.ontology.kb.owl;

import it.istc.pst.kbcl.ontology.kb.owl.exception.OWLClassNotFoundException;
import it.istc.pst.kbcl.ontology.kb.owl.exception.OWLIndividualNotFoundException;
import it.istc.pst.kbcl.ontology.kb.owl.exception.OWLPropertyNotFoundException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasonerFactory;
import com.hp.hpl.jena.vocabulary.ReasonerVocabulary;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class OWLDatasetManager 
{
	public static final String PROPERTY_LABEL_HAS_CHANNEL = "hasChannel";
	public static final String PROPERTY_LABEL_HAS_FUNCTIONALITY = "hasFunctionality";
	public static final String PROPERTY_LABEL_HAS_NEIGHBOR = "hasNeighbor";
	public static final String PROPERTY_LABEL_HAS_PORT = "hasPort";
	public static final String PROPERTY_LABEL_HAS_INPUT_PORT = "hasInput";
	public static final String PROPERTY_LABEL_HAS_OUTPUT_PORT = "hasOutput";
	public static final String PROPERTY_LABEL_CONNECT = "connect";
	public static final String PROPERTY_LABEL_HAS_ELEMENT = "hasElement";
	public static final String PROPERTY_LABEL_HAS_CROSS_TRANSFER = "hasCrossTransfer";
	public static final String PROPERTY_LABEL_HAS_CONVEYOR = "hasConveyor";
	
	// module agent ID and type
	public static final String CONSTANT_AGENT_TYPE = "Agent";
	public static final String CONSTANT_MODULE_TYPE = "Module";
	private static final String CONSTANT_MODULE_ID = "t1";
	
	// module ports
	public static final String CONSTANT_ELEMENT_TYPE = "Element";
	public static final String CONSTANT_PORT_TYPE = "Port";
	private static final String[] CONSTANT_PORTS = new String[] {
		"portF", "portB", "portR1", "portR2", "portR3", "portL1", "portL2", "portL3"
	};
	
	// module conveyors
	public static final String CONSTANT_CONVEYOR_TYPE = "ConveyorEngine";
	private static final String[] CONSTANT_CONVEYORS = new String[] {
		"conveyor0", "conveyor1", "conveyor2", "conveyor3"
	};
	
	// module cross transfers
	public static final String CONSTANT_CROSS_TRANSFER_TYPE = "CrossTransferEngine";
	private static final String[] CONSTANT_CROSS_TRANSFERS = new String[] {
		"cross1", "cross2", "cross3"
	};
	
	// possible channels
	public static final String CONSTANT_FUNCTIONALITY_TYPE = "Functionality";
	public static final String CONSTANT_CHANNEL_TYPE = "Channel";
	private static final String[] CONSTANT_CHANNELS = new String[] {
		// F
		"channelFB", "channelFF", "channelFL1", "channelFL2", "channelFL3", "channelFR1", "channelFR2", "channelFR3",
		// B
		"channelBB", "channelBF", "channelBL1", "channelBL2", "channelBL3", "channelBR1", "channelBR2", "channelBR3",
		// L1
		"channelL1B", "channelL1F", "channelL1L1", "channelL1L2", "channelL1L3", "channelL1R1", "channelL1R2", "channelL1R3",
		// L2
		"channelL2B", "channelL2F", "channelL2L1", "channelL2L2", "channelL2L3", "channelL2R1", "channelL2R2", "channelL2R3",
		// L3
		"channelL3B", "channelL3F", "channelL3L1", "channelL3L2", "channelL3L3", "channelL3R1", "channelL3R2", "channelL3R3",
		// R1
		"channelR1B", "channelR1F", "channelR1L1", "channelR1L2", "channelR1L3", "channelR1R1", "channelR1R2", "channelR1R3",
		// R2
		"channelR2B", "channelR2F", "channelR2L1", "channelR2L2", "channelR2L3", "channelR2R1", "channelR2R2", "channelR2R3",
		// R3
		"channelR3B", "channelR3F", "channelR3L1", "channelR3L2", "channelR3L3", "channelR3R1", "channelR3R2", "channelR3R3"
	};
	
	// possible neighbors
	public static final String CONSTANT_NEIGHBOR_TYPE = "Neighbor";
	private static final String[] CONSTANT_NEIGHBORS = new String[] {
		"neighborF", "neighborB", "neighborL", "neighborR"
	};
	
	private static final String NS_URL = "http://pst.istc.cnr.it/ontology/kbcl";
	private static final String NS = NS_URL + "#";
	private static final String DATASET = "kbcl/kbcl_v5.owl";
	
	private OntModel model;
	private InfModel infModel;
	private String label;
	private boolean debug;
	private long totalTime;
	private long maxTime;
	
	private static OWLDatasetManager INSTANCE = null;

	/**
	 * 
	 */
	private OWLDatasetManager() 
	{
		try 
		{
			// get start time
			long start = System.currentTimeMillis();
			// get property file
			Properties prop = new Properties();
			prop.load(new FileInputStream(new File("etc/dataManager.properties")));
			// get debug flag
			this.debug = prop.getProperty("debug").equals("1") ? true : false;
			
			// set label
			this.label = this.getClass().getSimpleName();
			// create the model
			this.model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RDFS_INF);
			this.model.getDocumentManager().addAltEntry(NS_URL, "file:" + DATASET);
			this.model.read(NS_URL, "RDF/XML");
			
			// setup
			this.setup();
			
			// set rule reasoner configuration
			Resource config = this.model.createResource();
			// get reasoner type from configuration file
			String reasonerType = prop.getProperty("rule_reasoner_type");
			config.addProperty(ReasonerVocabulary.PROPruleMode, reasonerType);
			config.addProperty(ReasonerVocabulary.PROPruleSet, "etc/infRules");
			// create generic rule reasoner
			Reasoner reasoner = GenericRuleReasonerFactory.theInstance().create(config);
			
			// initialize inference model
			this.infModel = ModelFactory.createInfModel(reasoner, this.model);
			// set inference time
			this.totalTime = System.currentTimeMillis() - start;
			this.maxTime = this.totalTime;
		}
		catch (OWLClassNotFoundException ex) {
			System.err.println(ex.getMessage());
		}
		catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	/**
	 * 
	 */
	public static OWLDatasetManager getSingletonInstance() {
		if (INSTANCE == null) {
			INSTANCE = new OWLDatasetManager();
		}
		return INSTANCE;
	}
	
	/**
	 * 
	 * @return
	 */
	public long getTotalInferenceTime() {
		return this.totalTime;
	}
	
	/**
	 * 
	 * @return
	 */
	public long getMaxInferenceTime() {
		return this.maxTime;
	}
	
	/**
	 * 
	 * @param id
	 * @param className
	 * @return
	 * @throws OWLClassNotFoundException
	 */
	public OWLInstance createIndividual(String id, String className) 
			throws OWLClassNotFoundException
	{
		// get class
		OntClass ic = this.model.getOntClass(NS + className);
		// check if class exists
		if (ic == null) {
			throw new OWLClassNotFoundException("[" + this.label + "]: No class found \"" + NS + className + "\"");
		}
		
		// create individual
		Individual i = ic.createIndividual(NS + id);
		// return owl element
		return new OWLInstance(i.getURI(), i.getLocalName(), new OWLClass(ic.getURI(), ic.getLocalName()));
	}
	
	/**
	 * 
	 * @param className
	 * @return
	 * @throws OWLClassNotFoundException
	 */
	public List<OWLClass> retrieveAllSubclasses(String className) 
			throws OWLClassNotFoundException
	{
		// list of subclasses 
		List<OWLClass> list = new ArrayList<>();
		// start time
		long start = System.currentTimeMillis();
		// get class
		OntClass c = this.model.getOntClass(NS + className);
		// update inference time
		long time = System.currentTimeMillis() - start;
		this.totalTime += time;
		// set max time
		this.maxTime = Math.max(this.maxTime, time);
		if (c == null) {
			throw new OWLClassNotFoundException("[" + this.label + "]: Class not found \"" + NS + className + "\"");
		}
		
		// list subclasses
		Iterator<OntClass> it = c.listSubClasses();
		while (it.hasNext()) {
			// next subclass
			OntClass sc = it.next();
			if (!sc.isAnon()) {
				list.add(new OWLClass(sc.getURI(), sc.getLocalName()));
			}
		}
		// get list
		return list;
	}
	
	/**
	 * 
	 * @param subjectName
	 * @param propertyName
	 * @return
	 * @throws OWLIndividualNotFoundException
	 * @throws OWLPropertyNotFoundException
	 */
	public List<OWLInstance> retrieveAllInstancesRelatedByProperty(String subjectName, String propertyName) 
			throws OWLIndividualNotFoundException, OWLPropertyNotFoundException
	{
		// list of individuals
		List<OWLInstance> list = new ArrayList<>();
		// get start time
		long start = System.currentTimeMillis();
		// get subject individual
		Individual subject = this.model.getIndividual(NS + subjectName);
		if (subject == null) {
			// exception
			throw new OWLIndividualNotFoundException("[" + this.label + "]: Individual not found \"" + NS + subjectName + "\"");
		}
	
		// get property
		Property p = this.model.getOntProperty(NS + propertyName);
		if (p == null) {
			// exception
			throw new OWLPropertyNotFoundException("[" + this.label + "]: Property not found \"" + NS + propertyName + "\"");
		}
		
		// list statements
		Iterator<Statement> it = this.infModel.listStatements(subject, p, (RDFNode) null);
		// update inference time
		long time = System.currentTimeMillis() - start;
		this.totalTime += time;
		this.maxTime = Math.max(this.maxTime, time);
		while (it.hasNext()) {
			// get next statement
			Statement s = it.next();
			// get statement's object
			Resource obj = s.getObject().asResource();
			if (!obj.isAnon()) {
				// find in model
				Individual n = this.model.getIndividual(obj.getURI());
				// get class
				Resource nType = n.getRDFType();
				list.add(new OWLInstance(n.getURI(), n.getLocalName(), 
						new OWLClass(nType.getURI(), nType.getLocalName())));
			}
		}
		
		// get list
		return list;
	}
	
	/**
	 * 
	 * @param className
	 * @return
	 * @throws OWLClassNotFoundException
	 */
	public List<OWLInstance> retrieveAllInstancesOfClass(String className) 
			throws OWLClassNotFoundException
	{
		// list instances
		List<OWLInstance> list = new ArrayList<>();
		long start = System.currentTimeMillis();
		// get class
		OntClass c = this.model.getOntClass(NS + className);
		if (c == null) {
			throw new OWLClassNotFoundException("[" + this.label + "]: Class not found \"" + NS + className + "\"");
		}
		
		// get individuals
		Iterator<? extends OntResource> it = c.listInstances(false);
		// update inference time
		long time = System.currentTimeMillis() - start;
		this.totalTime += time;
		this.maxTime = Math.max(this.maxTime, time);
		while (it.hasNext()) {
			// next individual
			Individual i = it.next().asIndividual();
			if (!i.isAnon()) {
				list.add(new OWLInstance(i.getURI(), i.getLocalName(), new OWLClass(c.getURI(), c.getLocalName())));
			}
		}
		
		// get list
		return list;
	}
	
	/**
	 * 
	 * @param label2
	 * @param propertyLabelConnect
	 * @param label3
	 */
	public void assertStatement(String subjectName, String propertyName, String objectName) 
			throws OWLIndividualNotFoundException, OWLPropertyNotFoundException 
	{
		// start time
		long start = System.currentTimeMillis();
		// get subject individual
		Resource subject = this.infModel.getResource(NS + subjectName);
		if (subject == null) {
			throw new OWLIndividualNotFoundException("[" + this.label +"]: No Individual found \"" + NS + subjectName + "\"");
		}
		
		// get object individual
		Resource object = this.infModel.getResource(NS + objectName);
		if (object == null) {
			throw new OWLIndividualNotFoundException("[" + this.label +"]: No Individual found \"" + NS + objectName + "\"");
		}
		
		// get property
		Property p = this.infModel.getProperty(NS + propertyName);
		if (p == null) {
			throw new OWLPropertyNotFoundException("[" + this.label + "]: No property found \"" + NS + propertyName + "\"");
		}
		
		// assert property directly into the inference model
		subject.addProperty(p, object);
		// update inference time
		long time = System.currentTimeMillis() - start;
		this.totalTime += time;
		this.maxTime = Math.max(this.maxTime, time);
	}
	
	/**
	 * 
	 * @param subjectName
	 * @param propertyName
	 * @param objectName
	 * @throws OWLIndividualNotFoundException
	 * @throws OWLPropertyNotFoundException
	 */
	public void removeStatement(String subjectName, String propertyName, String objectName) 
			throws OWLIndividualNotFoundException, OWLPropertyNotFoundException 
	{
		// get subject individual
		Individual subject = this.model.getIndividual(NS + subjectName);
		if (subject == null) {
			throw new OWLIndividualNotFoundException("[" + this.label +"]: No Individual found \"" + NS + subjectName + "\"");
		}
		
		// get object individual
		Individual object = this.model.getIndividual(NS + objectName);
		if (object == null) {
			throw new OWLIndividualNotFoundException("[" + this.label +"]: No Individual found \"" + NS + objectName + "\"");
		}
		
		// get property
		Property p = this.model.getProperty(NS + propertyName);
		if (p == null) {
			throw new OWLPropertyNotFoundException("[" + this.label + "]: No property found \"" + NS + propertyName + "\"");
		}
		
		// remove property
		subject.removeProperty(p, object);
		// start time
		long start = System.currentTimeMillis();
		// update inference model
		this.infModel.rebind();
		// update inference time
		long time = System.currentTimeMillis() - start;
		this.totalTime += time;
		this.maxTime = Math.max(this.maxTime, time);
	}
	
	/**
	 * 
	 */
	public void close() {
		if (this.infModel != null) {
			this.infModel.close();
			this.infModel = null;
		}
		if (this.model != null) {
			this.model.close();
			this.model = null;
		}
		INSTANCE = null;
	}
	
	
	/**
	 * 
	 * @throws OWLClassNotFoundException
	 */
	private void setup() 
			throws OWLClassNotFoundException 
	{
		// check debug flag
		if (this.debug) {
			System.out.println("Setting up OWLDataManager... ");
		}
		// get module agent type
		OntClass module = this.model.getOntClass(NS + CONSTANT_MODULE_TYPE);
		if (module == null) {
			throw new OWLClassNotFoundException("[" + this.label + "]: Module class " + NS + CONSTANT_MODULE_TYPE + " not found");
		}
		
		// create agent individual
		Individual t = module.createIndividual(NS + CONSTANT_MODULE_ID);
		if (this.debug) {
			System.out.println("... Individual " + NS + CONSTANT_MODULE_ID + " successfully created!");
		}
		
		// add cross transfer constants
		this.setupCrossTransferConstants(t);
		// add conveyor constants
		this.setupConveyorConstants(t);
		// add neighbor constants
		Map<String, Individual> neighbors = this.setupNeighborConstants();
		// add port constants
		Map<String, Individual> ports = this.setupPortConstants(t, neighbors);
		// add channel constants
		this.setupChannelFucntionConstants(ports);
	}
	
	/**
	 * 
	 * @param agent
	 * @throws OWLClassNotFoundException
	 */
	private void setupConveyorConstants(Individual agent) 
			throws OWLClassNotFoundException 
	{
		// get class
		OntClass conveyorType = this.model.getOntClass(NS + CONSTANT_CONVEYOR_TYPE);
		if (conveyorType == null) {
			throw new OWLClassNotFoundException("[" + this.label+ "]: Conveyor class " + NS + CONSTANT_CONVEYOR_TYPE +  " not found");
		}
		
		// create individuals
		for (String conveyorName : CONSTANT_CONVEYORS) {
			// create individual
			Individual conveyor = conveyorType.createIndividual(NS + conveyorName);
			if (this.debug) {
				System.out.println("... Individual " + NS + conveyorName + " successfully created!");
			}
			
			// associate cross transfer to module
			Property he = this.model.getProperty(NS + PROPERTY_LABEL_HAS_ELEMENT);
			agent.addProperty(he, conveyor);
			if (this.debug) {
				System.out.println("Statement [" + agent.getLocalName() + " " + he.getLocalName() + " " + conveyor.getLocalName() + "] successfully added!");
			}
		}
	}

	/**
	 * 
	 * @param agent
	 * @throws OWLClassNotFoundException
	 */
	private void setupCrossTransferConstants(Individual agent) 
			throws OWLClassNotFoundException 
	{
		// get class
		OntClass crossType = this.model.getOntClass(NS + CONSTANT_CROSS_TRANSFER_TYPE);
		if (crossType == null) {
			throw new OWLClassNotFoundException("[" + this.label+ "]: Cross Transfer class " + NS + CONSTANT_CROSS_TRANSFER_TYPE +  " not found");
		}
		
		// create individuals
		for (String crossName : CONSTANT_CROSS_TRANSFERS) {
			// create individual
			Individual cross = crossType.createIndividual(NS + crossName);
			if (this.debug) {
				System.out.println("... Individual " + NS + crossName + " successfully created!");
			}
			
			// associate cross transfer to module
			Property he = this.model.getProperty(NS + PROPERTY_LABEL_HAS_ELEMENT);
			agent.addProperty(he, cross);
			if (this.debug) {
				System.out.println("Statement [" + agent.getLocalName() + " " + he.getLocalName() + " " + cross.getLocalName() + "] successfully added!");
			}
		}
	}

	/**
	 * 
	 * @return
	 * @throws OWLClassNotFoundException
	 */
	private Map<String, Individual> setupNeighborConstants() 
			throws OWLClassNotFoundException 
	{
		// get neighbor type
		OntClass neighborType = this.model.getOntClass(NS + CONSTANT_NEIGHBOR_TYPE);
		// check 
		if (neighborType == null) {
			throw new OWLClassNotFoundException("[" + this.label + "]: Neighbor class " + NS + CONSTANT_NEIGHBOR_TYPE + " not found");
		}
		// index neighbor
		Map<String, Individual> neighbors = new HashMap<>();
		// create constant neighbors
		for (String neighborName : CONSTANT_NEIGHBORS) {
			// create individual
			Individual n = neighborType.createIndividual(NS + neighborName);
			if (this.debug) {
				System.out.println("... Individual " + NS + neighborName + " successfully created!");
			}
			// add individual to cache
			neighbors.put(neighborName, n);
		}
		
		return neighbors;
	}
	
	/**
	 * 
	 * @param agent
	 * @param neighbors
	 * @return
	 * @throws OWLClassNotFoundException
	 */
	private Map<String, Individual> setupPortConstants(Individual agent, Map<String, Individual> neighbors) 
			throws OWLClassNotFoundException
	{
		// index of created ports
		Map<String, Individual> ports = new HashMap<>();
		// get port type
		OntClass portType = this.model.getOntClass(NS + CONSTANT_PORT_TYPE);
		if (portType == null) {
			throw new OWLClassNotFoundException("[" + this.label + "]: Port class " + NS + CONSTANT_PORT_TYPE + " not found");
		}
		
		// create constant ports
		for (String portName : CONSTANT_PORTS) {
			// create individual
			Individual i = portType.createIndividual(NS + portName);
			if (this.debug) {
				System.out.println("... Individual " + NS + portName + " successfully created!");
			}
			
			// associate port to module
			Property he = this.model.getProperty(NS + PROPERTY_LABEL_HAS_ELEMENT);
			agent.addProperty(he, i);
			if (this.debug) {
				System.out.println("Statement [" + agent.getLocalName() + " " + he.getLocalName() + " " + i.getLocalName() + "] successfully added!");
			}

			// get connect property
			Property c = this.model.getProperty(NS + PROPERTY_LABEL_CONNECT);
			// associate port to neighbor
			switch (portName) {
				case "portF" : {
					// get connected neighbor
					Individual n = neighbors.get("neighborF");
					i.addProperty(c, n);
					if (this.debug) {
						System.out.println("Statement [" + i.getLocalName() + " " + c.getLocalName() + " " + n.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "portB" : {
					// get connected neighbor
					Individual n = neighbors.get("neighborB");
					i.addProperty(c, n);
					if (this.debug) {
						System.out.println("Statement [" + i.getLocalName() + " " + c.getLocalName() + " " + n.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "portL1" : {
					// get connected neighbor
					Individual n = neighbors.get("neighborL");
					i.addProperty(c, n);
					if (this.debug) {
						System.out.println("Statement [" + i.getLocalName() + " " + c.getLocalName() + " " + n.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "portL2" : {
					// get connected neighbor
					Individual n = neighbors.get("neighborL");
					i.addProperty(c, n);
					if (this.debug) {
						System.out.println("Statement [" + i.getLocalName() + " " + c.getLocalName() + " " + n.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "portL3" : {
					// get connected neighbor
					Individual n = neighbors.get("neighborL");
					i.addProperty(c, n);
					if (this.debug) {
						System.out.println("Statement [" + i.getLocalName() + " " + c.getLocalName() + " " + n.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "portR1" : {
					// get connected neighbor
					Individual n = neighbors.get("neighborR");
					i.addProperty(c, n);
					if (this.debug) {
						System.out.println("Statement [" + i.getLocalName() + " " + c.getLocalName() + " " + n.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "portR2" : {
					// get connected neighbor
					Individual n = neighbors.get("neighborR");
					i.addProperty(c, n);
					if (this.debug) {
						System.out.println("Statement [" + i.getLocalName() + " " + c.getLocalName() + " " + n.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "portR3" : {
					// get connected neighbor
					Individual n = neighbors.get("neighborR");
					i.addProperty(c, n);
					if (this.debug) {
						System.out.println("Statement [" + i.getLocalName() + " " + c.getLocalName() + " " + n.getLocalName() + "] successfully added!");
					}
				}
				break;
			}
			
			// add index
			ports.put(portName, i);
		}
		
		// get ports
		return ports;
	}

	/**
	 * 
	 * @param ports
	 * @throws OWLClassNotFoundException
	 */
	private void setupChannelFucntionConstants(Map<String, Individual> ports) 
			throws OWLClassNotFoundException
	{
		// get function type
		OntClass channelType = this.model.getOntClass(NS + CONSTANT_CHANNEL_TYPE);
		if (channelType == null) { 
			throw new OWLClassNotFoundException("[" + this.label + "]: Channel class " + NS + CONSTANT_CHANNEL_TYPE + " not found");
		}
		
		// create constant channels
		for (String channelName : CONSTANT_CHANNELS) {
			// create individual
			Individual c = channelType.createIndividual(NS + channelName);
			if (this.debug) {
				System.out.println("... Individual " + NS + channelName + " successfully created!");
			}
			
			// get input/output properties
			Property ip = this.model.getProperty(NS + PROPERTY_LABEL_HAS_INPUT_PORT);
			Property op = this.model.getProperty(NS + PROPERTY_LABEL_HAS_OUTPUT_PORT);
			// set input/output ports
			switch (channelName) 
			{
				case "channelFB" : {
					// get input/output ports
					Individual input = ports.get("portF");
					Individual output = ports.get("portB");
					// set input and output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelFF" : {
					// get input/output ports
					Individual port = ports.get("portF");
					// set input/output ports
					c.addProperty(ip, port);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + port.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, port);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + port.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelFL1" : {
					// get input/output ports
					Individual input = ports.get("portF");
					Individual output = ports.get("portL1");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelFL2" : {
					// get input/output ports
					Individual input = ports.get("portF");
					Individual output = ports.get("portL2");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break; 
				
				case "channelFL3" : {
					// get input/output ports
					Individual input = ports.get("portF");
					Individual output = ports.get("portL3");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelFR1" : {
					// get input/output ports
					Individual input = ports.get("portF");
					Individual output = ports.get("portR1");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelFR2" : {
					// get input/output ports
					Individual input = ports.get("portF");
					Individual output = ports.get("portR2");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelFR3" : {
					// get input/output ports
					Individual input = ports.get("portF");
					Individual output = ports.get("portR3");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelBB" : {
					// get input/output ports
					Individual port = ports.get("portB");
					// set input/output ports
					c.addProperty(ip, port);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + port.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, port);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + port.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelBF" : {
					// get input/output ports
					Individual input = ports.get("portB");
					Individual output = ports.get("portF");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelBL1" : {
					// get input/output ports
					Individual input = ports.get("portB");
					Individual output = ports.get("portL1");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelBL2" : {
					// get input/output ports
					Individual input = ports.get("portB");
					Individual output = ports.get("portL2");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelBL3" : {
					// get input/output ports
					Individual input = ports.get("portB");
					Individual output = ports.get("portL3");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelBR1" : {
					// get input/output ports
					Individual input = ports.get("portB");
					Individual output = ports.get("portR1");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelBR2" : {
					// get input/output ports
					Individual input = ports.get("portB");
					Individual output = ports.get("portR2");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelBR3" : {
					// get input/output ports
					Individual input = ports.get("portB");
					Individual output = ports.get("portR3");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelL1B" : {
					// get input/output ports
					Individual input = ports.get("portL1");
					Individual output = ports.get("portB");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelL1F" : {
					// get input/output ports
					Individual input = ports.get("portL1");
					Individual output = ports.get("portF");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelL1L1" : {
					// get input/output ports
					Individual port = ports.get("portL1");
					// set input/output ports
					c.addProperty(ip, port);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + port.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, port);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + port.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelL1L2" : {
					// get input/output ports
					Individual input = ports.get("portL1");
					Individual output = ports.get("portL2");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelL1L3" : {
					// get input/output ports
					Individual input = ports.get("portL1");
					Individual output = ports.get("portL3");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelL1R1" : {
					// get input/output ports
					Individual input = ports.get("portL1");
					Individual output = ports.get("portR1");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelL1R2" : {
					// get input/output ports
					Individual input = ports.get("portL1");
					Individual output = ports.get("portR2");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelL1R3" : {
					// get input/output ports
					Individual input = ports.get("portL1");
					Individual output = ports.get("portR3");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelL2B" : {
					// get input/output ports
					Individual input = ports.get("portL2");
					Individual output = ports.get("portB");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelL2F" : {
					// get input/output ports
					Individual input = ports.get("portL2");
					Individual output = ports.get("portF");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelL2L1" : {
					// get input/output ports
					Individual input = ports.get("portL2");
					Individual output = ports.get("portL1");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelL2L2" : {
					// get input/output ports
					Individual port = ports.get("portL2");
					// set input/output ports
					c.addProperty(ip, port);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + port.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, port);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + port.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelL2L3" : {
					// get input/output ports
					Individual input = ports.get("portL2");
					Individual output = ports.get("portL3");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelL2R1" : {
					// get input/output ports
					Individual input = ports.get("portL2");
					Individual output = ports.get("portR1");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelL2R2" : {
					// get input/output ports
					Individual input = ports.get("portL2");
					Individual output = ports.get("portR2");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelL2R3" : {
					// get input/output ports
					Individual input = ports.get("portL2");
					Individual output = ports.get("portR3");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelL3B" : {
					// get input/output ports
					Individual input = ports.get("portL3");
					Individual output = ports.get("portB");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelL3F" : {
					// get input/output ports
					Individual input = ports.get("portL3");
					Individual output = ports.get("portF");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelL3L1" : {
					// get input/output ports
					Individual input = ports.get("portL3");
					Individual output = ports.get("portL1");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelL3L2" : {
					// get input/output ports
					Individual input = ports.get("portL3");
					Individual output = ports.get("portL2");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelL3L3" : {
					// get input/output ports
					Individual port = ports.get("portL3");
					// set input/output ports
					c.addProperty(ip, port);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + port.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, port);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + port.getLocalName() + "] successfully added!");
					}
				}
				break; 
				
				case "channelL3R1" : {
					// get input/output ports
					Individual input = ports.get("portL3");
					Individual output = ports.get("portR1");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelL3R2" : {
					// get input/output ports
					Individual input = ports.get("portL3");
					Individual output = ports.get("portR2");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelL3R3" : {
					// get input/output ports
					Individual input = ports.get("portL3");
					Individual output = ports.get("portR3");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelR1B" : {
					// get input/output ports
					Individual input = ports.get("portR1");
					Individual output = ports.get("portB");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelR1F" : {
					// get input/output ports
					Individual input = ports.get("portR1");
					Individual output = ports.get("portF");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelR1L1" : {
					// get input/output ports
					Individual input = ports.get("portR1");
					Individual output = ports.get("portL1");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelR1L2" : {
					// get input/output ports
					Individual input = ports.get("portR1");
					Individual output = ports.get("portL2");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelR1L3" : {
					// get input/output ports
					Individual input = ports.get("portR1");
					Individual output = ports.get("portL3");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelR1R1" : {
					// get input/output ports
					Individual port = ports.get("portR1");
					// set input/output ports
					c.addProperty(ip, port);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + port.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, port);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + port.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelR1R2" : {
					// get input/output ports
					Individual input = ports.get("portR1");
					Individual output = ports.get("portR2");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelR1R3" : {
					// get input/output ports
					Individual input = ports.get("portR1");
					Individual output = ports.get("portR3");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelR2B" : {
					// get input/output ports
					Individual input = ports.get("portR2");
					Individual output = ports.get("portB");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelR2F" : {
					// get input/output ports
					Individual input = ports.get("portR2");
					Individual output = ports.get("portF");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelR2L1" : {
					// get input/output ports
					Individual input = ports.get("portR2");
					Individual output = ports.get("portL1");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelR2L2" : {
					// get input/output ports
					Individual input = ports.get("portR2");
					Individual output = ports.get("portL2");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelR2L3" : {
					// get input/output ports
					Individual input = ports.get("portR2");
					Individual output = ports.get("portL3");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelR2R1" : {
					// get input/output ports
					Individual input = ports.get("portR2");
					Individual output = ports.get("portR1");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelR2R2" : {
					// get input/output ports
					Individual port = ports.get("portR2");
					// set input/output ports
					c.addProperty(ip, port);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + port.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, port);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + port.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelR2R3" : {
					// get input/output ports
					Individual input = ports.get("portR2");
					Individual output = ports.get("portR3");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelR3B" : {
					// get input/output ports
					Individual input = ports.get("portR3");
					Individual output = ports.get("portB");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelR3F" : {
					// get input/output ports
					Individual input = ports.get("portR3");
					Individual output = ports.get("portF");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break; 
				
				case "channelR3L1" : {
					// get input/output ports
					Individual input = ports.get("portR3");
					Individual output = ports.get("portL1");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelR3L2" : {
					// get input/output ports
					Individual input = ports.get("portR3");
					Individual output = ports.get("portL2");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break; 
				
				case "channelR3L3" : {
					// get input/output ports
					Individual input = ports.get("portR3");
					Individual output = ports.get("portL3");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelR3R1" : {
					// get input/output ports
					Individual input = ports.get("portR3");
					Individual output = ports.get("portR1");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelR3R2" : {
					// get input/output ports
					Individual input = ports.get("portR3");
					Individual output = ports.get("portR2");
					// set input/output ports
					c.addProperty(ip, input);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + input.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, output);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + output.getLocalName() + "] successfully added!");
					}
				}
				break;
				
				case "channelR3R3" : {
					// get input/output ports
					Individual port = ports.get("portR3");
					// set input/output ports
					c.addProperty(ip, port);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + ip.getLocalName() + " " + port.getLocalName() + "] successfully added!");
					}
					c.addProperty(op, port);
					if (this.debug) {
						System.out.println("Statement [" + c.getLocalName() + " " + op.getLocalName() + " " + port.getLocalName() + "] successfully added!");
					}
				}
				break;
			}
		}
	}
}
