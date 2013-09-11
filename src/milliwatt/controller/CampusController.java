package milliwatt.controller;

import java.util.ArrayList;

import milliwatt.model.Campus;
import milliwatt.model.HttpsPage;
import milliwatt.utils.Global;

public class CampusController {
	
	public static ArrayList<Campus> getCampusList(){
		ArrayList<Campus> CampusList = new ArrayList<Campus>();
		
		HttpsPage site = new HttpsPage(Global.MW_CAMPUS);
		site.connect();
		
		String htmlString = site.getOnlyHTML();
		
		//System.out.println(htmlString);
		
		String id_campus="";
		String nome_campus="";
		String urlName_campus="";
		Campus campus = null;
		int index =0;
		
		while(index !=-1){
			index = htmlString.indexOf(Global.MW_CAMPUS_ID)+4;//4 de offset para pegar o codigo da disciplina
			
			while (htmlString.charAt(index)!='>'){
				id_campus += htmlString.charAt(index);
				index++;
			}
			index++;
			
			while (htmlString.charAt(index)!='<'){
				nome_campus += htmlString.charAt(index);
				index++;
			}
			index++;
			
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
			urlName_campus="";
		}
		site.disconnect();	
		return CampusList;
	}
	
}
