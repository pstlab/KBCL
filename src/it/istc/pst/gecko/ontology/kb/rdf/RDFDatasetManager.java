package it.istc.pst.gecko.ontology.kb.rdf;

import java.io.InputStream;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class RDFDatasetManager 
{
	protected static final String NS_RDFS = "http://www.w3.org/2000/01/rdf-schema#";
	protected static final String NS_GECKO = "http://pst.istc.cnr.it/ijcai/gecko/ontology#";
	private static final String DATASET = "ijcai/dataset/full.xml";
	protected Model model;
	
	private static RDFDatasetManager INSTANCE = null;
	
	/**
	 * 
	 */
	private RDFDatasetManager() {
		// create a default model
		this.model = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open(DATASET);
		this.model.read(in, "RDF/XML");
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
