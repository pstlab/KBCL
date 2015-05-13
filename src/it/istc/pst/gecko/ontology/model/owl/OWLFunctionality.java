package it.istc.pst.gecko.ontology.model.owl;

import it.istc.pst.gecko.ontology.model.Functionality;
import it.istc.pst.gecko.ontology.model.FunctionalityType;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class OWLFunctionality extends Functionality 
{
	/**
	 * 
	 * @param id
	 * @param label
	 * @param type
	 */
	protected OWLFunctionality(String id, String label, FunctionalityType type) {
		super(id, label, type);
	}
}
