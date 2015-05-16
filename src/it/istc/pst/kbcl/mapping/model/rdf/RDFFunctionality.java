package it.istc.pst.kbcl.mapping.model.rdf;

import it.istc.pst.kbcl.mapping.kb.rdf.RDFFunctionalityDAO;
import it.istc.pst.kbcl.mapping.kb.rdf.RDFMappingKnowledgeBaseFactory;
import it.istc.pst.kbcl.model.Functionality;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class RDFFunctionality extends Functionality 
{
	protected String dmin;			
	protected String dmax;
	protected List<RDFFunctionalityImplementation> implementations;
	
	protected RDFFunctionalityDAO dao;
	
	/**
	 * 
	 * @param id
	 * @param label
	 * @param dmin
	 * @param dmax
	 * @param type
	 */
	protected RDFFunctionality(String id, String label, String dmin, String dmax, RDFFunctionalityType type) {
		super(id, label, type);
		this.dmin = dmin;
		this.dmax = dmax;
		
		// lazy approach
		this.implementations = null;
		
		// get DAO
		RDFMappingKnowledgeBaseFactory factory = new RDFMappingKnowledgeBaseFactory();
		this.dao = factory.createFunctionalityDAO();
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
	public List<RDFFunctionalityImplementation> getImplementations() {
		// check list
		if (this.implementations == null) {
			// load data
			this.implementations = this.dao.retrieveFunctionalityImplementations(this);
		}
		return new ArrayList<RDFFunctionalityImplementation>(this.implementations);
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
