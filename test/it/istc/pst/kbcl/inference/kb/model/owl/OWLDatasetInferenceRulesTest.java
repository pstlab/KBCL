package it.istc.pst.kbcl.inference.kb.model.owl;

import it.istc.pst.kbcl.inference.kb.owl.KBCLVocabulary_v2;

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
public class OWLDatasetInferenceRulesTest 
{
	private static final String ABOX_URL = "http://pst.istc.cnr.it/kbcl/instance";
	private static final String ABOX_PATH = "dev/onto/v2/abox/single.rdf";
//	private static final String TBOX_URL = "http://pst.istc.cnr.it/kbcl/ontology";
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
		this.tbox.getDocumentManager().addAltEntry(KBCLVocabulary_v2.ONTOLOGY_URL.getValue(), "file:" + TBOX_PATH);
		this.tbox.read(KBCLVocabulary_v2.ONTOLOGY_URL.getValue(), "RDF/XML");
		
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
		
		try {
			System.gc();
			Thread.sleep(1000);
		}
		catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
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
		Iterator<Statement> it = this.inf.listStatements(
				(Resource) null, 
				RDF.type, 
				this.inf.getResource(KBCLVocabulary_v2.CLASS_TRANSPORTATIO_MODULE_URI.getValue())
		);
		
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
		System.out.println("Statements concerning ObjectProperty \"" + KBCLVocabulary_v2.PROPERTY_CONNECTION_LABEL + "\"");
		Property p = this.tbox.getOntProperty(KBCLVocabulary_v2.PROPERTY_CONNECTION_URI.getValue());
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
		Iterator<Statement> it = this.inf.listStatements(
				(Resource) null, 
				RDF.type, 
				this.inf.getResource(KBCLVocabulary_v2.CLASS_TRANSPORTATIO_MODULE_URI.getValue())
		);
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
		it = this.inf.listStatements(
				tm, 
				this.inf.getProperty(KBCLVocabulary_v2.PROPERTY_HAS_COMPONENT_URI.getValue()),
				(RDFNode) null
		);
		
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
		Iterator<Statement> it = this.inf.listStatements(
				(Resource) null, 
				RDF.type, 
				this.inf.getResource(KBCLVocabulary_v2.CLASS_TRANSPORTATIO_MODULE_URI.getValue())
		);
		
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
		it = this.inf.listStatements(
				this.inf.getResource(ABOX_URL + "#" + tm.getLocalName()), 
				inf.getProperty(KBCLVocabulary_v2.PROPERTY_HAS_COLLABORATOR_URI.getValue()), 
				(RDFNode) null
		);
		
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
				inf.getResource(KBCLVocabulary_v2.CLASS_FUNCTION_CHANNEL_URI.getValue()));
		
		Assert.assertNotNull(it);
		while (it.hasNext()) {
			Statement s = it.next();
			Assert.assertNotNull(s);
			System.out.println("- " + s);
			
			// print start location
			Iterator<Statement> sit = this.inf.listStatements(
					s.getSubject().asResource(), 
					this.inf.getProperty(KBCLVocabulary_v2.PROPERTY_START_LOCATION_URI.getValue()), 
					(RDFNode) null
			);
			
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
					this.inf.getProperty(KBCLVocabulary_v2.PROPERTY_END_LOCATION_URI.getValue()),
					(RDFNode) null
			);
			
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
		Iterator<Statement> it = this.inf.listStatements(
				(Resource) null, 
				RDF.type, 
				this.inf.getResource(KBCLVocabulary_v2.CLASS_TRANSPORTATIO_MODULE_URI.getValue())
		);
		
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
		it = this.inf.listStatements(
				this.inf.getResource(ABOX_URL + "#" + tm.getLocalName()), 
				this.inf.getProperty(KBCLVocabulary_v2.PROPERTY_CAN_DO_CHANNEL_URI.getValue()), 
				(RDFNode) null
		);
		
		Assert.assertNotNull(it);
		while (it.hasNext()) {
			Statement s = it.next();
			Assert.assertNotNull(s);
			Assert.assertEquals(s.getSubject().getURI(), tm.getURI());
			System.out.println("- " + s);
			// print start location
			Iterator<Statement> sit = this.inf.listStatements(
					s.getObject().asResource(), 
					inf.getProperty(KBCLVocabulary_v2.PROPERTY_START_LOCATION_URI.getValue()), 
					(RDFNode) null
			);
			
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
					this.inf.getProperty(KBCLVocabulary_v2.PROPERTY_END_LOCATION_URI.getValue()),
					(RDFNode) null
			);
			
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
	
	/**
	 * 
	 */
	@Test
	public void moduleChangeOverInferenceRuleTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- moduleChangeOverInferenceRuleTest()");
		
		// initialize transportation module reference
		Resource tm = null;
		// get transportation modules
		Iterator<Statement> it = this.inf.listStatements(
				(Resource) null, 
				RDF.type, 
				this.inf.getResource(KBCLVocabulary_v2.CLASS_TRANSPORTATIO_MODULE_URI.getValue())
		);
		
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
		System.out.println("Retrieve change-over functions the module actually can do:");
		it = this.inf.listStatements(
				this.inf.getResource(ABOX_URL + "#" + tm.getLocalName()), 
				this.inf.getProperty(KBCLVocabulary_v2.PROPERTY_CAN_DO_CHANGE_OVER_URI.getValue()),
				(RDFNode) null
		);
		
		Assert.assertNotNull(it);
		while (it.hasNext()) {
			Statement s = it.next();
			Assert.assertNotNull(s);
			Assert.assertEquals(s.getSubject().getURI(), tm.getURI());
			System.out.println("- " + s);
		}
	}
	
	/**
	 * 
	 */
	@Test
	public void componentFailureTest() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-------- componentFailureTest()");
		
		// initialize transportation module reference
		Resource tm = null;
		// get transportation modules
		Iterator<Statement> it = this.inf.listStatements(
				(Resource) null, 
				RDF.type, 
				this.inf.getResource(KBCLVocabulary_v2.CLASS_TRANSPORTATIO_MODULE_URI.getValue())
		);
		
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
		
		// print information about primitive channels
		System.out.println("Channel functions before the event");
		this.printChannelFunctions(tm);
		
		// get information about primitive channels
		System.out.println("Retrieve cross transfer information:");
		it = this.inf.listStatements(
				this.inf.getResource(ABOX_URL + "#" + tm.getLocalName()), 
				this.inf.getProperty(KBCLVocabulary_v2.PROPERTY_HAS_CROSS_TRANSFER_URI.getValue()), 
				(RDFNode) null
		);
		
		Resource cross = null;
		Assert.assertNotNull(it);
		while (it.hasNext()) {
			Statement s = it.next();
			cross = s.getObject().asResource();
			Assert.assertNotNull(s);
			Assert.assertNotNull(cross);
		}
		Assert.assertNotNull(cross);
		System.out.println("- " + cross);

		// get performance information
		it = this.inf.listStatements(
				cross, 
				this.inf.getProperty(KBCLVocabulary_v2.PROPERTY_HAS_PERFORMANCE_URI.getValue()),
				(RDFNode) null
		);
		
		Assert.assertNotNull(it);
		Statement sToRemove = null;
		count = 0;
		while (it.hasNext()) {
			// only one result expected
			sToRemove = it.next();
			count++;
			Assert.assertNotNull(sToRemove);
		}
		Assert.assertTrue(count == 1);
		
		// remove statement
		System.out.println("Removing statement " + sToRemove);
		this.inf.remove(sToRemove);
		
		// get failure performance instance
		Resource failure = this.inf.getResource(KBCLVocabulary_v2.COSTANT_FAILURE_PERFORMANCE_URI.getValue());
		// add statement
		Statement sToAdd = this.inf.createStatement(cross, this.inf.getProperty(KBCLVocabulary_v2.PROPERTY_HAS_PERFORMANCE_URI.getValue()), failure);
		System.out.println("Added statement " + sToAdd);
		
		// print information about primitive channels
		System.out.println("Channel functions after the event");
		this.printChannelFunctions(tm);
	}
	
	/**
	 * 
	 * @param tm
	 */
	private void printChannelFunctions(Resource tm) {
		System.out.println("Retrieve channel functions the module actually can do:");
		Iterator<Statement> it = this.inf.listStatements(this.inf.getResource(ABOX_URL + "#" + tm.getLocalName()), 
				this.inf.getProperty(KBCLVocabulary_v2.PROPERTY_CAN_DO_CHANNEL_URI.getValue()), 
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
					inf.getProperty(KBCLVocabulary_v2.PROPERTY_START_LOCATION_URI.getValue()),
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
					this.inf.getProperty(
							KBCLVocabulary_v2.PROPERTY_END_LOCATION_URI.getValue()),
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
