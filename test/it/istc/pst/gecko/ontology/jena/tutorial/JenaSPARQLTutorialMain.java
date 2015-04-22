package it.istc.pst.gecko.ontology.jena.tutorial;

import java.io.InputStream;
import java.util.Iterator;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class JenaSPARQLTutorialMain 
{
	private static final String NS_RDFS = "http://www.w3.org/2000/01/rdf-schema#";
	private static final String NS_GECKO = "http://pst.istc.cnr.it/gecko/test/ontology#";
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// create a model using reasoner
		Model model = ModelFactory.createDefaultModel();
		
		// use the file manager to find the input file
		String inputFileName = "gecko/sparql/kb.xml";
		InputStream in = FileManager.get().open(inputFileName);
		if (in == null) {
			throw new IllegalArgumentException("File: " + inputFileName + " not found!");
		}
		
		// read RDF graph
		model.read(in, "RDF/XML");
		// write model to standard output
//		model.write(System.out, "Turtle");
		
		// create a query
		String queryString = "SELECT ?id ?func "
				+ "WHERE { "
				+ "?f <" + NS_RDFS + "subClassOf>* <" + NS_GECKO +"action> . "
						+ "?func a ?f . ?func <" + NS_GECKO + "ID> ?id ."
								+ "}";
		
		Query query = QueryFactory.create(queryString);
		
		try (QueryExecution exec = QueryExecutionFactory.create(query, model)) {
			// execute the SELECT query
			Iterator<QuerySolution> it = exec.execSelect();
			// iterate over the solutions
			while (it.hasNext()) {
				QuerySolution sol = it.next();
				String res = sol.getResource("?func").getURI();
				String id = sol.getLiteral("?id").getLexicalForm();
				
				System.out.println("- " + id + " " + res);
			}
		}
	}
}
