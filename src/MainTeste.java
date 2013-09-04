import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import model.Global;
import model.HttpPage;
import model.HttpsPage;


public class MainTeste {
	public static void main(String args[]){
		
		HttpsPage site = new HttpsPage(Global.MW_CAMPUS);
		site.connect();
		
		String htmlString = site.getOnlyHTML();
		System.out.println(htmlString);
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
		
		
		String id_campus="";
		String nome_campus="";
		String urlName_campus="";
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
		
		htmlString=htmlString.substring(index);
		index = htmlString.indexOf(Global.MW_CAMPUS_ID); // se for -1 PARA
		id_campus="";
		nome_campus="";
		 urlName_campus="";
	}
		
		//System.out.println(aux.charAt(aux2));
	
		site.disconnect();
		
		
		
		
	}

}
