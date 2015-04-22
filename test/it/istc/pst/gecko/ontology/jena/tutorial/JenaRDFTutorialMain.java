package it.istc.pst.gecko.ontology.jena.tutorial;

import java.io.InputStream;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.VCARD;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class JenaRDFTutorialMain
{
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) 
	{
		/*
		 * In JENA all state information provided by a collection of RDF triples is contained in a 
		 * data structure called a Model.
		 * 
		 *  The Model denotes an RDF graph, so called because it contains a collection of RDF nodes,
		 *  attached to each other by labeled relations. Each relation goes only in one direction (the 
		 *  model is an instance of a Directed Graph) so the triple:
		 *  
		 *  	example:ijd foaf:name "Ian"
		 *  
		 *  can be read as 'resource example:ijd has property foaf:name with value "Ian"'
		 */
		
		
		String personURI = "http://somewhere/JohnSmith";
		String givenName = "John";
		String familyName = "Smith";
		String fullName = givenName + " " + familyName;
		
		// create an empty model
		Model model = ModelFactory.createDefaultModel();
		
		// create the resource
		Resource res = model.createResource(personURI);
		
		// add the property 
		res.addProperty(VCARD.FN, fullName);
		// create a "compound" property a using blank node in cascading style
		res.addProperty(VCARD.N, 
				model.createResource()	// blank node
					.addProperty(VCARD.Given, givenName)
					.addProperty(VCARD.Family, familyName));
		
		// iterate on the statements of the model (the data graph)
		StmtIterator it = model.listStatements();
		while (it.hasNext()) {
			// get next statement
			Statement st = it.next();
			// get subject
			Resource subject = st.getSubject();
			// get property
			Property predicate = st.getPredicate();
			// get object
			RDFNode object = st.getObject();

			// print subject
			System.out.print(subject);
			System.out.print(" " + predicate + " ");
			// check object type
			if (object.isResource()) {
				// the object is a resource
				System.out.println(object);
			}
			else {
				// the object is a literal
				System.out.println("\"" + object + "\"");
			}
			System.out.println(".");
		}
		
		// now write to the console in RDF/XML document format
		System.out.println("\n\nThe model in RDF/XML document format\n");
		model.write(System.out);
		
		// write model in the N-TRIPLES document format
		System.out.println("\n\nThe model in N-TRIPLES document format\n");
		model.write(System.out, "N-TRIPLES");
		
		// write model in the Turtle document format
		System.out.println("\n\nThe model in Turtle document format\n");
		model.write(System.out, "Turtle");
		
		System.out.println("\n\n\nRead a data graph stored in RDF/XML format and write to the output\n");
		// now read a data graph stored in RDF/XML formatted document
		model = ModelFactory.createDefaultModel();
		// use the file manager to find the input file
		String inputFileName = "jenaTutorial.xml";
		InputStream in = FileManager.get().open(inputFileName);
		if (in == null) {
			throw new IllegalArgumentException("File: " + inputFileName + " not found!");
		}
		
		// read the RDF/XML file
		model.read(in, null);
		
		// write model to standard output
		model.write(System.out, "RDF/XML-ABBREV");
	}
}
