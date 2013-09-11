package milliwatt.main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import milliwatt.controller.*;
import milliwatt.model.Campus;
import milliwatt.model.HttpPage;
import milliwatt.model.HttpsPage;
import milliwatt.utils.Global;

public class MainTeste {
	public static void main(String args[]){
		
	//CampusController.getCampusList().toString();	
		/*
	ArrayList<Campus> campusList = CampusController.getCampusList();
	Campus c = campusList.get(3);
	System.out.println("\n\n\n"+c.getUrlName());*/

		DepartamentoController.getDepartmentList();	
	
		/*
		File file = new File("./teste");
		
		
		BufferedReader in = null;
		try {
			 in = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String inputLine, aux="";
		try{
		System.out.println(in.readLine());
				while ((inputLine = in.readLine()) != null) {
					inputLine = inputLine + "\n";
		            // Imprime página no console
		            //System.out.println(inputLine);//DEBUG
		            // Grava pagina no arquivo
		            aux = aux+inputLine;
		        }
		}catch(IOException e){e.printStackTrace();}
*/
		//System.out.println(aux);
		

		
		//System.out.println(aux.charAt(aux2));
	
		
	}


}