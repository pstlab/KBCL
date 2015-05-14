package it.istc.pst.gecko.ontology.kb.owl;

import it.istc.pst.gecko.ontology.kb.exception.ResourceNotFoundException;
import it.istc.pst.gecko.ontology.model.FunctionalityType;
import it.istc.pst.gecko.ontology.model.owl.OWLChannel;
import it.istc.pst.gecko.ontology.model.owl.OWLFunctionality;
import it.istc.pst.gecko.ontology.model.owl.OWLFunctionalityType;
import it.istc.pst.gecko.ontology.model.owl.OWLModelFactory;
import it.istc.pst.gecko.ontology.model.owl.OWLPort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
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
	
	/**
	 * 
	 * @param name
	 * @param type
	 * @return
	 */
	public OWLFunctionality createFunctionality(String name, FunctionalityType type) {
		// get class
		OntClass fc = this.dataset.model.getOntClass(type.getId());
		Individual f = fc.createIndividual(OWLDatasetManager.NS + name);
		
		// create functionality
		return this.factory.createFunctionality(f.getURI(), f.getLocalName(), 
				this.factory.createFunctionalityType(fc.getURI(), fc.getLocalName()));
	}

	/**
	 * FIXME: DA TESTARE!!!
	 * 
	 * @param function
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public OWLPort retrieveChannelInputPort(OWLChannel function) 
			throws ResourceNotFoundException
	{
		List<OWLPort> list = new ArrayList<>();
		
		// get subject
		OntClass c = this.dataset.model.getOntClass(function.getId());
		// get property
		Property p = this.dataset.model.getProperty(OWLDatasetManager.NS + "hasInput");
		// get object
		
		Iterator<RDFNode> it = this.dataset.model.listObjectsOfProperty(c, p);
		while (it.hasNext()) {
			// get next node
			OntResource res = it.next().as(OntResource.class);
			if (!res.isAnon()) {
				// create element 
				list.add(this.factory.createPort(res.getURI(), res.getLocalName()));
			}
		}
		
		// check result
		if (list.isEmpty()) {
			throw new ResourceNotFoundException("No Input port found for channel " + function);
		}
		
		// get port
		return list.get(0);
	}
	
	/**
	 * FIXME: DA TESTARE!!!
	 * 
	 * @param function
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public OWLPort retrieveChannelOutputPort(OWLChannel function) 
			throws ResourceNotFoundException
	{
		List<OWLPort> list = new ArrayList<>();
		
		// get subject
		OntClass c = this.dataset.model.getOntClass(function.getId());
		// get property
		Property p = this.dataset.model.getProperty(OWLDatasetManager.NS + "hasOutput");
		// get object
		
		Iterator<RDFNode> it = this.dataset.model.listObjectsOfProperty(c, p);
		while (it.hasNext()) {
			// get next node
			OntResource res = it.next().as(OntResource.class);
			if (!res.isAnon()) {
				// create element 
				list.add(this.factory.createPort(res.getURI(), res.getLocalName()));
			}
		}
		
		// check result
		if (list.isEmpty()) {
			throw new ResourceNotFoundException("No Output port found for channel " + function);
		}
		
		// get port
		return list.get(0);
	}

	
	/**
	 * FIXME: DA TESTARE!!!
	 * 
	 * @param port
	 * @param function
	 */
	public void setInputPortToChannel(OWLPort port, OWLChannel function) {
		// get individual
		Individual c = this.dataset.model.getIndividual(function.getId());
		// get property
		Property p = this.dataset.model.getProperty(OWLDatasetManager.NS + "hasInput");
		// get port
		Resource res = this.dataset.model.getResource(port.getId());
		// check if has property
		if (c.hasProperty(p)) {
			// remove existing association
			c.removeProperty(p, (RDFNode) null);
		}
		
		// set new input port
		c.addProperty(p, res);
	}
	
	/**
	 * FIXME: DA TESTARE!!!
	 * 
	 * @param port
	 * @param function
	 */
	public void setOutputPortToChannel(OWLPort port, OWLChannel function) {
		// get individual
		Individual c = this.dataset.model.getIndividual(function.getId());
		// get property
		Property p = this.dataset.model.getProperty(OWLDatasetManager.NS + "hasOutput");
		// get port
		Resource res = this.dataset.model.getResource(port.getId());
		// check if has property
		if (c.hasProperty(p)) {
			// remove existing association
			c.removeProperty(p, (RDFNode) null);
		}
		
		// set new input port
		c.addProperty(p, res);
	}
}
