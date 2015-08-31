package it.istc.pst.kbcl.app.cli;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author alessandroumbrico
 *
 */
public enum KbclCLICommand 
{
	/**
	 * help command
	 */
	HELP("?", "help command", new String[] {}),
	
	/**
	 * show domain information
	 */
	SHOW("show", "show KB's information - options {agents, agent-types, functionality-types, functionalities, components, neighbors}", 
			new String[] {"agents", "agent-types", "functionality-types", "functionalities", "components", "neighbors"}),
	
	/**
	 * select an agent on which to initialize the KBCL
	 */
	SELECT("select", "select an agent on which to initialize the KBCL", 
			new String[] {}),
	
	/**
	 * 
	 */
	STATS("stats", "print statistics about inference and mapping time", 
			new String[] {}),
	
	/**
	 * 
	 */
	ADD("add", "add an element to the current agent", 
			new String[] {}),
	
	/**
	 * 
	 */
	REMOVE("remove", "remove a component of the module", 
			new String[] {}),
	
	/**
	 * 
	 */
	EVENT("event", "an exogenous event concerning module's neighbors - options {neighbor-off, port-off, component-off}", 
			new String[] {"neighbor-off", "port-off", "component-off"}),
	
	/**
	 * 
	 */
	PLAN("plan", "plan a functional activity", 
			new String[] {}),
	
	/**
	 * exit command
	 */
	EXIT("exit", "close CLI client", 
			new String[] {});
	
	private String cmd;
	private String usage;
	private String[] parameters;
	
	/**
	 * 
	 * @param cmd
	 * @param usage
	 * @param parameters
	 */
	private KbclCLICommand(String cmd, String usage , String[] parameters) {
		this.cmd = cmd;
		this.usage = usage;
		this.parameters = parameters;
	}
	
	public String getCmd() {
		return cmd.toUpperCase();
	}
	
	public String getHelp() {
		return usage;
	}
	
	public List<String> getCmdParameters() {
		return Arrays.asList(this.parameters);
	}
}
