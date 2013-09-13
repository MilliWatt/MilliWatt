package milliwatt.model;

import java.util.ArrayList;

public class Turma {

	private String identificador;
	private String horarioInicioTurma;
	private String horarioFimTurma;
	private int total_vagas;
	private int vagas_disponiveis;
	private int vagas_ocupadas;
	private ArrayList<String> diasTurma;
	private ArrayList<Professor> listaProfessores;
	
	public Turma(){}

	public Turma(String identificador, String horarioInicioTurma,String horarioFimTurma, int total_vagas, 
			int vagas_disponiveis,int vagas_ocupadas, ArrayList<String> diasTurma,ArrayList<Professor> listaProfessores) {
		this.identificador = identificador;
		this.horarioInicioTurma = horarioInicioTurma;
		this.horarioFimTurma = horarioFimTurma;
		this.total_vagas = total_vagas;
		this.vagas_disponiveis = vagas_disponiveis;
		this.vagas_ocupadas = vagas_ocupadas;
		this.diasTurma = diasTurma;
		this.listaProfessores = listaProfessores;
	}

	public int getTotal_vagas() {
		return total_vagas;
	}

	public void setTotal_vagas(int total_vagas) {
		this.total_vagas = total_vagas;
	}

	public int getVagas_disponiveis() {
		return vagas_disponiveis;
	}

	public void setVagas_disponiveis(int vagas_disponiveis) {
		this.vagas_disponiveis = vagas_disponiveis;
	}

	public int getVagas_ocupadas() {
		return vagas_ocupadas;
	}

	public void setVagas_ocupadas(int vagas_ocupadas) {
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
		return horarioInicioTurma;
	}

	public void setHorarioInicioTurma(String horarioInicioTurma) {
		this.horarioInicioTurma = horarioInicioTurma;
	}

	public String getHorarioFimTurma() {
		return horarioFimTurma;
	}

	public void setHorarioFimTurma(String horarioFimTurma) {
		this.horarioFimTurma = horarioFimTurma;
	}

	public ArrayList<String> getDiasTurma() {
		return diasTurma;
	}

	public void setDiasTurma(ArrayList<String> diasTurma) {
		this.diasTurma = diasTurma;
	}
	
	
	
}
