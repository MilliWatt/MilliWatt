package milliwatt.model;

import java.util.ArrayList;

public class Turma {

	private String identificador;
	private String horarioInicioTurma;
	private String horarioFimTurma;
	private ArrayList<String> diasTurma;
	
	public Turma(){}

	public Turma(String identificador, String horarioInicioTurma,
			String horarioFimTurma, ArrayList<String> diasTurma) {
		this.identificador = identificador;
		this.horarioInicioTurma = horarioInicioTurma;
		this.horarioFimTurma = horarioFimTurma;
		this.diasTurma = diasTurma;
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
