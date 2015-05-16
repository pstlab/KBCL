package it.istc.pst.kbcl.ontology.kb.owl;


/**
 * 
 * @author alessandroumbrico
 *
 */
public class OWLInstance 
{
	private OWLClass type;
	private String url;
	private String label;
	
	/**
	 * 
	 * @param url
	 * @param label
	 * @param type
	 */
	protected OWLInstance(String url, String label, OWLClass type) {
		this.url = url;
		this.label = label;
		this.type = type;
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getLabel() {
		return label;
	}
	
	public OWLClass getType() {
		return type;
	}
	
	@Override
	public int hashCode() {
		return this.url.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof OWLInstance) && ((OWLInstance) obj).url.equals(this.url);
	}
	
	@Override
	public String toString() {
		return "[OWLInstance url= " + this.url + ", label= " + this.label+ ", type= " + this.type + "]";
	}
}
