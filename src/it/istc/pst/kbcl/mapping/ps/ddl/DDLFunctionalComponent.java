package it.istc.pst.kbcl.mapping.ps.ddl;

import java.util.HashSet;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class DDLFunctionalComponent extends DDLComponent 
{
	private DDLValue defaultValue;
	
	/**
	 * 
	 * @param name
	 * @param horizon
	 */
	public DDLFunctionalComponent(String name, long horizon) {
		super(name, DDLComponentType.FUNCTIONAL_COMPONENT);
		
		// create default value
		this.defaultValue = new DDLValue("Idle", 1, horizon, this);
		// initialize state machine
		this.values.put(this.defaultValue, new HashSet<DDLValue>());
	}
	
	/**
	 * 
	 */
	@Override
	public void addValue(DDLValue value) {
		// check if value exists
		if (!this.values.containsKey(value)) {
			// add entry
			this.values.put(value, new HashSet<DDLValue>());
		}
		// add default constraints
		this.values.get(this.defaultValue).add(value);
		this.values.get(value).add(defaultValue);
	}
}
