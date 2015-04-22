package it.istc.pst.gecko.ontology.kb;

import it.istc.pst.gecko.ontology.kb.rdf.exception.RDFResourceNotFoundException;
import it.istc.pst.gecko.ontology.model.Component;
import it.istc.pst.gecko.ontology.model.ExternalComponent;
import it.istc.pst.gecko.ontology.model.Restriction;
import it.istc.pst.gecko.ontology.model.State;

import java.util.List;

/**
 * 
 * @author alessandroumbrico
 *
 */
public interface ComponentDAO 
{
	/**
	 * 
	 * @return
	 */
	public List<Component> retrieveAllInternalComponents();
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws RDFResourceNotFoundException
	 */
	public Component retrieveInternalComponentById(String id) throws RDFResourceNotFoundException;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws RDFResourceNotFoundException
	 */
	public ExternalComponent retrieveExternalComponentById(String id) throws RDFResourceNotFoundException;
	
	
	/**
	 * 
	 * @param comp
	 * @return
	 */
	public List<State> retrieveComponentStates(Component comp);
	

	/**
	 * 
	 * @param comp
	 * @return
	 */
	public Component retrieveConnectedComponent(ExternalComponent comp);
	
	/**
	 * 
	 * @param comp
	 * @return
	 */
	public List<Restriction> retrieveComponentRestrictions(Component comp);
	
	/**
	 * 
	 * @param restriction
	 * @return
	 */
	public List<State> retrieveRestrictionStates(Restriction restriction);
}
