package it.istc.pst.gecko.ontology.kb.rdf.exception;

import it.istc.pst.gecko.ontology.kb.owl.exception.OWLClassNotFoundException;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class RDFResourceNotFoundException extends OWLClassNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param msg
	 */
	public RDFResourceNotFoundException(String msg) {
		super(msg);
	}
}
