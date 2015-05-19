package it.istc.pst.kbcl.inference.jena.tutorial;

import java.util.Iterator;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.hp.hpl.jena.reasoner.rulesys.Rule;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class KBCLOntologyReasoner {

	private static final String KBCL_ONTO_URL = "http://pst.istc.cnr.it/ontology/kbcl";
	private static final String KBCL_NS = KBCL_ONTO_URL + "#";
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		// create model
		OntModel base = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
		// specify document
		base.getDocumentManager().addAltEntry(KBCL_ONTO_URL, 
				"file:kbcl/kbcl_v4.owl");
		
		// import ONTOLOGY into the base model
		base.read(KBCL_ONTO_URL, "RDF/XML");
		
		// get declared properties
		Individual t1 = base.getIndividual(KBCL_NS + "module_t1");
		System.out.println("- Individual " + t1.getURI());
		Iterator<Resource> it = t1.listRDFTypes(false);
		while (it.hasNext()) {
			System.out.println("---> " + it.next().getURI());
		}
		
		// check declared properties
		StmtIterator stmtIt = t1.listProperties();
		while (stmtIt.hasNext()) {
			Statement stmt = stmtIt.next();
			System.out.println("- Property " + stmt.getPredicate().getURI() + " " + stmt.getObject());
		}
		
		Individual portF = base.getIndividual(KBCL_NS + "port_f");
		// remove individual
		System.out.println("\nDisconnecting element...  " + portF);
		Property p = base.getProperty(KBCL_NS + "connect");
		portF.removeProperty(p, base.getIndividual(KBCL_NS + "neighbor_f"));
		
		// forward rule
		String rules = "[r1: (?m " + KBCL_NS + "hasElement ?e), (?e " + KBCL_NS + "connect ?n) "
				+ "-> "
				+ "(?m " + KBCL_NS + "hasPort ?e), (?m " + KBCL_NS + "hasNeighbor ?n)]\n"
				+ "[r2: (?f " + KBCL_NS + "hasOutput ?a), (?f " + KBCL_NS +"hasInput ?b), (?m " + KBCL_NS +"hasPort ?a), (?m " + KBCL_NS + "hasPort ?b)"
				+ "->"
				+ "(?m " + KBCL_NS + "hasChannel ?f)]";
		
		System.out.println("\n" + rules + "\n");
		
		Reasoner r = new GenericRuleReasoner(Rule.parseRules(rules));
		InfModel inf = ModelFactory.createInfModel(r, base);
		// get every statement related to individual t1
		Iterator<Statement> list = inf.listStatements(t1.asResource(), (Property) null, (RDFNode) null);
		while (list.hasNext()) {
			System.out.println("---> statement: " + list.next());
		}

	}
}
