package it.istc.pst.kbcl.inference.kb.model.owl;

import java.util.Iterator;

import com.hp.hpl.jena.ontology.OntClass;
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
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasonerFactory;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.ReasonerVocabulary;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class OWLDataset_OntoV2_Test 
{
//	private static final OntModelSpec MODEL = OntModelSpec.OWL_MEM_RDFS_INF;
	private static final String ABOX_URL = "http://pst.istc.cnr.it/kbcl/instance";
	private static final String ABOX_PATH = "dev/onto/v2/abox/simple.rdf";
	private static final String TBOX_URL = "http://pst.istc.cnr.it/kbcl/ontology";
	private static final String TBOX_PATH = "dev/onto/v2/tbox/onto_v2.owl";
	private static final String RULES_PATH = "dev/onto/v2/rules_testing.rules";
	
//	private OWLDatasetManager dm;
	
	/**
	 * 
	 */
//	@Before
	public static void main(String[] args) 
	{
		System.out.println("************************************************************");
		System.out.println("********* OWL Dataset Inference - Onto_v2 - Test ***********");
		System.out.println("************************************************************");
		
		// create the model of the schema (TBOX)
		OntModel tbox = ModelFactory.createOntologyModel();
		// read the schema from a file
		tbox.getDocumentManager().addAltEntry(TBOX_URL, "file:" + TBOX_PATH);
		tbox.read(TBOX_URL, "RDF/XML");

//		// specify generic rule reasoner configuration
//		Resource cfg = tbox.createResource();
//		cfg.addProperty(ReasonerVocabulary.PROPruleMode, "forwardRETE");
//		cfg.addProperty(ReasonerVocabulary.PROPruleSet, RULES_PATH);
//		// create reasoner
//		Reasoner reasoner = GenericRuleReasonerFactory.theInstance().create(cfg);
//		// bind schema
//		reasoner.bindSchema(tbox);
		
		Reasoner reasoner = ReasonerRegistry.getOWLMicroReasoner();
		reasoner.setParameter(ReasonerVocabulary.PROPruleSet, RULES_PATH);
		// specialize the reasoner by setting the schema
		reasoner = reasoner.bindSchema(tbox);
		
		// create data model (ABOX)
		Model abox = FileManager.get().loadModel(ABOX_PATH, ABOX_URL, "RDF/XML");
		// bind the reasoner on data
		InfModel inf = ModelFactory.createInfModel(reasoner, abox);
		
		System.out.println();
		System.out.println("-----------------------------------------------------");
		String prop = "connection";
		System.out.println("Statements concerning ObjectProperty \"" + prop + "\"");
		// get connection property
		Property p = tbox.getOntProperty(TBOX_URL + "#" + prop);
		Iterator<Statement> it = inf.listStatements((Resource) null, p, (RDFNode) null);
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		
		// get transportation modules
		System.out.println();
		System.out.println("-----------------------------------------------------");
		System.out.println("Retrieve all transportation modules");
		it = inf.listStatements((Resource) null, 
				RDF.type, 
				inf.getResource(TBOX_URL + "#TransportationModule"));
		
		while (it.hasNext()) {
			Statement s = it.next();
			System.out.println("- " + s.getSubject());
		}
		
		// get information about components
		System.out.println();
		System.out.println("-----------------------------------------------------");
		System.out.println("Retrieve all statements concerning module's components");
		it = inf.listStatements(inf.getResource(ABOX_URL + "#module"),
				inf.getProperty(TBOX_URL + "#hasComponent"),
				(RDFNode) null);
		
		while (it.hasNext()) {
			Statement s = it.next();
			System.out.println("- " + s.getObject());
		}
		
		// get information about collaborators
		System.out.println();
		System.out.println("-----------------------------------------------------");
		System.out.println("Retrieve all statements concerning module's collaborators");
		it = inf.listStatements(inf.getResource(ABOX_URL + "#module"), 
				inf.getProperty(TBOX_URL + "#hasCollaborator"), 
				(RDFNode) null);
		
		while (it.hasNext()) {
			Statement s = it.next();
			System.out.println("- " + s.getObject());
		}
		
		
		// get information about primitive channels
		System.out.println();
		System.out.println("-----------------------------------------------------");
		System.out.println("Retrieve information concerning primitive channels");
		it = inf.listStatements((Resource) null, 
				RDF.type, 
				inf.getResource(TBOX_URL + "#Channel"));
		
		while (it.hasNext()) {
			Statement s = it.next();
			System.out.println("- " + s);
			// print start location
			Iterator<Statement> sit = inf.listStatements(
					s.getSubject().asResource(), 
					inf.getProperty(TBOX_URL + "#startLocation"), 
					(RDFNode) null);
			
			System.out.println("- Start Location:");
			while (sit.hasNext()) {
				System.out.println("-- " + sit.next().getObject());
			}
			
			sit = inf.listStatements(
					s.getSubject().asResource(), 
					inf.getProperty(TBOX_URL + "#endLocation"),
					(RDFNode) null);
			System.out.println("- End Location:");
			while (sit.hasNext()) {
				System.out.println("-- " + sit.next().getObject());
			}
		}
		
		// get information about primitive channels
		System.out.println();
		System.out.println("-----------------------------------------------------");
		System.out.println("Retrieve channels the module can do");
		it = inf.listStatements(inf.getResource(ABOX_URL + "#module"), 
				inf.getProperty(TBOX_URL + "#canDoChannel"), 
				(RDFNode) null);
		
		while (it.hasNext()) {
			Statement s = it.next();
			System.out.println("- " + s);
			// print start location
			Iterator<Statement> sit = inf.listStatements(
					s.getObject().asResource(), 
					inf.getProperty(TBOX_URL + "#startLocation"), 
					(RDFNode) null);
			
			System.out.println("- Start Location:");
			while (sit.hasNext()) {
				System.out.println("-- " + sit.next().getObject());
			}
			
			sit = inf.listStatements(
					s.getObject().asResource(), 
					inf.getProperty(TBOX_URL + "#endLocation"),
					(RDFNode) null);
			System.out.println("- End Location:");
			while (sit.hasNext()) {
				System.out.println("-- " + sit.next().getObject());
			}
		}
	}
	
//	/**
//	 * 
//	 */
//	@After
//	public void cleanup() {
//		if (this.dm != null) {
//			// close data-set manager
//			this.dm.close();
//		}
//	}
//	
//	/**
//	 * 
//	 */
//	@Test
//	public void initializationTest() {
//		System.out.println("-----------------------------------------------------------------");
//		System.out.println("-------- initializationTest()");
//		System.out.println();
//		// create factory
//		this.dm = OWLDatasetManager.getSingletonInstance(MODEL, RULES_PATH, ABOX_PATH, ABOX_URL, TBOX_PATH, TBOX_URL);
//		Assert.assertNotNull(this.dm);
//		System.out.println("Ok!");
//		System.out.println("-----------------------------------------------------------------");
//	}
//	
//	/**
//	 * 
//	 */
//	@Test
//	public void retrieveTransportationModuleInformationTest() {
//		System.out.println("-----------------------------------------------------------------");
//		System.out.println("-------- retrieveTransportationModuleInformationTest()");
//		System.out.println();
//		try 
//		{
//			// create factory
//			this.dm = OWLDatasetManager.getSingletonInstance(MODEL, RULES_PATH, 
//					ABOX_PATH, ABOX_URL, TBOX_PATH, TBOX_URL);
//			Assert.assertNotNull(this.dm);
//			
//			// get TM information
//			List<OWLInstance> modules = this.dm.retrieveAllInstancesOfClass("TransportationModule");
//			Assert.assertNotNull(modules);
//			Assert.assertTrue(!modules.isEmpty());
//			
//			// get TM 
//			System.out.println("Transportation Module");
//			OWLInstance tm = modules.get(0);
//			Assert.assertNotNull(tm);
//			System.out.println("- " + tm.getUrl());
//			
//			// get devices' data
//			List<OWLInstance> devices = this.dm.retrieveAllInstancesRelatedByProperty(tm.getLabel(), "hasDevice");
//			Assert.assertNotNull(devices);
//			Assert.assertTrue(!devices.isEmpty());
//			System.out.println("Module's devices:");
//			for (OWLInstance device : devices) {
//				System.out.println("- " + device.getUrl());
//			}
//			
//			System.out.println();
//			
//			// get components
//			List<OWLInstance> components = this.dm.retrieveAllInstancesRelatedByProperty(tm.getLabel(), "hasComponent");
//			Assert.assertNotNull(components);
//			Assert.assertTrue(!components.isEmpty());
//			System.out.println("Module's components:");
//			for (OWLInstance component : components) {
//				System.out.println("- " + component.getUrl());
//				// get component's locations
//				List<OWLInstance> locations = this.dm.retrieveAllInstancesRelatedByProperty(component.getLabel(), "hasLocation");
//				Assert.assertNotNull(locations);
//				System.out.println("   Component's Locations:");
//				for (OWLInstance location : locations) {
//					System.out.println("--- " + location.getUrl());
//					// get connections
//					List<OWLInstance> connected = this.dm.retrieveAllInstancesRelatedByProperty(location.getLabel(), "connection");
//					Assert.assertNotNull(connected);
//					for (OWLInstance cl : connected) {
//						System.out.println("---- connected to " + cl.getUrl());
//					}
//				}
//			}
//			
//			System.out.println();
//			
//			// get collaborators
//			List<OWLInstance> collaborators = this.dm.retrieveAllInstancesRelatedByProperty(tm.getLabel(), "hasCollaborator");
//			Assert.assertNotNull(collaborators);
//			Assert.assertTrue(!collaborators.isEmpty());
//			System.out.println("Module's collaborators:");
//			for (OWLInstance collaborator : collaborators) {
//				System.out.println("- " + collaborator.getUrl());
//				// get collaborator's locations
//				List<OWLInstance> locations = this.dm.retrieveAllInstancesRelatedByProperty(collaborator.getLabel(), "hasLocation");
//				Assert.assertNotNull(locations);
//				System.out.println("   Collaborator's Locations:");
//				for (OWLInstance location : locations) {
//					System.out.println("--- " + location.getUrl());
//					// get connections
//					List<OWLInstance> connected = this.dm.retrieveAllInstancesRelatedByProperty(location.getLabel(), "connection");
//					Assert.assertNotNull(connected);
//					for (OWLInstance cl : connected) {
//						System.out.println("---- connected to " + cl.getUrl());
//					}
//				}
//			}
//			
//			System.out.println();
//			
//			
//			// get channels
//			List<OWLInstance> channels = this.dm.retrieveAllInstancesOfClass("Channel");
//			Assert.assertNotNull(channels);
//			Assert.assertTrue(!channels.isEmpty());
//			System.out.println("Available Channels:");
//			for (OWLInstance channel : channels) {
//				System.out.println("- " + channel.getUrl());
//			}
//		}
//		catch (Exception ex) {
//			System.err.println("> " + ex.getMessage());
//		}
//		
//		
//		System.out.println("-----------------------------------------------------------------");
//	}
}
