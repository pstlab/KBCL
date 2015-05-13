package it.istc.pst.gecko.ontology.model;


/**
 * 
 * @author alessandroumbrico
 *
 */
public abstract class Component 
{
	protected String id;
	protected String label;
	
	
	/**
	 * 
	 * @param id
	 * @param label
	 */
	protected Component(String id, String label) {
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
		return (obj instanceof Component) && ((Component) obj).id.equals(this.id);
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[Component id=" + this.id +"\n"
				+ "\tlabel= " + this.label + "]\n";
	}
}
