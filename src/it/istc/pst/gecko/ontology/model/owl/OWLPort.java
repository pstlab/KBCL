package it.istc.pst.gecko.ontology.model.owl;

import it.istc.pst.gecko.ontology.kb.exception.PropertyNotFoundException;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class OWLPort extends OWLElement 
{
	private OWLElement connectedNeighbor;
	
	/**
	 * 
	 * @param id
	 * @param label
	 * @param type
	 */
	protected OWLPort(String id, String label, OWLElementType type) {
		super(id, label, type);
		
		// lazy approach
		this.connectedNeighbor = null;
	}
	
	/**
	 * 
	 * @return
	 */
	public OWLElement getConnectedNeighbor() {
		if (this.connectedNeighbor == null) {
			try {
				this.connectedNeighbor = this.dao.retrievePortNeighbor(this);
			}
			catch (PropertyNotFoundException ex) {
				this.connectedNeighbor = null;
				System.err.println(ex.getLocalizedMessage());
			}
		}
		// get neighbor
		return this.connectedNeighbor;
	}
	
	/**
	 * 
	 * @param neighbor
	 */
	public void setConnectedNeighbor(OWLElement neighbor) {
		// save relation
		this.dao.setPortNeighbor(neighbor, this);
		// update
		this.connectedNeighbor = neighbor; 
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[Element id=" + this.id +"\n"
				+ "\tlabel= " + this.label + "\n"
				+ "\ttype= " + this.type + "\n"
				+ "\tconnect= " + this.connectedNeighbor + "]\n";
	}
}
