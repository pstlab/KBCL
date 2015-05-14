package it.istc.pst.gecko.ontology.model;


/**
 * 
 * @author alessandroumbrico
 *
 */
public abstract class Agent 
{
	protected String id;
	protected String label;
	protected AgentType type;

	/**
	 * 
	 * @param id
	 * @param label
	 * @param type
	 */
	protected Agent(String id, String label, AgentType type) {
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
	public AgentType getType() {
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
		return (obj instanceof Agent) && ((Agent) obj).id.equals(this.id);
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[Agent id=" + this.id + "\n"
				+ "\tlabel= " + this.label +"\n"
				+ "\ttype= " + this.type + "]\n";
	}
}
