package it.istc.pst.gecko.ontology.model;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class TemporalConstraint 
{
	private String id;
	private String label;
	private State target;
	
	/**
	 * 
	 * @param id
	 * @param label
	 * @param target
	 */
	public TemporalConstraint(String id, String label, State target) {
		this.id = id;
		this.label = label;
		this.target = target;
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
	public State getTarget() {
		return target;
	}
	
	/**
	 * 
	 */
	@Override
	public int hashCode() {
		return this.id.hashCode() + this.target.hashCode();
	}
	
	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof TemporalConstraint)) {
			throw new RuntimeException("Uncomparable objects");
		}
		TemporalConstraint other = (TemporalConstraint) obj;
		return this.id.equals(other.getId()) && this.target.equals(other.getTarget());
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[TemporalConstraint id= " + this.id + "\n"
				+ "\tlabel= " + this.label + "\n"
				+ "\ttarget= " + this.target + "]\n";
	}
}
