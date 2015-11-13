package it.istc.pst.kbcl.inference.kb.owl;

import it.istc.pst.kbcl.inference.kb.owl.exception.OWLClassNotFoundException;
import it.istc.pst.kbcl.inference.kb.owl.exception.OWLIndividualNotFoundException;
import it.istc.pst.kbcl.inference.kb.owl.exception.OWLPropertyNotFoundException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

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
public class OWLDatasetManager_v1 
{
	public static final String PROPERTY_LABEL_HAS_CHANNEL = "hasChannel";
	public static final String PROPERTY_LABEL_HAS_NEIGHBOR = "hasNeighbor";
	public static final String PROPERTY_LABEL_HAS_PORT = "hasPort";
	public static final String PROPERTY_LABEL_HAS_INPUT_PORT = "hasInput";
	public static final String PROPERTY_LABEL_HAS_OUTPUT_PORT = "hasOutput";
	public static final String PROPERTY_LABEL_CONNECT = "connect";
	public static final String PROPERTY_LABEL_HAS_ELEMENT = "hasElement";
	public static final String PROPERTY_LABEL_HAS_CROSS_TRANSFER = "hasCrossTransfer";
	public static final String PROPERTY_LABEL_HAS_CONVEYOR = "hasConveyor";
	public static final String PROPERTY_LABEL_ENABLE = "enable";
	
	// module agent ID and type
	public static final String CONSTANT_AGENT_TYPE = "Agent";
	
	// module ports
//	public static final String CONSTANT_ELEMENT_TYPE = "Element";
	public static final String CONSTANT_ELEMENT_TYPE = "Component";
	public static final String CONSTANT_PORT_TYPE = "Port";
	
	// module conveyors
//	public static final String CONSTANT_CONVEYOR_TYPE = "ConveyorEngine";
	public static final String CONSTANT_CONVEYOR_TYPE = "Conveyor";
	
	// module cross transfers
//	public static final String CONSTANT_CROSS_TRANSFER_TYPE = "CrossTransferEngine";
	public static final String CONSTANT_CROSS_TRANSFER_TYPE = "CrossTransfer";
	
	// possible channels
//	public static final String CONSTANT_FUNCTIONALITY_TYPE = "Functionality";
	public static final String CONSTANT_FUNCTIONALITY_TYPE = "Function";
	public static final String CONSTANT_CHANNEL_TYPE = "Channel";
	
	// possible neighbors
//	public static final String CONSTANT_NEIGHBOR_TYPE = "Neighbor";
	
	private OntModel model;
	private InfModel infModel;
	private String label;
	
	private long time;
	private long totalTime;
	private long maxTime;
	
	// TBox
	private String TBOX_URL;
	private String TBOX_NS;
	// ABox
	private String ABOX_URL;
	private String ABOX_NS;
	
	private static OWLDatasetManager_v1 INSTANCE = null;
	
	/**
	 * 
	 * @param model
	 * @param rulesPath
	 * @param aboxPath
	 * @param aboxURL
	 * @param tboxPath
	 * @param tboxURL
	 */
	private OWLDatasetManager_v1(OntModelSpec model, String rulesPath, String aboxPath, String aboxURL, String tboxPath, String tboxURL) {
		// get thread CPU time in nanoseconds
		ThreadMXBean bean = ManagementFactory.getThreadMXBean();
		// get start time
		long start = bean.getCurrentThreadUserTime();
		
		// set label
		this.label = this.getClass().getSimpleName();
		// create the model
		this.model = ModelFactory.createOntologyModel(model);
		
		// get TBOX
		this.TBOX_URL = tboxURL;
		this.TBOX_NS = this.TBOX_URL + "#";
		// load TBOX
		this.model.getDocumentManager().addAltEntry(TBOX_URL, "file:" + tboxPath);
		this.model.read(TBOX_URL, "RDF/XML");
		
		// get ABOX
		this.ABOX_URL = aboxURL;
		this.ABOX_NS = this.ABOX_URL + "#";
		// load ABOX
		this.model.getDocumentManager().addAltEntry(this.ABOX_URL, "file:" + aboxPath);
		this.model.read(this.ABOX_URL, "RDF/XML");
		
		// set rule reasoner configuration
		Resource config = this.model.createResource();
		// get reasoner type from configuration file
		config.addProperty(ReasonerVocabulary.PROPruleMode, "forwardRETE");
		config.addProperty(ReasonerVocabulary.PROPruleSet, rulesPath);
		// create generic rule reasoner
		Reasoner reasoner = GenericRuleReasonerFactory.theInstance().create(config);
		
		// initialize inference model
		this.infModel = ModelFactory.createInfModel(reasoner, this.model);
		// set inference time
		this.time = bean.getCurrentThreadUserTime() - start;
		this.maxTime = this.time;
		this.totalTime = this.time;
	}

	/**
	 * 
	 */
	private OWLDatasetManager_v1() 
	{
		try 
		{
			// get thread CPU time in nanoseconds
			ThreadMXBean bean = ManagementFactory.getThreadMXBean();
			// get start time
			long start = bean.getCurrentThreadUserTime();
			
			// get property file
			Properties prop = new Properties();
			prop.load(new FileInputStream(new File("etc/reasoner.properties")));
			
			// set label
			this.label = this.getClass().getSimpleName();
			// create the model
			this.model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RDFS_INF);
			
			// get TBOX
			String tboxPath = prop.getProperty("tbox");
			this.TBOX_URL = prop.getProperty("tbox_url");
			this.TBOX_NS = this.TBOX_URL + "#";
			// load TBOX
			this.model.getDocumentManager().addAltEntry(TBOX_URL, "file:" + tboxPath);
			this.model.read(TBOX_URL, "RDF/XML");
			
			// get ABOX
			String aboxPath = prop.getProperty("abox");
			this.ABOX_URL = prop.getProperty("abox_url");
			this.ABOX_NS = this.ABOX_URL + "#";
			// load ABOX
			this.model.getDocumentManager().addAltEntry(this.ABOX_URL, "file:" + aboxPath);
			this.model.read(this.ABOX_URL, "RDF/XML");
			
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
			this.time = bean.getCurrentThreadUserTime() - start;
			this.maxTime = this.time;
			this.totalTime = this.time;
		}
		catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	/**
	 * 
	 */
	public static OWLDatasetManager_v1 getSingletonInstance() {
		if (INSTANCE == null) {
			INSTANCE = new OWLDatasetManager_v1();
		}
		return INSTANCE;
	}

	/**
	 * 
	 * @param model
	 * @param rulesPath
	 * @param aboxPath
	 * @param aboxURL
	 * @param tboxPath
	 * @param tboxURL
	 * @return
	 */
	public static OWLDatasetManager_v1 getSingletonInstance(OntModelSpec model, String rulesPath, String aboxPath, String aboxURL, String tboxPath, String tboxURL) {
		if (INSTANCE == null) {
			INSTANCE = new OWLDatasetManager_v1(model, rulesPath, aboxPath, aboxURL, tboxPath, tboxURL);
		}
		return INSTANCE;
	}
	
	/**
	 * 
	 * @return
	 */
	public long getTotalInferenceTime() {
		// convert from nanoseconds to milliseconds
		return TimeUnit.NANOSECONDS.toMillis(this.totalTime);
	}

	/**
	 * 
	 * @return
	 */
	public long getLastInferenceTime() {
		return TimeUnit.NANOSECONDS.toMillis(this.time);
	}
	
	/**
	 * 
	 * @return
	 */
	public long getMaxInferenceTime() {
		return TimeUnit.NANOSECONDS.toMillis(this.maxTime);
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
		OntClass ic = this.model.getOntClass(TBOX_NS + className);
		// check if class exists
		if (ic == null) {
			throw new OWLClassNotFoundException("[" + this.label + "]: No class found \"" + TBOX_NS + className + "\"");
		}
		
		// create individual
		Individual i = ic.createIndividual(this.ABOX_NS + id);
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
		// get thread CPU time in nanoseconds
		ThreadMXBean bean = ManagementFactory.getThreadMXBean();
		// start time
		long start = bean.getCurrentThreadUserTime();
		
		// get class
		OntClass c = this.model.getOntClass(TBOX_NS + className);
		// update inference time
		this.time = bean.getCurrentThreadUserTime() - start;
		
		this.totalTime += this.time;
		// set max time
		this.maxTime = Math.max(this.maxTime, this.time);
		if (c == null) {
			throw new OWLClassNotFoundException("[" + this.label + "]: Class not found \"" + TBOX_NS + className + "\"");
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
		// get thread CPU time in nanoseconds
		ThreadMXBean bean = ManagementFactory.getThreadMXBean();
		// get start time
		long start = bean.getCurrentThreadUserTime(); 
				
		// get subject individual
		Individual subject = this.model.getIndividual(this.ABOX_NS + subjectName);
		if (subject == null) {
			// exception
			throw new OWLIndividualNotFoundException("[" + this.label + "]: Individual not found \"" + TBOX_NS + subjectName + "\"");
		}
	
		// get property
		Property p = this.model.getOntProperty(TBOX_NS + propertyName);
		if (p == null) {
			// exception
			throw new OWLPropertyNotFoundException("[" + this.label + "]: Property not found \"" + TBOX_NS + propertyName + "\"");
		}
		
		// list statements
		Iterator<Statement> it = this.infModel.listStatements(subject, p, (RDFNode) null);
		// update inference time
		this.time = bean.getCurrentThreadUserTime() - start;
		this.totalTime += this.time;
		this.maxTime = Math.max(this.maxTime, this.time);
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
		// get thread CPU time in nanoseconds
		ThreadMXBean bean = ManagementFactory.getThreadMXBean();
		long start = bean.getCurrentThreadUserTime(); 
		// get class
		OntClass c = this.model.getOntClass(TBOX_NS + className);
		if (c == null) {
			throw new OWLClassNotFoundException("[" + this.label + "]: Class not found \"" + TBOX_NS + className + "\"");
		}
		
		// get individuals
		Iterator<? extends OntResource> it = c.listInstances(false);
		// update inference time
		this.time = bean.getCurrentThreadUserTime() - start;
		this.totalTime += this.time;
		this.maxTime = Math.max(this.maxTime, this.time);
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
		// get subject individual
		Individual subject = this.model.getIndividual(this.ABOX_NS + subjectName);
		if (subject == null) {
			throw new OWLIndividualNotFoundException("[" + this.label +"]: No Individual found \"" + TBOX_NS + subjectName + "\"");
		}
		
		// get object individual
		Individual object = this.model.getIndividual(this.ABOX_NS + objectName);
		if (object == null) {
			throw new OWLIndividualNotFoundException("[" + this.label +"]: No Individual found \"" + TBOX_NS + objectName + "\"");
		}
		
		// get property
		Property p = this.model.getProperty(this.TBOX_NS + propertyName);
		if (p == null) {
			throw new OWLPropertyNotFoundException("[" + this.label + "]: No property found \"" + TBOX_NS + propertyName + "\"");
		}
		
		// assert property directly into the inference model
		subject.addProperty(p, object);
		// get thread CPU time in nanoseconds
		ThreadMXBean bean = ManagementFactory.getThreadMXBean();		
		// start time
		long start = bean.getCurrentThreadUserTime(); 
		// update inference model
		this.infModel.rebind();
		// update inference time
		this.time = bean.getCurrentThreadUserTime() - start;
		this.totalTime += this.time;
		this.maxTime = Math.max(this.maxTime, this.time);
	}
	
	/**
	 * 
	 * @param propertyLabel
	 * @param label
	 * @return
	 */
	public List<String> removeStatementWithTarget(String propertyLabel, String objectLabel) 
			throws OWLIndividualNotFoundException, OWLPropertyNotFoundException
	{
		// get object individual
		Individual object = this.model.getIndividual(this.ABOX_NS + objectLabel);
		if (object == null) {
			throw new OWLIndividualNotFoundException("[" + this.label + "]: No individual found \"" + this.ABOX_NS + objectLabel + "\"");
		}
		
		// get property
		Property p = this.model.getProperty(this.TBOX_NS + propertyLabel);
		if (p == null) {
			throw new OWLPropertyNotFoundException("[" + this.label + "]: No property found \"" + this.TBOX_NS + propertyLabel + "\"");
		}

		// get affected subject list
		List<String> subjects = new ArrayList<>();
		// found subject
		Iterator<Resource> it = this.model.listResourcesWithProperty(p, object);
		while (it.hasNext()) {
			// get subject
			Individual subject = it.next().as(Individual.class);
			subject.removeProperty(p, object);
			subjects.add(subject.getLocalName());
			System.out.println("Statement  \"" + subject.getLocalName() + " " + propertyLabel + " " +objectLabel + "\" successfully removed");
		}
		
		// get thread CPU time in nanoseconds
		ThreadMXBean bean = ManagementFactory.getThreadMXBean();
		// start time
		long start = bean.getCurrentThreadUserTime();
		// update inference model
		this.infModel.rebind();
		// update inference time
		this.time = bean.getCurrentThreadUserTime() - start;
		this.totalTime += this.time;
		this.maxTime = Math.max(this.maxTime, this.time);
		
		// get list
		return subjects;
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
		Individual subject = this.model.getIndividual(this.ABOX_NS + subjectName);
		if (subject == null) {
			throw new OWLIndividualNotFoundException("[" + this.label +"]: No Individual found \"" + TBOX_NS + subjectName + "\"");
		}
		
		// get object individual
		Individual object = this.model.getIndividual(this.ABOX_NS + objectName);
		if (object == null) {
			throw new OWLIndividualNotFoundException("[" + this.label +"]: No Individual found \"" + TBOX_NS + objectName + "\"");
		}
		
		// get property
		Property p = this.model.getProperty(TBOX_NS + propertyName);
		if (p == null) {
			throw new OWLPropertyNotFoundException("[" + this.label + "]: No property found \"" + TBOX_NS + propertyName + "\"");
		}
		
		// remove property
		if (subject.hasProperty(p, object)) {
			subject.removeProperty(p, object);
			
			// get thread CPU time in nanoseconds
			ThreadMXBean bean = ManagementFactory.getThreadMXBean();
			// start time
			long start = bean.getCurrentThreadUserTime();
			
			// update inference model
			this.infModel.rebind();
			// update inference time
			this.time = bean.getCurrentThreadUserTime() - start;
			this.totalTime += this.time;
			this.maxTime = Math.max(this.maxTime, this.time);
		}
		else {
			// no property related 
			throw new OWLPropertyNotFoundException("[" + this.label + "]: Statement \"" +  subjectName + " " + propertyName + " " + objectName + "\" not found");
		}
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
	
}
