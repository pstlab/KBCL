package it.istc.pst.gecko.ontology.model.owl;

import it.istc.pst.gecko.ontology.kb.owl.OWLKnowledgeBaseFactory;

import java.util.List;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class OWLModelFactory {

	private static OWLModelFactory INSTANCE = null;
	private OWLKnowledgeBaseFactory factory;
	
	/**
	 * 
	 */
	private OWLModelFactory() {
		this.factory = new OWLKnowledgeBaseFactory();
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

	/**
	 * 
	 * @param id
	 * @param label
	 * @return
	 */
	public OWLFunctionalityType createFunctionalityType(String id, String label) {
		return new OWLFunctionalityType(id, label);
	}
	
	/**
	 * 
	 * @param id
	 * @param label
	 * @param type
	 * @return
	 */
	public OWLFunctionality createFunctionality(String id, String label, OWLFunctionalityType type) {
		return new OWLFunctionality(id, label, type);
	}

	/**
	 * 
	 * @param id
	 * @param label
	 * @param type
	 * @return
	 */
	public OWLPort createPort(String id, String label) {
		// find port type
		OWLElementType portType = null;
		List<OWLElementType> types = this.factory.createElementDAO().retrieveAllElementTypes();
		for (OWLElementType type : types) {
			// check type
			if (type.getLabel().equals("Port")) {
				portType = type;
				break;
			}
		}
		
		// create port
		return new OWLPort(id, label, portType);
	}
	
	/**
	 * 
	 * @param id
	 * @param label
	 * @return
	 */
	public OWLChannel createChannel(String id, String label) {
		// find channel type
		OWLFunctionalityType channelType = null;
		List<OWLFunctionalityType> types = this.factory.createFunctionalityDAO().retrieveAllFunctionalityTypes();
		for (OWLFunctionalityType type : types) {
			// check type
			if (type.getLabel().equals("Channel")) {
				channelType = type;
				break;
			}
		}
		
		// create channel
		return new OWLChannel(id, label, channelType);
	}

	/**
	 * 
	 * @param id
	 * @param label
	 * @return
	 */
	public OWLElement createNeighbor(String id, String label) {
		// find port type
		OWLElementType neighborType = null;
		List<OWLElementType> types = this.factory.createElementDAO().retrieveAllElementTypes();
		for (OWLElementType type : types) {
			// check type
			if (type.getLabel().equals("Neighbor")) {
				neighborType = type;
				break;
			}
		}
		
		// create port
		return new OWLElement(id, label, neighborType);
	}
}
