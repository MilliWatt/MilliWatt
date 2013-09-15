package milliwatt.main;

import java.util.ArrayList;

import milliwatt.controller.CampusController;
import milliwatt.controller.DepartamentoController;
import milliwatt.controller.DisciplinaController;
import milliwatt.controller.TurmaController;
import milliwatt.dao.CampusDAO;
import milliwatt.model.Campus;
import milliwatt.model.Departamento;
import milliwatt.model.Disciplina;

public class MainTeste {
	public static void main(String args[]){

		//Page p =  Page.getPage("http:\\www.google.com");		
		//System.out.println(p.toString());

		ArrayList<Campus> campusList = CampusController.getCampusList();		

		ArrayList<Departamento> departamentoList = DepartamentoController.getDepartmentList(campusList, "4");
		campusList.get(3).setDepartamentoList(departamentoList);
//		ArrayList<Disciplina> disciplinaList = DisciplinaController.getDisciplinaList(departamentoList, "650");
//		TurmaController.getTurmaList(disciplinaList, "113034");

		ArrayList<Departamento> departamentoList = DepartamentoController.getDepartmentList(campusList, "4");		
		ArrayList<Disciplina> disciplinaList = DisciplinaController.getDisciplinaList(departamentoList, "650");
		TurmaController.getTurmaList(disciplinaList, "193640");
		
		
		CampusDAO.toFile(campusList);
		ArrayList<Campus> load=  CampusDAO.toObject();
		
		System.out.println(load.get(3).getDepartamentoList().get(0).getSigla());

		
	}

}
