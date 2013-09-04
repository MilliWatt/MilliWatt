package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;

public class HttpsPage {
	
	String urlName;
	URL url;
	HttpsURLConnection conn;
	
	

	public HttpsPage(String urlName) {
		super();
		this.urlName = urlName;
		try {
			this.url = new URL(urlName);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.setProperty("javax.net.ssl.trustStore", (Global.KEY_DIR +"matriculaweb.unb.br.jks"));		
	}


	public void connect() {
		this.conn = new HttpsURLConnection(url) {
			
			@Override
			public void connect() throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean usingProxy() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void disconnect() {
				// TODO Auto-generated method stub
				//this.disconnect();
			}
			
			@Override
			public Certificate[] getServerCertificates()
					throws SSLPeerUnverifiedException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Certificate[] getLocalCertificates() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getCipherSuite() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		
		
		this.conn.setDefaultHostnameVerifier(new HostnameVerifier() {
			
			@Override
			public boolean verify(String arg0, SSLSession arg1) {
				// TODO Auto-generated method stub
				return true;
			}
		});
		

		
	}


	public void disconnect() {
		// TODO Auto-generated method stub
		this.conn.disconnect();
		
	}

	
	public String getHTML() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getOnlyHTML() {
		
		try{
		String aux = null;
		aux = Global.HTML_DIR  + this.url.hashCode() + this.url.getHost();
		File file= new File(aux);
		BufferedReader in = new BufferedReader(new InputStreamReader(this.url.openStream()));
		
		BufferedWriter out = new BufferedWriter(new FileWriter(file));
		
		String inputLine;
		
		while ((inputLine = in.readLine()) != null) {
            // Imprime página no console
           // System.out.println(inputLine);//DEBUG
            // Grava pagina no arquivo
            out.write(inputLine);
            out.newLine();
            aux = aux+inputLine + "\n";
        }
		in.close();
		out.flush();
		out.close();
		return aux;
		}catch(IOException e){
			
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	
	
	

}
