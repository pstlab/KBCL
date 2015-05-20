package it.istc.pst.kbcl.mapping.ps.ddl;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class DDLConstraint 
{
	private DDLValue reference;
	private DDLValue target;
	private DDLTemporalConstraintType constraint;
	
	/**
	 * 
	 * @param reference
	 * @param target
	 * @param constraint
	 */
	public DDLConstraint(DDLValue reference, DDLValue target, DDLTemporalConstraintType constraint) {
		this.reference = reference;
		this.target = target;
		this.constraint = constraint;
	}
	
	/**
	 * 
	 * @return
	 */
	public DDLValue getReference() {
		return reference;
	}
	
	/**
	 * 
	 * @return
	 */
	public DDLValue getTarget() {
		return target;
	}
	
	/**
	 * 
	 * @return
	 */
	public DDLTemporalConstraintType getConstraint() {
		return constraint;
	}
	
	/**
	 * 
	 */
	@Override
	public int hashCode() {
		return this.reference.hashCode() + this.target.hashCode() + this.constraint.getLabel().hashCode();
	}
	
	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof DDLConstraint)) {
			throw new RuntimeException("Uncomparable objects");
		}
		DDLConstraint other = (DDLConstraint) obj;
		return this.reference.equals(other.getReference()) && 
				this.target.equals(other.getTarget()) && 
				this.constraint.equals(other.getConstraint());
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[DDLConstraint type= " + this.constraint + "\n"
				+ "\ttarget= " + this.target + "]\n";
	}
}
