package it.istc.pst.gecko.ontology.kb.rdf;

import it.istc.pst.gecko.ontology.kb.AgentDAO;
import it.istc.pst.gecko.ontology.kb.rdf.exception.RDFResourceNotFoundException;
import it.istc.pst.gecko.ontology.model.Agent;
import it.istc.pst.gecko.ontology.model.AgentType;
import it.istc.pst.gecko.ontology.model.Component;
import it.istc.pst.gecko.ontology.model.ExternalComponent;
import it.istc.pst.gecko.ontology.model.Functionality;
import it.istc.pst.gecko.ontology.model.FunctionalityType;
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
public class RDFAgentDAO implements AgentDAO
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
	@Override
	public List<AgentType> retrieveAllAgentTypes() {
		// prepare data
		List<AgentType> types = new ArrayList<AgentType>();
		
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
			AgentType a = 
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
	@Override
	public List<Agent> retrieveAllAgents() {
		// prepare data
		List<Agent> agents = new ArrayList<Agent>();
		
		// get all agent types
		List<AgentType> types = this.retrieveAllAgentTypes();
		// check if any instance of the current type of agent exists
		for (AgentType type : types) {
			// get agents of the current type
			agents.addAll(this.retrieveAgentsByType(type));
		}
		
		// get agents
		return agents;
	}

	/**
	 * Check if any Agent of the selected AgentType exists
	 */
	@Override
	public List<Agent> retrieveAgentsByType(AgentType type) {
		// prepare data
		List<Agent> agents = new ArrayList<Agent>();
		
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
			Agent a = this.factory.createAgent(
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
	 */
	@Override
	public Agent retrieveAgentById(String id) 
			throws RDFResourceNotFoundException 
	{
		// prepare data
		Agent a = null;
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
	 */
	@Override
	public Map<FunctionalityType, List<Functionality>> retrieveAgentFunctionalities(Agent agent) {
		// prepare data
		Map<FunctionalityType, List<Functionality>> functionalities = new HashMap<FunctionalityType, List<Functionality>>();
		
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
				functionalities.put(type, new ArrayList<Functionality>());
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
	 */
	@Override
	public List<Component> retrieveAgentInternalComponents(Agent agent) {
		// prepare data
		List<Component> components = new ArrayList<Component>();
		
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
	 */
	@Override
	public List<ExternalComponent> retrieveAgentExternalComponents(Agent agent) {
		// prepare data
		List<ExternalComponent> neighbors = new ArrayList<ExternalComponent>();
		
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

	@Override
	public Agent createAgent(String name, AgentType type) {
		// TODO Auto-generated method stub
		return null;
	}
}
