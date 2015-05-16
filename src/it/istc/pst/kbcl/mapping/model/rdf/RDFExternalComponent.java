package it.istc.pst.kbcl.mapping.model.rdf;


/**
 * 
 * @author alessandroumbrico
 *
 */
public class RDFExternalComponent extends RDFComponent 
{
	protected RDFComponent connectedBy;
	
	/**
	 * 
	 * @param id
	 * @param label
	 */
	protected RDFExternalComponent(String id, String label) {
		super(id, label);
		// lazy approach
		this.connectedBy = null;
	}
	
	/**
	 * 
	 * @return
	 */
	public RDFComponent getConnectedBy() {
		// check component
		if (this.connectedBy == null) {
			// load data
			this.connectedBy = this.dao.retrieveConnectedComponent(this);
		}
		return connectedBy;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[ExternalComponent id=" + this.id + "\n"
				+ "\tlabel= " + this.label + "\n"
				+ "\tconnectedBy= " + this.connectedBy + "\n"
				+ "\tstates= " + this.states +"]\n";
	}
}
