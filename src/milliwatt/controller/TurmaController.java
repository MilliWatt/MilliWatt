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
	
	public static ArrayList<String> capturaInformacoesDaTurma(String inicioStringDias, String fimStringDias, String htmlString){
		
		int i = 0;
		String dia = "";
		String local = "";
		String horaFim = "";
		String horaInicio = "";
		String professor = "";
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

			i = 0;
		}

		inicio = htmlStringPartida.indexOf("<center>")+8;
		
		while(htmlStringPartida.charAt(inicio) != '<'){
			professor += htmlStringPartida.charAt(inicio);
			inicio++;
		}

		informacoesDaTurma.add(professor);
		
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
		String horarioInicio = "";
		String horarioFim = "";
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

		//String urlName_disciplina = disciplinaDesejada.getUrlName();

		//HttpsPage site = new HttpsPage(urlName_disciplina);

		HttpsPage site = new HttpsPage("https://matriculaweb.unb.br/matriculaweb/graduacao/oferta_dados.aspx?cod=193640&dep=650");
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
			
//			for(int o = 0; o < informacoesDaTurma.length; o++){
//				System.out.println("---------------");
//				System.out.println(informacoesDaTurma[o]);
//			}
			
			switch(informacoesDaTurma.size()){
			
				case 5:
					diaList.add(informacoesDaTurma.get(0));
					horarioInicio = informacoesDaTurma.get(1);
					horarioFim = informacoesDaTurma.get(2);
					locais.add(informacoesDaTurma.get(3));
					professor = new Professor(informacoesDaTurma.get(4));
					professorList.add(professor);
					System.out.println("CASO 5");
					break;
				case 9:
					diaList.add(informacoesDaTurma.get(0));
					horarioInicio = informacoesDaTurma.get(1);
					horarioFim = informacoesDaTurma.get(2);
					locais.add(informacoesDaTurma.get(3)); 
					diaList.add(informacoesDaTurma.get(4));
					horarioInicio = informacoesDaTurma.get(5);
					horarioFim = informacoesDaTurma.get(6);
					locais.add(informacoesDaTurma.get(7));
					professor = new Professor(informacoesDaTurma.get(8));
					professorList.add(professor);
					System.out.println("CASO 9");
					break;
				case 13:
					diaList.add(informacoesDaTurma.get(0));
					horarioInicio = informacoesDaTurma.get(1);
					horarioFim = informacoesDaTurma.get(2);
					locais.add(informacoesDaTurma.get(3));
					diaList.add(informacoesDaTurma.get(4));
					horarioInicio = informacoesDaTurma.get(5);
					horarioFim = informacoesDaTurma.get(6);
					locais.add(informacoesDaTurma.get(7));
					diaList.add(informacoesDaTurma.get(8));
					horarioInicio = informacoesDaTurma.get(9);
					horarioFim = informacoesDaTurma.get(10);
					locais.add(informacoesDaTurma.get(11));
					professor = new Professor(informacoesDaTurma.get(12));
					professorList.add(professor);
					System.out.println("CASO 13");
					break;
				
			}
			
			System.out.println("ESTAMOS AQUI!");
			System.out.println("Turma: " + identificador);//BRANCO
			System.out.println("Hora Fim: "+horarioFim);//NULO
			System.out.println("Hora Inicio: "+horarioInicio);//NULO
			System.out.println("Vagas: "+total_vagas);//OK
			System.out.println("Vagas ocupadas: "+vagas_ocupadas);//OK
			System.out.println("Vagas disponiveis: "+vagas_disponiveis);//OK
			System.out.println("Total de dias: "+diaList.size());//+-OK
			System.out.println("Total de profs: "+professorList.size());//+-OK
			System.out.println("Nome da materia: "+disciplinaDesejada.getNomeDisciplina());//OK
			
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

