package it.istc.pst.kbcl.mapping.kb.rdf;

import it.istc.pst.kbcl.mapping.kb.rdf.exception.RDFResourceNotFoundException;
import it.istc.pst.kbcl.mapping.model.rdf.RDFComponent;
import it.istc.pst.kbcl.mapping.model.rdf.RDFExternalComponent;
import it.istc.pst.kbcl.mapping.model.rdf.RDFModelFactory;
import it.istc.pst.kbcl.mapping.model.rdf.RDFRestriction;
import it.istc.pst.kbcl.mapping.model.rdf.RDFState;

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
public class RDFComponentDAO 
{
	private RDFDatasetManager manager;
	private RDFModelFactory factory;
	
	/**
	 * 
	 */
	protected RDFComponentDAO() {
		// get data set reference
		this.manager = RDFDatasetManager.getSingletonInstance();
		this.factory = RDFModelFactory.getSingletonInstance();
	}
	
	/**
	 * 
	 * @return
	 */
	public List<RDFComponent> retrieveAllInternalComponents() {
		// prepare data
		List<RDFComponent> components = new ArrayList<>();
		
		// prepare query
		String queryString = "SELECT ?comp ?label "
				+ "WHERE {"
				+ "	?type <" + RDFDatasetManager.NS_RDFS + "subClassOf>+ <" + RDFDatasetManager.NS + "component> . "
				+ " ?comp a ?type . "
				+ " ?comp <" + RDFDatasetManager.NS + "ID> ?label  ."
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
			RDFComponent comp = this.factory.createComponent(
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
	 * @param id
	 * @return
	 * @throws RDFResourceNotFoundException
	 */
	public RDFComponent retrieveInternalComponentById(String id) 
			throws RDFResourceNotFoundException 
	{
		// prepare data
		RDFComponent comp = null;
		
		// prepare query
		String queryString = "SELECT ?label "
				+ "WHERE {"
				+ " <" + id + "> a ?t . "
				+ " ?t <" + RDFDatasetManager.NS_RDFS +"subClassOf>* <" + RDFDatasetManager.NS + "component> . "
				+ " <" + id + "> <" + RDFDatasetManager.NS + "ID> ?label . "
				
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
			comp = this.factory.createComponent(
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
	 * @param id
	 * @return
	 * @throws RDFResourceNotFoundException
	 */
	public RDFExternalComponent retrieveExternalComponentById(String id) 
			throws RDFResourceNotFoundException 
	{
		// prepare data
		RDFExternalComponent comp = null;
		
		// prepare query
		String queryString = "SELECT ?label "
				+ "WHERE {"
				+ " <" + id + "> a ?t . "
				+ " ?t <" + RDFDatasetManager.NS_RDFS + "subClassOf>* <" + RDFDatasetManager.NS+ "neighbor> . "
				+ " <" + id + "> <" + RDFDatasetManager.NS + "ID> ?label . "
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
			comp = this.factory.createExternalCopmonent(
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
	 * @param comp
	 * @return
	 */
	public List<RDFState> retrieveComponentStates(RDFComponent comp) {
		// prepare data
		List<RDFState> states = new ArrayList<>();
		
		// prepare query
		String queryString = "SELECT ?state ?label ?dmin ?dmax "
				+ "WHERE { "
				+ " <" + comp.getId() + "> <" + RDFDatasetManager.NS + "hasState> ?state . "
				+ " ?state <" + RDFDatasetManager.NS + "ID> ?label . "
				+ " ?state <" + RDFDatasetManager.NS +"minDuration> ?dmin . "
				+ " ?state <" + RDFDatasetManager.NS + "maxDuration> ?dmax . "
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
			RDFState s = this.factory.createState(
					sol.get("?state").asResource().getURI(),
					sol.get("?label").asLiteral().getLexicalForm(),
					sol.get("?dmin").asLiteral().getLexicalForm(),
					sol.get("?dmax").asLiteral().getLexicalForm(),
					this.factory.createComponent(comp.getId(), comp.getLabel()));
			
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
	public RDFComponent retrieveConnectedComponent(RDFExternalComponent neighbor) {
		// prepare data
		RDFComponent c = null;
		
		// prepare query
		String queryString = "SELECT ?c ?l "
				+ "WHERE { "
				+ " <" + neighbor.getId() + "> a ?type . "
				+ " ?type <" + RDFDatasetManager.NS_RDFS + "subClassOf>* <" + RDFDatasetManager.NS + "neighbor> . "
				+ " <" + neighbor.getId() + "> <" + RDFDatasetManager.NS + "connectedBy> ?c . "
				+ " ?c <" + RDFDatasetManager.NS + "ID> ?l ."
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
			c = this.factory.createComponent(
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
	 * @param comp
	 * @return
	 */
	public List<RDFRestriction> retrieveComponentRestrictions(RDFComponent comp) {
		// prepare data
		List<RDFRestriction> restrictions = new ArrayList<>();
		
		// prepare query
		String queryString = "SELECT ?restriction  "
				+ "WHERE { "
				+ " <" + comp.getId() + "> <" + RDFDatasetManager.NS + "hasRestriction> ?restriction . "
				+ " ?restriction a <" + RDFDatasetManager.NS + "restriction> . "
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
			RDFRestriction restriction = this.factory.createRestriction(
					sol.get("?restriction").asResource().getURI(),
					this.factory.createComponent(comp.getId(), comp.getLabel()));
			
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
	 * @param restriction
	 * @return
	 */
	public List<RDFState> retrieveRestrictionStates(RDFRestriction restriction) {
		// prepare data
		List<RDFState> states = new ArrayList<>();
		
		// prepare query
		String queryString = "SELECT ?state ?label ?dmin ?dmax "
				+ "WHERE { "
				+ " <" + restriction.getId() + "> <" + RDFDatasetManager.NS + "hasState> ?state . "
				+ " ?state <" + RDFDatasetManager.NS + "ID> ?label . "
				+ " ?state <" + RDFDatasetManager.NS + "minDuration> ?dmin . "
				+ " ?state <" + RDFDatasetManager.NS + "maxDuration> ?dmax . "
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
			RDFState state = this.factory.createState(
					sol.get("?state").asResource().getURI(),
					sol.get("?label").asLiteral().getLexicalForm(),
					sol.get("?dmin").asLiteral().getLexicalForm(),
					sol.get("?dmax").asLiteral().getLexicalForm(),
					this.factory.createComponent(
							restriction.getComponent().getId(), 
							restriction.getComponent().getLabel())
					);
			
			// add state
			states.add(state);
		}
		
		// close executor
		exec.close();
		// get component's states
		return states;
	}
}
