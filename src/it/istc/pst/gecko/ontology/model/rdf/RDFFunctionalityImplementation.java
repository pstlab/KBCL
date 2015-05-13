package it.istc.pst.gecko.ontology.model.rdf;

import it.istc.pst.gecko.ontology.model.Functionality;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class RDFFunctionalityImplementation 
{
	private RDFFunctionality funcionality;
	private List<RDFTemporalConstraint> constraints;
	private Map<RDFState, List<RDFTemporalConstraint>> restrictions;
	
	/**
	 * 
	 * @param func
	 */
	protected RDFFunctionalityImplementation(RDFFunctionality func) {
		this.funcionality = func;
		this.constraints = new ArrayList<>();
		this.restrictions = new HashMap<>();
	}
	
	/**
	 * 
	 * @return
	 */
	public Functionality getFuncionality() {
		return funcionality;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<RDFTemporalConstraint> getConstraints() {
		return new ArrayList<RDFTemporalConstraint>(constraints);
	}
	
	/**
	 * 
	 * @param c
	 */
	public void addConstraint(RDFTemporalConstraint c) {
		this.constraints.add(c);
	}
	
	/**
	 * 
	 * @return
	 */
	public Map<RDFState, List<RDFTemporalConstraint>> getRestrictions() {
		return new HashMap<>(this.restrictions);
	}
	
	/**
	 * 
	 * @param c
	 */
	public void addRestriction(RDFState s, RDFTemporalConstraint c) {
		// check map
		if (!this.restrictions.containsKey(s)) {
			// initialize
			this.restrictions.put(s, new ArrayList<RDFTemporalConstraint>());
		}
		// add restriction constraint
		this.restrictions.get(s).add(c);
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[FunctionalityImplementation\n"
				+ "\t- constraints= " + this.constraints + "\n"
				+ "\t- restrictions= " + this.restrictions + "]\n";
	}
}
