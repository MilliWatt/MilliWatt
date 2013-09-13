package milliwatt.model.page;

import java.net.MalformedURLException;
import java.net.URL;

public abstract  class Page implements PagePattern{
	
	String urlName;
	URL url;
	
	public  Page(String urlName) {
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
	
	public static Page getPage(String urlName){
		String aux = urlName.substring(0,5);
		System.out.println(aux);
		if(aux.equals("https")) return new HttpsPage(urlName);
		if(aux.equals("http:")) return new HttpPage(urlName);
		else return null;
		
	}

}
	
	
	
	
	



