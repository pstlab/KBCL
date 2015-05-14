package it.istc.pst.gecko.ontology.kb.owl;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.hp.hpl.jena.reasoner.rulesys.Rule;

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
	
	// inference rules
	protected static final String RULES = ""
			+ "[r1: (?m " + NS + "hasElement ?e), (?e " + NS + "connect ?n) "
				+ "-> (?m " + NS + "hasPort ?e)]\n"
			+ "[r2: (?m " + NS + "hasElement ?e), (?e " + NS + "connect ?n) "
				+ "-> (?m " + NS + "hasNeighbor ?n)]\n"
			+ "[r3: (?f " + NS + "hasOutput ?a), (?f " + NS +"hasInput ?b), (?m " + NS +"hasPort ?a), (?m " + NS + "hasPort ?b) "
				+ "-> (?m " + NS + "hasChannel ?f)]";
	
	protected OntModel model;
	protected InfModel infModel;
	
	private static OWLDatasetManager INSTANCE = null;
	
	/**
	 * 
	 */
	private OWLDatasetManager() {
		// create the model
		this.model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RULE_INF);
		this.model.getDocumentManager().addAltEntry(NS_URL, "file:" + DATASET);
		
		// import ONTOLOGY model
		this.model.read(NS_URL, "RDF/XML");
		
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
