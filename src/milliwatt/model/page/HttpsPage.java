package milliwatt.model.page;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;

import java.security.cert.Certificate;

import milliwatt.utils.Global;

public class HttpsPage extends Page {


	HttpsURLConnection conn;

	
	
	
	
//	public HttpsPage(String urlName) {
//		this.urlName = urlName;
//		try {
//			this.url = new URL(urlName);
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}
//		System.setProperty("javax.net.ssl.trustStore", (Global.KEY_DIR + "matriculaweb.unb.br.jks"));
//	}

	
	public HttpsPage(String urlName) {
		super(urlName);
		System.setProperty("javax.net.ssl.trustStore", (Global.KEY_DIR + "matriculaweb.unb.br.jks"));
		this.conn = null;
		// TODO Auto-generated constructor stub
	}
	
	


	public HttpsURLConnection getConn() {
		return conn;
	}




	public void setConn(HttpsURLConnection conn) {
		this.conn = conn;
	}




	public void connect() {
		
		this.conn = new HttpsURLConnection(url) {

			@Override
			public void connect() throws IOException {}

			@Override
			public boolean usingProxy() {
				return false;
			}

			@Override
			public void disconnect() {
				// this.disconnect();
			}

			@Override
			public Certificate[] getServerCertificates()
					throws SSLPeerUnverifiedException {
				return null;
			}

			@Override
			public Certificate[] getLocalCertificates() {
				return null;
			}

			@Override
			public String getCipherSuite() {
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
		this.conn.disconnect();

	}

	
	public String getHTML() {
		return null;
	}

	
	public String getOnlyHTML() {

		try {
			String aux = null;
			aux = Global.HTML_DIR + this.url.hashCode() + this.url.getHost();
			File file = new File(aux);
			BufferedReader in = new BufferedReader(new InputStreamReader(this.url.openStream()));

			BufferedWriter out = new BufferedWriter(new FileWriter(file));

			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				// Imprime página no console
				// System.out.println(inputLine);//DEBUG
				// Grava pagina no arquivo
				out.write(inputLine);
				out.newLine();
				aux = aux + inputLine + "\n";
			}
			in.close();
			out.flush();
			out.close();
			return aux;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
