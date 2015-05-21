package it.istc.pst.kbcl.mapping.kb.rdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import it.istc.pst.kbcl.mapping.kb.rdf.exception.RDFPropertyNotFoundException;
import it.istc.pst.kbcl.mapping.kb.rdf.exception.RDFResourceNotFoundException;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class RDFDatasetManager 
{
	protected static final String CONSTANT_RDF_AGENT_TYPE_NAME = "Agent";
	protected static final String CONSTANT_RDF_PROPERTY_LABEL_HAS_FUNCTIONALITY = "hasFunctionality";
	protected static final String CONSTANT_RDF_PROPERTY_LABEL_HAS_COMPONENT = "hasComponent";
	protected static final String CONSTANT_RDF_PROPERTY_LABEL_HAS_NEIGHBOR = "hasNeighbor";
	
	protected static final String NS_RDFS = "http://www.w3.org/2000/01/rdf-schema#";
	
	private final String DATASET;
	private final String MAPPING_URL;
	protected final String NS;
	
	protected OntModel model;
	
	private String label;

	private static RDFDatasetManager INSTANCE = null;
	public static RDFDatasetManager getSingletonInstance() {
		if (INSTANCE == null) {
			INSTANCE = new RDFDatasetManager();
		}
		return INSTANCE;
	}
	
	/**
	 * 
	 */
	private RDFDatasetManager() 
	{
		try 
		{
			Properties prop = new Properties();
			prop.load(new FileInputStream(new File("etc/psModuleCfg.properties")));
			// get knowledge base path
			this.DATASET = prop.getProperty("mapping_kb");
			this.MAPPING_URL = prop.getProperty("mapping_url");
			this.NS = this.MAPPING_URL + "#";
		
			// set label
			this.label = this.getClass().getSimpleName();
			// create a default model
			this.model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
			this.model.getDocumentManager().addAltEntry(this.MAPPING_URL, "file:" + DATASET);
			this.model.read(this.MAPPING_URL, "RDF/XML");
		}
		catch (IOException ex) {
			throw new RuntimeException(ex.getLocalizedMessage());
		}
	}
	
	/**
	 * 
	 * @param subjectName
	 * @param propertyName
	 * @param objectName
	 * @throws RDFResourceNotFoundException
	 * @throws RDFPropertyNotFoundException
	 */
	public void assertStatement(String subjectName, String propertyName, String objectName) 
			throws RDFResourceNotFoundException, RDFPropertyNotFoundException
	{
		// get resource
		Resource s = this.model.getResource(this.NS + subjectName);
		if (s == null) {
			throw new RDFResourceNotFoundException("[" + this.label + "]: RDF resource " + subjectName + " not found");
		}
		// get property
		Property p = this.model.getProperty(this.NS + propertyName);
		if (p == null) {
			throw new RDFPropertyNotFoundException("[" + this.label + "]: RDF property " + propertyName + " not found");
		}
		// get object
		Resource o = this.model.getResource(this.NS + objectName);
		if (o == null) {
			throw new RDFResourceNotFoundException("[" + this.label + "]: RDF resource " + objectName + " not found");
		}
		
		// assert triple
		this.model.add(s, p, o);
	}
	
	/**
	 * 
	 * @param resName
	 * @param typeName
	 * @throws RDFResourceNotFoundException
	 */
	public Resource createResource(String resName, String typeName) 
			throws RDFResourceNotFoundException
	{
		// get resource type
		Resource type = this.model.getResource(this.NS + typeName);
		if (type == null) {
			throw new RDFResourceNotFoundException("[" + this.label + "]: RDF resource " + typeName + " not found");
		}
		
		// insert resource
		Resource res = this.model.createResource(this.NS + resName, type);
		return res;
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 * @throws RDFResourceNotFoundException
	 */
	public Resource retrieveResource(String name) 
			throws RDFResourceNotFoundException 
	{
		Resource res = this.model.getResource(this.NS + name);
		if (res == null) {
			throw new RDFResourceNotFoundException("[" + this.label + "]: RDF resource " + name + " not found");
		}
		// get resource
		return res;
	}

	/**
	 * 
	 */
	public void close() {
		if (this.model != null) {
			this.model.close();
			this.model = null;
		}
		INSTANCE = null;
	}
}
