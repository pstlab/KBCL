package it.istc.pst.kbcl.inference.kb.owl;

/**
 * 
 * @author alessandroumbrico
 *
 */
public enum KBCLVocabulary_v2 {

	ONTOLOGY_URL("http://pst.istc.cnr.it/kbcl/ontology"),
	
	COSTANT_FAILURE_PERFORMANCE_LABEL("failure"),
	COSTANT_FAILURE_PERFORMANCE_URI(ONTOLOGY_URL + "#" + COSTANT_FAILURE_PERFORMANCE_LABEL),
	
	COSTANT_OPERATIVE_PERFORMANCE_LABEL("operative"),
	COSTANT_OPERATIVE_PERFORMANCE_URI(ONTOLOGY_URL + "#" + COSTANT_OPERATIVE_PERFORMANCE_LABEL),
	
	COSTANT_MAINTENANCE_PERFORMANCE_LABEL("maintenance"),
	COSTANT_MAINTENANCE_PERFORMANCE_URI(ONTOLOGY_URL + "#" + COSTANT_MAINTENANCE_PERFORMANCE_LABEL),
	
	CLASS_FUNCTION_LABEL("Function"),
	CLASS_FUNCTION_URI(ONTOLOGY_URL + "#" + CLASS_FUNCTION_LABEL),
	
	CLASS_FUNCTION_CHANNEL_LABEL("Channel"),
	CLASS_FUNCTION_CHANNEL_URI(ONTOLOGY_URL + "#" + CLASS_FUNCTION_CHANNEL_LABEL),
	
	CLASS_FUNCTION_CHANGE_OVER_LABEL("ChangeOver"),
	CLASS_FUNCTION_CHANGE_OVER_URI(ONTOLOGY_URL + "#" + CLASS_FUNCTION_CHANGE_OVER_LABEL),
	
	
	CLASS_ROBOT_LABEL("Robot"),
	CLASS_ROBOT_URI(ONTOLOGY_URL + "#" + CLASS_ROBOT_LABEL),
	
	CLASS_TRANSPORTATIO_MODULE_LABEL("TransportationModule"),
	CLASS_TRANSPORTATIO_MODULE_URI(ONTOLOGY_URL + "#" + CLASS_TRANSPORTATIO_MODULE_LABEL),
	
	CLASS_DEVICE_LABEL("Device"),
	CLASS_DEVICE_URI(ONTOLOGY_URL + "#" + CLASS_DEVICE_LABEL),
	
	CLASS_COMPONENT_LABEL("Component"),
	CLASS_COMPONENT_URI(ONTOLOGY_URL + "#" + CLASS_COMPONENT_LABEL),
	
	CLASS_CONVEYOR_LABEL("Conveyor"),
	CLASS_CONVEYOR_URI(ONTOLOGY_URL + "#" + CLASS_CONVEYOR_LABEL),
	
	CLASS_PORT_LABEL("Port"),
	CLASS_PORT_URI(ONTOLOGY_URL + "#" + CLASS_PORT_LABEL),
	
	CLASS_CROSS_TRANSFER_LABEL("CrossTransfer"),
	CLASS_CROSS_TRANSFER_URI(ONTOLOGY_URL + "#" + CLASS_CROSS_TRANSFER_LABEL),
	
	CLASS_QUALITY_LABEL("Quality"),
	CLASS_QUALITY_URI(ONTOLOGY_URL + "#" + CLASS_QUALITY_LABEL),
	
	CLASS_PHYSICAL_QUALITY_LABEL("PhysicalQuality"),
	CLASS_PHYSICAL_QUALITY_URI(ONTOLOGY_URL + "#" + CLASS_PHYSICAL_QUALITY_LABEL),
	
	CLASS_SPATIAL_LOCATION_LABEL("SpatialLocation"),
	CLASS_SPATIAL_LOCATION_URI(ONTOLOGY_URL + "#" + CLASS_SPATIAL_LOCATION_LABEL),
	
	CLASS_PERFORMANCE_LABEL("Performance"),
	CLASS_PERFORMANCE_URI(ONTOLOGY_URL + "#" + CLASS_PERFORMANCE_LABEL),
	
	CLASS_OPERATIVE_LABEL("Operative"),
	CLASS_OPERATIVE_URI(ONTOLOGY_URL + "#" + CLASS_OPERATIVE_LABEL),
	
	CLASS_NON_OPERATIVE_LABEL("NonOperative"),
	CLASS_NON_OPERATIVE_URI(ONTOLOGY_URL + "#" + CLASS_NON_OPERATIVE_LABEL),
	
	CLASS_MAINTENANCE_LABEL("Maintenance"),
	CLASS_MAINTENANCE_URI(ONTOLOGY_URL + "#" + CLASS_MAINTENANCE_LABEL),
	
	CLASS_FAILURE_LABEL("Failure"),
	CLASS_FAILURE_URI(ONTOLOGY_URL + "#" + CLASS_FAILURE_LABEL),
	
	PROPERTY_CAN_DO_FUNCTION_LABEL("canDoFunction"),
	PROPERTY_CAN_DO_FUNCTION_URI(ONTOLOGY_URL + "#" + PROPERTY_CAN_DO_FUNCTION_LABEL),
	
	PROPERTY_CAN_DO_CHANNEL_LABEL("canDoChannel"),
	PROPERTY_CAN_DO_CHANNEL_URI(ONTOLOGY_URL + "#" + PROPERTY_CAN_DO_CHANNEL_LABEL),
	
	PROPERTY_CAN_DO_CHANGE_OVER_LABEL("canDoChangeOver"),
	PROPERTY_CAN_DO_CHANGE_OVER_URI(ONTOLOGY_URL + "#" + PROPERTY_CAN_DO_CHANGE_OVER_LABEL),
	
	PROPERTY_HAS_DEVICE_LABEL("hasDevice"),
	PROPERTY_HAS_DEVICE_URI(ONTOLOGY_URL + "#" + PROPERTY_HAS_DEVICE_LABEL),
	
	PROPERTY_HAS_COMPONENT_LABEL("hasComponent"),
	PROPERTY_HAS_COMPONENT_URI(ONTOLOGY_URL + "#" + PROPERTY_HAS_COMPONENT_LABEL),
	
	PROPERTY_HAS_PORT_LABEL("hasPort"),
	PROPERTY_HAS_PORT_URI(ONTOLOGY_URL + "#" + PROPERTY_HAS_PORT_LABEL),
	
	PROPERTY_HAS_CROSS_TRANSFER_LABEL("hasCrossTransfer"),
	PROPERTY_HAS_CROSS_TRANSFER_URI(ONTOLOGY_URL + "#" + PROPERTY_HAS_CROSS_TRANSFER_LABEL),
	
	PROPERTY_HAS_CONVEYOR_LABEL("hasConveyor"),
	PROPERTY_HAS_CONVEYOR_URI(ONTOLOGY_URL + "#" + PROPERTY_HAS_CONVEYOR_LABEL),
	
	PROPERTY_HAS_COLLABORATOR_LABEL("hasCollaborator"),
	PROPERTY_HAS_COLLABORATOR_URI(ONTOLOGY_URL + "#" + PROPERTY_HAS_COLLABORATOR_LABEL),
	
	PROPERTY_HAS_PERFORMANCE_LABEL("hasPerformance"),
	PROPERTY_HAS_PERFORMANCE_URI(ONTOLOGY_URL + "#" + PROPERTY_HAS_PERFORMANCE_LABEL),
	
	PROPERTY_HAS_LOCATION_LABEL("hasLocation"),
	PROPERTY_HAS_LOCATION_URI(ONTOLOGY_URL + "#" + PROPERTY_HAS_LOCATION_LABEL),
	
	PROPERTY_START_LOCATION_LABEL("startLocation"),
	PROPERTY_START_LOCATION_URI(ONTOLOGY_URL + "#" + PROPERTY_START_LOCATION_LABEL),
	
	PROPERTY_END_LOCATION_LABEL("endLocation"),
	PROPERTY_END_LOCATION_URI(ONTOLOGY_URL + "#" + PROPERTY_END_LOCATION_LABEL),
	
	PROPERTY_CONNECTION_LABEL("connection"),
	PROPERTY_CONNECTION_URI(ONTOLOGY_URL + "#" + PROPERTY_CONNECTION_LABEL);
	
	private String value;
	
	/**
	 * 
	 * @param value
	 */
	private KBCLVocabulary_v2(String value) {
		this.value = value;
	}

	/**
	 * 
	 * @return
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return this.value;
	}
	
}