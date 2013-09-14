package milliwatt.controller;

import java.util.ArrayList;

import milliwatt.model.Campus;
import milliwatt.model.page.HttpsPage;
import milliwatt.utils.Global;

public class CampusController {
	
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
	
	public static ArrayList<Campus> getCampusList(){
		ArrayList<Campus> CampusList = new ArrayList<Campus>();
		
		HttpsPage site = new HttpsPage(Global.MW_CAMPUS);
		site.connect();
		
		String htmlString = site.getOnlyHTML();
		
		String id_campus="";
		String nome_campus="";
		String urlName_campus="";
		Campus campus = null;
		int index = 0;

		while(index !=-1){

			index = htmlString.indexOf(Global.MW_CAMPUS_ID)+4;
			id_campus = capturaIdCampus(htmlString, index);
			index += 2;//problemimnha
			
			nome_campus = capturaNomeCampus(htmlString, index);
			
			index++;
			
			urlName_campus = geraURLCampus(urlName_campus, id_campus);
					
//			System.out.println(id_campus);
//			System.out.println(nome_campus);
//			System.out.println(urlName_campus);
			
			htmlString = htmlString.substring(index);// Parte a String
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
