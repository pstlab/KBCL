package it.istc.pst.kbcl.ontology.exception;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class KBSetupErrorException extends Exception 
{
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param msg
	 */
	public KBSetupErrorException(String msg) {
		super(msg);
	}
}
