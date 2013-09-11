package milliwatt.model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Campus {
	String id;
	String nome;
	String urlName;
	URL url;
	
	ArrayList<Departamento> departamentoList;
	
	public Campus(){
		this.id="";
		this.nome="";
		this.urlName="";
		this.url=null;
		this.departamentoList= null;
	}

	public Campus(String id, String nome, String urlName) {
		super();
		this.id = id;
		this.nome = nome;
		this.urlName = urlName;
		try {
			this.url = new URL(urlName);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.departamentoList = new ArrayList<Departamento>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public ArrayList<Departamento> getDepartamentoList() {
		return departamentoList;
	}

	public void setDepartamentoList(ArrayList<Departamento> departamentoList) {
		this.departamentoList = departamentoList;						
	}

	@Override
	public String toString() {
		return "Campus [id=" + id + ", nome=" + nome + ", urlName=" + urlName
				+ ", url=" + url + ", departamentoList=" + departamentoList
				+ "]";
	}
	
	
	
	//metodos que fazem algo mais ninja.
	
	public void addDepartamento(Departamento dep){
		this.departamentoList.add(dep);
	}
	
	
	
	
	
}
