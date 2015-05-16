package it.istc.pst.kbcl.mapping.kb.rdf.exception;


/**
 * 
 * @author alessandroumbrico
 *
 */
public class RDFResourceNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param msg
	 */
	public RDFResourceNotFoundException(String msg) {
		super(msg);
	}
}
