package it.istc.pst.kbcl.ontology.model.owl;

import it.istc.pst.kbcl.model.Element;
import it.istc.pst.kbcl.ontology.kb.owl.OWLDatasetManager;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class OWLElement extends Element 
{
	protected OWLDatasetManager dataset;
	
	/**
	 * 
	 * @param id
	 * @param label
	 * @param type
	 */
	protected OWLElement(String id, String label, OWLElementType type) {
		super(id, label, type);
		this.dataset = OWLDatasetManager.getSingletonInstance();
	}
}
