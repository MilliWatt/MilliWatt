package milliwatt.model;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class Page {
	
	String urlName;
	URL url;
	
	public Page(String urlName) {
		super();
		this.urlName = urlName;

		try {
			this.url = new URL(urlName);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public String getUrlName() {
		return urlName;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}
	
	
	
	
	
//	public abstract void connect();
//	public abstract void disconnect();
//	public abstract String getHTML();
//	public abstract String getOnlyHTML();

}
