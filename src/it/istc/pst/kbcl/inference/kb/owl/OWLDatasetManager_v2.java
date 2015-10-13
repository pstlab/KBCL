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
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.ReasonerVocabulary;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class OWLDatasetManager_v2 
{
	private OntModel tbox;
	private Model abox;
	private InfModel inf;
	private String label;
	
	private long time;
	private long totalTime;
	private long maxTime;
	
	// ABox
	private String ABOX_URL;
	private String ABOX_NS;
	
	private static OWLDatasetManager_v2 INSTANCE = null;
	
	/**
	 * 
	 */
	private OWLDatasetManager_v2() 
	{
		try 
		{
			// get thread CPU time in nanoseconds
			ThreadMXBean bean = ManagementFactory.getThreadMXBean();
			// get start time
			long start = bean.getCurrentThreadUserTime();
			
			// get property file
			Properties prop = new Properties();
			prop.load(new FileInputStream(new File("etc/reasoner_v2.properties")));
			
			
			// get schema path
			String tboxPath = prop.getProperty(OWLDatasetCfg.PROPERTY_TBOX_PATH.getValue());
			// create the model schema (TBox)
			this.tbox = ModelFactory.createOntologyModel();
			// read the schema from a file
			this.tbox.getDocumentManager().addAltEntry(KBCLVocabulary_v2.ONTOLOGY_URL.getValue(), "file:" + tboxPath);
			this.tbox.read(KBCLVocabulary_v2.ONTOLOGY_URL.getValue(), "RDF/XML");
			
			// create reasoner
			Reasoner reasoner = ReasonerRegistry.getOWLMicroReasoner();
			reasoner.setParameter(ReasonerVocabulary.PROPruleSet, prop.getProperty(OWLDatasetCfg.PROPERTY_RULES_PATH.getValue()));
			// specialize the reasoner by setting the schema
			reasoner = reasoner.bindSchema(this.tbox);
			
			// create data model (ABox)
			this.ABOX_URL = prop.getProperty(OWLDatasetCfg.PROPERTY_ABOX_URL.getValue());
			this.ABOX_NS = this.ABOX_URL + "#";
			this.abox = FileManager.get().loadModel(this.ABOX_URL, prop.getProperty(OWLDatasetCfg.PROPERTY_ABOX_URL.getValue()), "RDF/XML");
			
			// bind the reasoner on data
			this.inf = ModelFactory.createInfModel(reasoner, this.abox);
			
			// set label
			this.label = this.getClass().getSimpleName();
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
	public static OWLDatasetManager_v2 getSingletonInstance() {
		if (INSTANCE == null) {
			INSTANCE = new OWLDatasetManager_v2();
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
			throws OWLClassNotFoundException {
		// get type
		OntClass type = this.tbox.getOntClass(KBCLVocabulary_v2.ONTOLOGY_URL + "#" + className);
		// check if class exists
		if (type == null) {
			throw new OWLClassNotFoundException("[" + this.label + "]: No class found \"" + KBCLVocabulary_v2.ONTOLOGY_URL + "#" + className + "\"");
		}
		
		// create resource
		Resource res = this.inf.createResource(id, type);
		// get instance
		return new OWLInstance(res.getURI(), res.getLocalName(), new OWLClass(type.getURI(), type.getLocalName()));
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
		OntClass c = this.tbox.getOntClass(KBCLVocabulary_v2.ONTOLOGY_URL + "#" + className);
		// update inference time
		this.time = bean.getCurrentThreadUserTime() - start;
		
		this.totalTime += this.time;
		// set max time
		this.maxTime = Math.max(this.maxTime, this.time);
		if (c == null) {
			throw new OWLClassNotFoundException("[" + this.label + "]: Class not found \"" + KBCLVocabulary_v2.ONTOLOGY_URL + "#" + className + "\"");
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
		Resource subject = this.inf.getResource(this.ABOX_NS + subjectName);
		if (subject == null) {
			// exception
			throw new OWLIndividualNotFoundException("[" + this.label + "]: Individual not found \"" + this.ABOX_NS + subjectName + "\"");
		}
	
		// get property
		Property p = this.inf.getProperty(KBCLVocabulary_v2.ONTOLOGY_URL + "#" + propertyName);
		if (p == null) {
			// exception
			throw new OWLPropertyNotFoundException("[" + this.label + "]: Property not found \"" + KBCLVocabulary_v2.ONTOLOGY_URL + "#" + propertyName + "\"");
		}
		
		// list statements
		Iterator<Statement> it = this.inf.listStatements(subject, p, (RDFNode) null);
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
				Resource n = this.inf.getResource(obj.getURI());
				// get class
				Resource nType = n.getRequiredProperty(RDF.type).getObject().asResource();
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
		OntClass c = this.tbox.getOntClass(KBCLVocabulary_v2.ONTOLOGY_URL + "#" + className);
		if (c == null) {
			throw new OWLClassNotFoundException("[" + this.label + "]: Class not found \"" + KBCLVocabulary_v2.ONTOLOGY_URL + "#" + className + "\"");
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
		if (this.abox != null) {
			this.abox.close();
			this.abox = null;
		}
		if (this.inf != null) {
			this.inf.close();
			this.inf = null;
		}
		if (this.tbox != null) {
			this.tbox.close();
			this.tbox = null;
		}
		INSTANCE = null;
	}
	
}
