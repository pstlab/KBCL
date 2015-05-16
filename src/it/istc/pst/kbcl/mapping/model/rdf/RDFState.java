package it.istc.pst.kbcl.mapping.model.rdf;


/**
 * 
 * @author alessandroumbrico
 *
 */
public class RDFState 
{
	private String id;
	private String label;
	private String dmin;
	private String dmax;
	private RDFComponent component;
	
	/**
	 * 
	 * @param id
	 * @param label
	 * @param dmin
	 * @param dmax
	 * @param component
	 */
	protected RDFState(String id, String label, String dmin, String dmax, RDFComponent component) {
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
	public RDFComponent getComponent() {
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
		return (obj instanceof RDFState) && ((RDFState) obj).id.equals(this.id) && ((RDFState) obj).component.equals(this.component);
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
