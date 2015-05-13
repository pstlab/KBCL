package it.istc.pst.gecko.ontology.model.owl;



/**
 * 
 * @author alessandroumbrico
 *
 */
public class OWLModelFactory {

	private static OWLModelFactory INSTANCE = null;
	
	/**
	 * 
	 */
	private OWLModelFactory() {
		// nothing
	}
	
	/**
	 * 
	 * @return
	 */
	public static OWLModelFactory getSingletonInstance() {
		if (INSTANCE == null) {
			INSTANCE = new OWLModelFactory();
		}
		return INSTANCE;
	}
	
	/**
	 * 
	 * @param id
	 * @param label
	 * @return
	 */
	public OWLAgentType createAgentType(String id, String label) {
		return new OWLAgentType(id, label);
	}
	
	/**
	 * 
	 * @param id
	 * @param label
	 * @param type
	 * @return
	 */
	public OWLAgent createAgent(String id, String label, OWLAgentType type) {
		return new OWLAgent(id, label, type);
	}
	
	/**
	 * 
	 * @param id
	 * @param label
	 * @return
	 */
	public OWLElementType createElementType(String id, String label) {
		return new OWLElementType(id, label);
	}
	
	/**
	 * 
	 * @param id
	 * @param label
	 * @param type
	 * @return
	 */
	public OWLElement createElement(String id, String label, OWLElementType type) {
		return new OWLElement(id, label, type);
	}
	
}
