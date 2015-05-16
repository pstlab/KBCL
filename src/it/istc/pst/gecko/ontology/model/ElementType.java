package it.istc.pst.gecko.ontology.model;


/**
 * 
 * @author alessandroumbrico
 *
 */
public abstract class ElementType {
	
	private String id;
	private String label;
	
	/**
	 * 
	 * @param id
	 * @param label
	 */
	protected ElementType(String id, String label) {
		this.id = id;
		this.label = label;
	}

	/**
	 * 
	 * @return
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * 
	 */
	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
	
	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof ElementType) && ((ElementType) obj).id.equals(this.id);
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[ElementType id=" + this.id +", label= " + this.label + "]\n";
	}
}
