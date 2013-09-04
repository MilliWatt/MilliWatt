import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import model.Campus;
import model.Global;
import model.HttpPage;
import model.HttpsPage;
import controller.*;

public class MainTeste {
	public static void main(String args[]){
		
		ArrayList<Campus> list = null;
		list = DAOCampus.getCampusList();
		list.toString();
		
		
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