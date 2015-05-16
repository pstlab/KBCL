package it.istc.pst.kbcl.model;

public interface EventPublisher {
	
	/**
	 * 
	 * @param observer
	 */
	public void subscribe(EventObserver observer);
	
	/**
	 * 
	 * @param observer
	 */
	public void unSubscribe(EventObserver observer);
}
