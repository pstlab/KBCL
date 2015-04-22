package it.istc.pst.gecko.ontology.model;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class AgentType 
{
	private String id;
	private String label;
	
	/**
	 * 
	 * @param id
	 * @param label
	 */
	public AgentType(String id, String label) {
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
		if (!(obj instanceof AgentType)) {
			throw new RuntimeException("Uncomparable objects");
		}
		
		AgentType other = (AgentType) obj;
		return this.id.equals(other.getId());
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[AgentType id=" + this.id +"\n"
				+ "\tlabel= " + this.label + "]\n";
	}
}
