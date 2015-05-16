package it.istc.pst.gecko.ontology.model;


/**
 * 
 * @author alessandroumbrico
 *
 */
public abstract class Functionality 
{
	protected String id;
	protected String label;
	protected FunctionalityType type;
	
	
	/**
	 * 
	 * @param id
	 * @param label
	 * @param type
	 */
	protected Functionality(String id, String label, FunctionalityType type) {
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
		return this.label;
	}
	
	/**
	 * 
	 * @return
	 */
	public FunctionalityType getType() {
		return this.type;
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
		return (obj instanceof Functionality) && ((Functionality) obj).id.equals(this.id);
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[Functionality id=" + this.id + ", label= " + this.label + "]\n";
	}
}
