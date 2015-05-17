package it.istc.pst.kbcl.mapping.model.rdf;

import it.istc.pst.kbcl.mapping.kb.rdf.RDFAgentDAO;
import it.istc.pst.kbcl.mapping.kb.rdf.RDFDatasetManager;
import it.istc.pst.kbcl.mapping.kb.rdf.RDFMappingKnowledgeBaseFactory;
import it.istc.pst.kbcl.mapping.kb.rdf.exception.RDFPropertyNotFoundException;
import it.istc.pst.kbcl.mapping.kb.rdf.exception.RDFResourceNotFoundException;
import it.istc.pst.kbcl.ontology.model.owl.OWLAgent;
import it.istc.pst.kbcl.ontology.model.owl.OWLElement;
import it.istc.pst.kbcl.ontology.model.owl.OWLFunctionality;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class RDFMappingKnowledgeBaseFacade 
{
	private RDFMappingKnowledgeBaseFactory factory;
	private RDFAgent agent;

	/**
	 * 
	 * @param data
	 * @throws RDFResourceNotFoundException
	 * @throws RDFPropertyNotFoundException
	 */
	public RDFMappingKnowledgeBaseFacade(OWLAgent data) 
			throws RDFResourceNotFoundException, RDFPropertyNotFoundException
	{
		// create factory
		this.factory = new RDFMappingKnowledgeBaseFactory();
		RDFAgentDAO dao = this.factory.createAgentDAO();
		// create agent elements
		this.agent = dao.createAgent(data.getLabel());
		
		// insert agent's functionalities
		for (OWLFunctionality f : data.getFunctionalities()) {
			dao.addFunctionality(this.agent.getLabel(), f.getLabel());
		}
		
		for (OWLElement e : data.getComponents()) {
			dao.addComponent(this.agent.getLabel(), e.getLabel());
		}
		
		// insert agent's neighbors
		for (OWLElement n : data.getNeighbors()) {
			dao.addNeighbor(this.agent.getLabel(), n.getLabel());
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public RDFAgent getAgent() {
		return this.agent;
	}

	/**
	 * 
	 */
	public void close() {
		this.factory = null;
		this.agent = null;
		// close data manager
		RDFDatasetManager dataset = RDFDatasetManager.getSingletonInstance();
		dataset.close();
	}
}
