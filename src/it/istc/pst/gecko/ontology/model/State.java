package it.istc.pst.gecko.ontology.model;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class State 
{
	private String id;
	private String label;
	private String dmin;
	private String dmax;
	private Component component;
	
	/**
	 * 
	 * @param id
	 * @param label
	 * @param dmin
	 * @param dmax
	 * @param component
	 */
	public State(String id, String label, String dmin, String dmax, Component component) {
		this.id = id;
		this.label = label;
		this.component = component;
		this.dmin = dmin;
		this.dmax = dmax;
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
	public String getMinDuration() {
		return dmin;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getMaxDuration() {
		return dmax;
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
	public Component getComponent() {
		return component;
	}
	
	/**
	 * 
	 */
	@Override
	public int hashCode() {
		return this.id.hashCode() + this.component.hashCode();
	}
	
	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof State)) {
			throw new RuntimeException("Uncomparable objects");
		}
		State other = (State) obj;
		return this.id.equals(other.getId()) && this.component.equals(other.getComponent());
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[State id=" + this.id + "\n"
				+ "\tlabel= " + this.label +"]\n";
	}
}
