package it.istc.pst.gecko.ontology.kb.rdf;

import it.istc.pst.gecko.ontology.kb.rdf.exception.RDFResourceNotFoundException;
import it.istc.pst.gecko.ontology.model.Agent;
import it.istc.pst.gecko.ontology.model.AgentType;
import it.istc.pst.gecko.ontology.model.rdf.RDFAgent;
import it.istc.pst.gecko.ontology.model.rdf.RDFAgentType;
import it.istc.pst.gecko.ontology.model.rdf.RDFComponent;
import it.istc.pst.gecko.ontology.model.rdf.RDFExternalComponent;
import it.istc.pst.gecko.ontology.model.rdf.RDFFunctionality;
import it.istc.pst.gecko.ontology.model.rdf.RDFFunctionalityType;
import it.istc.pst.gecko.ontology.model.rdf.RDFModelFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
public class RDFAgentDAO
{
	private RDFDatasetManager manager;
	private RDFModelFactory factory;
	
	/**
	 * 
	 */
	protected RDFAgentDAO() {
		// get data set reference
		this.manager = RDFDatasetManager.getSingletonInstance();
		this.factory = RDFModelFactory.getSingletonInstance();
	}
	
	/**
	 * 
	 * @return
	 */
	public List<RDFAgentType> retrieveAllAgentTypes() {
		// prepare data
		List<RDFAgentType> types = new ArrayList<>();
		
		// prepare query
		String queryString = "SELECT ?type ?label "
				+ "WHERE {"
				+ " ?type <" + RDFDatasetManager.NS_RDFS + "subClassOf>+ <" + RDFDatasetManager.NS_GECKO + "agent> ."
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
			RDFAgentType a = 
					this.factory.createAgentType(
							sol.get("?type").asResource().getURI(),
							sol.get("?label").asLiteral().getLexicalForm());
			// add 
			types.add(a);
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
	public List<RDFAgent> retrieveAllAgents() {
		// prepare data
		List<RDFAgent> agents = new ArrayList<>();
		
		// get all agent types
		List<RDFAgentType> types = this.retrieveAllAgentTypes();
		// check if any instance of the current type of agent exists
		for (RDFAgentType type : types) {
			// get agents of the current type
			agents.addAll(this.retrieveAgentsByType(type));
		}
		
		// get agents
		return agents;
	}

	/**
	 * Check if any Agent of the selected AgentType exists
	 * 
	 * @param type
	 * @return
	 */
	public List<RDFAgent> retrieveAgentsByType(AgentType type) {
		// prepare data
		List<RDFAgent> agents = new ArrayList<>();
		
		// prepare query
		String queryString = "SELECT ?agent ?label "
				+ "WHERE {"
				+ " ?agent a <" + type.getId() + "> ."
				+ " ?agent <" + RDFDatasetManager.NS_GECKO + "ID> ?label ."
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
			// get information
			RDFAgent a = this.factory.createAgent(
					sol.get("?agent").asResource().getURI(),
					sol.get("?label").asLiteral().getLexicalForm(),
					this.factory.createAgentType(type.getId(), type.getLabel()));
			// add 
			agents.add(a);
		}
		
		// close query executor
		exec.close();
		// get agents
		return agents;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws RDFResourceNotFoundException
	 */
	public RDFAgent retrieveAgentById(String id) 
			throws RDFResourceNotFoundException 
	{
		// prepare data
		RDFAgent a = null;
		// prepare query
		String queryString = "SELECT ?label ?type ?typeLabel "
				+ "WHERE {"
				+ " <" + id + "> <" + RDFDatasetManager.NS_GECKO + "ID> ?label ."
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
			RDFAgentType type = this.factory.createAgentType(
					sol.get("?type").asResource().getURI(),
					sol.get("?typeLabel").asLiteral().getLexicalForm());
			
			// get information
			a = this.factory.createAgent(
					id,
					sol.get("?label").asLiteral().getLexicalForm(),
					type);
		}
		
		// close query executor
		exec.close();
		// check data
		if (a == null) {
			throw new RDFResourceNotFoundException("[AgentRDFDAO]: No Agent Found with ID " + id);
		}
		
		// get agents
		return a;
	}
	
	/**
	 * 
	 * @param agent
	 * @return
	 */
	public Map<RDFFunctionalityType, List<RDFFunctionality>> retrieveAgentFunctionalities(Agent agent) {
		// prepare data
		Map<RDFFunctionalityType, List<RDFFunctionality>> functionalities = new HashMap<>();
		
		// prepare query
		String queryString = "SELECT ?func ?label ?dmin ?dmax ?type ?typeLabel "
				+ "WHERE { "
				+ " <" + agent.getId() +  "> <" + RDFDatasetManager.NS_GECKO + "hasFunctionality> ?func . "
				+ " ?func <" + RDFDatasetManager.NS_GECKO + "ID> ?label . "
				+ " ?func <" + RDFDatasetManager.NS_GECKO + "minDuration> ?dmin . "
				+ " ?func <" + RDFDatasetManager.NS_GECKO + "maxDuration> ?dmax . "
				+ " ?func a ?type . "
				+ " ?type <" + RDFDatasetManager.NS_RDFS + "label> ?typeLabel . "
				+ "}";
		
		// execute query
		Query query = QueryFactory.create(queryString);
		// execute query and build result
		QueryExecution exec = QueryExecutionFactory.create(query, this.manager.model);
		Iterator<QuerySolution> it = exec.execSelect();
		while (it.hasNext()) {
			QuerySolution sol = it.next();
			// create FunctionalityType
			RDFFunctionalityType type = this.factory.createFunctionalityType(
					sol.get("?type").asResource().getURI(),
					sol.get("?typeLabel").asLiteral().getLexicalForm());
			
			// create Functionality
			RDFFunctionality func = this.factory.createFunctionality(
					sol.get("?func").asResource().getURI(),
					sol.get("?label").asLiteral().getLexicalForm(),
					sol.get("?dmin").asLiteral().getLexicalForm(),
					sol.get("?dmax").asLiteral().getLexicalForm(),
					type);
			
			// check data
			if (!functionalities.containsKey(type)) {
				functionalities.put(type, new ArrayList<RDFFunctionality>());
			}
			
			// add functionality
			functionalities.get(type).add(func);
		}
		
		// close executor
		exec.close();
		// get agent's functionalities
		return functionalities;
	}
	
	/**
	 * 
	 * @param agent
	 * @return
	 */
	public List<RDFComponent> retrieveAgentInternalComponents(Agent agent) {
		// prepare data
		List<RDFComponent> components = new ArrayList<RDFComponent>();
		
		// prepare query
		String queryString = "SELECT ?c ?label "
				+ "WHERE { "
				+ " <" + agent.getId() + "> <" + RDFDatasetManager.NS_GECKO + "hasComponent> ?c . "
				+ " ?c <" + RDFDatasetManager.NS_GECKO + "ID> ?label ."
				+ "}";
		
		// execute query
		Query query = QueryFactory.create(queryString);
		// execute query and build result
		QueryExecution exec = QueryExecutionFactory.create(query, this.manager.model);
		Iterator<QuerySolution> it = exec.execSelect();
		while (it.hasNext()) {
			// get next
			QuerySolution sol = it.next();
			// create component
			RDFComponent c = this.factory.createComponent(
					sol.get("?c").asResource().getURI(), 
					sol.get("?label").asLiteral().getLexicalForm());
			
			// add component
			components.add(c);
		}
		
		// close executor
		exec.close();
		// get agent's components
		return components;
	}
	
	/**
	 * 
	 * @param agent
	 * @return
	 */
	public List<RDFExternalComponent> retrieveAgentExternalComponents(Agent agent) {
		// prepare data
		List<RDFExternalComponent> neighbors = new ArrayList<>();
		
		// prepare query
		String queryString = "SELECT ?n ?l "
				+ "WHERE { "
				+ " <" + agent.getId() + "> <" + RDFDatasetManager.NS_GECKO + "hasNeighbor> ?n . "
				+ " ?n <" + RDFDatasetManager.NS_GECKO + "ID> ?l ."
				+ "}";
		
		// execute query
		Query query = QueryFactory.create(queryString);
		// execute query and build result
		QueryExecution exec = QueryExecutionFactory.create(query, this.manager.model);
		Iterator<QuerySolution> it = exec.execSelect();
		while (it.hasNext()) {
			// get next solution
			QuerySolution sol = it.next();
			// create neighbor
			RDFExternalComponent neighbor = this.factory.createExternalCopmonent(
					sol.get("?n").asResource().getURI(),
					sol.get("?l").asLiteral().getLexicalForm());
			
			// add neighbor
			neighbors.add(neighbor);
		}
		
		// close executor
		exec.close();
		// get agent's neighbors
		return neighbors;
	}
}
