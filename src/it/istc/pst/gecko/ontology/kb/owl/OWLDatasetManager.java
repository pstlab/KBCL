package it.istc.pst.gecko.ontology.kb.owl;

import it.istc.pst.gecko.ontology.exception.KBSetupErrorException;
import it.istc.pst.gecko.ontology.kb.owl.exception.OWLClassNotFoundException;
import it.istc.pst.gecko.ontology.kb.owl.exception.OWLIndividualNotFoundException;
import it.istc.pst.gecko.ontology.kb.owl.exception.OWLPropertyNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.hp.hpl.jena.reasoner.rulesys.Rule;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class OWLDatasetManager 
{
	public static final String PROPERTY_LABEL_HAS_CHANNEL = "hasChannel";
	public static final String PROPERTY_LABEL_HAS_ACTUATOR = "hasInternalActuator";
	public static final String PROPERTY_LABEL_HAS_NEIGHBOR = "hasNeighbor";
	public static final String PROPERTY_LABEL_HAS_PORT = "hasPort";
	public static final String PROPERTY_LABEL_HAS_INPUT_PORT = "hasInput";
	public static final String PROPERTY_LABEL_HAS_OUTPUT_PORT = "hasOutput";
	public static final String PROPERTY_LABEL_CONNECT = "connect";
	
	// module agent ID and type
	private static final String CONSTANT_MODULE_TYPE = "Module";
	private static final String CONSTANT_MODULE_ID = "t1";
	
	// possible ports
	private static final String CONSTANT_PORT_TYPE = "Port";
	private static final String[] CONSTANT_PORTS = new String[] {
		"portF", "portB", "portR1", "portR2", "portR3", "portL1", "portL2", "portL3"
	};
	
	// possible channels
	private static final String CONSTANT_CHANNEL_TYPE = "Channel";
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
	private static final String CONSTANT_NEIGHBOR_TYPE = "Neighbor";
	private static final String[] CONSTANT_NEIGHBORS = new String[] {
		"neighborF", "neighborB", "neighborL", "neighborR"
	};
	
	
	
	
	
	private static final String NS_URL = "http://pst.istc.cnr.it/ontology/kbcl";
	private static final String NS = NS_URL + "#";
	private static final String DATASET = "kbcl/kbcl_v5.owl";
	
	// inference rules
	private static final String RULES = ""
			+ "[r1: (?m " + NS + "hasElement ?e), (?e " + NS + "connect ?n) "
				+ "-> (?m " + NS + "hasPort ?e)]\n"
			+ "[r2: (?m " + NS + "hasElement ?e), (?e " + NS + "connect ?n) "
				+ "-> (?m " + NS + "hasNeighbor ?n)]\n"
			+ "[r3: (?f " + NS + "hasOutput ?a), (?f " + NS +"hasInput ?b), (?m " + NS +"hasPort ?a), (?m " + NS + "hasPort ?b) "
				+ "-> (?m " + NS + "hasChannel ?f)]";
	
	private OntModel model;
	private InfModel infModel;
	private String label;
	
	private static OWLDatasetManager INSTANCE = null;
	
	/**
	 * 
	 */
	private OWLDatasetManager() {
		// set label
		this.label = this.getClass().getSimpleName();
		// create the model
		this.model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RULE_INF);
		this.model.getDocumentManager().addAltEntry(NS_URL, "file:" + DATASET);
		
		// import ONTOLOGY model
		this.model.read(NS_URL, "RDF/XML");
		
		// setup
		this.setup();
		
		// setup INFERENCE model
		this.infModel = ModelFactory.createInfModel(new GenericRuleReasoner(Rule.parseRules(RULES)), this.model);
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
		List<OWLClass> list = new ArrayList<>();
		// get class
		OntClass c = this.model.getOntClass(NS + className);
		if (c == null) {
			throw new OWLClassNotFoundException("[" + this.label + "]: Class not found \"" + NS + className + "\"");
		}
		
		// list subclasses
		Iterator<OntClass> it = c.listSubClasses();
		while (it.hasNext()) {
			// next subclass
			OntClass sc = it.next();
			list.add(new OWLClass(sc.getURI(), sc.getLocalName()));
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
		List<OWLInstance> list = new ArrayList<>();
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
		while (it.hasNext()) {
			// get next statement
			Statement s = it.next();
			// get statement's object
			Individual obj = s.getObject().as(Individual.class);
			// get class
			Resource objType = obj.getRDFType();
			list.add(new OWLInstance(obj.getURI(), obj.getLocalName(), 
					new OWLClass(objType.getURI(), objType.getLocalName())));
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
		List<OWLInstance> list = new ArrayList<>();
		// get class
		OntClass c = this.model.getOntClass(NS + className);
		if (c == null) {
			throw new OWLClassNotFoundException("[" + this.label + "]: Class not found \"" + NS + className + "\"");
		}
		
		// get individuals
		Iterator<? extends OntResource> it = c.listInstances(false);
		while (it.hasNext()) {
			// next individual
			Individual i = it.next().asIndividual();
			list.add(new OWLInstance(i.getURI(), i.getLocalName(), new OWLClass(c.getURI(), c.getLocalName())));
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
		
		// assert property
		subject.addProperty(p, object);
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
	 * @throws KBSetupErrorException
	 */
	private void setup() 
	{
		// get module agent type
		OntClass module = this.model.getOntClass(NS + CONSTANT_MODULE_TYPE);
		// create agent individual
		module.createIndividual(NS + CONSTANT_MODULE_ID);
		
		// get neighbor type
		OntClass neighborType = this.model.getOntClass(NS + CONSTANT_NEIGHBOR_TYPE);
		// create constant neighbors
		for (String neighborName : CONSTANT_NEIGHBORS) {
			// create individual
			neighborType.createIndividual(NS + neighborName);
		}
		
		// index of created ports
		Map<String, Individual> indexPort = new HashMap<>();
		// get port type
		OntClass portType = this.model.getOntClass(NS + CONSTANT_PORT_TYPE);
		// create constant ports
		for (String portName : CONSTANT_PORTS) {
			// create individual
			Individual i = portType.createIndividual(NS + portName);
			// add index
			indexPort.put(portName, i);
		}
		
		// get function type
		OntClass channelType = this.model.getOntClass(NS + CONSTANT_CHANNEL_TYPE);
		// create constant channels
		for (String channelName : CONSTANT_CHANNELS) {
			// create individual
			Individual c = channelType.createIndividual(NS + channelName);
			// get input/output properties
			Property ip = this.model.getProperty(NS + PROPERTY_LABEL_HAS_INPUT_PORT);
			Property op = this.model.getProperty(NS + PROPERTY_LABEL_HAS_OUTPUT_PORT);
			// set input/output ports
			switch (channelName) 
			{
				case "channelFB" : {
					// get input/output ports
					Individual input = indexPort.get("portF");
					Individual output = indexPort.get("portB");
					// set input and output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelFF" : {
					// get input/output ports
					Individual port = indexPort.get("portF");
					// set input/output ports
					c.addProperty(ip, port);
					c.addProperty(op, port);
				}
				break;
				
				case "channelFL1" : {
					// get input/output ports
					Individual input = indexPort.get("portF");
					Individual output = indexPort.get("portL1");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelFL2" : {
					// get input/output ports
					Individual input = indexPort.get("portF");
					Individual output = indexPort.get("portL2");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break; 
				
				case "channelFL3" : {
					// get input/output ports
					Individual input = indexPort.get("portF");
					Individual output = indexPort.get("portL3");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelFR1" : {
					// get input/output ports
					Individual input = indexPort.get("portF");
					Individual output = indexPort.get("portR1");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelFR2" : {
					// get input/output ports
					Individual input = indexPort.get("portF");
					Individual output = indexPort.get("portR2");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelFR3" : {
					// get input/output ports
					Individual input = indexPort.get("portF");
					Individual output = indexPort.get("portR3");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelBB" : {
					// get input/output ports
					Individual port = indexPort.get("portB");
					// set input/output ports
					c.addProperty(ip, port);
					c.addProperty(op, port);
				}
				break;
				
				case "channelBF" : {
					// get input/output ports
					Individual input = indexPort.get("portB");
					Individual output = indexPort.get("portF");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelBL1" : {
					// get input/output ports
					Individual input = indexPort.get("portB");
					Individual output = indexPort.get("portL1");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelBL2" : {
					// get input/output ports
					Individual input = indexPort.get("portB");
					Individual output = indexPort.get("portL2");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelBL3" : {
					// get input/output ports
					Individual input = indexPort.get("portB");
					Individual output = indexPort.get("portL3");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelBR1" : {
					// get input/output ports
					Individual input = indexPort.get("portB");
					Individual output = indexPort.get("portR1");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelBR2" : {
					// get input/output ports
					Individual input = indexPort.get("portB");
					Individual output = indexPort.get("portR2");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelBR3" : {
					// get input/output ports
					Individual input = indexPort.get("portB");
					Individual output = indexPort.get("portR3");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelL1B" : {
					// get input/output ports
					Individual input = indexPort.get("portL1");
					Individual output = indexPort.get("portB");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelL1F" : {
					// get input/output ports
					Individual input = indexPort.get("portL1");
					Individual output = indexPort.get("portF");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelL1L1" : {
					// get input/output ports
					Individual port = indexPort.get("portL1");
					// set input/output ports
					c.addProperty(ip, port);
					c.addProperty(op, port);
				}
				break;
				
				case "channelL1L2" : {
					// get input/output ports
					Individual input = indexPort.get("portL1");
					Individual output = indexPort.get("portL2");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelL1L3" : {
					// get input/output ports
					Individual input = indexPort.get("portL1");
					Individual output = indexPort.get("portL3");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelL1R1" : {
					// get input/output ports
					Individual input = indexPort.get("portL1");
					Individual output = indexPort.get("portR1");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelL1R2" : {
					// get input/output ports
					Individual input = indexPort.get("portL1");
					Individual output = indexPort.get("portR2");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelL1R3" : {
					// get input/output ports
					Individual input = indexPort.get("portL1");
					Individual output = indexPort.get("portR3");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelL2B" : {
					// get input/output ports
					Individual input = indexPort.get("portL2");
					Individual output = indexPort.get("portB");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelL2F" : {
					// get input/output ports
					Individual input = indexPort.get("portL2");
					Individual output = indexPort.get("portF");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelL2L1" : {
					// get input/output ports
					Individual input = indexPort.get("portL2");
					Individual output = indexPort.get("portL1");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelL2L2" : {
					// get input/output ports
					Individual port = indexPort.get("portL2");
					// set input/output ports
					c.addProperty(ip, port);
					c.addProperty(op, port);
				}
				break;
				
				case "channelL2L3" : {
					// get input/output ports
					Individual input = indexPort.get("portL2");
					Individual output = indexPort.get("portL3");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelL2R1" : {
					// get input/output ports
					Individual input = indexPort.get("portL2");
					Individual output = indexPort.get("portR1");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelL2R2" : {
					// get input/output ports
					Individual input = indexPort.get("portL2");
					Individual output = indexPort.get("portR2");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelL2R3" : {
					// get input/output ports
					Individual input = indexPort.get("portL2");
					Individual output = indexPort.get("portR3");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelL3B" : {
					// get input/output ports
					Individual input = indexPort.get("portL3");
					Individual output = indexPort.get("portB");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelL3F" : {
					// get input/output ports
					Individual input = indexPort.get("portL3");
					Individual output = indexPort.get("portF");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelL3L1" : {
					// get input/output ports
					Individual input = indexPort.get("portL3");
					Individual output = indexPort.get("portL1");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelL3L2" : {
					// get input/output ports
					Individual input = indexPort.get("portL3");
					Individual output = indexPort.get("portL2");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelL3L3" : {
					// get input/output ports
					Individual port = indexPort.get("portL3");
					// set input/output ports
					c.addProperty(ip, port);
					c.addProperty(op, port);
				}
				break; 
				
				case "channelL3R1" : {
					// get input/output ports
					Individual input = indexPort.get("portL3");
					Individual output = indexPort.get("portR1");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelL3R2" : {
					// get input/output ports
					Individual input = indexPort.get("portL3");
					Individual output = indexPort.get("portR2");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelL3R3" : {
					// get input/output ports
					Individual input = indexPort.get("portL3");
					Individual output = indexPort.get("portR3");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelR1B" : {
					// get input/output ports
					Individual input = indexPort.get("portR1");
					Individual output = indexPort.get("portB");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelR1F" : {
					// get input/output ports
					Individual input = indexPort.get("portR1");
					Individual output = indexPort.get("portF");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelR1L1" : {
					// get input/output ports
					Individual input = indexPort.get("portR1");
					Individual output = indexPort.get("portL1");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelR1L2" : {
					// get input/output ports
					Individual input = indexPort.get("portR1");
					Individual output = indexPort.get("portL2");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelR1L3" : {
					// get input/output ports
					Individual input = indexPort.get("portR1");
					Individual output = indexPort.get("portL3");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelR1R1" : {
					// get input/output ports
					Individual port = indexPort.get("portR1");
					// set input/output ports
					c.addProperty(ip, port);
					c.addProperty(op, port);
				}
				break;
				
				case "channelR1R2" : {
					// get input/output ports
					Individual input = indexPort.get("portR1");
					Individual output = indexPort.get("portR2");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelR1R3" : {
					// get input/output ports
					Individual input = indexPort.get("portR1");
					Individual output = indexPort.get("portR3");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelR2B" : {
					// get input/output ports
					Individual input = indexPort.get("portR2");
					Individual output = indexPort.get("portB");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelR2F" : {
					// get input/output ports
					Individual input = indexPort.get("portR2");
					Individual output = indexPort.get("portF");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelR2L1" : {
					// get input/output ports
					Individual input = indexPort.get("portR2");
					Individual output = indexPort.get("portL1");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelR2L2" : {
					// get input/output ports
					Individual input = indexPort.get("portR2");
					Individual output = indexPort.get("portL2");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelR2L3" : {
					// get input/output ports
					Individual input = indexPort.get("portR2");
					Individual output = indexPort.get("portL3");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelR2R1" : {
					// get input/output ports
					Individual input = indexPort.get("portR2");
					Individual output = indexPort.get("portR1");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelR2R2" : {
					// get input/output ports
					Individual port = indexPort.get("portR2");
					// set input/output ports
					c.addProperty(ip, port);
					c.addProperty(op, port);
				}
				break;
				
				case "channelR2R3" : {
					// get input/output ports
					Individual input = indexPort.get("portR2");
					Individual output = indexPort.get("portR3");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelR3B" : {
					// get input/output ports
					Individual input = indexPort.get("portR3");
					Individual output = indexPort.get("portB");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelR3F" : {
					// get input/output ports
					Individual input = indexPort.get("portR3");
					Individual output = indexPort.get("portF");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break; 
				
				case "channelR3L1" : {
					// get input/output ports
					Individual input = indexPort.get("portR3");
					Individual output = indexPort.get("portL1");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelR3L2" : {
					// get input/output ports
					Individual input = indexPort.get("portR3");
					Individual output = indexPort.get("portL2");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break; 
				
				case "channelR3L3" : {
					// get input/output ports
					Individual input = indexPort.get("portR3");
					Individual output = indexPort.get("portL3");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelR3R1" : {
					// get input/output ports
					Individual input = indexPort.get("portR3");
					Individual output = indexPort.get("portR1");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelR3R2" : {
					// get input/output ports
					Individual input = indexPort.get("portR3");
					Individual output = indexPort.get("portR2");
					// set input/output ports
					c.addProperty(ip, input);
					c.addProperty(op, output);
				}
				break;
				
				case "channelR3R3" : {
					// get input/output ports
					Individual port = indexPort.get("portR3");
					// set input/output ports
					c.addProperty(ip, port);
					c.addProperty(op, port);
				}
				break;
			}
		}
	}
}
