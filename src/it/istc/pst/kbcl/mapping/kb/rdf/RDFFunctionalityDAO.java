package it.istc.pst.kbcl.mapping.kb.rdf;

import it.istc.pst.kbcl.mapping.kb.rdf.exception.RDFResourceNotFoundException;
import it.istc.pst.kbcl.mapping.model.rdf.RDFComponent;
import it.istc.pst.kbcl.mapping.model.rdf.RDFFunctionality;
import it.istc.pst.kbcl.mapping.model.rdf.RDFFunctionalityImplementation;
import it.istc.pst.kbcl.mapping.model.rdf.RDFFunctionalityType;
import it.istc.pst.kbcl.mapping.model.rdf.RDFModelFactory;
import it.istc.pst.kbcl.mapping.model.rdf.RDFState;
import it.istc.pst.kbcl.mapping.model.rdf.RDFTemporalConstraint;
import it.istc.pst.kbcl.model.Functionality;
import it.istc.pst.kbcl.model.FunctionalityType;

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
public class RDFFunctionalityDAO 
{
	private RDFDatasetManager manager;
	private RDFModelFactory factory;
	
	/**
	 * 
	 */
	protected RDFFunctionalityDAO() {
		// get data set reference
		this.manager = RDFDatasetManager.getSingletonInstance();
		this.factory = RDFModelFactory.getSingletonInstance();
	}
	
	/**
	 * 
	 * @return
	 */
	public List<RDFFunctionalityType> retrieveAllFunctionalityTypes() {
		// prepare data
		List<RDFFunctionalityType> types = new ArrayList<>();
		
		// prepare query
		String queryString = "SELECT ?type ?label "
				+ "WHERE {"
				+ " ?type <" + RDFDatasetManager.NS_RDFS + "subClassOf>+ <" + RDFDatasetManager.NS + "functionality> ."
				+ " ?type <" + RDFDatasetManager.NS_RDFS + "label> ?label ."
				+ "}";
		
		// execute query
		Query query = QueryFactory.create(queryString);
		// execute query and build result
		QueryExecution exec = QueryExecutionFactory.create(query, this.manager.model);
		Iterator<QuerySolution> it = exec.execSelect();
		// check results
		while (it.hasNext()) {
			// get next
			QuerySolution sol = it.next();
			// get information
			RDFFunctionalityType f = this.factory.createFunctionalityType(
						sol.get("?type").asResource().getURI(),
						sol.get("?label").asLiteral().getLexicalForm());
			// add 
			types.add(f);
		}
		
		// close query executor
		exec.close();
		// get agent types
		return types;
	}

	/**
	 * 
	 * @return
	 */
	public List<RDFFunctionality> retrieveAllFunctionalities() {
		// prepare data
		List<RDFFunctionality> functionalities = new ArrayList<>();
		
		// get functionality types
		List<RDFFunctionalityType> types = this.retrieveAllFunctionalityTypes();
		for (RDFFunctionalityType type : types) {
			functionalities.addAll(this.retrieveFunctionalitiesByType(type));
		}
		
		// get functionalities
		return functionalities;
	}

	/**
	 * 
	 * @param type
	 * @return
	 */
	public List<RDFFunctionality> retrieveFunctionalitiesByType(FunctionalityType type) {
		// prepare data
		List<RDFFunctionality> funcs = new ArrayList<>();
		
		// prepare query
		String queryString = "SELECT ?func ?label ?dmin ?dmax "
				+ "WHERE { "
				+ " ?func a <" + type.getId() + "> . "
				+ " ?func <" + RDFDatasetManager.NS + "ID> ?label . "
				+ " ?func <" + RDFDatasetManager.NS + "minDuration> ?dmin . "
				+ " ?func <" + RDFDatasetManager.NS + "maxDuration> ?dmax . "
				+ "}";
		
		// execute query
		Query query = QueryFactory.create(queryString);
		// execute query and build result
		QueryExecution exec = QueryExecutionFactory.create(query, this.manager.model);
		Iterator<QuerySolution> it = exec.execSelect();
		// check results
		while (it.hasNext()) {
			// get next
			QuerySolution sol = it.next();
			// get information
			RDFFunctionality f = this.factory.createFunctionality(
					sol.get("?func").asResource().getURI(),
					sol.get("?label").asLiteral().getLexicalForm(),
					sol.get("?dmin").asLiteral().getLexicalForm(),
					sol.get("?dmax").asLiteral().getLexicalForm(),
					this.factory.createFunctionalityType(type.getId(), type.getLabel()));
			// add 
			funcs.add(f);
		}
		
		// close query executor
		exec.close();
		// get agent types
		return funcs;
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 * @throws RDFResourceNotFoundException
	 */
	public RDFFunctionality retrieveFunctionalityByName(String name) 
			throws RDFResourceNotFoundException
	{
		// retrieve functionality
		return this.retrieveFunctionalityById(RDFDatasetManager.NS + name);
	}

	/**
	 * 
	 */
	public RDFFunctionality retrieveFunctionalityById(String id) 
			throws RDFResourceNotFoundException 
	{
		// prepare data
		RDFFunctionality func = null;
		// prepare query
		String queryString = "SELECT ?label ?dmin ?dmax ?type ?typeLabel "
				+ "WHERE {"
				+ " <" + id + "> <" + RDFDatasetManager.NS + "ID> ?label . "
				+ " <" + id +"> <" + RDFDatasetManager.NS + "minDuration> ?dmin . "
				+ " <" + id +"> <" + RDFDatasetManager.NS + "maxDuration> ?dmax . "
				+ " <" + id + "> a ?type . "
				+ " ?type <" + RDFDatasetManager.NS_RDFS + "label> ?typeLabel ." 
				+ "} ";
			
		// execute query
		Query query = QueryFactory.create(queryString);
		// execute query and build result
		QueryExecution exec = QueryExecutionFactory.create(query, this.manager.model);
		Iterator<QuerySolution> it = exec.execSelect();
		// check results
		while (it.hasNext()) {
			// get next
			QuerySolution sol = it.next();
			
			// create type
			RDFFunctionalityType type = this.factory.createFunctionalityType(
					sol.get("?type").asResource().getURI(),
					sol.get("?typeLabel").asLiteral().getLexicalForm());
			
			// get information
			func = this.factory.createFunctionality(
					id,
					sol.get("?label").asLiteral().getLexicalForm(),
					sol.get("?dmin").asLiteral().getLexicalForm(),
					sol.get("?dmax").asLiteral().getLexicalForm(),
					type);
		}
		
		// close query executor
		exec.close();
		// check data
		if (func == null) {
			throw new RDFResourceNotFoundException("[FunctionalityRDFDAO]: No Functionality found with ID " + id);
		}
		
		// get agents
		return func;
	}
	
	/**
	 * 
	 */
	public List<RDFFunctionalityImplementation> retrieveFunctionalityImplementations(RDFFunctionality f) {
		// prepare data
		List<RDFFunctionalityImplementation> list = new ArrayList<>();
		
		// get functionality's implementation IDs
		List<String> ids = this.retrieveFuntionalityImplementationResources(f);
		for (String id : ids) 
		{
			// create implementation
			RDFFunctionalityImplementation impl = this.factory.createFunctionalityImplementation(
					this.factory.createFunctionality(f.getId(), f.getLabel(), f.getMinDuration(), f.getMaxDuration(), 
							this.factory.createFunctionalityType(f.getType().getId(), f.getType().getLabel())));
			
			// prepare query to get implementation constraints
			String queryString = "SELECT ?constraint ?state ?stateLabel ?dmin ?dmax ?comp ?compLabel "
					+ "WHERE { "
					+ " ?constraint <" + RDFDatasetManager.NS_RDFS + "subPropertyOf>+ <" + RDFDatasetManager.NS + "temporal> . "
					+ " <" + id + "> ?constraint ?state . "
					+ " ?state <" + RDFDatasetManager.NS + "ID> ?stateLabel . "
					+ " ?state <" + RDFDatasetManager.NS + "minDuration> ?dmin . "
					+ " ?state <" + RDFDatasetManager.NS + "maxDuration> ?dmax . "
					+ " ?comp <" + RDFDatasetManager.NS + "hasState> ?state . "
					+ " ?comp <" + RDFDatasetManager.NS + "ID> ?compLabel . "
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
				RDFComponent comp = this.factory.createComponent(
						sol.get("?comp").asResource().getURI(),
						sol.get("?compLabel").asLiteral().getLexicalForm());
				
				// create state
				RDFState state = this.factory.createState(
						sol.get("?state").asResource().getURI(),
						sol.get("?stateLabel").asLiteral().getLexicalForm(),
						sol.get("?dmin").asLiteral().getLexicalForm(),
						sol.get("?dmax").asLiteral().getLexicalForm(),
						comp);
				
				// create constraint
				String constraintId = sol.get("?constraint").asResource().getURI();
				String label = constraintId.split("#")[1].toUpperCase();
				RDFTemporalConstraint constraint = this.factory.createTemporalConstraint(constraintId, label, state);
				// add constraint to implementation
				impl.addConstraint(constraint);
			}
			
			// prepare query to get restriction constraints
			queryString = "SELECT ?res ?temporalLabel "
					+ " ?fromState ?fromStateLabel ?fromStateMinDur ?fromStateMaxDur "
					+ " ?toState ?toStateLabel ?toStateMinDur ?toStateMaxDur "
					+ " ?fromComp ?fromCompLabel "
					+ " ?toComp ?toCompLabel "
					+ "WHERE { "
					+ "	<" + id + "> <" + RDFDatasetManager.NS + "hasRestriction> ?res . "
					+ " ?res <" + RDFDatasetManager.NS + "type> ?temporalLabel . "
					+ " ?res <" + RDFDatasetManager.NS + "from> ?fromState . "
					+ " ?res <" + RDFDatasetManager.NS + "to> ?toState . "
					+ " ?fromComp <" + RDFDatasetManager.NS + "hasState> ?fromState . "
					+ " ?fromComp <" + RDFDatasetManager.NS + "ID> ?fromCompLabel . "
					+ " ?toComp <" + RDFDatasetManager.NS + "hasState> ?toState . "
					+ " ?toComp <" + RDFDatasetManager.NS +  "ID> ?toCompLabel . "
					+ " ?fromState <" + RDFDatasetManager.NS + "ID> ?fromStateLabel . "
					+ " ?fromState <" + RDFDatasetManager.NS + "minDuration> ?fromStateMinDur . "
					+ " ?fromState <" + RDFDatasetManager.NS + "maxDuration> ?fromStateMaxDur . "
					+ " ?toState <" + RDFDatasetManager.NS + "ID> ?toStateLabel . "
					+ " ?toState <" + RDFDatasetManager.NS + "minDuration> ?toStateMinDur . "
					+ " ?toState <" + RDFDatasetManager.NS + "maxDuration> ?toStateMaxDur . "
					+ "}";
			
			// execute query
			query = QueryFactory.create(queryString);
			// execute query and build result
			exec = QueryExecutionFactory.create(query, this.manager.model);
			it = exec.execSelect();
			while (it.hasNext()) {
				// get next solution
				QuerySolution sol = it.next();
				
				// create "from" state
				RDFState fromState = this.factory.createState(
						sol.get("?fromState").asResource().getURI(), 
						sol.get("?fromStateLabel").asLiteral().getLexicalForm(),
						sol.get("?fromStateMinDur").asLiteral().getLexicalForm(), 
						sol.get("?fromStateMaxDur").asLiteral().getLexicalForm(), 
						this.factory.createComponent(
								sol.get("?fromComp").asResource().getURI(), 
								sol.get("?fromCompLabel").asLiteral().getLexicalForm()));
				
				// create "to" state
				RDFState toState = this.factory.createState(
						sol.get("?toState").asResource().getURI(), 
						sol.get("?toStateLabel").asLiteral().getLexicalForm(),
						sol.get("?toStateMinDur").asLiteral().getLexicalForm(), 
						sol.get("?toStateMaxDur").asLiteral().getLexicalForm(), 
						this.factory.createComponent(
								sol.get("?toComp").asResource().getURI(),
								sol.get("?toCompLabel").asLiteral().getLexicalForm()));
				
				// create temporal constraint
				RDFTemporalConstraint restriction = this.factory.createTemporalConstraint(
						sol.get("?res").asResource().getURI(),
						sol.get("?temporalLabel").asLiteral().getLexicalForm(), 
						toState);
				
				// add restriction to implementation
				impl.addRestriction(fromState, restriction);
			}
			
			// close executor
			exec.close();
			// add implementation
			list.add(impl);
		}
		// return implementations
		return list;
	}
	
	/**
	 * 
	 * @param f
	 * @return
	 */
	private List<String> retrieveFuntionalityImplementationResources(Functionality f) {
		// prepare data 
		List<String> ids = new ArrayList<String>();
		
		// prepare query
		String queryString = "SELECT ?impl "
				+ "WHERE { "
				+ "	<" + f.getId() + "> <" + RDFDatasetManager.NS + "hasImplementation> ?impl . "
				+ "}";
		
		// execute query
		Query query = QueryFactory.create(queryString);
		// execute query and build result
		QueryExecution exec = QueryExecutionFactory.create(query, this.manager.model);
		Iterator<QuerySolution> it = exec.execSelect();
		while (it.hasNext()) {
			// next solution
			QuerySolution sol = it.next();
			// add implementation id
			ids.add(sol.get("?impl").asResource().getURI());
		}
		// close executor
		exec.close();
		// return IDs
		return ids;
	}
}
