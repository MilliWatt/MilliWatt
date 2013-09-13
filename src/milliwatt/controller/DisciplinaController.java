package milliwatt.controller;

import java.util.ArrayList;

import milliwatt.model.Campus;
import milliwatt.model.Departamento;
import milliwatt.model.HttpsPage;
import milliwatt.utils.Global;




import milliwatt.model.Campus;
import milliwatt.model.Departamento;
import milliwatt.model.Disciplina;
import milliwatt.model.HttpsPage;
import milliwatt.utils.Global;


public class DisciplinaController {
	
	public static Departamento capturaDepartamentoDesejado(ArrayList<Departamento> departamentoList, String id_departamento){
		Departamento departamento = null;
		for(int i = 0; i < departamentoList.size(); i++ ){
			if(departamentoList.get(i).getCodigo().equals(id_departamento)) 
				departamento = departamentoList.get(i);
				break;
		}
		return departamento;
	}
	
	public static String capturaSiglaDepartamento(String htmlString){
		
		String sigla_departamento = "";
		int index = htmlString.indexOf(Global.MW_DEPARTAMENT_ID);
		index = index - 34;
		
		while (htmlString.charAt(index)!='>'){
			sigla_departamento += htmlString.charAt(index);
			index--;
		}
		
		return sigla_departamento;
		
	}
	
	public static ArrayList<Disciplina> getDisciplinaList(ArrayList<Departamento> departamentoList, String id_departamento){
		
		ArrayList<Disciplina> disciplinasList = new ArrayList<Disciplina>();
		Departamento departamentoDesejado = capturaDepartamentoDesejado(departamentoList, id_departamento);
		
		
		String urlNameDisciplina = "";
		String urlName_departamento = departamentoDesejado.getUrlName();
		
		//System.out.println(urlName_departamento);
		HttpsPage site = new HttpsPage(urlName_departamento);
		
		site.connect();
		
		String htmlString = site.getOnlyHTML();
		
		String id_Disciplina = "";
		String nome_Disciplina = "";
		Departamento departamento = null;
		int index = 0;
		
		while(index !=-1){
			sigla_departamento = capturaSiglaDepartamento(htmlString);


	
	public static String capturaIdCampus(String htmlString, int index){
			
			String id_campus = "";
			
			while (htmlString.charAt(index)!='>'){
				id_campus += htmlString.charAt(index);
				index++;
			}
			return id_campus;
		}
		
	public static String capturaNomeCampus(String htmlString, int index){
			
			String nome_campus = "";
			
			while (htmlString.charAt(index)!='<'){
				nome_campus += htmlString.charAt(index);
				index++;
			}
			
			return nome_campus;
		}
		
	public static String geraURLCampus(String urlName_campus, String id_campus){
			return Global.MW_DEPARTMENT + id_campus;
		}
	
	public static ArrayList<Campus> getDisciplinasList(ArrayList<Departamento> departamentoList){
			ArrayList<Campus> CampusList = new ArrayList<Campus>();
			
			HttpsPage site = new HttpsPage(Global.MW_CAMPUS);
			site.connect();
			
			String htmlString = site.getOnlyHTML();
			
			//System.out.println(htmlString);
			
			String id_campus="";
			String nome_campus="";
			String urlName_campus="";
			Campus campus = null;
			int index = 0;
			
			while(index !=-1){
				//4 de offset para pegar o codigo da disciplina
				index = htmlString.indexOf(Global.MW_CAMPUS_ID)+4;
				
				id_campus = capturaIdCampus(htmlString, index);
				
				index += 2;//problemimnha
				
				nome_campus = capturaNomeCampus(htmlString, index);
				
				index++;
				
				urlName_campus = geraURLCampus(urlName_campus, id_campus);
				
				
				System.out.println(id_campus);
				System.out.println(nome_campus);
				System.out.println(urlName_campus);
				
				
				htmlString=htmlString.substring(index);// Parte a String
				index = htmlString.indexOf(Global.MW_CAMPUS_ID); // se for -1 PARA
				
				campus = new Campus(id_campus, nome_campus, urlName_campus);
				CampusList.add(campus);
				
				id_campus="";
				nome_campus="";
				urlName_campus="";
			}
			
			site.disconnect();
			
			return CampusList;
		}
		
			for(int i = sigla_departamento.length(); i > 0; i--){
				sigla_departamento_invertida += sigla_departamento.charAt(i-1);
			}
			
			index = htmlString.indexOf(Global.MW_DEPARTAMENT_ID)+4;
			
			while (htmlString.charAt(index)!='>'){
				id_departamento += htmlString.charAt(index);
				index++;
			}
			
			index++;
			
			while (htmlString.charAt(index)!='<'){
				nome_departamento += htmlString.charAt(index);
				index++;
			}
			
			index++;
			
			urlNameDepartamento = Global.MW_DISCIPLINE + id_departamento;
			
			System.out.println(id_departamento);
			System.out.println(nome_departamento);
			//System.out.println(sigla_departamento);
			System.out.println(sigla_departamento_invertida);
			System.out.println(urlNameDepartamento);
			
			
			htmlString = htmlString.substring(index);// Parte a String
			index = htmlString.indexOf(Global.MW_DEPARTAMENT_ID); // se for -1 PARA
			
			departamento = new Departamento(id_departamento, nome_departamento, sigla_departamento_invertida, urlNameDepartamento);
			departamentoList.add(departamento);
			
			id_departamento="";
			nome_departamento="";
			urlNameDepartamento="";
			sigla_departamento="";
			sigla_departamento_invertida="";
		}
		
		site.disconnect();
		
		return departamentoList;
	}

}

