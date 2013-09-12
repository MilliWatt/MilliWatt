package milliwatt.controller;

import java.util.ArrayList;

import milliwatt.model.Campus;
import milliwatt.model.Departamento;
import milliwatt.model.HttpsPage;
import milliwatt.utils.Global;

public class DepartamentoController {

	public static ArrayList<Departamento> getDepartmentList(ArrayList<Campus> campusList, String id){
		
		ArrayList<Departamento> departamentoList = new ArrayList<Departamento>();
		Campus c = null;
		
		for(int i = 0; i < campusList.size(); i++ ){
			if(campusList.get(i).getId().equals(id)) 
				 c = campusList.get(i);//Recebe o objeto Campus que representa a FGA
		}
		
		String urlName_departamento = c.getUrlName();
		
		//System.out.println(urlName_departamento);
		HttpsPage site = new HttpsPage(urlName_departamento);
		
		site.connect();
		
		String htmlString = site.getOnlyHTML();
		
		String id_departamento = "";
		String sigla_departamento = "";
		String sigla_departamento_invertida = "";
		String nome_departamento = "";
		Departamento departamento = null;
		int index = 0;
		
		while(index !=-1){
			
			index = htmlString.indexOf(Global.MW_DEPARTAMENT_ID);
			index = index - 34;
			
			while (htmlString.charAt(index)!='>'){
				sigla_departamento += htmlString.charAt(index);
				index--;
			}
			
			//FALTA INVERTER A SIGLA AQUI
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
			
			System.out.println(id_departamento);
			System.out.println(nome_departamento);
			//System.out.println(sigla_departamento);
			System.out.println(sigla_departamento_invertida);
			System.out.println(urlName_departamento);
			
			
			htmlString = htmlString.substring(index);// Parte a String
			index = htmlString.indexOf(Global.MW_DEPARTAMENT_ID); // se for -1 PARA
			
			departamento = new Departamento(id_departamento, nome_departamento, sigla_departamento_invertida, urlName_departamento);
			departamentoList.add(departamento);
			
			id_departamento="";
			nome_departamento="";
			urlName_departamento="";
			sigla_departamento="";
			sigla_departamento_invertida="";
		}
		
		site.disconnect();
		
		return departamentoList;
	}
}
