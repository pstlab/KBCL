package it.istc.pst.kbcl.inference.model.owl;

import it.istc.pst.kbcl.inference.kb.owl.OWLDatasetManager_v1;
import it.istc.pst.kbcl.model.Element;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class OWLElement extends Element 
{
	protected OWLDatasetManager_v1 dataset;
	
	/**
	 * 
	 * @param id
	 * @param label
	 * @param type
	 */
	protected OWLElement(String id, String label, OWLElementType type) {
		super(id, label, type);
		this.dataset = OWLDatasetManager_v1.getSingletonInstance();
	}
}
