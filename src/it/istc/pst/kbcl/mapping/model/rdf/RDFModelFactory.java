package it.istc.pst.kbcl.mapping.model.rdf;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class RDFModelFactory {

	private static RDFModelFactory INSTANCE = null;
	
	/**
	 * 
	 */
	private RDFModelFactory() {
		// nothing
	}
	
	/**
	 * 
	 * @return
	 */
	public static RDFModelFactory getSingletonInstance() {
		if (INSTANCE == null) {
			INSTANCE = new RDFModelFactory();
		}
		return INSTANCE;
	}
	
	/**
	 * 
	 * @param id
	 * @param label
	 * @return
	 */
	public RDFAgentType createAgentType(String id, String label) {
		return new RDFAgentType(id, label);
	}
	
	/**
	 * 
	 * @param id
	 * @param label
	 * @param type
	 * @return
	 */
	public RDFAgent createAgent(String id, String label, RDFAgentType type) {
		return new RDFAgent(id, label, type);
	}
	
	/**
	 * 
	 * @param id
	 * @param label
	 * @return
	 */
	public RDFComponent createComponent(String id, String label) {
		return new RDFComponent(id, label);
	}
	
	/**
	 * 
	 * @param id
	 * @param label
	 */
	public RDFExternalComponent createExternalCopmonent(String id, String label) {
		return new RDFExternalComponent(id, label);
	}
	
	/**
	 * 
	 * @param id
	 * @param label
	 * @param dmin
	 * @param dmax
	 * @param type
	 * @return
	 */
	public RDFFunctionality createFunctionality(String id, String label, String dmin, String dmax, RDFFunctionalityType type) {
		return new RDFFunctionality(id, label, dmin, dmax, type);
	}
	
	/**
	 * 
	 * @param func
	 * @return
	 */
	public RDFFunctionalityImplementation createFunctionalityImplementation(RDFFunctionality func) {
		return new RDFFunctionalityImplementation(func);
	}
	
	/**
	 * 
	 * @param id
	 * @param label
	 * @return
	 */
	public RDFFunctionalityType createFunctionalityType(String id, String label) {
		return new RDFFunctionalityType(id, label); 
	}
	
	/**
	 * 
	 * @param id
	 * @param comp
	 * @return
	 */
	public RDFRestriction createRestriction(String id, RDFComponent comp) {
		return new RDFRestriction(id, comp);
	}
	
	/**
	 * 
	 * @param id
	 * @param label
	 * @param dmin
	 * @param dmax
	 * @param component
	 * @return
	 */
	public RDFState createState(String id, String label, String dmin, String dmax, RDFComponent component) {
		return new RDFState(id, label, dmin, dmax, component);
	}
	
	/**
	 * 
	 * @param id
	 * @param label
	 * @param target
	 * @return
	 */
	public RDFTemporalConstraint createTemporalConstraint(String id, String label, RDFState target) {
		return new RDFTemporalConstraint(id, label, target);
	}
 }
