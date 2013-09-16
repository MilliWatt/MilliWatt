package milliwatt.model;

import java.io.Serializable;
//import java.net.MalformedURLException;
//import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("serial")
public class Turma implements Serializable{

	private String identificador;
	private String diaHoraInicioTurma;
	private String diaHoraFimTurma;
	private String total_vagas;
	private String vagas_disponiveis;
	private String vagas_ocupadas;
	private ArrayList<String> local;
	private ArrayList<String> diasTurma;
	private ArrayList<Professor> listaProfessores;
	private Disciplina disciplina;

	public Turma(){}

	public Turma(String identificador, String diaHoraInicioTurma, String diaHoraFimTurma, String total_vagas, 
			String vagas_disponiveis,String vagas_ocupadas, ArrayList<String> diasTurma,
			ArrayList<Professor> listaProfessores, Disciplina disciplina, ArrayList<String> local) {
		this.identificador = identificador;
		this.diaHoraInicioTurma = diaHoraInicioTurma;
		this.diaHoraFimTurma = diaHoraFimTurma;
		this.total_vagas = total_vagas;
		this.vagas_disponiveis = vagas_disponiveis;
		this.vagas_ocupadas = vagas_ocupadas;
		this.diasTurma = diasTurma;
		this.listaProfessores = listaProfessores;
		this.disciplina = disciplina;
		this.local = local;

	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	public String getTotal_vagas() {
		return total_vagas;
	}

	public ArrayList<String> getLocal() {
		return local;
	}

	public void setLocal(ArrayList<String> local) {
		this.local = local;
	}

	public void setTotal_vagas(String total_vagas) {
		this.total_vagas = total_vagas;
	}

	public String getVagas_disponiveis() {
		return vagas_disponiveis;
	}

	public void setVagas_disponiveis(String vagas_disponiveis) {
		this.vagas_disponiveis = vagas_disponiveis;
	}

	public String getVagas_ocupadas() {
		return vagas_ocupadas;
	}

	public void setVagas_ocupadas(String vagas_ocupadas) {
		this.vagas_ocupadas = vagas_ocupadas;
	}

	public ArrayList<Professor> getListaProfessores() {
		return listaProfessores;
	}

	public void setListaProfessores(ArrayList<Professor> listaProfessores) {
		this.listaProfessores = listaProfessores;
	}
	
	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getHorarioInicioTurma() {
		return diaHoraInicioTurma;
	}

	public void setHorarioInicioTurma(String horarioInicioTurma) {
		this.diaHoraInicioTurma = horarioInicioTurma;
	}

	public String getHorarioFimTurma() {
		return diaHoraFimTurma;
	}

	public void setHorarioFimTurma(String horarioFimTurma) {
		this.diaHoraFimTurma = horarioFimTurma;
	}

	public ArrayList<String> getDiasTurma() {
		return diasTurma;
	}

	public void setDiasTurma(ArrayList<String> diasTurma) {
		this.diasTurma = diasTurma;
	}
	
	
	
}
