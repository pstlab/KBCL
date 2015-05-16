package it.istc.pst.kbcl.exception;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class KbclNoAgentSelectedException extends Exception 
{
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param msg
	 */
	public KbclNoAgentSelectedException(String msg) {
		super(msg);
	}
}
