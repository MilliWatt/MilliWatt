package milliwatt.controller;

import java.util.ArrayList;

import milliwatt.model.Campus;
import milliwatt.model.Departamento;
import milliwatt.model.Disciplina;
import milliwatt.model.Professor;
import milliwatt.model.Turma;
import milliwatt.model.page.HttpsPage;
import milliwatt.utils.Global;

public class TurmaController {
	
	public static String capturaTotalVagasDisciplina(String htmlString, int index){
		
		String total_vagas = "";
		
		index += 7;
		
		while (htmlString.charAt(index)!= ' '){
			total_vagas += htmlString.charAt(index);
			index++;
		}
		return total_vagas;
	}
	
	public static String capturaVagasOcupadasDisciplina(String htmlString, int index){
		
		String vagas_ocupadas = "";
		
		index += 23;
		
		while (htmlString.charAt(index)!= '<'){
			vagas_ocupadas += htmlString.charAt(index);
			index++;
		}
		return vagas_ocupadas;
	}
	
	public static String capturaVagasDisponiveisDisciplina(String htmlString, int index){
		
		String vagas_disponiveis = "";
		
		index += 23;
		
		while (htmlString.charAt(index)!= '<'){
			vagas_disponiveis += htmlString.charAt(index);
			index++;
		}
		return vagas_disponiveis;
	}
	
	public static ArrayList<String> divideStringDias(String inicioStringDias, String fimStringDias, String htmlString){
		
		ArrayList<String> diasHorariosProfessoresLocalTurma = new ArrayList<String>();

		int inicio = htmlString.indexOf(inicioStringDias);
		int fim = htmlString.indexOf(fimStringDias);
		
		String htmlStringPartida = htmlString.substring(inicio, fim);
		
		while (htmlString.charAt(index)!='>'){
			 += htmlStringPartida.charAt(index);
			index++;
		}
	
		return diasHorariosProfessoresLocalTurma;
	}
	
	public static String capturaIdentificadorDisciplina(String htmlString, int index){
		
		String identificador = "";
		
		index += 16;
		
		while (htmlString.charAt(index)!='<'){
			identificador += htmlString.charAt(index);
			index++;
		}
		
		return identificador;
		
	}
	
	public static String inverteSiglaDepartamento(String sigla_departamento){
		
		String sigla_departamento_invertida = "";
		
		for(int i = sigla_departamento.length(); i > 0; i--){
			sigla_departamento_invertida += sigla_departamento.charAt(i-1);
		}
		
		return sigla_departamento_invertida;
	}
	
	public static Disciplina capturaDisciplinaDesejada(ArrayList<Disciplina> disciplinaList, String id_disciplina){
		
		Disciplina disciplina = null;
		
		for (Disciplina d : disciplinaList){
			if (d.getCodigoDisciplina().equals(id_disciplina)){
				disciplina = d;
			}
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
		
		Disciplina disciplinaDesejada = capturaDisciplinaDesejada(disciplinaList, id_disciplina);
		
		String urlName_disciplina = disciplinaDesejada.getUrlName();
		
		HttpsPage site = new HttpsPage(urlName_disciplina);
		
		site.connect();
		
		String htmlString = site.getOnlyHTML();
		
		while(index !=-1){

			index = htmlString.indexOf(Global.MW_CLASS_ID);

			identificador = capturaIdentificadorDisciplina(htmlString, index);
			
			index = htmlString.indexOf(Global.MW_CLASS_VACANCIES);
			
			total_vagas = capturaTotalVagasDisciplina(htmlString, index);
			
			index = htmlString.indexOf(Global.MW_CLASS_VACANCIES_OCCUPIED);
			
			vagas_ocupadas = capturaVagasOcupadasDisciplina(htmlString, index);
			
			index = htmlString.indexOf(Global.MW_CLASS_VACANCIES_AVAILABLE);
			
			vagas_disponiveis = capturaVagasDisponiveisDisciplina(htmlString, index);
			
			divideStringDias(Global.MW_CLASS_DAYS_BEGIN, Global.MW_CLASS_DAYS_END, htmlString);

			/*System.out.println(id_departamento);
			System.out.println(nome_departamento);
			//System.out.println(sigla_departamento);
			System.out.println(sigla_departamento_invertida);
			System.out.println(urlName_departamento);*/
			
			index = htmlString.indexOf(Global.MW_CLASS_DAYS_END);
			htmlString = htmlString.substring(index);// Parte a String
			//index = htmlString.indexOf(Global.MW_DEPARTAMENT_ID); // se for -1 PARA
			
			//departamento = new Departamento(id_departamento, nome_departamento, sigla_departamento_invertida, urlName_departamento);
			//departamentoList.add(departamento);
			
//			id_departamento="";
//			nome_departamento="";
//			urlName_departamento="";
//			sigla_departamento="";
//			sigla_departamento_invertida="";
		}
		
		site.disconnect();
		
		return turmaList;
	}
}
