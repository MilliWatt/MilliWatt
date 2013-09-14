package milliwatt.controller;

import java.util.ArrayList;
import milliwatt.model.Departamento;
import milliwatt.model.Disciplina;
import milliwatt.model.page.HttpsPage;
import milliwatt.utils.Global;

public class DisciplinaController {
	
	public static Departamento capturaDepartamentoDesejado(ArrayList<Departamento> departamentoList, String id_departamento){
		
		Departamento departamento = null;
		
		for(int i = 0; i < departamentoList.size(); ){
			if(departamentoList.get(i).getCodigo().equals(id_departamento)){
				departamento = departamentoList.get(i);
				break;
			}
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
	
	public static String capturaIDDisciplina(String htmlString, int index){
		
		String id_disciplina = "";

		while (htmlString.charAt(index)!= '&'){
			id_disciplina += htmlString.charAt(index);
			index++;
		}
	
		return id_disciplina;
		
	}
	
	public static String capturaNomeDisciplina(String htmlString, int index){
		
		String nome_discplina = "";
		
		while (htmlString.charAt(index)!='>')
			index++;
		
		index++;
		
		while(htmlString.charAt(index)!='<'){
			nome_discplina += htmlString.charAt(index);
			index++;
		}
		
		return nome_discplina;
	}
	
	public static String geraURLDisciplina(String id_disciplina, String id_departamento){
		return Global.MW_CLASS_DISCIPLINE + id_disciplina + Global.MW_CLASS_DEPARTMENT + id_departamento;
	}
	
	public static ArrayList<Disciplina> getDisciplinaList(ArrayList<Departamento> departamentoList, String id_departamento){
		
		ArrayList<Disciplina> disciplinasList = new ArrayList<Disciplina>();
		Departamento departamentoDesejado = capturaDepartamentoDesejado(departamentoList, id_departamento);
		
		String urlName_departamento = departamentoDesejado.getUrlName();
		
		HttpsPage site = new HttpsPage(urlName_departamento);
		//Page p =  Page.getPage(urlName_departamento);	
		
		site.connect();
		
		String htmlString = site.getOnlyHTML();
		
		String urlName_disciplina = "";
		String id_disciplina = "";
		String nome_disciplina = "";
		Disciplina disciplina = null;
		int index = 0;
		
		while(index !=-1){
			
			index = htmlString.indexOf(Global.MW_DISCIPLINE_ID)+22;
			
			id_disciplina = capturaIDDisciplina(htmlString, index);
			
			index += 6;
			
			nome_disciplina = capturaNomeDisciplina(htmlString, index);
			
			id_departamento = departamentoDesejado.getCodigo();
			
			urlName_disciplina = geraURLDisciplina(id_disciplina, id_departamento);
			
			//System.out.println(id_departamento);
			System.out.println(id_disciplina);
			System.out.println(nome_disciplina);
			System.out.println(urlName_disciplina);
			
			htmlString = htmlString.substring(index);// Parte a String
			index = htmlString.indexOf(Global.MW_DISCIPLINE_ID); // se for -1 PARA
			
			disciplina = new Disciplina(id_disciplina, nome_disciplina, urlName_disciplina);
			disciplinasList.add(disciplina);
			
			id_disciplina="";
			nome_disciplina="";
			urlName_disciplina="";
			
			
		}
		return disciplinasList;
	}

}

