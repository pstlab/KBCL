package it.istc.pst.kbcl.mapping.kb.rdf.exception;


/**
 * 
 * @author alessandroumbrico
 *
 */
public class RDFPropertyNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param msg
	 */
	public RDFPropertyNotFoundException(String msg) {
		super(msg);
	}
}
