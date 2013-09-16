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
//		String auxiliar = "";
//		
//		while(htmlString.charAt(index) != '>'){
//			auxiliar += htmlString.charAt(index);
//		}
//		
//		if(auxiliar.contains("green")){
//			index += 25;	
//		}else{
			index += 23;
//		}		
		
		while(htmlString.charAt(index) != '<'){
			vagas_ocupadas += htmlString.charAt(index);
			index++;
		}
		return vagas_ocupadas;
	}
	
	public static String capturaVagasDisponiveisTurma(String htmlString, int index){
		
		String vagas_disponiveis = "";
//		String auxiliar = "";
//		
//		while(htmlString.charAt(index) != '>'){
//			auxiliar += htmlString.charAt(index);
//		}
//		
//		if(auxiliar.contains("blue")){
//			index += 24;	
//		}else{
			index += 23;
//		}	
		
		while (htmlString.charAt(index)!= '<'){
			vagas_disponiveis += htmlString.charAt(index);
			index++;
		}
		return vagas_disponiveis;
	}
	
	public static ArrayList<String> capturaInformacoesDaTurma(String inicioStringDias, String fimStringDias, String htmlString){
		
		int i = 0;
		String dia = "";
		String local = "";
		String horaFim = "";
		String horaInicio = "";
		String professor = "";
		String auxiliar = "";
		ArrayList<String> informacoesDaTurma = new ArrayList<String>();
		
		int inicio = htmlString.indexOf(inicioStringDias);
		int fim = htmlString.indexOf(fimStringDias);
		String htmlStringPartida = htmlString.substring(inicio, fim);
		fim = htmlStringPartida.length()-1;
		
		while(true){

			while (htmlStringPartida.charAt(i) != '>'){
				i++;
			}
		
			i+=4;

			while (htmlStringPartida.charAt(i)!='<'){
				dia += htmlStringPartida.charAt(i);
				i++;
			}

			informacoesDaTurma.add(dia);
			i += 42;
			
			while (htmlStringPartida.charAt(i) != '<'){
				horaInicio += htmlStringPartida.charAt(i);
				i++;
			}

			informacoesDaTurma.add(horaInicio);
			i += 40;
			
			while (htmlStringPartida.charAt(i) != '<'){
				horaFim += htmlStringPartida.charAt(i);
				i++;
			}	

			informacoesDaTurma.add(horaFim);
			i += 76;
			
			while (htmlStringPartida.charAt(i) != '<'){
				local += htmlStringPartida.charAt(i);
				i++;
			}

			informacoesDaTurma.add(local);
			
			htmlStringPartida = htmlStringPartida.substring(i, fim);

			fim = htmlStringPartida.length()-1;

			inicio = htmlStringPartida.indexOf(inicioStringDias);
			
			if(inicio == -1){
				break;
			}
			
			htmlStringPartida = htmlStringPartida.substring(inicio, fim);
			
			fim = htmlStringPartida.length()-1;

			dia = "";
			local = "";
			horaFim = "";
			horaInicio = "";
			professor = "";
			i = 0;
		}
		
		inicio = htmlStringPartida.indexOf("<center>")+8;
		
		while(true){
			
			while(htmlStringPartida.charAt(inicio) != '<'){
				professor += htmlStringPartida.charAt(inicio);
				inicio++;
			}

			informacoesDaTurma.add(professor);

			auxiliar = htmlStringPartida.substring(inicio, inicio+13);
			
			if(auxiliar.equals("<br></center>")){
				break;
			}else{
				inicio+=4;
			}
			professor = "";
			auxiliar = "";

		}
		
//		System.out.println(informacoesDaTurma.get(12));
//		System.out.println(informacoesDaTurma.get(13));
//		System.out.println(informacoesDaTurma.get(14));
//		System.out.println(informacoesDaTurma.size());
		
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

			if(d.getCodigoDisciplina().equals(id_disciplina))
				disciplina = d;
		}

		return disciplina;
		
	}

	public static String geraURLPreReqDisciplina(String codigoDisciplina){
		return Global.MW_DISCIPLINE_PRE_REQ + codigoDisciplina;
	}
	
	public static Disciplina criaDisciplinaPreReq(String informacoes){
		
		Disciplina disciplina = null;
		String codigoDisciplinaPreReq = "";
		String nomeDisciplina = "";
		int index = 0;

		while(informacoes.charAt(index)!= '-')
			index++;

		index++;
                
		for(int j = 0; j < 6; j++){
			codigoDisciplinaPreReq += informacoes.charAt(index);
			index++;
		}

                index++;
                
		while (index!= informacoes.length()){
			nomeDisciplina += informacoes.charAt(index);
			index++;
		}
                
		disciplina = new Disciplina(codigoDisciplinaPreReq, nomeDisciplina);

		return disciplina;
		
	}
	
	public static ArrayList<Disciplina> capturaPreRequisitosDisciplinaDesejada(Disciplina disciplinaDesejada){
		
		ArrayList<Disciplina> preRequisitosList = new ArrayList<Disciplina>();
		Disciplina d = null;
		String pre_req = "";
		String auxiliar = "";
		
		String codigoDisciplina = disciplinaDesejada.getCodigoDisciplina();
		int index = 0;
		
		String urlName_preReq = geraURLPreReqDisciplina(codigoDisciplina);
		
		HttpsPage site = new HttpsPage(urlName_preReq);
		
		site.connect();
		
		String htmlString = site.getOnlyHTML();
		
		index = htmlString.lastIndexOf(Global.MW_DISCIPLINE_PRE_REQ_ID)+37;
		
		while(true){
			while (htmlString.charAt(index)!='<'){
				pre_req += htmlString.charAt(index);
				index++;
			}
			
			d = criaDisciplinaPreReq(pre_req);
                        
			preRequisitosList.add(d);
			
			auxiliar = htmlString.substring(index, index+5);
					
			if(auxiliar.equals("</td>")){
				break;
			}else{
				index+=4;
			}
			pre_req = "";
		}
		
		site.disconnect();
		
		return preRequisitosList;
		
	}
	
	public static ArrayList<Turma> getTurmaList(ArrayList<Disciplina> disciplinaList, String id_disciplina){
		
		int index = 0;
		String identificador = "";
		String diaHoraInicio = "";
		String diaHoraFim = "";
		String total_vagas = "";
		String vagas_disponiveis = "";
		String vagas_ocupadas = "";
		ArrayList<String> locais = new ArrayList<String>();
		ArrayList<String> diaList = new ArrayList<String>();
		ArrayList<Turma> turmaList = new ArrayList<Turma>();
		ArrayList<Professor> professorList = new ArrayList<Professor>();
		ArrayList<String> informacoesDaTurma = null;
		Turma turma = null;
		Professor professor = null;
		
		Disciplina disciplinaDesejada = capturaDisciplinaDesejada(disciplinaList, id_disciplina);

		ArrayList<Disciplina> preReqList = capturaPreRequisitosDisciplinaDesejada(disciplinaDesejada);

		String urlName_disciplina = disciplinaDesejada.getUrlName();

		HttpsPage site = new HttpsPage(urlName_disciplina);

		site.connect();
		
		String htmlString = site.getOnlyHTML();
		
		//String htmlStringAuxilar = htmlString;
		
		while(index != -1){

			index = htmlString.indexOf(Global.MW_CLASS_ID);

			identificador = capturaIdentificadorTurma(htmlString, index);
			
			htmlString = htmlString.substring(index);
			
			index = htmlString.indexOf(Global.MW_CLASS_VACANCIES);
			
			total_vagas = capturaTotalVagasTurma(htmlString, index);
			
			htmlString = htmlString.substring(index);
			
			index = htmlString.indexOf(Global.MW_CLASS_VACANCIES_OCCUPIED);
			
			vagas_ocupadas = capturaVagasOcupadasTurma(htmlString, index);
			
			htmlString = htmlString.substring(index);
			
			index = htmlString.indexOf(Global.MW_CLASS_VACANCIES_AVAILABLE);
			
			vagas_disponiveis = capturaVagasDisponiveisTurma(htmlString, index);
			
			htmlString = htmlString.substring(index);
			
			informacoesDaTurma = capturaInformacoesDaTurma(Global.MW_CLASS_DAYS_BEGIN, Global.MW_CLASS_DAYS_END, htmlString);

			switch(informacoesDaTurma.size()){
			
				case 5:
					diaList.add(informacoesDaTurma.get(0));
					diaHoraInicio = informacoesDaTurma.get(1);
					diaHoraFim = informacoesDaTurma.get(2);
					locais.add(informacoesDaTurma.get(3));
					
					for(int i = 4; i < informacoesDaTurma.size(); i++){
						professor = new Professor(informacoesDaTurma.get(i));
						professorList.add(professor);	
					}
					
//					System.out.println("Dia 1: " + diaList.get(0));
//					System.out.println("Hora Inicio: "+ diaHoraInicio);
//					System.out.println("Hora Fim: "+ diaHoraFim);
//					System.out.println("Local: "+ locais.get(0));
					
					for(int i = 0; i < professorList.size();i++){
//						System.out.println("Professor(es): "+professorList.get(i).getNome());
					}
					
					break;
				case 9:
					diaList.add(informacoesDaTurma.get(0));
					diaHoraInicio = informacoesDaTurma.get(1);
					diaHoraFim = informacoesDaTurma.get(2);
					locais.add(informacoesDaTurma.get(3)); 
					
//					System.out.println("Dia 1: " + diaList.get(0));
//					System.out.println("Hora Inicio: "+ diaHoraInicio);
//					System.out.println("Hora Fim: "+ diaHoraFim);
//					System.out.println("Local: "+ locais.get(0));
					
					diaList.add(informacoesDaTurma.get(4));
					diaHoraInicio = informacoesDaTurma.get(5);
					diaHoraFim = informacoesDaTurma.get(6);
					locais.add(informacoesDaTurma.get(7));
					
//					System.out.println("Dia 2: " + diaList.get(1));
//					System.out.println("Hora Inicio: "+ diaHoraInicio);
//					System.out.println("Hora Fim: "+ diaHoraFim);
//					System.out.println("Local: "+ locais.get(1));
					
					for(int i = 8; i < informacoesDaTurma.size(); i++){
						professor = new Professor(informacoesDaTurma.get(i));
						professorList.add(professor);	
					}

//					System.out.println("Professor(es): "+professorList.get(0).getNome());
					
					break;
				case 13:
					//d h h l d h h l d h h l p p p
					diaList.add(informacoesDaTurma.get(0));
					diaHoraInicio = informacoesDaTurma.get(1);
					diaHoraFim = informacoesDaTurma.get(2);
					locais.add(informacoesDaTurma.get(3));
					
//					System.out.println("Dia 1: " + diaList.get(0));
//					System.out.println("Hora Inicio: "+ diaHoraInicio);
//					System.out.println("Hora Fim: "+ diaHoraFim);
//					System.out.println("Local: "+ locais.get(0));
					
					diaList.add(informacoesDaTurma.get(4));
					diaHoraInicio = informacoesDaTurma.get(5);
					diaHoraFim = informacoesDaTurma.get(6);
					locais.add(informacoesDaTurma.get(7));
					
//					System.out.println("Dia 2: " + diaList.get(1));
//					System.out.println("Hora Inicio: "+ diaHoraInicio);
//					System.out.println("Hora Fim: "+ diaHoraFim);
//					System.out.println("Local: "+ locais.get(1));
					
					diaList.add(informacoesDaTurma.get(8));
					diaHoraInicio = informacoesDaTurma.get(9);
					diaHoraFim = informacoesDaTurma.get(10);
					locais.add(informacoesDaTurma.get(11));
					
//					System.out.println("Dia 3: " + diaList.get(2));
//					System.out.println("Hora Inicio: "+ diaHoraInicio);
//					System.out.println("Hora Fim: "+ diaHoraFim);
//					System.out.println("Local: "+ locais.get(2));
					
					for(int i = 12, j = 0; i < informacoesDaTurma.size(); i++, j++){
						professor = new Professor(informacoesDaTurma.get(i));
						professorList.add(professor);
//						System.out.println("Professor(es): "+ professorList.get(j).getNome());
					}
					

					
					break;
				
			}
			
//			System.out.println("Turma: " + identificador);
//			System.out.println("Nome da materia: "+disciplinaDesejada.getNomeDisciplina());
//			System.out.println("Vagas: "+total_vagas);
//			System.out.println("Vagas ocupadas: "+vagas_ocupadas);
//			System.out.println("Vagas disponiveis: "+vagas_disponiveis);
			
			htmlString = htmlString.substring(index);
			index = htmlString.indexOf(Global.MW_CLASS_DAYS_END);
				
			turma = new Turma(identificador, diaHoraInicio, diaHoraFim, total_vagas, vagas_disponiveis, vagas_ocupadas, diaList, professorList, disciplinaDesejada, locais);
			turmaList.add(turma);
			
			identificador = "";
			total_vagas = "";
			vagas_disponiveis = "";
			vagas_ocupadas = "";
			
			index = htmlString.indexOf(Global.MW_CLASS_ID);

		}
		
		site.disconnect();
		
		return turmaList;
	}
}

