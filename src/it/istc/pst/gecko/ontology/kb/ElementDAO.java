package it.istc.pst.gecko.ontology.kb;

import it.istc.pst.gecko.ontology.kb.exception.ResourceNotFoundException;
import it.istc.pst.gecko.ontology.model.Element;
import it.istc.pst.gecko.ontology.model.ElementType;

import java.util.List;

/**
 * 
 * @author alessandroumbrico
 *
 */
public interface ElementDAO {

	/**
	 * 
	 * @return
	 */
	public List<ElementType> retrieveAllElementTypes();
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public ElementType retrieveElementTypeById(String id) 
			throws ResourceNotFoundException;
	
	/**
	 * 
	 * @return
	 */
	public List<Element> retrieveAllElements();
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	public List<Element> retrieveElementByType(ElementType type);
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public Element retrieveElementById(String id) 
			throws ResourceNotFoundException;
	
	/**
	 * 
	 * @param name
	 * @param type
	 * @return
	 */
	public Element createElement(String name, ElementType type);
}
