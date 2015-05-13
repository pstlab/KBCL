package it.istc.pst.gecko.ontology.model;

import it.istc.pst.gecko.ontology.kb.FunctionalityDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author alessandroumbrico
 *
 */
public abstract class Functionality 
{
	private String id;
	private String label;
	private FunctionalityType type;
	private String dmin;			
	private String dmax;
	private List<FunctionalityImplementation> implementations;
	
	protected FunctionalityDAO dao;
	
	/**
	 * 
	 * @param id
	 * @param label
	 * @param dmin
	 * @param dmax
	 * @param type
	 */
	protected Functionality(String id, String label, String dmin, String dmax, FunctionalityType type) {
		this.id = id;
		this.label = label;
		this.type = type;
		this.dmin = dmin;
		this.dmax = dmax;
		
		// lazy approach
		this.implementations = null;
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
		return this.label;
	}
	
	/**
	 * 
	 * @return
	 */
	public FunctionalityType getType() {
		return this.type;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getMinDuration() {
		return this.dmin;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getMaxDuration() {
		return this.dmax;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<FunctionalityImplementation> getImplementations() {
		// check list
		if (this.implementations == null) {
			// load data
			this.implementations = this.dao.retrieveFunctionalityImplementations(this);
		}
		return new ArrayList<FunctionalityImplementation>(this.implementations);
	}
	
	/**
	 * 
	 */
	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
	
	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Functionality)) {
			throw new RuntimeException("Uncomparable objects");
		}
		
		Functionality other = (Functionality) obj;
		return this.id.equals(other.getId());
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[Functionality id=" + this.id + "\n"
				+ "\tlabel= " + this.label + "\n"
				+ "\tduration= [" + this.dmin + ", " + this.dmax + "]\n"
				+ "\ttype= " + this.type + "\n"
				+ "\timplementations= " + this.implementations + "]\n";
	}
}
