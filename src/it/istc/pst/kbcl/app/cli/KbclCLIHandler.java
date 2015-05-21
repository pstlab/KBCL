package it.istc.pst.kbcl.app.cli;

import it.istc.pst.kbcl.KbclManager;
import it.istc.pst.kbcl.exception.KbclNoAgentSelectedException;
import it.istc.pst.kbcl.exception.KbclRequestProcessingFailureException;
import it.istc.pst.kbcl.mapping.kb.rdf.exception.RDFPropertyNotFoundException;
import it.istc.pst.kbcl.mapping.kb.rdf.exception.RDFResourceNotFoundException;
import it.istc.pst.kbcl.mapping.ps.ddl.exception.DDLPlanningModelInitializationFailureException;
import it.istc.pst.kbcl.model.Agent;
import it.istc.pst.kbcl.model.AgentType;
import it.istc.pst.kbcl.model.Element;
import it.istc.pst.kbcl.model.Functionality;
import it.istc.pst.kbcl.model.FunctionalityType;

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
	private KbclManager kbcl;
	
	/**
	 * 
	 */
	protected KbclCLIHandler() {
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
		// add command
		else if (cmd.equalsIgnoreCase(KbclCLICommand.ADD.getCmd())) {
			// get element label
			String elementLabel = (splits != null && splits.length > 1) ? splits[1].trim() : null;
			if (elementLabel != null) {
				try {
					// get agent
					Agent agent = this.kbcl.getFocusedAgent();
					agent.addComponent(elementLabel);
				}
				catch (KbclNoAgentSelectedException ex) {
					System.out.println(ex.getLocalizedMessage());
				}
			}
			else {
				System.out.println("Specify a valid element to add to the module" );
			}
		}
		// remove command
		else if (cmd.equalsIgnoreCase(KbclCLICommand.REMOVE.getCmd())) {
			String label = (splits != null && splits.length > 1) ? splits[1].trim() : null;
			if (label != null) {
				// check if an agent has been selected 
				try {
					// select element
					Agent agent = this.kbcl.getFocusedAgent();
					if (!agent.removeComponent(label)) {
						System.out.println("No Component found with label " + label);
					}
				}
				catch (KbclNoAgentSelectedException ex) {
					System.out.println(ex.getMessage());
				}
			}
			else {
				System.out.println("Specify the label of the element to remove from module");
			}
		}
		// show command
		else if (cmd.equalsIgnoreCase(KbclCLICommand.SHOW.getCmd())) {
			// get command's parameter
			String parameter = (splits != null && splits.length > 1) ? splits[1].toUpperCase() : null;
			if (parameter != null) 
			{
				// check parameter
				if (parameter.equalsIgnoreCase("agents")) {
					// print agents
					System.out.println("Agents in the Knowledge Base");
					// get agents
					for (Agent a : this.kbcl.getAgents()) {
						System.out.println("- (" + a.getLabel() + ") -> " + a);
					}
				}
				else if (parameter.equalsIgnoreCase("agent-types")) 
				{
					// print agent types
					System.out.println("Agent types");
					for (AgentType type : this.kbcl.getAgentTypes()) {
						System.out.println("- (" + type.getLabel() + ") -> " + type);
					}
				}
				else if (parameter.equalsIgnoreCase("functionality-types")) 
				{
					// print functionality types
					System.out.println("Functionality types");
					for (FunctionalityType type : this.kbcl.getFunctionalityTypes()) {
						System.out.println("- (" + type.getLabel() + ") -> " + type);
					}
				}
				else if (parameter.equalsIgnoreCase("functionalities")) 
				{
					// check if an agent has been selected
					try {
						// print agent's functionalities
						System.out.println("Agent's functionalities");
						// get focus agent
						Agent agent = this.kbcl.getFocusedAgent();
						for (Functionality func : agent.getFunctionalities()) {		//.getFunctionalityIndex()) {
							System.out.println("- (" + func.getLabel() + ") -> " + func);
						}
					}
					catch (KbclNoAgentSelectedException ex) {
						System.out.println(ex.getMessage());
					}
				}
				else if (parameter.equalsIgnoreCase("components")) 
				{
					// check if an agent has been selected
					try {
						// print agent's functionalities
						System.out.println("Agent's components");
						Agent agent = this.kbcl.getFocusedAgent();
						for (Element el : agent.getComponents()) {
							System.out.println("- (" + el.getLabel() + ") -> " + el);
						}
					}
					catch (KbclNoAgentSelectedException ex) {
						System.out.println(ex.getMessage());
					}
				}
				else if (parameter.equalsIgnoreCase("neighbors")) 
				{
					// check if an agent has been selected
					try {
						// print agent's functionalities
						System.out.println("Agent's neighbors");
						// get focused agent
						Agent agent = this.kbcl.getFocusedAgent();
						for (Element n : agent.getNeighbors()) {
							System.out.println("- (" + n.getLabel() + ") " + n);
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
		else if (cmd.equalsIgnoreCase(KbclCLICommand.STATS.getCmd())) {
			// print statistics
			System.out.println("KBCL Statistics:");
			System.out.println("- Total inference time: " + this.kbcl.getTotalInferenceTime() + " msecs");
			System.out.println("- Maximum time spent for inferring data after operation: " + this.kbcl.getMaxInferenceTime() + " msecs");
			System.out.println("- Planning Domain synthesis time: " + this.kbcl.getMappingTime() + " msecs");
			System.out.println("- Maximum Planning Domain synthesis time: " + this.kbcl.getMaxMappingTime() + " msecs");
			System.out.println("- Total Planning time: " + this.kbcl.getTotalPlanningTime() + " msecs");
			System.out.println("- Maximum Planning time for a request: " + this.kbcl.getMaximumPlanningTime() + " msecs");
		}
		else if (cmd.equalsIgnoreCase(KbclCLICommand.SELECT.getCmd())) {
			// get parameter
			String label = (splits != null && splits.length > 1) ? splits[1].trim() : null;
			if (label != null) {
				try 
				{
					// get agent by label
					Agent agent = this.kbcl.getAgent(label);
					if (agent != null) {
						// initialize mapping 
						this.kbcl.setup(agent);
						System.out.println("Selected agent:\n" + agent);
					}
					else {
						System.out.println("No agent found with label " + label);
					}
				}
				catch (RDFResourceNotFoundException | RDFPropertyNotFoundException ex) {
					System.err.println("Error while retrieving KB's resources\n" + ex.getMessage());
				}
				catch (DDLPlanningModelInitializationFailureException ex) {
					System.err.println("Error while building planning model\n" + ex.getMessage());
				}
			}
			else {
				System.out.println("Select agent's label");
			}
		}
		else if (cmd.equalsIgnoreCase(KbclCLICommand.PLAN.getCmd())) {
			// check if agent has been selected
			try {
				// get index of selected functionality to plan with
				String funcLabel = (splits != null && splits.length > 1) ? splits[1].trim() : null;
				if (funcLabel != null) {
					try {
						// get agent
						Agent agent = this.kbcl.getFocusedAgent();
						// select functionality
						Functionality func = agent.getFunctionality(funcLabel);
						if (func != null) {
							System.out.println("Selected functionality\n" + func);
							
							// request functionality
							this.kbcl.plan(func);
							// print resulting PDB
							System.out.println(this.kbcl.getPDBDescription());
						}
						else {
							System.out.println("No functionality found with label " + funcLabel);
						}
					}
					catch (KbclRequestProcessingFailureException | RDFResourceNotFoundException ex) {
						System.err.println(ex.getMessage());
					}
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
