package milliwatt.model;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Departamento implements Serializable{

	private String codigo;
	private String nome;
	private String sigla;
	private String urlName;
	private URL url;
	private ArrayList<Disciplina> DisciplinaList;
	
	public Departamento(String codigo, String nome, String sigla, String urlName) {
		
		this.codigo = codigo;	
		this.nome = nome;
		this.sigla = sigla;
		this.urlName = urlName;
		try {
			this.url = new URL(urlName);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		this.DisciplinaList = new ArrayList<Disciplina>();
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getCodigo() {
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

	public ArrayList<Disciplina> getDisciplinalist() {
		return DisciplinaList;
	}

	public void setDisciplinalist(ArrayList<Disciplina> disciplinalist) {
		DisciplinaList = disciplinalist;
	}

	

	
	
}
