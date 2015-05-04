package it.istc.pst.gecko.ontology.jena.tutorial;

import java.util.Iterator;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;

public class KbclOntoManager {
	
	private static final String KBCL_ONTO_URL = "http://pst.istc.cnr.it/kbcl/ontology";
	private static final String KBCL_NS = KBCL_ONTO_URL + "#";

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// create the model
		OntModel base = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
		// create the reasoner
		OntModel reasoner = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_MICRO_RULE_INF, base);
		
		// define classes into the base model
		OntClass func = base.createClass(KBCL_NS + "Function");
		OntClass action = base.createClass(KBCL_NS + "Action");
		OntClass test = base.createClass(KBCL_NS + "Test");
		OntClass channel = base.createClass(KBCL_NS + "Channel");
		
		// create taxonomy
		func.addSubClass(action);
		func.addSubClass(test);
		action.addSubClass(channel);
		// set disjoint
		test.setDisjointWith(action);
		action.setDisjointWith(test);
		
		// create Port class
		OntClass port = base.createClass(KBCL_NS + "Port");
		
		// create property
		ObjectProperty hasInput = base.createObjectProperty(KBCL_NS + "hasInput");
		hasInput.addDomain(channel);
		hasInput.addRange(port);
		ObjectProperty hasOutput = base.createObjectProperty(KBCL_NS + "hasOutput");
		hasOutput.addDomain(channel);
		hasOutput.addRange(port);
		
		// create individuals
		Individual pf = port.createIndividual(KBCL_NS + "portf");
		Individual pb = port.createIndividual(KBCL_NS + "portb");
		
		// individual of Function class
		Individual cfb = func.createIndividual(KBCL_NS + "channelfb");
		// set property
		cfb.addProperty(hasInput, pf);
		cfb.addProperty(hasOutput, pb);

		Iterator<Resource> it = cfb.listRDFTypes(false);
		while (it.hasNext()) {
			System.out.println(it.next().getURI());
		}
		
		System.out.println("--------------------------------------------------");
		
		// check inference
		it = reasoner.getIndividual(cfb.getURI()).listRDFTypes(false);
		while (it.hasNext()) {
			System.out.println(cfb.getURI() + " is inferred to be in class " + it.next().getURI());
		}

	}
}
