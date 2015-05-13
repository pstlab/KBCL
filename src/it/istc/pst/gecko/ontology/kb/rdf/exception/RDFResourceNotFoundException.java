package it.istc.pst.gecko.ontology.kb.rdf.exception;

import it.istc.pst.gecko.ontology.kb.exception.ResourceNotFoundException;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class RDFResourceNotFoundException extends ResourceNotFoundException {

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
