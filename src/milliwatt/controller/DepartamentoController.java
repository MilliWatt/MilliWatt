package milliwatt.controller;

import java.util.ArrayList;

import milliwatt.model.Campus;
import milliwatt.model.Departamento;
import milliwatt.model.HttpsPage;
import milliwatt.utils.Global;

public class DepartamentoController {

	/*
1
Darcy Ribeiro
https://matriculaweb.unb.br/matriculaweb/graduacao/oferta_dep.aspx?cod=1
2
Planaltina
https://matriculaweb.unb.br/matriculaweb/graduacao/oferta_dep.aspx?cod=2
3
Ceilândia
https://matriculaweb.unb.br/matriculaweb/graduacao/oferta_dep.aspx?cod=3
4
Gama
https://matriculaweb.unb.br/matriculaweb/graduacao/oferta_dep.aspx?cod=4
*/
	
	public static ArrayList<Departamento> getDepartmentList(){
		
		String urlName;
		ArrayList<Campus> campusList = CampusController.getCampusList();
		Campus c = campusList.get(3);//Recebe o objeto Campus que representa a FGA
		
		ArrayList<Departamento> departamentoList = new ArrayList<Departamento>();
		
		HttpsPage site = new HttpsPage(c.getUrlName());
		site.connect();
		
		String htmlString = site.getOnlyHTML();
		
		//System.out.println(htmlString);
		
		String id_departamento="";
		String sigla_departamento="";
		String nome_departamento = "";
		String urlName_departamento="";
		Campus campus = null;
		int index =0;
		
		while(index !=-1){
			
			index = htmlString.indexOf(Global.MW_DEPARTAMENT_ID);
			//System.out.println(htmlString.charAt(index+20));
			
			while (htmlString.charAt(index-37)!='>'){
				index--;
			}
			
			while (htmlString.charAt(index)!='<'){
				sigla_departamento += htmlString.charAt(index);
				index++;
			}
			System.out.println(sigla_departamento);
			index = htmlString.indexOf(Global.MW_DEPARTAMENT_ID);
			
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
			/*
			urlName_campus = Global.MW_DEPARTMENT + id_campus;
			
			System.out.println(id_campus);
			System.out.println(nome_campus);
			System.out.println(urlName_campus);
			
			htmlString=htmlString.substring(index);// Parte a String
			index = htmlString.indexOf(Global.MW_CAMPUS_ID); // se for -1 PARA
			
			campus = new Campus(id_campus, nome_campus, urlName_campus);
			CampusList.add(campus);
			
			id_campus="";
			nome_campus="";
			urlName_campus="";*/
		}
		site.disconnect();	
		//return departamentoList;
		return null;
	}
}
