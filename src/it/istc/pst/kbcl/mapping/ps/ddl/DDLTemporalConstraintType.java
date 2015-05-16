package it.istc.pst.kbcl.mapping.ps.ddl;

/**
 * 
 * @author alessandroumbrico
 *
 */
public enum DDLTemporalConstraintType {
	
	/**
	 * 
	 */
	EQUALS("EQUALS"),
	
	/**
	 * 
	 */
	MEETS("MEETS"),
	
	/**
	 * 
	 */
	BEFORE("BEFORE"),
	
	/**
	 * 
	 */
	STARTS_DURING("STARTS-DURING"),
	
	/**
	 * 
	 */
	ENDS_DURING("ENDS-DURING"),
	
	/**
	 * 
	 */
	START_START("START-START"),
	
	/**
	 * 
	 */
	END_END("END-END"),
	
	/**
	 * 
	 */
	CONTAINS("CONTAINS"),
	
	/**
	 * 
	 */
	DURING("DURING");
	
	String label;
	
	/**
	 * 
	 * @param label
	 */
	private DDLTemporalConstraintType(String label) {
		this.label = label;
	}
	 
	/**
	 * 
	 * @param label
	 * @return
	 */
	public static DDLTemporalConstraintType getType(String label) {
		DDLTemporalConstraintType type = null;
		// check label
		switch (label.toUpperCase()) {
			case "DURING" : {
				type = DDLTemporalConstraintType.DURING;
				break;
			}
			
			case "CONTAINS" : {
				type = DDLTemporalConstraintType.CONTAINS;
				break;
			}
			
			case "STARTS-DURING" : {
				type = DDLTemporalConstraintType.STARTS_DURING;
				break;
			}
			
			case "ENDS-DURING" : {
				type = DDLTemporalConstraintType.ENDS_DURING;
				break;
			}
			
			case "START-START" : {
				type = DDLTemporalConstraintType.START_START;
				break;
			}
			
			case "END-END" : {
				type = DDLTemporalConstraintType.END_END;
				break;
			}
			
			case "BEFORE" : {
				type = DDLTemporalConstraintType.BEFORE;
				break;
			}
			
			case "MEETS" : {
				type = DDLTemporalConstraintType.MEETS;
				break;
			}
			
			case "EQUALS" : {
				type = DDLTemporalConstraintType.EQUALS;
				break;
			}
			
			default : {
				throw new RuntimeException("Unknown temporal constraint type " + label);
			}
		}
		return type;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		String description = this.name();
		// check type
		switch (this) {
		
			case MEETS :
			case EQUALS : {
				description += " ";
			}
			break;
		
			case START_START : 
			case END_END : 
			case BEFORE: {
				description += " [0,+INF] ";
			}
			break;
			
			case STARTS_DURING : 
			case ENDS_DURING : 
			case DURING :
			case CONTAINS: {
				description += " [0,+INF] [0,+INF] ";
			}
			break;
			
			default : {
				throw new RuntimeException("Unknown temporal constraint type " + this.label);
			}
		}
		return description;
	}
}
