package it.istc.pst.kbcl.mapping.ps.ddl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author alessandroumbrico
 *
 */
public abstract class DDLComponent 
{
	protected String name;
	protected String timeline;
	protected DDLComponentType type;
	protected Map<DDLValue, Set<DDLValue>> values;		// state machine
	
	/**
	 * 
	 * @param name
	 * @param type
	 */
	protected DDLComponent(String name, DDLComponentType type) {
		this.name = name;
		this.timeline = name + "_timeline";
		this.type = type;
		// initialize state machine
		this.values = new HashMap<DDLValue, Set<DDLValue>>();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getTimeline() {
		return this.timeline;
	}
	
	/**
	 * 
	 * @return
	 */
	public DDLComponentType getType() {
		return this.type;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<DDLValue> getValues() {
		return new ArrayList<DDLValue>(this.values.keySet());
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isExternal() {
		return false;
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	public List<DDLValue> getSuccessors(DDLValue value) {
		return new ArrayList<DDLValue>(this.values.get(value));
	}
	
	/**
	 * 
	 * @param value
	 */
	public abstract void addValue(DDLValue value);
	
	/**
	 * 
	 */
	@Override
	public int hashCode() {
		return this.name.hashCode() + this.timeline.hashCode();
	}
	
	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof DDLComponent)) { 
			throw new RuntimeException("Uncomparable objects");
		}
		DDLComponent other = (DDLComponent) obj;
		return this.name.equals(other.getName()) && this.timeline.equals(other.getTimeline());
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[DDLComponent name= " + this.name + "\n"
				+ "\ttype= " + this.type + "\n"
				+ "\ttimeline= " + this.timeline + "\n"
				+ "\tvalues= " + this.values + "]";
	}

	/**
	 * 
	 * @return
	 */
	public DDLValue getInitialValue() {
		// get initial value
		DDLValue val = null;
		// check component name
		for (DDLValue v : this.values.keySet()) {
			// check value 
			// FIXME - RENDERE PARAMETRICO
			if (v.getValue().contains("Still") ||
					v.getValue().contains("Idle") ||
					v.getValue().contains("Down") || 
					(v.getValue().contains("Available") && !(v.getValue().contains("not") || v.getValue().contains("Not")))) 
			{
				// found
				val = v;
			}
		}
		// get initial value
		return val;
	}
}
