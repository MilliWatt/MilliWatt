package model;

public class Global {
	//sempre termina com BARRA
	public static final String HTML_DIR = "./HTML/";
	public static final String MW_HOME = "https://matriculaweb.unb.br/matriculaweb/";
	
	
	public static final String MW_GRADUATE = MW_HOME + "graduacao/";
	public static final String MW_POSTGRADUATE = MW_HOME + "posgraduacao/";
	
	
	public static final String MW_CAMPUS = MW_GRADUATE + "oferta_campus.aspx";//obter lista de campus
	
	//cod de 1 a 4 sendo 4 o Gama
	public static final String MW_DEPARTMENT = MW_GRADUATE+"oferta_dep.aspx?cod="; //obter lista de departamentos e seus codigos.
	
	public static final String MW_DISCIPLINE =  MW_GRADUATE + "oferta_dis.aspx?cod=";//obter lista de disciplinas e seus codigos.
	
	// obter lista de turmas e informações de horarios e professores e etc
	public static final String MW_CLASS = MW_GRADUATE + "oferta_dados.aspx?cod=" +/*CODIGO DISCI*/ "&dep=";/*COD_DEP */ 	
	
	
	
	
	
}
