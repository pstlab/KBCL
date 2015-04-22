package it.istc.pst.gecko.ontology.model;


/**
 * 
 * @author alessandroumbrico
 *
 */
public class ExternalComponent extends Component
{
	private Component connectedBy;
	
	/**
	 * 
	 * @param id
	 * @param label
	 */
	public ExternalComponent(String id, String label) {
		super(id, label);
		// lazy approach
		this.connectedBy = null;
	}
	
	/**
	 * 
	 * @return
	 */
	public Component getConnectedBy() {
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
	public boolean equals(Object obj) {
		if (!(obj instanceof ExternalComponent)) {
			throw new RuntimeException("Uncomparable objects");
		}
		ExternalComponent other = (ExternalComponent) obj;
		return this.id.equals(other.getId());
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
