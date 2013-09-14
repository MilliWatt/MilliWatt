package milliwatt.main;

import java.util.ArrayList;

import milliwatt.controller.CampusController;
import milliwatt.controller.DepartamentoController;
import milliwatt.controller.DisciplinaController;
import milliwatt.model.Campus;
import milliwatt.model.Departamento;

public class MainTeste {
	public static void main(String args[]){

		//Page p =  Page.getPage("http:\\www.google.com");		
		//System.out.println(p.toString());

		ArrayList<Campus> campusList = CampusController.getCampusList();		
		ArrayList<Departamento> departamentoList = DepartamentoController.getDepartmentList(campusList, "1");		
		DisciplinaController.getDisciplinaList(departamentoList, "113");
		
		
		
		/*
		File file = new File("./teste");
		
		
		BufferedReader in = null;
		try {
			 in = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
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
		//System.out.println(aux);
		//System.out.println(aux.charAt(aux2));
		*/

		
	}

}
