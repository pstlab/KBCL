package it.istc.pst.gecko.ontology.kb.exception;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class ResourceNotFoundException extends Exception 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param msg
	 */
	public ResourceNotFoundException(String msg) {
		super(msg);
	}
}
