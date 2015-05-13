package it.istc.pst.gecko.ontology.model;


/**
 * 
 * @author alessandroumbrico
 *
 */
public abstract class Element 
{
	protected String id;
	protected String label;
	protected ElementType type;
	
	
	
	/**
	 * 
	 * @param id
	 * @param label
	 * @param type
	 */
	protected Element(String id, String label, ElementType type) {
		this.id = id;
		this.label = label;
		this.type = type;
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
	 * @return
	 */
	public ElementType getType() {
		return type;
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
		return (obj instanceof Element) && ((Element) obj).id.equals(this.id);
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[Element id=" + this.id +"\n"
				+ "\tlabel= " + this.label + ""
				+ "\ttype= " + this.type + "]\n";
	}
}
