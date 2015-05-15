package it.istc.pst.gecko.ontology.model.owl;

import it.istc.pst.gecko.ontology.kb.owl.OWLDatasetManager;
import it.istc.pst.gecko.ontology.kb.owl.OWLInstance;
import it.istc.pst.gecko.ontology.kb.owl.exception.OWLIndividualNotFoundException;
import it.istc.pst.gecko.ontology.kb.owl.exception.OWLPropertyNotFoundException;

import java.util.List;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class OWLPort extends OWLElement 
{
	private OWLElement neighbor;
	
	/**
	 * 
	 * @param id
	 * @param label
	 * @param type
	 */
	protected OWLPort(String id, String label, OWLElementType type) {
		super(id, label, type);
		// lazy approach
		this.neighbor = null;
	}
	
	/**
	 * 
	 * @return
	 */
	public OWLElement getConnectedNeighbor() {
		if (this.neighbor == null) {
			try {
				// load from data-set
				List<OWLInstance> ns = this.dataset
						.retrieveAllInstancesRelatedByProperty(this.label, 
								OWLDatasetManager.PROPERTY_LABEL_CONNECT);
				
				// check list
				if (!ns.isEmpty()) {
					// get connected neighbor
					OWLInstance n = ns.get(0);
					
					// TODO : create and set
					
					// check size
					if (ns.size() > 1) {
						System.err.println("... Warning more than one connected neighbor found for port " + this.id);
					}
				}
				else {
					System.err.println("... Warning no connected neighbor found for port " + this.id);
				}
			}
			catch (OWLIndividualNotFoundException | OWLPropertyNotFoundException ex) {
				this.neighbor = null;
				System.err.println(ex.getMessage());
			}
		}
		// get neighbor
		return this.neighbor;
	}
	
	/**
	 * 
	 * @param neighbor
	 */
	public void setConnectedNeighbor(OWLElement neighbor) {
		try {
			// save relation
			this.dataset.assertStatement(this.label, OWLDatasetManager.PROPERTY_LABEL_CONNECT, neighbor.getLabel());
			// update
			this.neighbor = neighbor;
		}
		catch (OWLIndividualNotFoundException | OWLPropertyNotFoundException ex) {
			this.neighbor = null;
			System.err.println(ex.getMessage());
		}
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[Element id=" + this.id +"\n"
				+ "\tlabel= " + this.label + "\n"
				+ "\ttype= " + this.type + "\n"
				+ "\tconnect= " + this.neighbor + "]\n";
	}
}
