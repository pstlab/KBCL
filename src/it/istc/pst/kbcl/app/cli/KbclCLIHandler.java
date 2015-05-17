package it.istc.pst.kbcl.app.cli;

import it.istc.pst.kbcl.KbclManager;
import it.istc.pst.kbcl.exception.KbclNoAgentSelectedException;
import it.istc.pst.kbcl.model.Agent;
import it.istc.pst.kbcl.model.AgentType;
import it.istc.pst.kbcl.model.Element;
import it.istc.pst.kbcl.model.Functionality;
import it.istc.pst.kbcl.model.FunctionalityType;
import it.istc.pst.kbcl.ontology.model.owl.OWLAgent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class KbclCLIHandler implements Runnable
{
//	private RDFKnowledgeBaseFacade facade;
//	private RDFAgent agent;
//	private OWLKnowledgeBaseFacade facade;
//	private OWLAgent agent;
	private KbclManager kbcl;
	
	/**
	 * 
	 */
	protected KbclCLIHandler() {
//		this.facade = OWLKnowledgeBaseFacade.getSingletonInstance();
//		this.agent = null;
		this.kbcl = new KbclManager();
	}
	
	/**
	 * 
	 */
	public void run() {
		System.out.println("************************************");
		System.out.println("************ KBCL CLI **************");
		System.out.println("************************************");
		try 
		{
			// get input reader
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			boolean exit = false;
			do {
				// display prompt
				System.out.print("KBCL > ");
				String input = reader.readLine();
				try {
					// process input
					exit = this.processCmd(input);
					System.out.println();
				} catch (Exception ex) {
					System.err.println(ex.getMessage());
					ex.printStackTrace();
					exit = true;
				}
			}
			while (!exit);
			
			// exiting
			System.out.println("Bye!;)");
		}
		catch (IOException ex) {
			System.err.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param input
	 * @return
	 */
	private boolean processCmd(String input) 
			throws Exception 
	{
		boolean exit = false;
		// check input string 
		if (input == null) {
			throw new Exception("Specify a valid command!");
		}
		
		// check command type
		String[] splits = input.split("\\s");
		// get command
		String cmd = splits[0].toUpperCase();
		
		// check command
		if (cmd.equals(KbclCLICommand.HELP.getCmd())) {
			// help commands
			System.out.println("Available commands");
			for (KbclCLICommand cliCommand : KbclCLICommand.values()) {
				System.out.println("- [" + cliCommand.getCmd() + "] - " + cliCommand.getHelp());
			}
		}
		else if (cmd.equalsIgnoreCase(KbclCLICommand.EXIT.getCmd())) {
			// exit
			exit = true;
		}
		// remove command
		else if (cmd.equalsIgnoreCase(KbclCLICommand.REMOVE.getCmd())) {
			String parameter = (splits != null && splits.length > 1) ? splits[1].toUpperCase().trim() : null;
			if (parameter != null) {
				// check if an agent has been selected 
				try {
					// get index of the element to remove
					int index = Integer.parseInt(parameter);
					// select element
					OWLAgent agent = this.kbcl.getFocusedAgent();
					Element el = agent.removeComponent(index);
					System.out.println("Element " + el + " successfully removed!");
				}
				catch (KbclNoAgentSelectedException ex) {
					System.out.println(ex.getMessage());
				}
			}
			else {
				System.out.println("Select an element to remove");
			}
		}
		// show command
		else if (cmd.equalsIgnoreCase(KbclCLICommand.SHOW.getCmd())) {
			// get command's parameter
			String parameter = (splits != null && splits.length > 1) ? splits[1].toUpperCase() : null;
			if (parameter != null) {
				// check parameter
				if (parameter.equalsIgnoreCase("agents")) {
					// print agents
					System.out.println("Agents in the Knowledge Base");
					int counter = 0;
					for (Agent agent : this.kbcl.getAgents()) {
						System.out.println("- (" + counter + ") " + agent);
						counter++;
					}
				}
				else if (parameter.equalsIgnoreCase("agent-types")) {
					// print agent types
					System.out.println("Agent types");
					int counter = 0;
					for (AgentType type : this.kbcl.getAgentTypes()) {
						System.out.println("- (" + counter + ") " + type);
						counter++;
					}
				}
				else if (parameter.equalsIgnoreCase("functionality-types")) {
					// print functionality types
					System.out.println("Functionality types in the KB");
					int counter = 0;
					for (FunctionalityType type : this.kbcl.getFunctionalityTypes()) {
						System.out.println("- (" + counter + ") " + type);
						counter++;
					}
				}
				else if (parameter.equalsIgnoreCase("functionalities")) {
					// check if an agent has been selected
					try {
						// print agent's functionalities
						System.out.println("Agent's functionalities");
						int counter = 0;
						// get focus agent
						OWLAgent agent = this.kbcl.getFocusedAgent();
						for (Functionality func : agent.getFunctionalities()) {
							System.out.println("- (" + counter + ") " + func);
							counter++;
						}
					}
					catch (KbclNoAgentSelectedException ex) {
						System.out.println(ex.getMessage());
					}
				}
				else if (parameter.equalsIgnoreCase("components")) {
					// check if an agent has been selected
					try {
						// print agent's functionalities
						System.out.println("Agent's components");
						int counter = 0;
						for (Element el : this.kbcl.getFocusedAgent().getComponents()) {
							System.out.println("- (" + counter + ") " + el);
							counter++;
						}
					}
					catch (KbclNoAgentSelectedException ex) {
						System.out.println(ex.getMessage());
					}
				}
				else if (parameter.equalsIgnoreCase("neighbors")) {
					// check if an agent has been selected
					try {
						// print agent's functionalities
						System.out.println("Agent's neighbors");
						int counter = 0;
						// get focused agent
						OWLAgent agent = this.kbcl.getFocusedAgent();
						for (Element n : agent.getNeighbors()) {
							System.out.println("- (" + counter + ") " + n);
							counter++;
						}
					}
					catch (KbclNoAgentSelectedException ex) {
						System.out.println(ex.getMessage());
					}
				}
				else {
					// print command usage
					System.out.println("- Possible parameters " + KbclCLICommand.SHOW.getCmdParameters());
				}
			}
			else {
				// print command usage
				System.out.println("- [" + KbclCLICommand.SHOW.getCmd() + "] - " + KbclCLICommand.SHOW.getHelp());
			}
		}
		else if (cmd.equalsIgnoreCase(KbclCLICommand.SELECT.getCmd())) {
			// get parameter
			String parameter = (splits != null && splits.length > 1) ? splits[1].toUpperCase().trim() : null;
			if (parameter != null) {
				// parse index
				int index = Integer.parseInt(parameter);
				try {
					// get agent
					OWLAgent agent = this.kbcl.setFocus(index);
					System.out.println("Selected agent:\n" + agent);
					
					// initialize mapping 
					this.kbcl.initialize(agent);
				}
				catch (Exception ex) {
					System.err.println(ex.getMessage());
					ex.printStackTrace();
				}
				
				
				// FIXME start loop - get current time
//				long time = System.currentTimeMillis();
//				try 
//				{
//					// create KBCL manager and start loop
//					this.manager = new KbclManager(this.agent, HORIZON);
//					System.out.println("KBCL succesfully initialized!");
//				}
//				catch (KbclInitializationException | DDLPlanningModelInitializationFailureException ex) {
//					System.err.println(ex.getMessage());
//				}
//				finally {
//					// update time
//					time = System.currentTimeMillis() - time;
//					System.out.println("KBCL initialization done in " + time + " msec");
//				}
				
			}
			else {
				System.out.println("Select agent index");
			}
		}
		else if (cmd.equalsIgnoreCase(KbclCLICommand.PLAN.getCmd())) {
			// check if agent has been selected
			try {
				// get index of selected functionality to plan with
				String parameter = (splits != null && splits.length > 1) ? splits[1].toUpperCase().trim() : null;
				if (parameter != null) {
					// get functionality index
					int index = Integer.parseInt(parameter);
					// get agent
					OWLAgent agent = this.kbcl.getFocusedAgent();
					// select functionality
					Functionality func = agent.getFunctionalities().get(index);
					System.out.println("Selected functionality\n" + func);
					
					// FIXME - COSTRUIRE MODELLO DI PLANNING
//					try {
//						// request functionality
//						this.manager.planRequest(func);
//						// print resulting PDB
//						System.out.println(this.manager.getPDBDescription());
//					}
//					catch (KbclRequestProcessingFailureException ex) {
//						System.err.println(ex.getMessage());
//					}
				}
				else {
					System.out.println("Select agent's functionality index");
				}
			}
			catch (KbclNoAgentSelectedException ex) {
				// no agent selected
				System.out.println(ex.getMessage());
			}
		}
		else {
			// unknown command
			System.out.println("Unknownw Command!\nAvailable commands");
			for (KbclCLICommand cliCommand : KbclCLICommand.values()) {
				System.out.println("- [" + cliCommand.getCmd() + "] - " + cliCommand.getHelp());
			}
		}
		return exit;
	}
}