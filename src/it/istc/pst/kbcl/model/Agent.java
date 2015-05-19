package it.istc.pst.kbcl.model;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author alessandroumbrico
 *
 */
public abstract class Agent implements EventPublisher 
{
	protected String id;
	protected String label;
	protected AgentType type;
	
	protected List<EventObserver> observers;

	/**
	 * 
	 * @param id
	 * @param label
	 * @param type
	 */
	protected Agent(String id, String label, AgentType type) {
		this.id = id;
		this.label = label;
		this.type = type;
		this.observers = new ArrayList<>();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * 
	 * @return
	 */
	public AgentType getType() {
		return type;
	}
	
	/**
	 * 
	 * @param observer
	 */
	@Override
	public void subscribe(EventObserver observer) {
		// add observer
		this.observers.add(observer);
	}
	
	/**
	 * 
	 */
	@Override
	public void unSubscribe(EventObserver observer) {
		if (this.observers.contains(observer)) {
			// remove 
			this.observers.remove(observer);
		}
	}
	
	/**
	 * 
	 */
	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
	
	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Agent) && ((Agent) obj).id.equals(this.id);
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[Agent id=" + this.id + ", label= " + this.label +"]\n";
	}
}
