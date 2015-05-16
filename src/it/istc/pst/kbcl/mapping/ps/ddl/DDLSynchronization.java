package it.istc.pst.kbcl.mapping.ps.ddl;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class DDLSynchronization 
{
	private DDLValue reference;
	private List<DDLConstraint> constraints;
	
	/**
	 * 
	 * @param reference
	 */
	protected DDLSynchronization(DDLValue reference) {
		this.reference = reference;
		// lazy approach
		this.constraints = new ArrayList<DDLConstraint>();
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
	public List<DDLConstraint> getConstraints() {
		return new ArrayList<DDLConstraint>(this.constraints);
	}
	
//	/**
//	 * 
//	 * @param target
//	 * @param label
//	 */
//	public void addConstraint(DDLValue target, String label) {
//		// crate and add constraint
//		this.constraints.add(new DDLConstraint(
//				this.reference, 
//				target, 
//				DDLTemporalConstraintType.getType(label)));
//	}
	
	/**
	 * 
	 * @param cons
	 */
	public void addConstraint(DDLConstraint cons) {
		this.constraints.add(cons);
	}
	
	/**
	 * 
	 */
	@Override
	public int hashCode() {
		return this.reference.hashCode();
	}
	
	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof DDLSynchronization)) {
			throw new RuntimeException("Uncomparable objects");
		}
		DDLSynchronization other = (DDLSynchronization) obj;
		return this.reference.equals(other.getReference());
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[DDLSynchronization reference= " + this.reference + "\n"
				+ "\tconstraints= " + this.constraints + "]\n";
	}
}
