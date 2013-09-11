package milliwatt.model;

import java.net.MalformedURLException;
import java.net.URL;

public class Departamento {

	private int codigo;
	private String nome;
	private String urlName;
	private URL url;
	
	
	public Departamento(int codigo, String nome, String urlName, URL url) {
		
		this.codigo = codigo;	
		this.nome = nome;
		this.urlName = urlName;
		this.url = url;
		try {
			this.url = new URL(urlName);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	
	
}
