package milliwatt.model;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

@SuppressWarnings("serial")
public class Disciplina implements Serializable{

	private String codigoDisciplina;
	private String nomeDisciplina;
	private String urlName;
	private URL url;	

	public Disciplina(){
		
	}
	
	public Disciplina(String codigoDisciplina, String nomeDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
		this.nomeDisciplina = nomeDisciplina;
	}
	
	public Disciplina(String codigoDisciplina, String nomeDisciplina, String urlName) {
		this.codigoDisciplina = codigoDisciplina;
		this.nomeDisciplina = nomeDisciplina;
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
	
	public String getCodigoDisciplina() {
		return codigoDisciplina;
	}

	public void setCodigoDisciplina(String codigoDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}
	
	
}
