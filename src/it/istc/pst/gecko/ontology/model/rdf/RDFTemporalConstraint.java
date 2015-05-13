package it.istc.pst.gecko.ontology.model.rdf;

import it.istc.pst.gecko.ontology.model.TemporalConstraint;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class RDFTemporalConstraint extends TemporalConstraint 
{
	/**
	 * 
	 * @param id
	 * @param label
	 * @param target
	 */
	protected RDFTemporalConstraint(String id, String label, RDFState target) {
		super(id, label, target);
	}
}
