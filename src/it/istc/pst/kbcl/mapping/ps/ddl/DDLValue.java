package it.istc.pst.kbcl.mapping.ps.ddl;


/**
 * 
 * @author alessandroumbrico
 *
 */
public class DDLValue 
{
	private String value;
	private long dmin;
	private long dmax;
	private DDLComponent component;
	
	/**
	 * 
	 * @param value
	 * @param dmin
	 * @param dmax
	 * @param component
	 */
	protected DDLValue(String value, long dmin, long dmax, DDLComponent component) {
		this.value = value;
		this.dmin = dmin;
		this.dmax = dmax;
		this.component = component;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * 
	 * @return
	 */
	public long getDmin() {
		return dmin;
	}
	
	/**
	 * 
	 * @return
	 */
	public long getDmax() {
		return dmax;
	}
	
	/**
	 * 
	 * @return
	 */
	public DDLComponent getComponent() {
		return component;
	}
	
	/**
	 * 
	 */
	@Override
	public int hashCode() {
		return this.value.hashCode() + this.component.hashCode();
	}
	
	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		// check type
		if (!(obj instanceof DDLValue)) {
			throw new RuntimeException("Uncomparable objects");
		}
		DDLValue other = (DDLValue) obj;
		return this.component.equals(other.getComponent()) && this.value.equals(other.getValue());
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[DDLValue value= " + this.value + "\n"
				+ "\tduration= [" + this.dmin +"," + this.dmax + "]]\n";
	}
}
