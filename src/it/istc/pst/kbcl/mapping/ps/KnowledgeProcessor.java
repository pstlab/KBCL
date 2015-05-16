package it.istc.pst.kbcl.mapping.ps;

import java.util.List;

/**
 * 
 * @author alessandroumbrico
 *
 */
public interface KnowledgeProcessor <F, I, E, S> 
{
	/**
	 * 
	 * @return
	 */
	public List<F> extractFunctionalComponents();
	
	/**
	 * 
	 * @return
	 */
	public List<I> extractInternalComponents();
	
	/**
	 * 
	 * @return
	 */
	public List<E> extractExternalComponents();
	
	/**
	 * 
	 * @return
	 */
	public List<S> extractSynchronizations();
}
