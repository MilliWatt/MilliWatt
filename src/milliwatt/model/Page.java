package milliwatt.model;

public abstract class Page {
	
	public abstract void connect();
	public abstract void disconnect();
	public abstract String getHTML();
	public abstract String getOnlyHTML();

}
