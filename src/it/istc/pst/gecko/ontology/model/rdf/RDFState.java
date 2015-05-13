package it.istc.pst.gecko.ontology.model.rdf;

import it.istc.pst.gecko.ontology.model.State;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class RDFState extends State 
{
	/**
	 * 
	 * @param id
	 * @param label
	 * @param dmin
	 * @param dmax
	 * @param component
	 */
	protected RDFState(String id, String label, String dmin, String dmax, RDFComponent component) {
		super(id, label, dmin, dmax, component);
	}
}
