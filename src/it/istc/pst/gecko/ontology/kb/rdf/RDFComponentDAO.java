package it.istc.pst.gecko.ontology.kb.rdf;

import it.istc.pst.gecko.ontology.kb.ComponentDAO;
import it.istc.pst.gecko.ontology.kb.rdf.exception.RDFResourceNotFoundException;
import it.istc.pst.gecko.ontology.model.Component;
import it.istc.pst.gecko.ontology.model.ExternalComponent;
import it.istc.pst.gecko.ontology.model.Restriction;
import it.istc.pst.gecko.ontology.model.State;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class RDFComponentDAO implements ComponentDAO 
{
	private RDFDatasetManager manager;
	
	/**
	 * 
	 */
	protected RDFComponentDAO() {
		// get data set reference
		this.manager = RDFDatasetManager.getSingletonInstance();
	}
	
	/**
	 * 
	 */
	@Override
	public List<Component> retrieveAllInternalComponents() {
		// prepare data
		List<Component> components = new ArrayList<Component>();
		
		// prepare query
		String queryString = "SELECT ?comp ?label "
				+ "WHERE {"
				+ "	?type <" + RDFDatasetManager.NS_RDFS + "subClassOf>+ <" + RDFDatasetManager.NS_GECKO + "component> . "
				+ " ?comp a ?type . "
				+ " ?comp <" + RDFDatasetManager.NS_GECKO + "ID> ?label  ."
				+ "}";
		
		// execute query
		Query query = QueryFactory.create(queryString);
		// execute query and build result
		QueryExecution exec = QueryExecutionFactory.create(query, this.manager.model);
		Iterator<QuerySolution> it = exec.execSelect();
		while (it.hasNext()) {
			// get next solution
			QuerySolution sol = it.next();
			// create component
			Component comp = new Component(
					sol.get("?comp").asResource().getURI(),
					sol.get("?label").asLiteral().getLexicalForm());
			
			// add component
			components.add(comp);
		}
		
		// close executor
		exec.close();
		// get components
		return components;
	}
	
	/**
	 * 
	 */
	@Override
	public Component retrieveInternalComponentById(String id) 
			throws RDFResourceNotFoundException 
	{
		// prepare data
		Component comp = null;
		
		// prepare query
		String queryString = "SELECT ?label "
				+ "WHERE {"
				+ " <" + id + "> a ?t . "
				+ " ?t <" + RDFDatasetManager.NS_RDFS +"subClassOf>* <" + RDFDatasetManager.NS_GECKO + "component> . "
				+ " <" + id + "> <" + RDFDatasetManager.NS_GECKO + "ID> ?label . "
				
				+ "}";
		
		// execute query
		Query query = QueryFactory.create(queryString);
		// execute query and build result
		QueryExecution exec = QueryExecutionFactory.create(query, this.manager.model);
		Iterator<QuerySolution> it = exec.execSelect();
		while (it.hasNext()) {
			// next solution
			QuerySolution sol = it.next();
			// create component
			comp = new Component(
					id,
					sol.get("?label").asLiteral().getLexicalForm());
		}
		
		// close executor
		exec.close();
		// check component
		if (comp == null) {
			throw new RDFResourceNotFoundException("[RDFComponentDAO]: No Component found with ID " + id);
		}
		return comp;
	}
	
	/**
	 * 
	 */
	@Override
	public ExternalComponent retrieveExternalComponentById(String id) 
			throws RDFResourceNotFoundException 
	{
		// prepare data
		ExternalComponent comp = null;
		
		// prepare query
		String queryString = "SELECT ?label "
				+ "WHERE {"
				+ " <" + id + "> a ?t . "
				+ " ?t <" + RDFDatasetManager.NS_RDFS + "subClassOf>* <" + RDFDatasetManager.NS_GECKO+ "neighbor> . "
				+ " <" + id + "> <" + RDFDatasetManager.NS_GECKO + "ID> ?label . "
				+ "}";
		
		// execute query
		Query query = QueryFactory.create(queryString);
		// execute query and build result
		QueryExecution exec = QueryExecutionFactory.create(query, this.manager.model);
		Iterator<QuerySolution> it = exec.execSelect();
		while (it.hasNext()) {
			// next solution
			QuerySolution sol = it.next();
			// create component
			comp = new ExternalComponent(
					id,
					sol.get("?label").asLiteral().getLexicalForm());
		}
		
		// close executor
		exec.close();
		// check component
		if (comp == null) {
			throw new RDFResourceNotFoundException("[RDFComponentDAO]: No Component found with ID " + id);
		}
		return comp;
	}
	
	/**
	 * 
	 */
	@Override
	public List<State> retrieveComponentStates(Component comp) {
		// prepare data
		List<State> states = new ArrayList<State>();
		
		// prepare query
		String queryString = "SELECT ?state ?label ?dmin ?dmax "
				+ "WHERE { "
				+ " <" + comp.getId() + "> <" + RDFDatasetManager.NS_GECKO + "hasState> ?state . "
				+ " ?state <" + RDFDatasetManager.NS_GECKO + "ID> ?label . "
				+ " ?state <" + RDFDatasetManager.NS_GECKO +"minDuration> ?dmin . "
				+ " ?state <" + RDFDatasetManager.NS_GECKO + "maxDuration> ?dmax . "
				+ "}";
		
		// execute query
		Query query = QueryFactory.create(queryString);
		// execute query and build result
		QueryExecution exec = QueryExecutionFactory.create(query, this.manager.model);
		Iterator<QuerySolution> it = exec.execSelect();
		while (it.hasNext()) {
			// get next solution
			QuerySolution sol = it.next();
			// create State
			State s = new State(
					sol.get("?state").asResource().getURI(),
					sol.get("?label").asLiteral().getLexicalForm(),
					sol.get("?dmin").asLiteral().getLexicalForm(),
					sol.get("?dmax").asLiteral().getLexicalForm(),
					comp);
			
			// add state
			states.add(s);
		}
		
		// close executor
		exec.close();
		// get component's states
		return states;
	}
	
	/**
	 * 
	 * @param neighbor
	 * @return
	 */
	@Override
	public Component retrieveConnectedComponent(ExternalComponent neighbor) {
		// prepare data
		Component c = null;
		
		// prepare query
		String queryString = "SELECT ?c ?l "
				+ "WHERE { "
				+ " <" + neighbor.getId() + "> a ?type . "
				+ " ?type <" + RDFDatasetManager.NS_RDFS + "subClassOf>* <" + RDFDatasetManager.NS_GECKO + "neighbor> . "
				+ " <" + neighbor.getId() + "> <" + RDFDatasetManager.NS_GECKO + "connectedBy> ?c . "
				+ " ?c <" + RDFDatasetManager.NS_GECKO + "ID> ?l ."
				+ "}";
		
		// execute query
		Query query = QueryFactory.create(queryString);
		// execute query and build result
		QueryExecution exec = QueryExecutionFactory.create(query, this.manager.model);
		Iterator<QuerySolution> it = exec.execSelect();
		while (it.hasNext()) {
			// get next solution
			QuerySolution sol = it.next();
			// create component
			c = new Component(
					sol.get("?c").asResource().getURI(),
					sol.get("?l").asLiteral().getLexicalForm());
		}
		
		// close executor
		exec.close();
		// get component
		return c;
	}
	
	/**
	 * 
	 */
	@Override
	public List<Restriction> retrieveComponentRestrictions(Component comp) {
		// prepare data
		List<Restriction> restrictions = new ArrayList<Restriction>();
		
		// prepare query
		String queryString = "SELECT ?restriction  "
				+ "WHERE { "
				+ " <" + comp.getId() + "> <" + RDFDatasetManager.NS_GECKO + "hasRestriction> ?restriction . "
				+ " ?restriction a <" + RDFDatasetManager.NS_GECKO + "restriction> . "
				+ "}";
		
		// execute query
		Query query = QueryFactory.create(queryString);
		// execute query and build result
		QueryExecution exec = QueryExecutionFactory.create(query, this.manager.model);
		Iterator<QuerySolution> it = exec.execSelect();
		while (it.hasNext()) {
			// get next solution
			QuerySolution sol = it.next();
			// create State
			Restriction restriction = new Restriction(
					sol.get("?restriction").asResource().getURI(),
					comp);
			
			// add state
			restrictions.add(restriction);
		}
		
		// close executor
		exec.close();
		// get component's states
		return restrictions;
	}
	
	/**
	 * 
	 */
	@Override
	public List<State> retrieveRestrictionStates(Restriction restriction) {
		// prepare data
		List<State> states = new ArrayList<State>();
		
		// prepare query
		String queryString = "SELECT ?state ?label ?dmin ?dmax "
				+ "WHERE { "
				+ " <" + restriction.getId() + "> <" + RDFDatasetManager.NS_GECKO + "hasState> ?state . "
				+ " ?state <" + RDFDatasetManager.NS_GECKO + "ID> ?label . "
				+ " ?state <" + RDFDatasetManager.NS_GECKO + "minDuration> ?dmin . "
				+ " ?state <" + RDFDatasetManager.NS_GECKO + "maxDuration> ?dmax . "
				+ "}";
		
		// execute query
		Query query = QueryFactory.create(queryString);
		// execute query and build result
		QueryExecution exec = QueryExecutionFactory.create(query, this.manager.model);
		Iterator<QuerySolution> it = exec.execSelect();
		while (it.hasNext()) {
			// get next solution
			QuerySolution sol = it.next();
			// create State
			State state = new State(
					sol.get("?state").asResource().getURI(),
					sol.get("?label").asLiteral().getLexicalForm(),
					sol.get("?dmin").asLiteral().getLexicalForm(),
					sol.get("?dmax").asLiteral().getLexicalForm(),
					restriction.getComponent());
			
			// add state
			states.add(state);
		}
		
		// close executor
		exec.close();
		// get component's states
		return states;
	}
}
