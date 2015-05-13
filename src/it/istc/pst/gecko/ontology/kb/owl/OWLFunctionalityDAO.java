package it.istc.pst.gecko.ontology.kb.owl;

import it.istc.pst.gecko.ontology.model.FunctionalityType;
import it.istc.pst.gecko.ontology.model.owl.OWLFunctionality;
import it.istc.pst.gecko.ontology.model.owl.OWLFunctionalityType;
import it.istc.pst.gecko.ontology.model.owl.OWLModelFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class OWLFunctionalityDAO 
{
	private OWLDatasetManager dataset;
	private OWLModelFactory factory;
	
	/**
	 * 
	 */
	protected OWLFunctionalityDAO() {
		this.dataset = OWLDatasetManager.getSingletonInstance();
		this.factory = OWLModelFactory.getSingletonInstance();
	}
	
	/**
	 * 
	 * @return
	 */
	public List<OWLFunctionalityType> retrieveAllFunctionalityTypes() {
		List<OWLFunctionalityType> list = new ArrayList<>();
		
		OntClass f = this.dataset.model.getOntClass(OWLDatasetManager.NS + "Functionality");
		// add root class
		list.add(this.factory.createFunctionalityType(f.getURI(), f.getLocalName()));
		// get subclasses
		Iterator<? extends OntClass> it = f.listSubClasses(false);
		while (it.hasNext()) {
			// next class
			OntClass subclass = it.next();
			// check anonymous type
			if (!subclass.isAnon()) {
				// create functionality type
				list.add(this.factory.createFunctionalityType(subclass.getURI(), subclass.getLocalName()));
			}
		}
		
		// get list
		return list;
	}

	/**
	 * 
	 * @return
	 */
	public List<OWLFunctionality> retrieveAllFunctionalities() {
		List<OWLFunctionality> list = new ArrayList<>();
		
		// get root class
		OntClass f = this.dataset.model.getOntClass(OWLDatasetManager.NS + "Functionality");
		Iterator<? extends OntResource> it = f.listInstances(false);
		while (it.hasNext()) {
			// get individual
			Individual fi = it.next().asIndividual();
			// get type
			Resource type = fi.getRDFType();
			// add functionality
			list.add(this.factory.createFunctionality(fi.getURI(), fi.getLocalName(), 
					this.factory.createFunctionalityType(type.getURI(), type.getLocalName())));
		}
		
		// get functionalities
		return list;
	}

	/**
	 * 
	 * @param type
	 * @return
	 */
	public List<OWLFunctionality> retrieveFunctionalitiesByType(FunctionalityType type) {
		List<OWLFunctionality> list = new ArrayList<>();
		
		OntClass fc = this.dataset.model.getOntClass(type.getId());
		Iterator<? extends OntResource> it = fc.listInstances(false);
		while (it.hasNext()) {
			// next individual
			Individual fi = it.next().asIndividual();
			// create functionality
			list.add(this.factory.createFunctionality(fi.getURI(), fi.getLocalName(), 
					this.factory.createFunctionalityType(fc.getURI(), fc.getLocalName())));
		}
		
		// get list
		return list;
	}
	
}
