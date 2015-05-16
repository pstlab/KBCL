package it.istc.pst.kbcl.mapping.kb.rdf;

import it.istc.pst.kbcl.mapping.kb.rdf.exception.RDFPropertyNotFoundException;
import it.istc.pst.kbcl.mapping.kb.rdf.exception.RDFResourceNotFoundException;

import java.util.Iterator;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;

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
	private static final String NS_URL = "http://pst.istc.cnr.it/kbcl/ps/mapping";
	protected static final String NS = NS_URL + "#";

	private static final String DATASET = "kbcl/mapping.rdf";
	protected OntModel model;
	
	private String label;
	private static RDFDatasetManager INSTANCE = null;
	
	/**
	 * 
	 */
	private RDFDatasetManager() {
		// set label
		this.label = this.getClass().getSimpleName();
		// create a default model
		this.model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
		this.model.getDocumentManager().addAltEntry(NS_URL, "file:" + DATASET);
		this.model.read(NS_URL, "RDF/XML");
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
		Resource s = this.model.getResource(NS + subjectName);
		if (s == null) {
			throw new RDFResourceNotFoundException("[" + this.label + "]: RDF resource " + subjectName + " not found");
		}
		// get property
		Property p = this.model.getProperty(NS + propertyName);
		if (p == null) {
			throw new RDFPropertyNotFoundException("[" + this.label + "]: RDF property " + propertyName + " not found");
		}
		// get object
		Resource o = this.model.getResource(NS + objectName);
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
		Resource type = this.model.getResource(NS + typeName);
		if (type == null) {
			throw new RDFResourceNotFoundException("[" + this.label + "]: RDF resource " + typeName + " not found");
		}
		
		// insert resource
		Resource res = this.model.createResource(NS + resName, type);
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
		Resource res = this.model.getResource(NS + name);
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

	/**
	 * 
	 * @return
	 */
	public static RDFDatasetManager getSingletonInstance() {
		if (INSTANCE == null) {
			INSTANCE = new RDFDatasetManager();
		}
		return INSTANCE;
	}
}
