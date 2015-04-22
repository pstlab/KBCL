package it.istc.pst.gecko.ontology.ps.ddl;

import java.util.HashSet;
import java.util.Set;


/**
 * 
 * @author alessandroumbrico
 *
 */
public class DDLExternalComponent extends DDLComponent 
{
	/**
	 * 
	 * @param name
	 */
	public DDLExternalComponent(String name) {
		super(name, DDLComponentType.EXTERNAL_COMPONENT);
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
	 */
	@Override
	public boolean isExternal() {
		return true;
	}
}
