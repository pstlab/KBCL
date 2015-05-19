package it.istc.pst.kbcl.mapping.ps;


/**
 * 
 * @author alessandroumbrico
 *
 */
public abstract class PlanningManager 
{
	protected long horizon;
	
	/**
	 * 
	 * @param name
	 * @param horizon
	 */
	protected PlanningManager(long horizon) {
		this.horizon = horizon;
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
	public abstract String getModelName();
	
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
