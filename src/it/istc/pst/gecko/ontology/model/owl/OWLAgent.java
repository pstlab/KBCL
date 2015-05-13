package it.istc.pst.gecko.ontology.model.owl;

import it.istc.pst.gecko.ontology.model.Agent;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class OWLAgent extends Agent {

	/**
	 * 
	 * @param id
	 * @param label
	 * @param type
	 */
	protected OWLAgent(String id, String label, OWLAgentType type) {
		super(id, label, type);
	}
}
