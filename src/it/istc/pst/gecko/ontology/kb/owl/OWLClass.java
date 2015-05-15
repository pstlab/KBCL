package it.istc.pst.gecko.ontology.kb.owl;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class OWLClass 
{
	private String url;
	private String label;
	
	/**
	 * 
	 * @param url
	 * @param label
	 */
	protected OWLClass(String url, String label) {
		this.url = url;
		this.label = label;
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getLabel() {
		return label;
	}
	
	@Override
	public int hashCode() {
		return this.url.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof OWLClass) && ((OWLClass) obj).url.equals(this.url);
	}
	
	@Override
	public String toString() {
		return "[OWLClass url= " + this.url + ", label= " + this.label +"]";
	}
}
