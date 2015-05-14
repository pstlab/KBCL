package it.istc.pst.gecko.ontology.kb.exception;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class PropertyNotFoundException extends Exception 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param msg
	 */
	public PropertyNotFoundException(String msg) {
		super(msg);
	}
}
