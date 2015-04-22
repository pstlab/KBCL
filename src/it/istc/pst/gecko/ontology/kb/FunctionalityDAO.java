package it.istc.pst.gecko.ontology.kb;

import it.istc.pst.gecko.ontology.kb.rdf.exception.RDFResourceNotFoundException;
import it.istc.pst.gecko.ontology.model.Functionality;
import it.istc.pst.gecko.ontology.model.FunctionalityImplementation;
import it.istc.pst.gecko.ontology.model.FunctionalityType;

import java.util.List;

/**
 * 
 * @author alessandroumbrico
 *
 */
public interface FunctionalityDAO 
{
	/**
	 * 
	 * @return
	 */
	public List<FunctionalityType> retrieveAllFunctionalityTypes();
	
	/**
	 * 
	 * @return
	 */
	public List<Functionality> retrieveAllFunctionalities();

	/**
	 * 
	 * @param type
	 * @return
	 */
	public List<Functionality> retrieveFunctionalitiesByType(FunctionalityType type);

	/**
	 * 
	 * @param id
	 * @return
	 * @throws RDFResourceNotFoundException
	 */
	public Functionality retrieveFunctionalityById(String id) throws RDFResourceNotFoundException;
	
	/**
	 * 
	 * @param f
	 * @return
	 */
	public List<FunctionalityImplementation> retrieveFunctionalityImplementations(Functionality f);
}
