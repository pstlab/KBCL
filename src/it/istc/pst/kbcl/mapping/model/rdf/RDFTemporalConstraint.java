package it.istc.pst.kbcl.mapping.model.rdf;


/**
 * 
 * @author alessandroumbrico
 *
 */
public class RDFTemporalConstraint 
{
	private String id;
	private String label;
	private RDFState target;
	
	/**
	 * 
	 * @param id
	 * @param label
	 * @param target
	 */
	protected RDFTemporalConstraint(String id, String label, RDFState target) {
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
	public RDFState getTarget() {
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
		return (obj instanceof RDFTemporalConstraint) && 
				((RDFTemporalConstraint) obj).id.equals(this.id) &&
				((RDFTemporalConstraint) obj).target.equals(this.target);
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
