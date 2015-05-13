package it.istc.pst.gecko.ontology.kb.owl.exception;

import it.istc.pst.gecko.ontology.kb.exception.ResourceNotFoundException;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class OWLResourceNotFoundException extends ResourceNotFoundException 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param msg
	 */
	public OWLResourceNotFoundException(String msg) {
		super(msg);
	}
}
