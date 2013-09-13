package milliwatt.model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Disciplina {

	private int codigoDisciplina;
	private String nomeDisciplina;
	private String urlName;
	private URL url;
	ArrayList<Turma> turmasDisciplina;

	public Disciplina(){
		
	}
	
	public Disciplina(int codigoDisciplina, String nomeDisciplina, ArrayList<Professor> professoresDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
		this.nomeDisciplina = nomeDisciplina;
		this.turmasDisciplina = new ArrayList<Turma>();
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
	
	public int getCodigoDisciplina() {
		return codigoDisciplina;
	}

	public void setCodigoDisciplina(int codigoDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}
	
	public ArrayList<Turma> getTurmasDisciplina() {
		return turmasDisciplina;
	}

	public void setTurmasDisciplina(ArrayList<Turma> turmasDisciplina) {
		this.turmasDisciplina = turmasDisciplina;
	}
	
}
