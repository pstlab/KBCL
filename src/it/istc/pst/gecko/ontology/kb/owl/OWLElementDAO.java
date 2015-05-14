package it.istc.pst.gecko.ontology.kb.owl;

import it.istc.pst.gecko.ontology.kb.exception.ResourceNotFoundException;
import it.istc.pst.gecko.ontology.kb.owl.exception.OWLResourceNotFoundException;
import it.istc.pst.gecko.ontology.model.ElementType;
import it.istc.pst.gecko.ontology.model.owl.OWLElement;
import it.istc.pst.gecko.ontology.model.owl.OWLElementType;
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
import com.hp.hpl.jena.rdf.model.Statement;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class OWLElementDAO 
{
	private OWLDatasetManager dataset;
	private OWLModelFactory factory;
	
	/**
	 * 
	 */
	protected OWLElementDAO() {
		this.dataset = OWLDatasetManager.getSingletonInstance();
		this.factory = OWLModelFactory.getSingletonInstance();
	}

	/**
	 * 
	 * @return
	 */
	public List<OWLElementType> retrieveAllElementTypes() {
		List<OWLElementType> list = new ArrayList<>();
		
		// get element class
		OntClass e = this.dataset.model.getOntClass(OWLDatasetManager.NS + "Element");
		// add root type
		list.add(this.factory.createElementType(e.getURI(), e.getLocalName()));
		
		// get all subclasses
		Iterator<OntClass> it = e.listSubClasses(false);
		while (it.hasNext()) {
			// get subclass
			OntClass subclass = it.next();
			if (!subclass.isAnon()) {
				// add element type
				list.add(this.factory.createElementType(subclass.getURI(), subclass.getLocalName()));
			}
		}
		
		// get element type
		return list;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public OWLElementType retrieveElementTypeById(String id) 
			throws ResourceNotFoundException
	{
		// get class
		OntClass e = this.dataset.model.getOntClass(id);
		if (e == null) {
			throw new OWLResourceNotFoundException("OWL Class not found for " + id);
		}
		
		// get element type
		return this.factory.createElementType(e.getURI(), e.getLocalName());
	}

	/**
	 * 
	 * @return
	 */
	public List<OWLElement> retrieveAllElements() {
		List<OWLElement> list = new ArrayList<>();
		
		// get ONTOLOGY class
		OntClass e = this.dataset.model.getOntClass(OWLDatasetManager.NS + "Element");
		Iterator<? extends OntResource> it = e.listInstances(false);
		while (it.hasNext()) {
			// get next individual
			Individual ie = it.next().asIndividual();
			// get individual class
			Resource type = ie.getRDFType();
			
			// create element
			list.add(this.factory.createElement(ie.getURI(), ie.getLocalName(), 
					this.factory.createElementType(type.getURI(), type.getLocalName())));
		}
		
		// get elements
		return list;
	}

	/**
	 * 
	 * @param type
	 * @return
	 */
	public List<OWLElement> retrieveElementsByType(ElementType type) {
		List<OWLElement> list = new ArrayList<>();
		
		// get resource
		OntClass typeClass = this.dataset.model.getOntClass(type.getId());
		// get individuals
		Iterator<? extends OntResource> it = typeClass.listInstances(false);
		while (it.hasNext()) {
			// get individual
			Individual ei = it.next().asIndividual();
			// add element
			list.add(this.factory.createElement(ei.getURI(), ei.getLocalName(), 
					this.factory.createElementType(typeClass.getURI(), typeClass.getLocalName())));
		}

		// get list
		return list;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public OWLElement retrieveElementById(String id) 
			throws ResourceNotFoundException
	{
		// get individual
		Individual e = this.dataset.model.getIndividual(id);
		// check
		if (e == null) {
			throw new ResourceNotFoundException("No Element individual found for \"" + id + "\"");
		}
		
		// get class
		Resource type = e.getRDFType();
		// get element
		return this.factory.createElement(e.getURI(), e.getLocalName(), 
				this.factory.createElementType(type.getURI(), type.getLocalName()));
	}

	/**
	 * 
	 * @param name
	 * @param type
	 * @return
	 */
	public OWLElement createElement(String name, ElementType type) 
	{
		// get class
		OntClass typeClass = this.dataset.model.getOntClass(type.getId());
		// create individual
		Individual e = typeClass.createIndividual(OWLDatasetManager.NS + name);
		
		// get element
		return this.factory.createElement(e.getURI(), e.getLocalName(), 
				this.factory.createElementType(typeClass.getURI(), typeClass.getLocalName()));
	}

	/**
	 * FIXME: DA TESTARE!!!
	 * 
	 * @param port
	 * @return
	 */
	public OWLElement retrievePortNeighbor(OWLPort port) {
		// get individual
		Individual p = this.dataset.model.getIndividual(port.getId());
		// get property
		Property prop = this.dataset.model.getProperty(OWLDatasetManager.NS + "connects");
		Statement stat = p.getProperty(prop);
		// get neighbor
		OntResource neighbor = stat.getObject().as(OntResource.class);
		// get type
		Resource type = neighbor.getRDFType();
		return this.factory.createElement(neighbor.getURI(), neighbor.getLocalName(), 
				this.factory.createElementType(type.getURI(), type.getLocalName()));
	}

	/**
	 * FIXME: DA TESTARE!!!
	 * 
	 * @param neighbor
	 * @param port
	 */
	public void setPortNeighbor(OWLElement neighbor, OWLPort port) {
		// get individual
		Individual p = this.dataset.model.getIndividual(port.getId());
		// get property
		Property prop = this.dataset.model.getProperty(OWLDatasetManager.NS + "connect");
		// get value
		Resource res = this.dataset.model.getIndividual(neighbor.getId());
		// check if property exists
		if (p.hasProperty(prop)) {
			// remove property
			p.removeProperty(prop, (RDFNode) null);
		}
		
		// insert property
		p.addProperty(prop, res);
	}

}
