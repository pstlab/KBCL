package it.istc.pst.gecko.ontology.kb.owl;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.hp.hpl.jena.reasoner.rulesys.Rule;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class OWLRuleBasedReasoner 
{
	private InfModel model;
	
	// forward rules
	private static final String RULES = "[r1: (?m " + OWLDatasetManager.NS + "hasElement ?e), (?e " + OWLDatasetManager.NS + "connect ?n) "
			+ "-> "
			+ "(?m " + OWLDatasetManager.NS + "hasPort ?e), (?m " + OWLDatasetManager.NS + "hasNeighbor ?n)]\n"
			+ "[r2: (?f " + OWLDatasetManager.NS + "hasOutput ?a), (?f " + OWLDatasetManager.NS +"hasInput ?b), (?m " + OWLDatasetManager.NS +"hasPort ?a), (?m " + OWLDatasetManager.NS + "hasPort ?b)"
			+ "->"
			+ "(?m " + OWLDatasetManager.NS + "hasChannel ?f)]";
	
	/**
	 * 
	 * @param dataset
	 */
	protected OWLRuleBasedReasoner(OntModel dataset) {
		// create model
		this.model = ModelFactory.createInfModel(new GenericRuleReasoner(Rule.parseRules(RULES)), dataset);
	}
	
	/**
	 * 
	 * @return
	 */
	public InfModel getInferenceModel() {
		return this.model;
	}
}
