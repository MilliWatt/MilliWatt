package milliwatt.controller;

import java.util.ArrayList;
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
		String[] informacoesDaTurma = new String[13];
		
		//System.out.println(htmlString.length());
		
		int inicio = htmlString.indexOf(inicioStringDias);//11049
		int fim = htmlString.indexOf(fimStringDias);//12224;
		String htmlStringPartida = htmlString.substring(inicio, fim);
		fim = htmlStringPartida.length()-1;
		//System.out.println(fim);
		//System.out.println(htmlStringPartida.charAt(fim));
		int indice = 0;
		int i = 0;
		//int p = 0;
		
		while(true){
			//p++;
			
			i = 0;

			while (htmlStringPartida.charAt(i) != '>'){
				i++;
			}
		
			i+=4;

			while (htmlStringPartida.charAt(i)!='<'){
				dia += htmlStringPartida.charAt(i);
				i++;
			}

			informacoesDaTurma[indice] = dia;
			i += 42;
			indice++;
			
			while (htmlStringPartida.charAt(i) != '<'){
				horaInicio += htmlStringPartida.charAt(i);
				i++;
			}

			informacoesDaTurma[indice] = horaInicio;
			i += 40;
			indice++;
			
			while (htmlStringPartida.charAt(i) != '<'){
				horaFim += htmlStringPartida.charAt(i);
				i++;
			}	

			informacoesDaTurma[indice] = horaFim;
			i += 76;
			indice++;
			
			while (htmlStringPartida.charAt(i) != '<'){
				local += htmlStringPartida.charAt(i);
				i++;
			}

			informacoesDaTurma[indice] = local;
			indice++;
			
//			System.out.println(i);
//			System.out.println(fim);
//			System.out.println(htmlStringPartida);
			
			htmlStringPartida = htmlStringPartida.substring(i, fim);
			
			//System.out.println(fim);
			
			fim = htmlStringPartida.length()-1;
			
			//System.out.println(fim);
			
			inicio = htmlStringPartida.indexOf(inicioStringDias);
			
			if(inicio == -1){
				break;
			}
			
			htmlStringPartida = htmlStringPartida.substring(inicio, fim);
			
			//break;
		}

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
		//int i = 1;
		
		for(Disciplina d : disciplinaList){
			
//			System.out.println(d.getCodigoDisciplina());
//			System.out.println(i);
//			i++;
			if(d.getCodigoDisciplina().equals(id_disciplina)){
				disciplina = d;
				//System.out.println(disciplina.getUrlName());
			}
		}

		return disciplina;
		
	}

	public static String geraURLPreReqDisciplina(String codigoDisciplina){
		return Global.MW_DISCIPLINE_PRE_REQ + codigoDisciplina;
	}
	
	public static ArrayList<String> capturaPreRequisitosDisciplinaDesejada(Disciplina disciplinaDesejada){
		
		ArrayList<String> preRequisitosList = new ArrayList<String>();
		String pre_req = "";
		String auxiliar = "";
		String codigoDisciplina = disciplinaDesejada.getCodigoDisciplina();
		int index = 0;
		
		String urlName_preReq = geraURLPreReqDisciplina(codigoDisciplina);
		System.out.println(urlName_preReq);
		HttpsPage site = new HttpsPage(urlName_preReq);
		
		site.connect();
		
		String htmlString = site.getOnlyHTML();
		
		index = htmlString.lastIndexOf(Global.MW_DISCIPLINE_PRE_REQ_ID)+37;
		
		while(true){
			while (htmlString.charAt(index)!='<'){
				pre_req += htmlString.charAt(index);
				index++;
			}
			System.out.println(pre_req);
			preRequisitosList.add(pre_req);
			
			auxiliar = htmlString.substring(index, index+5);
					
			if(auxiliar.equals("</td>")){
				break;
			}else{
				index+=4;
			}
		}
		
		site.disconnect();
		
		return preRequisitosList;
		
	}
	
	public static ArrayList<Turma> getTurmaList(ArrayList<Disciplina> disciplinaList, String id_disciplina){

		int index = 0;
		String identificador = "";
		String horarioInicio = "";
		String horarioFim = "";
		String total_vagas = "";
		String vagas_disponiveis = "";
		String vagas_ocupadas = "";
		ArrayList<String> locais = new ArrayList<String>();
		ArrayList<String> diaList = new ArrayList<String>();
		ArrayList<Turma> turmaList = new ArrayList<Turma>();
		ArrayList<Professor> professorList = new ArrayList<Professor>();
		String[] informacoesDaTurma = null;
		Turma turma = null;
		Professor professor = null;
		
		Disciplina disciplinaDesejada = capturaDisciplinaDesejada(disciplinaList, id_disciplina);

		//ArrayList<String> preReqList = 
		capturaPreRequisitosDisciplinaDesejada(disciplinaDesejada);

		//String urlName_disciplina = disciplinaDesejada.getUrlName();

		//HttpsPage site = new HttpsPage(urlName_disciplina);

		HttpsPage site = new HttpsPage("https://matriculaweb.unb.br/matriculaweb/graduacao/oferta_dados.aspx?cod=113042&dep=650");
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
			
			for(int o = 0; o < informacoesDaTurma.length; o++){
				System.out.println("---------------");
				System.out.println(informacoesDaTurma[o]);
			}
			
			switch(informacoesDaTurma.length){
			
				case 5:
					diaList.add(informacoesDaTurma[0]);
					horarioInicio = informacoesDaTurma[1];
					horarioFim = informacoesDaTurma[2];
					locais.add(informacoesDaTurma[3]);
					professor = new Professor(informacoesDaTurma[4]);
					professorList.add(professor);
					break;
				case 9:
					diaList.add(informacoesDaTurma[0]);
					horarioInicio = informacoesDaTurma[1];
					horarioFim = informacoesDaTurma[2];
					locais.add(informacoesDaTurma[3]); 
					diaList.add(informacoesDaTurma[4]);
					horarioInicio = informacoesDaTurma[5];
					horarioFim = informacoesDaTurma[6];
					locais.add(informacoesDaTurma[7]);
					professor = new Professor(informacoesDaTurma[8]);
					professorList.add(professor);
					break;
				case 13:
					diaList.add(informacoesDaTurma[0]);
					horarioInicio = informacoesDaTurma[1];
					horarioFim = informacoesDaTurma[2];
					locais.add(informacoesDaTurma[3]);
					diaList.add(informacoesDaTurma[4]);
					horarioInicio = informacoesDaTurma[5];
					horarioFim = informacoesDaTurma[6];
					locais.add(informacoesDaTurma[7]);
					diaList.add(informacoesDaTurma[8]);
					horarioInicio = informacoesDaTurma[9];
					horarioFim = informacoesDaTurma[10];
					locais.add(informacoesDaTurma[11]);
					professor = new Professor(informacoesDaTurma[12]);
					professorList.add(professor);
					break;
				
			}
			
			System.out.println("ESTAMOS AQUI!");
			System.out.println(identificador);//BRANCO
			System.out.println(horarioFim);//NULO
			System.out.println(horarioInicio);//NULO
			System.out.println(total_vagas);//OK
			System.out.println(vagas_ocupadas);//OK
			System.out.println(vagas_disponiveis);//OK
			System.out.println(diaList.size());//+-OK
			System.out.println(professorList.size());//+-OK
			System.out.println(disciplinaDesejada.getNomeDisciplina());//OK
			
			index = htmlString.indexOf(Global.MW_CLASS_DAYS_END);
			htmlString = htmlString.substring(index);// Parte a String
			
			turma = new Turma(identificador, horarioInicio, horarioFim, total_vagas, vagas_disponiveis, vagas_ocupadas, diaList, professorList, disciplinaDesejada, locais);
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

