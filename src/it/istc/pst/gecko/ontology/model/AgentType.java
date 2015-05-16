package it.istc.pst.gecko.ontology.model;

/**
 * 
 * @author alessandroumbrico
 *
 */
public abstract class AgentType 
{
	private String id;
	private String label;
	
	/**
	 * 
	 * @param id
	 * @param label
	 */
	protected AgentType(String id, String label) {
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
		return (obj instanceof AgentType) && ((AgentType) obj).id.equals(this.id);
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[AgentType id=" + this.id +", label= " + this.label + "]\n";
	}
}
