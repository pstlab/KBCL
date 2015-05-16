package it.istc.pst.gecko.ontology.model;

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
