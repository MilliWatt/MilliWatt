package milliwatt.main;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import milliwatt.controller.CampusController;
import milliwatt.controller.DepartamentoController;
import milliwatt.controller.DisciplinaController;
import milliwatt.controller.TurmaController;
import milliwatt.dao.CampusDAO;
import milliwatt.model.Campus;
import milliwatt.model.Departamento;
import milliwatt.model.Disciplina;
import milliwatt.model.Turma;

public class MainTeste {
	public static void main(String args[]){

		//Page p =  Page.getPage("http:\\www.google.com");		
		//System.out.println(p.toString());

		String campusDesejado = JOptionPane.showInputDialog(null, "Informe o nome completo do Campus Desejado!",  
				"Olá, Seja Bem Vindo(a)!", JOptionPane.INFORMATION_MESSAGE);
		String siglaDepartamentoDesejado = JOptionPane.showInputDialog(null, "Informe a sigla do Departamento Desejado!",  
				"Olá, Seja Bem Vindo(a)!", JOptionPane.INFORMATION_MESSAGE);
		String codigoDisciplinaDesejada = JOptionPane.showInputDialog(null, "Informe o nome da Disciplina Desejada!",  
				"Olá, Seja Bem Vindo(a)!", JOptionPane.INFORMATION_MESSAGE);
		
		ArrayList<String> substrings = new ArrayList<String>();
		String substring = "";
		
		for(int i = 0; i < codigoDisciplinaDesejada.length(); i++){
			if(codigoDisciplinaDesejada.charAt(i) != ' '){
				substring += codigoDisciplinaDesejada.charAt(i);	
			}
			substrings.add(substring);
			substring = "";
			JOptionPane.showMessageDialog(null, substrings);	
		}
		
		ArrayList<Campus> campusList = CampusController.getCampusList();		
		ArrayList<Departamento> departamentoList = DepartamentoController.getDepartmentList(campusList, campusDesejado);		
		ArrayList<Disciplina> disciplinaList = DisciplinaController.getDisciplinaList(departamentoList, siglaDepartamentoDesejado);
		ArrayList<Turma> turmaList = TurmaController.getTurmaList(disciplinaList, codigoDisciplinaDesejada);
		
		CampusDAO.toFile(campusList);
		ArrayList<Campus> load=  CampusDAO.toObject();
		
		JOptionPane.showMessageDialog(null, load.get(3).getDepartamentoList().get(0).getSigla());
		
	}

}
