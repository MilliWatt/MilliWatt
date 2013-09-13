package milliwatt.model.page;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import com.sun.net.ssl.HttpsURLConnection;

import milliwatt.utils.Global;

public class HttpPage extends Page {
	
	HttpURLConnection conn;
	
	public HttpPage(String urlName) {
		super(urlName);
		// TODO Auto-generated constructor stub
	}

	

//
//	public HttpPage(String urlName) {
//		//super();
//		this.urlName = urlName;
//		try {
//			this.url = new URL(urlName);
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}
//	}

	


	public void connect() {}
	
	
	public void disconnect() {}
	
	
	public String getHTML() {
		return null;
	}
	
	
	public String getOnlyHTML() {
		
		try{
		String aux = null;
		aux = Global.HTML_DIR  + this.url.hashCode() + this.url.getHost();
		File file= new File(aux);
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		
		BufferedWriter out = new BufferedWriter(new FileWriter(file));
		
		String inputLine;
		
		while ((inputLine = in.readLine()) != null) {
            // Imprime página no console
            //System.out.println(inputLine);//DEBUG
            // Grava pagina no arquivo
            out.write(inputLine);
            out.newLine();
            aux = aux+inputLine+"\n";
        }
		in.close();
		out.flush();
		out.close();
		return inputLine;
		}catch(IOException e){
			
			e.printStackTrace();
		}
		
		return null;
	}



}
