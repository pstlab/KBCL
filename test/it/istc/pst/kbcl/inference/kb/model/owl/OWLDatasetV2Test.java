package it.istc.pst.kbcl.inference.kb.model.owl;

import java.util.Iterator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.ReasonerVocabulary;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class OWLDatasetV2Test 
{
	private static final String ABOX_URL = "http://pst.istc.cnr.it/kbcl/instance";
	private static final String ABOX_PATH = "dev/onto/v2/abox/simple.rdf";
	private static final String TBOX_URL = "http://pst.istc.cnr.it/kbcl/ontology";
	private static final String TBOX_PATH = "dev/onto/v2/tbox/onto_v2.owl";
	private static final String RULES_PATH = "dev/onto/v2/rules_testing.rules";
	
	private OntModel tbox;
	private Model abox;
	private InfModel inf;
	
	/**
	 * 
	 */
	@Before
	public void init() {
		System.out.println("************************************************************");
		System.out.println("*********** OWL Dataset Inference Rules Test ***************");
		System.out.println("************************************************************");
		
		// create the model of the schema (TBOX)
		this.tbox = ModelFactory.createOntologyModel();
		// read the schema from a file
		this.tbox.getDocumentManager().addAltEntry(TBOX_URL, "file:" + TBOX_PATH);
		this.tbox.read(TBOX_URL, "RDF/XML");
		
		// create reasoner
		Reasoner reasoner = ReasonerRegistry.getOWLMicroReasoner();
		reasoner.setParameter(ReasonerVocabulary.PROPruleSet, RULES_PATH);
		// specialize the reasoner by setting the schema
		reasoner = reasoner.bindSchema(this.tbox);
		
		// create data model (ABOX)
		this.abox = FileManager.get().loadModel(ABOX_PATH, ABOX_URL, "RDF/XML");
		
		// bind the reasoner on data
		this.inf = ModelFactory.createInfModel(reasoner, this.abox);
	}
	
	/**
	 * 
	 */
	@After
	public void release() {
		// close inference models
		if (this.inf != null) {
			this.inf.close();
		}
		if (this.abox != null) {
			this.abox.close();
		}
		if (this.tbox != null) {
			this.tbox.close();
		}
		// release memory
		this.inf = null;
		this.abox = null;
		this.tbox = null;
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	@Test
	public void setupInferenceModel() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- setupInferenceModel()");
		Assert.assertNotNull(this.tbox);
		Assert.assertNotNull(this.abox);
		Assert.assertNotNull(this.inf);
		System.out.println("Ok!");
	}
	
	/**
	 * 
	 */
	@Test
	public void rawDataInferenceTets() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- rawDataInferenceTest()");
		
		// get transportation modules
		System.out.println("Retrieve all transportation modules:");
		Iterator<Statement> it = this.inf.listStatements((Resource) null, RDF.type, this.inf.getResource(TBOX_URL + "#TransportationModule"));
		
		Assert.assertNotNull(it);
		int count = 0;
		while (it.hasNext()) {
			Statement s = it.next();
			Assert.assertNotNull(s);
			count++;
			System.out.println("- " + s.getSubject());
		}
		Assert.assertTrue(count == 1);
		
		// get connection property
		String prop = "connection";
		System.out.println("Statements concerning ObjectProperty \"" + prop + "\"");
		Property p = this.tbox.getOntProperty(TBOX_URL + "#" + prop);
		Assert.assertNotNull(p);
		it = this.inf.listStatements((Resource) null, p, (RDFNode) null);
		while (it.hasNext()) {
			Statement s = it.next();
			Assert.assertNotNull(s);
			System.out.println("- " + s);
		}
	}
	
	/**
	 * 
	 */
	@Test
	public void componentsInferenceRuleTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- componentsInferenceRulesTest()");
		
		// initialize transportation module reference
		Resource tm = null;
		// get transportation modules
		Iterator<Statement> it = this.inf.listStatements((Resource) null, RDF.type, this.inf.getResource(TBOX_URL + "#TransportationModule"));
		Assert.assertNotNull(it);
		int count = 0;
		while (it.hasNext()) {
			Statement s = it.next();
			Assert.assertNotNull(s);
			count++;
			tm = s.getSubject();
			System.out.println("- " + tm);
		}
		Assert.assertTrue(count == 1);
		Assert.assertNotNull(tm);
		
		// get information about components
		System.out.println("Retrieve all statements concerning module's components");
		it = this.inf.listStatements(this.inf.getResource(ABOX_URL + "#" + tm.getLocalName()), 
				this.inf.getProperty(TBOX_URL + "#hasComponent"),
				(RDFNode) null);
		
		Assert.assertNotNull(it);
		while (it.hasNext()) {
			Statement s = it.next();
			Assert.assertNotNull(s);
			Assert.assertEquals(tm.getURI(), s.getSubject().getURI());
			System.out.println("- " + s.getObject());
		}
	}
	
	/**
	 * 
	 */
	@Test
	public void collaboratorsInferenceRuleTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- collaboratorsInferenceRulesTest()");
		
		// initialize transportation module reference
		Resource tm = null;
		// get transportation modules
		Iterator<Statement> it = this.inf.listStatements((Resource) null, 
				RDF.type, 
				this.inf.getResource(TBOX_URL + "#TransportationModule"));
		
		Assert.assertNotNull(it);
		int count = 0;
		while (it.hasNext()) {
			Statement s = it.next();
			Assert.assertNotNull(s);
			count++;
			tm = s.getSubject();
			System.out.println("- " + tm);
		}
		Assert.assertTrue(count == 1);
		Assert.assertNotNull(tm);
		
		// get information about collaborators
		System.out.println("Retrieve all statements concerning module's collaborators:");
		it = this.inf.listStatements(this.inf.getResource(ABOX_URL + "#" + tm.getLocalName()), 
				inf.getProperty(TBOX_URL + "#hasCollaborator"), 
				(RDFNode) null);
		
		Assert.assertNotNull(it);
		while (it.hasNext()) {
			Statement s = it.next();
			Assert.assertNotNull(s);
			Assert.assertEquals(tm.getURI(), s.getSubject().getURI());
			System.out.println("- " + s.getObject());
		}
	}
	
	/**
	 * 
	 */
	@Test
	public void primitiveChannelsInferenceRuleTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- primitiveChannelsInferenceRulesTest()");
		
		System.out.println("Retrieve information concerning primitive channels:");
		Iterator<Statement> it = inf.listStatements((Resource) null, 
				RDF.type, 
				inf.getResource(TBOX_URL + "#Channel"));
		
		Assert.assertNotNull(it);
		while (it.hasNext()) {
			Statement s = it.next();
			Assert.assertNotNull(s);
			System.out.println("- " + s);
			
			// print start location
			Iterator<Statement> sit = inf.listStatements(
					s.getSubject().asResource(), 
					inf.getProperty(TBOX_URL + "#startLocation"), 
					(RDFNode) null);
			
			Assert.assertNotNull(sit);
			System.out.println("- Start Location:");
			while (sit.hasNext()) {
				Statement ss = sit.next();
				Assert.assertNotNull(ss);
				Assert.assertEquals(s.getSubject().getURI(), ss.getSubject().getURI());
				System.out.println("-- " + ss.getObject());
			}
			
			sit = this.inf.listStatements(
					s.getSubject().asResource(), 
					inf.getProperty(TBOX_URL + "#endLocation"),
					(RDFNode) null);
			
			Assert.assertNotNull(sit);
			System.out.println("- End Location:");
			while (sit.hasNext()) {
				Statement ss = sit.next();
				Assert.assertNotNull(ss);
				Assert.assertEquals(s.getSubject().getURI(), ss.getSubject().getURI());
				System.out.println("-- " + ss.getObject());
			}
		}
	}
	
	/**
	 * 
	 */
	@Test
	public void moduleChannelInferenceRuleTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- moduleChannelInferenceRuleTest()");
		
		// initialize transportation module reference
		Resource tm = null;
		// get transportation modules
		Iterator<Statement> it = this.inf.listStatements((Resource) null, 
				RDF.type, 
				this.inf.getResource(TBOX_URL + "#TransportationModule"));
		
		Assert.assertNotNull(it);
		int count = 0;
		while (it.hasNext()) {
			Statement s = it.next();
			Assert.assertNotNull(s);
			count++;
			tm = s.getSubject();
			System.out.println("- " + tm);
		}
		Assert.assertTrue(count == 1);
		Assert.assertNotNull(tm);
		
		// get information about primitive channels
		System.out.println("Retrieve channel functions the module actually can do:");
		it = this.inf.listStatements(this.inf.getResource(ABOX_URL + "#" + tm.getLocalName()), 
				this.inf.getProperty(TBOX_URL + "#canDoChannel"), 
				(RDFNode) null);
		
		Assert.assertNotNull(it);
		while (it.hasNext()) {
			Statement s = it.next();
			Assert.assertNotNull(s);
			Assert.assertEquals(s.getSubject().getURI(), tm.getURI());
			System.out.println("- " + s);
			// print start location
			Iterator<Statement> sit = this.inf.listStatements(
					s.getObject().asResource(), 
					inf.getProperty(TBOX_URL + "#startLocation"), 
					(RDFNode) null);
			
			Assert.assertNotNull(sit);
			System.out.println("- Start Location:");
			while (sit.hasNext()) {
				Statement ss = sit.next();
				Assert.assertNotNull(ss);
				Assert.assertEquals(s.getObject().asResource().getURI(), ss.getSubject().getURI());
				System.out.println("-- " + ss.getObject());
			}
			
			sit = this.inf.listStatements(
					s.getObject().asResource(), 
					this.inf.getProperty(TBOX_URL + "#endLocation"),
					(RDFNode) null);
			
			Assert.assertNotNull(sit);
			System.out.println("- End Location:");
			while (sit.hasNext()) {
				Statement ss = sit.next();
				Assert.assertNotNull(ss);
				Assert.assertEquals(s.getObject().asResource().getURI(), ss.getSubject().getURI());
				System.out.println("-- " + ss.getObject());
			}
		}
	}
}
