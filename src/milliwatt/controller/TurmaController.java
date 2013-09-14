package milliwatt.controller;

import java.util.ArrayList;
import java.util.HashMap;

import milliwatt.model.Departamento;
import milliwatt.model.Disciplina;
import milliwatt.model.Professor;
import milliwatt.model.Turma;
import milliwatt.model.page.HttpsPage;
import milliwatt.utils.Global;

public class TurmaController {
	
	public static String capturaTotalVagasTurma(String htmlString, int index){
		
		String total_vagas = "";
		
		index += 7;
		
		while (htmlString.charAt(index)!= ' '){
			total_vagas += htmlString.charAt(index);
			index++;
		}
		return total_vagas;
	}
	
	public static String capturaVagasOcupadasTurma(String htmlString, int index){
		
		String vagas_ocupadas = "";
		
		index += 23;
		
		while (htmlString.charAt(index)!= '<'){
			vagas_ocupadas += htmlString.charAt(index);
			index++;
		}
		return vagas_ocupadas;
	}
	
	public static String capturaVagasDisponiveisTurma(String htmlString, int index){
		
		String vagas_disponiveis = "";
		
		index += 23;
		
		while (htmlString.charAt(index)!= '<'){
			vagas_disponiveis += htmlString.charAt(index);
			index++;
		}
		return vagas_disponiveis;
	}
	
	public static String[] capturaInformacoesDaTurma(String inicioStringDias, String fimStringDias, String htmlString){
		
		String dia = "";
		String local = "";
		String horaFim = "";
		String horaInicio = "";
		String professor = "";
		String[] informacoesDaTurma = new String[10];
		
		int fim = 0;
		int inicio = 0;
		String htmlStringPartida = "";
		int indice = 0;
		
		do{
			inicio = htmlString.indexOf(inicioStringDias);
			fim = htmlString.indexOf(fimStringDias);
			htmlStringPartida = htmlString.substring(inicio, fim);
			
			if(inicio == -1)
				break;
			
			while (htmlStringPartida.charAt(inicio)!='>'){
				inicio++;
			}
			
			inicio+=3;
			
			while (htmlStringPartida.charAt(inicio)!='<'){
				dia += htmlStringPartida.charAt(inicio);
				inicio++;
			}
			
			informacoesDaTurma[indice] = dia;
			inicio += 33;
			
			while (htmlStringPartida.charAt(inicio) != '<'){
				horaInicio += htmlStringPartida.charAt(inicio);
				inicio++;
			}
			
			informacoesDaTurma[indice] = horaInicio;
			inicio += 33;
			
			while (htmlStringPartida.charAt(inicio) != '<'){
				horaFim += htmlStringPartida.charAt(inicio);
				inicio++;
			}
			informacoesDaTurma[indice] = horaFim;
			inicio += 75;
			
			while (htmlStringPartida.charAt(inicio) != '<'){
				local += htmlStringPartida.charAt(inicio);
				inicio++;
			}
			informacoesDaTurma[indice] = local;

			htmlStringPartida = htmlStringPartida.substring(inicio, fim);
			
		}while(inicio != fim);
		
		inicio = htmlStringPartida.indexOf("<center>")+8;
		
		while(htmlStringPartida.charAt(inicio) != '<'){
			professor += htmlStringPartida.charAt(inicio);
			inicio++;
		}
		
		informacoesDaTurma[indice] = professor;
		
		return informacoesDaTurma;
	}
	
	public static String capturaIdentificadorTurma(String htmlString, int index){
		
		String identificador = "";
		
		index += 16;
		
		while (htmlString.charAt(index)!='<'){
			identificador += htmlString.charAt(index);
			index++;
		}
		
		return identificador;
		
	}
	
	public static Disciplina capturaDisciplinaDesejada(ArrayList<Disciplina> disciplinaList, String id_disciplina){
		
		Disciplina disciplina = null;
		
		for(Disciplina d : disciplinaList){
			
			System.out.println(d.getCodigoDisciplina());
			
			if(d.getCodigoDisciplina().equals(id_disciplina))
				disciplina = d;
		}

		return disciplina;
		
	}

	
	public static ArrayList<Turma> getTurmaList(ArrayList<Disciplina> disciplinaList, String id_disciplina){

		int index = 0;
		String identificador = "";
		String horarioInicio = "";
		String horarioFim = "";
		String total_vagas = "";
		String vagas_disponiveis = "";
		String vagas_ocupadas = "";
		ArrayList<Professor> professorList = new ArrayList<Professor>();
		ArrayList<String> diaList = new ArrayList<String>();
		ArrayList<Turma> turmaList = new ArrayList<Turma>();
		String[] informacoesDaTurma = null;
		Turma turma = null;
		Professor professor = null;
		
		Disciplina disciplinaDesejada = capturaDisciplinaDesejada(disciplinaList, id_disciplina);
		
		//System.out.println(disciplinaDesejada.getUrlName());
		
		String urlName_disciplina = disciplinaDesejada.getUrlName();
		
		//System.out.println(urlName_disciplina);
		
		HttpsPage site = new HttpsPage(urlName_disciplina);
		
		site.connect();
		
		String htmlString = site.getOnlyHTML();
		
		while(index !=-1){

			index = htmlString.indexOf(Global.MW_CLASS_ID);

			identificador = capturaIdentificadorTurma(htmlString, index);
			
			index = htmlString.indexOf(Global.MW_CLASS_VACANCIES);
			
			total_vagas = capturaTotalVagasTurma(htmlString, index);
			
			index = htmlString.indexOf(Global.MW_CLASS_VACANCIES_OCCUPIED);
			
			vagas_ocupadas = capturaVagasOcupadasTurma(htmlString, index);
			
			index = htmlString.indexOf(Global.MW_CLASS_VACANCIES_AVAILABLE);
			
			vagas_disponiveis = capturaVagasDisponiveisTurma(htmlString, index);
			
			informacoesDaTurma = capturaInformacoesDaTurma(Global.MW_CLASS_DAYS_BEGIN, Global.MW_CLASS_DAYS_END, htmlString);
			
			switch(informacoesDaTurma.length){
			
				case 4:
					diaList.add(informacoesDaTurma[0]);
					horarioInicio = informacoesDaTurma[1];
					horarioFim = informacoesDaTurma[2];
					professor = new Professor(informacoesDaTurma[3]);
					professorList.add(professor);
					break;
				case 7:
					diaList.add(informacoesDaTurma[0]);
					horarioInicio = informacoesDaTurma[1];
					horarioFim = informacoesDaTurma[2];
					diaList.add(informacoesDaTurma[3]);
					horarioInicio = informacoesDaTurma[4];
					horarioFim = informacoesDaTurma[5];
					professor = new Professor(informacoesDaTurma[6]);
					professorList.add(professor);
					break;
				case 10:
					diaList.add(informacoesDaTurma[0]);
					horarioInicio = informacoesDaTurma[1];
					horarioFim = informacoesDaTurma[2];
					diaList.add(informacoesDaTurma[3]);
					horarioInicio = informacoesDaTurma[4];
					horarioFim = informacoesDaTurma[5];
					diaList.add(informacoesDaTurma[6]);
					horarioInicio = informacoesDaTurma[7];
					horarioFim = informacoesDaTurma[8];
					professor = new Professor(informacoesDaTurma[9]);
					professorList.add(professor);
					break;
				
			}
			
			index = htmlString.indexOf(Global.MW_CLASS_DAYS_END);
			htmlString = htmlString.substring(index);// Parte a String
			
			turma = new Turma(identificador, horarioInicio, horarioFim, total_vagas, vagas_disponiveis, vagas_ocupadas, diaList, professorList, disciplinaDesejada);
			turmaList.add(turma);
			
			identificador = "";
			horarioInicio = "";
			horarioFim = "";
			total_vagas = "";
			vagas_disponiveis = "";
			vagas_ocupadas = "";

		}
		
		site.disconnect();
		
		return turmaList;
	}
}

