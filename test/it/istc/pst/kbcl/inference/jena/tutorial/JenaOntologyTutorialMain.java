package it.istc.pst.kbcl.inference.jena.tutorial;

import java.util.Iterator;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class JenaOntologyTutorialMain 
{
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// create the base model
		final String ONTOLOGY_URL = "http://www.eswc2006.org/technologies/ontology";
		final String NS = ONTOLOGY_URL + "#";
		OntModel base = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		// we declare that the ONTOLOGY is replicated locally on disk
		base.getDocumentManager().addAltEntry(
				ONTOLOGY_URL, 
				"file:jenaOntoTutorial.rdf");
		
		// import ONTOLOGY into the base model
		base.read(ONTOLOGY_URL, "RDF/XML");
		
		// create the reasoning model using the base
		OntModel inf = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_MICRO_RULE_INF, base);
		
		
		// create a dummy paper for this example
		OntClass paper = base.getOntClass(NS + "Paper");
		Individual p1 = base.createIndividual(NS + "paper1", paper);
		
		// list the asserted types
		Iterator<Resource> i = p1.listRDFTypes(false);	// shows direct and not-direct relationships 
		while (i.hasNext()) {
			System.out.println(p1.getURI() + " is asserted in class " + i.next());
		}
		
		System.out.println("--------------------------------------------------");
		
		// list the inferred types
		p1 = inf.getIndividual(NS + "paper1");
		i = p1.listRDFTypes(false);						// shows direct and not-direct relationships
		while (i.hasNext()) {
			System.out.println(p1.getURI() + " is inferred to be in class " + i.next());
		}
		
	}
	
}
