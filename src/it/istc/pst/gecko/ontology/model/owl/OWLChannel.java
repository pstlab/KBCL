package it.istc.pst.gecko.ontology.model.owl;

import it.istc.pst.gecko.ontology.kb.owl.OWLDatasetManager;
import it.istc.pst.gecko.ontology.kb.owl.OWLInstance;
import it.istc.pst.gecko.ontology.kb.owl.exception.OWLIndividualNotFoundException;
import it.istc.pst.gecko.ontology.kb.owl.exception.OWLPropertyNotFoundException;

import java.util.List;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class OWLChannel extends OWLFunctionality 
{
	private OWLPort input;
	private OWLPort output;
	
	/**
	 * 
	 * @param id
	 * @param label
	 * @param type
	 */
	protected OWLChannel(String id, String label, OWLFunctionalityType type) {
		super(id, label, type);
		// lazy approach
		this.input = null;
		this.output = null;
	}
	
	/**
	 * 
	 * @return
	 */
	public OWLPort getInput() {
		if (this.input == null) {
			try {
				// load data from data-set
				List<OWLInstance> ps = this.dataset
						.retrieveAllInstancesRelatedByProperty(this.label, 
								OWLDatasetManager.PROPERTY_LABEL_HAS_INPUT_PORT);
				
				// check size
				if (!ps.isEmpty()) {
					// get input port
					OWLInstance p = ps.get(0);
					// TODO : create input port
					
					if (ps.size() > 1) {
						System.err.println("... Warning more than one input port found found for " + this.id);
					}
				}
				else {
					System.err.println("... Warning no input port found for " +  this.id);
				}
			}
			catch (OWLIndividualNotFoundException | OWLPropertyNotFoundException ex) {
				this.input = null;
				System.err.println(ex.getMessage());
			}
		}
		
		// get input port
		return this.input;
	}

	
	/**
	 * 
	 * @return
	 */
	public OWLPort getOutput() {
		if (this.output == null) {
			try {
				// load data from data-set
				List<OWLInstance> ps = this.dataset
						.retrieveAllInstancesRelatedByProperty(this.label, 
								OWLDatasetManager.PROPERTY_LABEL_HAS_OUTPUT_PORT);
				
				// check size
				if (!ps.isEmpty()) {
					// get input port
					OWLInstance p = ps.get(0);
					// TODO : create input port
					
					if (ps.size() > 1) {
						System.err.println("... Warning more than one output port foudn found for " + this.id);
					}
				}
				else {
					System.err.println("... Warning no output port found for " +  this.id);
				}
			}
			catch (OWLIndividualNotFoundException | OWLPropertyNotFoundException ex) {
				this.input = null;
				System.err.println(ex.getMessage());
			}
		}
		
		// get output port
		return this.output;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[Channel id=" + this.id + "\n"
				+ "\tlabel= " + this.label + "\n"
				+ "\ttype= " + this.type + "\n"
				+ "\tinputPort= " + this.input + "\n"
				+ "\toutputPort= " + this.output +"]\n";
	}
}
