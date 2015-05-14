package it.istc.pst.gecko.ontology.model.owl;

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
			this.connectedNeighbor = this.dao.retrievePortNeighbor(this);
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
}
