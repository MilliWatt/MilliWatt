package milliwatt.controller;

import java.util.ArrayList;

import milliwatt.model.Campus;
import milliwatt.model.Departamento;
import milliwatt.model.page.HttpsPage;
import milliwatt.utils.Global;

public class DepartamentoController {

	public static String capturaNomeDepartamento(String htmlString, int index){
		
		String nome_departamento = "";
		
		while (htmlString.charAt(index)!='<'){
			nome_departamento += htmlString.charAt(index);
			index++;
		}
		return nome_departamento;
	}
	
	public static String capturaIDDepartamento(String htmlString, int index){
		
		String id_departamento = "";

		while (htmlString.charAt(index)!='>'){
			id_departamento += htmlString.charAt(index);
			index++;
		}
	
		return id_departamento;
	}
	
	public static String capturaSiglaDepartamento(String htmlString, int index){
		
		String sigla_departamento = "";
		
		index = index - 34;
		
		while (htmlString.charAt(index)!='>'){
			sigla_departamento += htmlString.charAt(index);
			index--;
		}
		
		return sigla_departamento;
		
	}
	
	public static String inverteSiglaDepartamento(String sigla_departamento){
		
		String sigla_departamento_invertida = "";
		
		for(int i = sigla_departamento.length(); i > 0; i--){
			sigla_departamento_invertida += sigla_departamento.charAt(i-1);
		}
		
		return sigla_departamento_invertida;
	}
	
	public static Campus capturaCampusDesejado(ArrayList<Campus> campusList, String id){
		
		Campus campus = null;
		
		for(int i = 0; i < campusList.size(); i++ ){
			if(campusList.get(i).getId().equals(id)) 
				 campus = campusList.get(i);//Recebe o objeto Campus que representa a FGA
		}
		
		return campus;
	}
	
	public static ArrayList<Departamento> getDepartmentList(ArrayList<Campus> campusList, String id){
		
		int index = 0;
		String id_departamento = "";
		String sigla_departamento = "";
		String sigla_departamento_invertida = "";
		String nome_departamento = "";
		Departamento departamento = null;
		ArrayList<Departamento> departamentoList = new ArrayList<Departamento>();
		
		Campus campusDesejado = capturaCampusDesejado(campusList, id);
		
		String urlName_departamento = campusDesejado.getUrlName();
		
		HttpsPage site = new HttpsPage(urlName_departamento);
		
		site.connect();
		
		String htmlString = site.getOnlyHTML();
		
		while(index !=-1){
			
			index = htmlString.indexOf(Global.MW_DEPARTAMENT_ID);

			sigla_departamento = capturaSiglaDepartamento(htmlString, index);
				
			sigla_departamento_invertida = inverteSiglaDepartamento(sigla_departamento);
			
			index = htmlString.indexOf(Global.MW_DEPARTAMENT_ID)+4;

			id_departamento = capturaIDDepartamento(htmlString, index);

			index += 4;//probleminha
		
			nome_departamento = capturaNomeDepartamento(htmlString, index);
			
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
