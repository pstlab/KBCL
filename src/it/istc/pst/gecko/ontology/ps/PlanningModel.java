package it.istc.pst.gecko.ontology.ps;

import it.istc.pst.gecko.ontology.model.rdf.RDFKnowledgeBaseFacade;

/**
 * 
 * @author alessandroumbrico
 *
 */
public abstract class PlanningModel 
{
	protected String name;
	protected long horizon;
	protected RDFKnowledgeBaseFacade facade;
	
	/**
	 * 
	 * @param name
	 * @param horizon
	 */
	protected PlanningModel(String name, long horizon) {
		this.name = name;
		this.horizon = horizon;
		// create KB facade
		this.facade = RDFKnowledgeBaseFacade.getSingletonInstance();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return
	 */
	public long getHorizon() {
		return horizon;
	}
	
	/**
	 * 
	 * @return
	 */
	public abstract String getPlanningDomainDescription();
	
	/**
	 * 
	 * @return
	 */
	public abstract String getPlanningProblemDescription();
}
