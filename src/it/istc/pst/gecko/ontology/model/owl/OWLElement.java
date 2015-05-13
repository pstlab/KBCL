package it.istc.pst.gecko.ontology.model.owl;

import it.istc.pst.gecko.ontology.model.Element;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class OWLElement extends Element 
{
	/**
	 * 
	 * @param id
	 * @param label
	 * @param type
	 */
	protected OWLElement(String id, String label, OWLElementType type) {
		super(id, label, type);
	}
}
