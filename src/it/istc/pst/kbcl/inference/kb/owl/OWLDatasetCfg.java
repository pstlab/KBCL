package it.istc.pst.kbcl.inference.kb.owl;

/**
 * 
 * @author alessandroumbrico
 *
 */
public enum OWLDatasetCfg 
{
	PROPERTY_TBOX_PATH("tbox_path"),
	PROPERTY_ABOX_PATH("abox_path"),
	PROPERTY_ABOX_URL("abox_url"),
	PROPERTY_RULES_PATH("rules_path");
	
	private String value;
	
	private OWLDatasetCfg(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
