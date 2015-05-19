package it.istc.pst.kbcl.inference.model.owl;

import it.istc.pst.kbcl.inference.kb.owl.OWLDatasetManager;
import it.istc.pst.kbcl.model.Functionality;
import it.istc.pst.kbcl.model.FunctionalityType;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class OWLFunctionality extends Functionality 
{
	protected OWLDatasetManager dataset;
	
	/**
	 * 
	 * @param id
	 * @param label
	 * @param type
	 */
	protected OWLFunctionality(String id, String label, FunctionalityType type) {
		super(id, label, type);
		// get data-set manager
		this.dataset = OWLDatasetManager.getSingletonInstance();
	}
}
