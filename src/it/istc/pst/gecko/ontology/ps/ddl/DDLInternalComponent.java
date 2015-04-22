package it.istc.pst.gecko.ontology.ps.ddl;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class DDLInternalComponent extends DDLComponent 
{
	/**
	 * 
	 * @param name
	 */
	public DDLInternalComponent(String name) {
		super(name, DDLComponentType.INTERNAL_COMPONENT);
	}
	
	/**
	 * 
	 */
	@Override
	public void addValue(DDLValue value) {
		// create successor set
		Set<DDLValue> successors = new HashSet<DDLValue>();
		for (DDLValue succ : this.values.keySet()) {
			// add value to successors
			successors.add(succ);
			// add transition to the new value
			this.values.get(succ).add(value);
		}
		// update state machine
		this.values.put(value, successors);
	}

	/**
	 * 
	 * @param vi
	 * @param vj
	 */
	public void removeConstraintBetweenValues(DDLValue vi, DDLValue vj) {
		if (this.values.containsKey(vi)) { 
			this.values.get(vi).remove(vj);
		}
		if (this.values.containsKey(vj)) {
			this.values.get(vj).remove(vi);
		}
	}
}
