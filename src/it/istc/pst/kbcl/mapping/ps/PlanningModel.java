package it.istc.pst.kbcl.mapping.ps;


/**
 * 
 * @author alessandroumbrico
 *
 */
public abstract class PlanningModel 
{
	protected String name;
	protected long horizon;
	
	/**
	 * 
	 * @param name
	 * @param horizon
	 */
	protected PlanningModel(String name, long horizon) {
		this.name = name;
		this.horizon = horizon;
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
