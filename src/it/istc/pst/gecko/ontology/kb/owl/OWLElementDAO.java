package it.istc.pst.gecko.ontology.kb.owl;

import it.istc.pst.gecko.ontology.kb.ElementDAO;
import it.istc.pst.gecko.ontology.kb.exception.ResourceNotFoundException;
import it.istc.pst.gecko.ontology.kb.owl.exception.OWLResourceNotFoundException;
import it.istc.pst.gecko.ontology.model.Element;
import it.istc.pst.gecko.ontology.model.ElementType;
import it.istc.pst.gecko.ontology.model.owl.OWLModelFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.hp.hpl.jena.ontology.OntClass;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class OWLElementDAO implements ElementDAO 
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
	@Override
	public List<ElementType> retrieveAllElementTypes() {
		List<ElementType> list = new ArrayList<>();
		
		// get element class
		OntClass e = this.dataset.model.getOntClass(OWLDatasetManager.NS + "Element");
		// get all subclasses
		Iterator<OntClass> it = e.listSubClasses();
		while (it.hasNext()) {
			// get subclass
			OntClass subclass = it.next();
			// add element type
			list.add(this.factory.createElementType(subclass.getURI(), subclass.getLocalName()));
		}
		
		// get element type
		return list;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ElementType retrieveElementTypeById(String id) 
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

	@Override
	public List<Element> retrieveAllElements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Element> retrieveElementByType(ElementType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Element retrieveElementById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Element createElement(String name, ElementType type) {
		// TODO Auto-generated method stub
		return null;
	}

}
