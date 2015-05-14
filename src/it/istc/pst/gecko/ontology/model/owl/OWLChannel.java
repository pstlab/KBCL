package it.istc.pst.gecko.ontology.model.owl;

import it.istc.pst.gecko.ontology.kb.exception.PropertyNotFoundException;

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
				// load data
				this.input = this.dao.retrieveChannelInputPort(this);
			}
			catch (PropertyNotFoundException ex) {
				this.input = null;
				System.err.println(ex.getLocalizedMessage());
			}
		}
		
		// get input port
		return this.input;
	}

	/**
	 * 
	 */
	public void setInput(OWLPort port) {
		// save relation
		this.dao.setInputPortToChannel(port, this);
		// update
		this.input = port;
	}
	
	/**
	 * 
	 * @return
	 */
	public OWLPort getOutput() {
		if (this.output == null) {
			try {
				// load data
				this.output = this.dao.retrieveChannelOutputPort(this);
			}
			catch (PropertyNotFoundException ex) {
				this.output = null;
				System.err.println(ex.getLocalizedMessage());
			}
		}
		
		// get output port
		return this.output;
	}
	
	/**
	 * 
	 * @param port
	 */
	public void setOutput(OWLPort port) {
		// save relation
		this.dao.setOutputPortToChannel(port, this);
		// update
		this.output = port;
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
