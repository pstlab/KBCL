package it.istc.pst.gecko.ontology.model.owl;

import it.istc.pst.gecko.ontology.kb.owl.OWLDatasetManager;
import it.istc.pst.gecko.ontology.kb.owl.OWLInstance;
import it.istc.pst.gecko.ontology.kb.owl.exception.OWLIndividualNotFoundException;
import it.istc.pst.gecko.ontology.kb.owl.exception.OWLPropertyNotFoundException;
import it.istc.pst.gecko.ontology.model.Event;
import it.istc.pst.gecko.ontology.model.EventObserver;
import it.istc.pst.gecko.ontology.model.EventPublisher;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class OWLPort extends OWLElement implements EventPublisher 
{
	private OWLElement neighbor;
	private List<EventObserver> observers;
	
	/**
	 * 
	 * @param id
	 * @param label
	 * @param type
	 */
	protected OWLPort(String id, String label, OWLElementType type) {
		super(id, label, type);
		this.observers = new ArrayList<>();
		try {
			// load from data-set
			List<OWLInstance> ns = this.dataset
					.retrieveAllInstancesRelatedByProperty(this.label, 
							OWLDatasetManager.PROPERTY_LABEL_CONNECT);
			
			// check list
			if (!ns.isEmpty()) {
				// get connected neighbor
				OWLInstance n = ns.get(0);
				this.neighbor = new OWLElement(n.getUrl(), n.getLabel(),
						new OWLElementType(n.getUrl(), n.getLabel()));
				
				// check size
				if (ns.size() > 1) {
					System.err.println("... Warning more than one connected neighbor found for port " + this.id);
				}
			}
			else { 
				System.err.println("... Warning no connected neighbor found for port " + this.id);
			}
		}
		catch (OWLIndividualNotFoundException | OWLPropertyNotFoundException ex) {
			this.neighbor = null;
			System.err.println(ex.getMessage());
		}
	}
	
	/**
	 * 
	 * @param observer
	 */
	@Override
	public void subscribe(EventObserver observer) {
		this.observers.add(observer);
	}
	
	/**
	 * 
	 */
	@Override
	public void unSubscribe(EventObserver observer) {
		if (this.observers.contains(observer)) {
			this.observers.remove(observer);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public OWLElement getConnectedNeighbor() {
		// get neighbor
		return this.neighbor;
	}
	
	/**
	 * 
	 * @param neighbor
	 */
	public void connectTo(OWLElement neighbor) {
		try {
			// save relation
			this.dataset.assertStatement(this.label, OWLDatasetManager.PROPERTY_LABEL_CONNECT, neighbor.getLabel());
			// update
			this.neighbor = neighbor;
		}
		catch (OWLIndividualNotFoundException | OWLPropertyNotFoundException ex) {
			this.neighbor = null;
			System.err.println(ex.getMessage());
		}
		finally {
			// notify observers
			for (EventObserver o : this.observers) {
				// notify
				o.update(Event.PORT_CONNECTION_UPDATE_EVENT);
			}
		}
	}
	
	/**
	 * 
	 */
	public void disconnect() {
		try {
			if (this.neighbor != null) {
				// remove connection property
				this.dataset.removeStatement(this.label, OWLDatasetManager.PROPERTY_LABEL_CONNECT, this.neighbor.getLabel());
				this.neighbor = null;
			}
		}
		catch (OWLPropertyNotFoundException | OWLIndividualNotFoundException ex) {
			System.err.println(ex.getMessage());
		}
		finally {
			// notify observers
			for (EventObserver o : this.observers) {
				// notify
				o.update(Event.PORT_CONNECTION_UPDATE_EVENT);
			}
		}
	}
}
