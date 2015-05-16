package it.istc.pst.kbcl.model;

/**
 * 
 * @author alessandroumbrico
 *
 */
public abstract class FunctionalityType 
{
	private String id;
	private String label;
	
	/**
	 * 
	 * @param id
	 * @param label
	 */
	protected FunctionalityType(String id, String label) {
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
		return this.label;
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
		if (!(obj instanceof FunctionalityType)) {
			throw new RuntimeException("Uncomparable objects");
		}
		
		FunctionalityType other = (FunctionalityType) obj;
		return this.id.equals(other.getId());
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[FunctionalityType id=" + this.id + ", label= " + this.label + "]\n"; 
	}
}
