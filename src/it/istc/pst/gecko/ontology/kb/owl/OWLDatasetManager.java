package it.istc.pst.gecko.ontology.kb.owl;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class OWLDatasetManager 
{
	protected static final String NS_URL = "http://pst.istc.cnr.it/ontology/kbcl";
	protected static final String NS = NS_URL + "#";
	protected static final String DATASET = "kbcl/kbcl_v5.owl";
	protected OntModel model;
	
	private static OWLDatasetManager INSTANCE = null;
	
	/**
	 * 
	 */
	private OWLDatasetManager() {
		// create the model
		OntModel base = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
		this.model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RULE_INF, base);
		this.model.getDocumentManager().addAltEntry(NS_URL, "file:" + DATASET);
		
		// import ONTOLOGY into model
		this.model.read(NS_URL, "RDF/XML");
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
	public OWLRuleBasedReasoner getReasoner() {
		return new OWLRuleBasedReasoner(this.model);
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
