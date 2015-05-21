package it.istc.pst.kbcl.mapping.ps;


/**
 * 
 * @author alessandroumbrico
 *
 */
public interface PlanningManager 
{
//	protected long horizon;
//	
//	/**
//	 * 
//	 * @param name
//	 * @param horizon
//	 */
//	protected PlanningManager() {
//		}
//	
//	/**
//	 * 
//	 * @return
//	 */
//	public long getHorizon() {
//		return horizon;
//	}
	
	/**
	 * 
	 * @return
	 */
	public String getModelName();
	
	/**
	 * 
	 * @return
	 */
	public String getPlanningDomainDescription();
	
	/**
	 * 
	 * @return
	 */
	public String getPlanningProblemDescription();
}
