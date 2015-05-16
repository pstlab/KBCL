package it.istc.pst.kbcl.exception;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class KbclRequestProcessingFailureException extends Exception 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param msg
	 */
	public KbclRequestProcessingFailureException(String msg) {
		super(msg);
	}
}
